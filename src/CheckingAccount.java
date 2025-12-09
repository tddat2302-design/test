public class CheckingAccount extends BankAccount {
    private String cardNumber;
    private double balance;

    public CheckingAccount(int id, String accountCode, String accountHolder, String cardNumber, double balance) {
        super(id, accountCode, accountHolder);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public String getType() { return "CHECKING"; }

    @Override
    public String toCSV() {
        return id + "," + accountCode + "," + accountHolder + "," + cardNumber + "," + balance;
    }

    @Override
    public String toString() {
        return id + ". " + getType() + " - " + accountCode + " - " + accountHolder + " - " + cardNumber + " - " + balance;
    }
}
