package web.commands;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.ProductMapper;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BuyOrRemoveCommand extends CommandProtectedPage{

    UserFacade userFacade;

    public BuyOrRemoveCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String buy = request.getParameter("buy");

        User currentUser = UserFacade.currentUser;

        if (buy == null) {
            int deleteId = Integer.parseInt(request.getParameter("delete"));
            ArrayList<Cupcake> userBasketList = currentUser.getBasketList();

            currentUser.setTotalBasketPrice(currentUser.getTotalBasketPrice() - userBasketList.get(deleteId).getPrice());

            userBasketList.remove(deleteId);

        } else if (buy != null && currentUser.getBasketList().size() > 0){

            if (currentUser.getBalance() >= currentUser.getTotalBasketPrice()){
                currentUser.setBalance(currentUser.getBalance() - currentUser.getTotalBasketPrice());
                request.getServletContext().setAttribute("currentuserbalance",currentUser.getBalance());

                for (Cupcake c : currentUser.getBasketList()) {
                    currentUser.getMyCupcakes().add(c);
                    LogicFacade.uploadCupcake(c);
                }

                currentUser.getBasketList().clear();
                currentUser.setTotalBasketPrice(0);
                currentUser.setOrderId(currentUser.getOrderId() + 1);

                userFacade.updateUserToDb(currentUser);

            } else {
                throw new UserException("Insufficient funds");
            }

        }
        request.getServletContext().setAttribute("totalBasketPrice", currentUser.getTotalBasketPrice());

        return REDIRECT_INDICATOR + "checkoutpage";
    }

}
