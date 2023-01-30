public class Account {
    private String cardnum;
    private String name;

    private String password;
    private double money;
    private double Withdrawal_Credit;
    private double transfer_amount;


    public Account() {
    }

    public Account(String cardnum, String name ,String password, double money, double withdrawal_Credit, double transfer_amount) {
        this.cardnum = cardnum;
        this.name = name;
        this.money = money;
        Withdrawal_Credit = withdrawal_Credit;
        this.transfer_amount = transfer_amount;
        this.password = password;
    }
    public void exist_money(double money){
        this.money += money;
    }
    public void take_money(double money){
        this.money -= money;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getWithdrawal_Credit() {
        return Withdrawal_Credit;
    }

    public void setWithdrawal_Credit(double withdrawal_Credit) {
        Withdrawal_Credit = withdrawal_Credit;
    }

    public double getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(double transfer_amount) {
        this.transfer_amount = transfer_amount;
    }
}
