package business.entities;

import java.util.ArrayList;

public class User
{

    public User(String email, String password, String role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String email;
    private String password;
    private String role;
    private int orderId;
    private int balance;

    private ArrayList<Cupcake> basketList = new ArrayList<>();
    private ArrayList<Cupcake> myCupcakes = new ArrayList<>();
    private int totalBasketPrice;

    public void addToBasketList(Cupcake cupcake){
        basketList.add(cupcake);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Cupcake> getMyCupcakes() {
        return myCupcakes;
    }

    public void setMyCupcakes(ArrayList<Cupcake> myCupcakes) {
        this.myCupcakes = myCupcakes;
    }

    public void setBasketList(ArrayList<Cupcake> basketList) {
        this.basketList = basketList;
    }

    public int getTotalBasketPrice() {
        return totalBasketPrice;
    }

    public void setTotalBasketPrice(int totalBasketPrice) {
        this.totalBasketPrice = totalBasketPrice;
    }

    public ArrayList<Cupcake> getBasketList() {
        return basketList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
