package business.services;

import business.entities.Cupcake;
import business.entities.Product;
import business.entities.User;
import business.persistence.ProductMapper;
import business.persistence.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class LogicFacade {

    public static List<Product> getAllToppings(){
        return ProductMapper.getAllToppings();
    }

    public static List<Product> getAllBottoms(){
        return ProductMapper.getAllBottoms();
    }

    public static String getProductNameById(int productId){
        return ProductMapper.getProductNameById(productId);
    }

    public static void uploadCupcake(Cupcake cupcake){
        ProductMapper.uploadCupcake(cupcake);
    }

    public static ArrayList<Cupcake> getAllCupcakes(){
        return ProductMapper.getAllCupcakes();
    }

    public static void deleteCupcake(int cupcakeId){
        ProductMapper.deleteCupcake(cupcakeId);
    }


}



