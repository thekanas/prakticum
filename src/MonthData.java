public class MonthData {
    String nameItem;
    boolean is_expense;
    float quantity;
    int sum_of_one;

    public MonthData() {}

    public MonthData(String nameItem, boolean is_expense, float quantity, int sum_of_one) {
        this.nameItem = nameItem;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }
}
