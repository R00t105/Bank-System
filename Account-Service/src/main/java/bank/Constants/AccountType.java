package bank.Constants;

/**
 * Account Type Enum - Defines the different types of bank accounts
 * 
 * Best Practice: Using enum instead of String constants for type safety
 * and to restrict the domain values
 */
public enum AccountType {
    SAVINGS,
    CHECKING,
    BUSINESS,
    INVESTMENT,
    LOAN
}