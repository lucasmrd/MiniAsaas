package com.asaas.mini

import com.asaas.mini.enums.PaymentStatus

import grails.gorm.transactions.Transactional

import org.quartz.JobExecutionContext

import java.time.LocalDate

import java.time.ZoneId

class ExpirePaymentsJob {

    static triggers = {
        cron name: 'dailyOverdueCheckTrigger', cronExpression: "0 1 0 * * ?"
    }

    @Transactional
    void execute(JobExecutionContext context) {
        log.info("JOB INICIADO: Verificando cobranças vencidas...")

        LocalDate todayLocal = LocalDate.now()
        Date today = Date.from(
                todayLocal.atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        )

        def overduePayments = Payment.where {
            status == PaymentStatus.PENDING &&
            dueDate  <  today &&
            deleted  == false
        }.list()

        if (!overduePayments) {
            log.info("JOB FINALIZADO: Nenhuma cobrança vencida encontrada.")
            return
        }

        log.info("Encontradas ${overduePayments.size()} cobranças vencidas. Atualizando status para OVERDUE...")

        overduePayments.each { payment ->
            log.debug("Atualizando cobrança ID: ${payment.id} de PENDENTE para VENCIDA.")
            payment.status = PaymentStatus.OVERDUE
        }

        log.info("JOB FINALIZADO: ${overduePayments.size()} cobranças foram atualizadas.")
    }
}