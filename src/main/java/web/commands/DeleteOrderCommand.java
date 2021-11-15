package web.commands;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DeleteOrderCommand extends CommandProtectedPage{

    public DeleteOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int customerId = Integer.parseInt(request.getParameter("customerid"));
        int orderid = Integer.parseInt(request.getParameter("orderid"));


        for (User u : UserFacade.userList) {
            ArrayList<Cupcake> myCupcakesCopy = new ArrayList<>();

            if (u.getId() == customerId){
                for (Cupcake c : u.getMyCupcakes()) {
                    if (c.getOrderId() == orderid){
                        LogicFacade.deleteCupcake(Integer.parseInt(c.getCupcakeId()));
                        myCupcakesCopy.add(c);
                    }
                }

                for (Cupcake c : myCupcakesCopy) {
                    u.getMyCupcakes().remove(c);
                }
            }

            u.getOrderIdList().clear();

            for (Cupcake c : u.getMyCupcakes()) {
                u.addToOrderIdList(c.getOrderId());
            }

        }

        return REDIRECT_INDICATOR + "orderspage";
    }

}
