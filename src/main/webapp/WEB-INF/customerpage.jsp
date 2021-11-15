<%@ page import="business.services.LogicFacade" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.getServletContext().setAttribute("bottomList", LogicFacade.getAllBottoms());
    request.getServletContext().setAttribute("toppingList", LogicFacade.getAllToppings());
%>

<t:genericpage>
    <jsp:attribute name="header">
         Order cupcakes
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>

        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a Customer of our wonderful site.

        <br><br>

        <h2>Velkommen ombord</h2>

        <br>

        <h3>Øens bedste cupcakes. Vælg og bestil her:</h3>

        <form name="order" action="${pageContext.request.contextPath}/fc/addcupcakecommand" method="POST">
            <input hidden value="${sessionScope.email}" name="currentuseremail">

            <table width="50%">
                <td>
                    <label for="bottoms">Vælg bund</label>
                    <select name="bottoms" id="bottoms">
                        <c:forEach var="bottomItem" items="${applicationScope.bottomList}">
                            <option value="${bottomItem.idProduct}">${bottomItem.productName}</option>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <label for="toppings">Vælg topping</label>
                    <select name="toppings" id="toppings">
                        <c:forEach var="toppingItem" items="${applicationScope.toppingList}">
                            <option value="${toppingItem.idProduct}">${toppingItem.productName}</option>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <label for="amount">Vælg antal</label>
                    <select name="amount" id="amount">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </td>
            </table>
            <br>

            <input class="btn btn-primary" type="submit" value="Add to basket">
            <a href="${pageContext.request.contextPath}/fc/checkoutpage">
                <input class="btn btn-primary" type="button" value="Go to checkout">
            </a>

        </form>


    </jsp:body>

</t:genericpage>

