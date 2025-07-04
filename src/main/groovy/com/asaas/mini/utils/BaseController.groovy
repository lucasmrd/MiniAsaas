package com.asaas.mini.utils

class BaseController {
    protected Integer getLimitPerPage() {
        String itemsPerPage = params.itemsPerPage?.toString()

        if (!itemsPerPage?.isNumber()) params.itemsPerPage = null

        params.itemsPerPage = params.itemsPerPage ? Integer.valueOf(params.itemsPerPage): 10

        return Math.min(params.itemsPerPage, 10)
    }

    protected Integer getCurrentPage() {
        Integer currentPage = Integer.valueOf(params.page ?: 1)

        return (currentPage - 1) * getLimitPerPage()
    }
}
