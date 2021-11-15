package business.persistence;

import business.entities.Cupcake;
import business.exceptions.UserException;
import business.entities.User;
import business.services.LogicFacade;
import business.services.UserFacade;

import java.sql.*;
import java.util.ArrayList;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT id, role, balance, orderid FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("id");
                    int balance = rs.getInt("balance");
                    int orderid = rs.getInt("orderid");

                    User user = new User(email, password, role);
                    user.setId(id);
                    user.setBalance(balance);
                    user.setOrderId(orderid);

                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public ArrayList<User> getUserList() {

        ArrayList<User> userList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM users";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    int orderId = rs.getInt("orderid");
                    int balance = rs.getInt("balance");

                    User user = new User(email, password, role);
                    user.setId(id);
                    user.setOrderId(orderId);
                    user.setBalance(balance);

                    userList.add(user);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            try {
                throw new UserException("Connection to database could not be established");
            } catch (UserException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }

    public void updateUserToDb(User user){
        System.out.println("im in usermapper ");

        try (Connection connection = database.connect()) {
            String sql = "UPDATE users SET orderid=?, balance=? WHERE id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user.getOrderId());
                ps.setInt(2, user.getBalance());
                ps.setInt(3, user.getId());

                ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initMyCupcakeLists(ArrayList<Cupcake> cupcakeList){

        for (Cupcake c : cupcakeList) {
            for (User u : UserFacade.userList) {
                if (c.getUserId() == u.getId()){
                    u.addtoMyCupcakes(c);
                    c.setBottomName(LogicFacade.getProductNameById(Integer.parseInt(c.getBottomId())));
                    c.setToppingName(LogicFacade.getProductNameById(Integer.parseInt(c.getToppingId())));

                    u.addToOrderIdList(c.getOrderId());

                    break;
                }
            }
        }


    }


}
