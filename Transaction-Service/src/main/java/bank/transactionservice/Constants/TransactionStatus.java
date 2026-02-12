package bank.transactionservice.Constants;

public enum TransactionStatus {

    NOT_ACTIVE,
    ACTIVE,
    COMMITTED,
    ROLLED_BACK,
    MARKED_ROLLBACK,
    FAILED_COMMIT,
    FAILED_ROLLBACK,
    COMMITTING,
    ROLLING_BACK;

}
