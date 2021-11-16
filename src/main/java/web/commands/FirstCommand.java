package web.commands;

import business.entities.Cupcake;
import business.entities.User;
import business.exceptions.UserException;
import business.services.LogicFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FirstCommand extends CommandUnprotectedPage{

    UserFacade userFacade;

    public FirstCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }


    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        UserFacade.userList = userFacade.getCustomerList();

        userFacade.initMyCupcakeLists(LogicFacade.getAllCupcakes());

        //request.getServletContext().setAttribute("customerList", UserFacade.userList);

        // Indlæs brugeres cupcakes i deres myCupcakes, før admin vises alle ordrer
        // Lav metode (initMyCupcakesList), der henter cupcakes fra databasen, foreach gennem hver bruger og
        // tilføj cupcake til myCupcakes, hvis brugerens id og cupcakens userid er ens.


        return REDIRECT_INDICATOR + "first";
    }

}
