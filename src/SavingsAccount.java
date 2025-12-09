public class SavingsAccount extends BankAccount {
    private double depositAmount;
    private int termMonths;

    public SavingsAccount(int id, String accountCode, String accountHolder, double depositAmount, int termMonths) {
        super(id, accountCode, accountHolder);
        this.depositAmount = depositAmount;
        this.termMonths = termMonths;
    }

    @Override
    public String getType() { return "SAVINGS"; }

    @Override
    public String toCSV() {
        return id + "," + accountCode + "," + accountHolder + "," + depositAmount + "," + termMonths;
    }

    @Override
    public String toString() {
        return id + ". " + getType() + " - " + accountCode + " - " + accountHolder + " - " + depositAmount + " - " + termMonths;
    }
}
