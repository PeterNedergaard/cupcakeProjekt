package web.commands;

import business.entities.User;
import business.services.LogicFacade;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends CommandUnprotectedPage
{
    private UserFacade userFacade;

    public LoginCommand(String pageToShow)
    {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
        User user = userFacade.login(email, password);

        UserFacade.currentUser = user;

        //UserFacade.userList = userFacade.getCustomerList();

        /*if (user.getRole().equals("employee")) {
            userFacade.initMyCupcakeLists(LogicFacade.getAllCupcakes());
        }*/

        request.getServletContext().setAttribute("currentuserbalance",user.getBalance());
        request.getServletContext().setAttribute("customerList", UserFacade.userList);

        HttpSession session = request.getSession();

        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setAttribute("email", email);

        String pageToShow =  user.getRole() + "page";
        return REDIRECT_INDICATOR + pageToShow;
        }
        catch (UserException ex)
        {
            request.setAttribute("error", "Wrong username or password!");
            return "loginpage";
        }
    }

}
