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
import java.util.Arrays;

public class AddCupcakeCommand extends CommandProtectedPage {

    UserFacade userFacade;

    public AddCupcakeCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User currentUser = UserFacade.currentUser;

        String bottomId = request.getParameter("bottoms");
        String toppingId = request.getParameter("toppings");

        int cupcakePrice;

        int amount = Integer.parseInt(request.getParameter("amount"));

        int bottomPrice = ProductMapper.getProductPriceById(Integer.parseInt(bottomId));
        int toppingPrice = ProductMapper.getProductPriceById(Integer.parseInt(toppingId));

        int userId = UserFacade.currentUser.getId();
        int orderId = UserFacade.currentUser.getOrderId();

        cupcakePrice = bottomPrice + toppingPrice;

        Cupcake cupcake = new Cupcake(bottomId, toppingId, amount, userId, orderId, cupcakePrice);

        for (int i = 0; i < amount; i++) {
            currentUser.addToBasketList(cupcake);

            cupcake.setBottomName(LogicFacade.getProductNameById(Integer.parseInt(bottomId)));
            cupcake.setToppingName(LogicFacade.getProductNameById(Integer.parseInt(toppingId)));

            currentUser.setTotalBasketPrice(currentUser.getTotalBasketPrice() + cupcakePrice);
        }

        request.getServletContext().setAttribute("totalBasketPrice", currentUser.getTotalBasketPrice());
        request.getServletContext().setAttribute("basketList", currentUser.getBasketList());


        return REDIRECT_INDICATOR + "customerpage";
    }

}
