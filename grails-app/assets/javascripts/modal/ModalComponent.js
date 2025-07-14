function ModalComponent (config) {
    if (!config.reference) {
        console.error("Modal reference is required.");
        return;
    }

    const reference = config.reference;
    const modalContent = reference.querySelector(".js-modal-content");
    const confirmationButton = reference.querySelector(".js-confirm-button");
    const cancelButton = reference.querySelector(".js-cancel-button");
    const confirmButtonTheme = config.confirmButtonTheme ?? "success";
    const cancelButtonTheme = config.cancelButtonTheme ?? "primary";
    const confirmButtonLabel = config.confirmButtonLabel ?? "Confirmar";
    const cancelButtonLabel = config.cancelButtonLabel ?? "Cancelar";

    const onConfirm = config.confirmationCallback || function () {};
    const onCancel = config.cancelationCallback || function () {};

    const autoClose = config.autoCloseOnConfirmation ?? true;

    const openModal = () => {
        if (config.htmlContent) {
            modalContent.innerHTML = config.htmlContent;
        }

        confirmationButton.setAttribute('theme', confirmButtonTheme)
        confirmationButton.setAttribute('description', confirmButtonLabel)
        cancelButton.setAttribute('theme', cancelButtonTheme)
        cancelButton.setAttribute('description', cancelButtonLabel)

        reference.openModal();
    };

    const closeModal = () => {
        reference.closeModal();
    };

    const confirm = () => {
        onConfirm();
        if (autoClose) closeModal();
    };

    const cancel = () => {
        onCancel();
        closeModal();
    };

    if (confirmationButton) {
        confirmationButton.addEventListener("atlas-button-click", confirm);
    }

    if (cancelButton) {
        cancelButton.addEventListener("atlas-button-click", cancel);
    }

    reference.addEventListener("atlas-modal-close", () => {
        if (autoClose) {
            confirmationButton?.removeEventListener("atlas-button-click", confirm);
            cancelButton?.removeEventListener("atlas-button-click", cancel);
        }
    });

    this.open = openModal;
    this.close = closeModal;
}