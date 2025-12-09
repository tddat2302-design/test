public abstract class BankAccount {
    protected int id;
    protected String accountCode;
    protected String accountHolder;

    public BankAccount(int id, String accountCode, String accountHolder) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountHolder = accountHolder;
    }

    public int getId() { return id; }
    public abstract String getType();
    public abstract String toCSV();
}
