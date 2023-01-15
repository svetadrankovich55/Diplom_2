package orders;

import java.util.List;

public class OrdersOfUser {

    private boolean success;
    private List <OrderOfUsers> orders;
    private int total;
    private int totalToday;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<OrderOfUsers> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderOfUsers> orders) {
        this.orders = orders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }
}
