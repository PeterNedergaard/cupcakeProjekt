package business.entities;

public class Cupcake {

    private String bottomId;
    private String bottomName;
    private String toppingId;
    private String toppingName;
    private int amount;
    private int userId;
    private int orderId;
    private int price;

    public Cupcake(String bottomId, String toppingId, int amount, int userId, int orderId, int price) {
        this.bottomId = bottomId;
        this.toppingId = toppingId;
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
        this.price = price;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public String getBottomId() {
        return bottomId;
    }

    public void setBottomId(String bottomId) {
        this.bottomId = bottomId;
    }

    public String getToppingId() {
        return toppingId;
    }

    public void setToppingId(String toppingId) {
        this.toppingId = toppingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
