package business.persistence;

import business.entities.Cupcake;
import business.entities.Product;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    private static Database database;

    public ProductMapper(Database database) {
        this.database = database;
    }


    public static List<Product> getAllProducts() {

        List<Product> productList = null;
        try {
            Connection connection = database.connect();
            String SQL = "SELECT * FROM products";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (productList == null) {
                    productList = new ArrayList<>();
                }
                int idProducts = rs.getInt("idproducts");
                String productType = rs.getString("producttype");
                String productName = rs.getString("productname");
                int idPrice = rs.getInt("idprice");
                Product product = new Product(idProducts, productType, productName, idPrice);
                productList.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }


    public static List<Product> getAllToppings() {
        List<Product> productList = getAllProducts();
        List<Product> toppingList = new ArrayList<>();

        for (Product p : productList) {
            if (p.getProductType().equals("topping")) {
                toppingList.add(p);
            }
        }

        return toppingList;
    }


    public static List<Product> getAllBottoms() {
        List<Product> productList = getAllProducts();
        List<Product> bottomList = new ArrayList<>();

        for (Product p : productList) {
            if (p.getProductType().equals("bottom")) {
                bottomList.add(p);
            }
        }

        return bottomList;
    }

    public static int getProductPriceById(int productId) {
        int priceId = 0;
        int price = 0;

        for (Product p : getAllProducts()) {
            if (p.getIdProduct() == productId) {
                priceId = p.getIdPrice();
            }
        }


        try (Connection connection = database.connect()) {
            String sql = "SELECT price FROM prices WHERE idprices=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, priceId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int priceDB = rs.getInt("price");

                    price = priceDB;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return price;
    }

    public static String getProductNameById(int productId){
        for (Product p : getAllProducts()) {
            if (p.getIdProduct() == productId){
                return p.getProductName();
            }
        }
        return "no name";
    }

    public static void uploadCupcake(Cupcake cupcake){

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO cupcakes (bottomid,toppingid,amount,userid,orderid,price) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Integer.parseInt(cupcake.getBottomId()));
                ps.setInt(2, Integer.parseInt(cupcake.getToppingId()));
                ps.setInt(3, cupcake.getAmount());
                ps.setInt(4, cupcake.getUserId());
                ps.setInt(5, cupcake.getOrderId());
                ps.setInt(6, cupcake.getPrice());

                ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
