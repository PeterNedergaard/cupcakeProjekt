package business.services;

import business.entities.Cupcake;
import business.entities.User;
import business.persistence.Database;
import business.persistence.ProductMapper;
import business.persistence.UserMapper;
import business.exceptions.UserException;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UserFacade
{
    UserMapper userMapper;
    ProductMapper productMapper;

    public static User currentUser;
    public static ArrayList<User> userList;

    public UserFacade(Database database)
    {
        userMapper = new UserMapper(database);
        productMapper = new ProductMapper(database);
    }

    public User login(String email, String password) throws UserException
    {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password) throws UserException
    {
        User user = new User(email, password, "customer");
        userMapper.createUser(user);
        return user;
    }

    public ArrayList<User> getUserList(){
        return userMapper.getUserList();
    }

    public User getUserByEmail(String email){

        for (User u : getUserList()) {
            if (u.getEmail().equals(email)){
                return u;
            }
        }

        return null;
    }

    public void updateUserToDb(User user){
        userMapper.updateUserToDb(user);
    }

    public void initMyCupcakeLists(ArrayList<Cupcake> cupcakeList){
        userMapper.initMyCupcakeLists(cupcakeList);
    }



}
