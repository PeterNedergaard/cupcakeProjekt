<%@ page import="business.services.UserFacade" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Edit your order
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div style="margin-top: 5em;" class="container">
            <h1 align="center">Checkout</h1>

            <form name="buyordelete" action="${pageContext.request.contextPath}/fc/buyorremovecommand" method="POST">
                <input hidden value="${sessionScope.email}" name="currentuseremail">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Topping</th>
                        <th scope="col">Bottom</th>
                        <th scope="col">Price</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="productItem" items="${applicationScope.basketList}" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.index+1}</th>
                            <td>
                                    ${productItem.toppingName}
                            </td>
                            <td>
                                    ${productItem.bottomName}
                            </td>
                            <td>
                                    ${productItem.price}
                            </td>
                            <td>
                                <button class="btn btn-primary" name="delete" type="submit" value="${loop.index}"
                                        style="background-color: #B94444; border-color: #B94444">Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Total</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${applicationScope.totalBasketPrice}$</td>
                    </tr>
                    </tbody>
                </table>

                <label for="pickupday">Vælg afhentningsdag</label>
                <select name="pickupday" id="pickupday">
                    <option value="day1">Mandag</option>
                    <option value="day2">Tirsdag</option>
                    <option value="day3">Onsdag</option>
                    <option value="day4">Torsdag</option>
                    <option value="day5">Fredag</option>
                </select>

                <label for="pickuptime">Vælg afhentningstidspunkt</label>
                <select name="pickuptime" id="pickuptime">
                    <option value="time1">12</option>
                    <option value="time1">13</option>
                    <option value="time1">14</option>
                    <option value="time1">15</option>
                    <option value="time1">16</option>
                </select>

                <div class="col text-center">
                    <c:if test="${applicationScope.basketList.size() > 0}">
                        <input style="padding-left: 40px; padding-right: 40px; background-color: green; border-color: green"
                           class="btn btn-primary" type="submit" value="Buy" name="buy">
                    </c:if>

                    <a href="${pageContext.request.contextPath}/fc/customerpage"> <input class="btn btn-primary"
                                                                                         type="button"
                                                                                         value="Order more"> </a>
                </div>
            </form>
        </div>


    </jsp:body>
</t:genericpage>