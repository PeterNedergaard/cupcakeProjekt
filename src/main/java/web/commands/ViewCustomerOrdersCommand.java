package web.commands;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ViewCustomerOrdersCommand extends CommandProtectedPage{

    public ViewCustomerOrdersCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int id = Integer.parseInt(request.getParameter("vieworder"));
        String email = "";
        Set<Integer> orderIdList = new TreeSet();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();

        for (User u : UserFacade.userList) {
            if (u.getId() == id){
                email = u.getEmail();
                orderIdList = u.getOrderIdList();
                cupcakeList = u.getMyCupcakes();
            }
        }

        request.getServletContext().setAttribute("id",id);
        request.getServletContext().setAttribute("email",email);
        request.getServletContext().setAttribute("orderidlist",orderIdList);
        request.getServletContext().setAttribute("cupcakelist",cupcakeList);


        return REDIRECT_INDICATOR + "vieworderpage";
    }
}
