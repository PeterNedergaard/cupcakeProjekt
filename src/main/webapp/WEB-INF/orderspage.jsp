<%@ page import="business.entities.User" %>
<%@ page import="business.services.UserFacade" %>
<%@ page import="business.entities.Cupcake" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%
    Cupcake cupcake = (Cupcake) pageContext.getAttribute("cupcake");
    String topping = cupcake.getToppingName();
    String bottom = cupcake.getBottomName();
    int price = cupcake.getPrice();
    pageContext.setAttribute("topping", topping);
    pageContext.setAttribute("bottom", bottom);
    pageContext.setAttribute("price", price);
%>--%>

<t:genericpage>
    <jsp:attribute name="header">
         All orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div style="margin-top: 5em;">

            <h1 align="center">Orders</h1>
            <br><br>

            <c:forEach var="customer" items="${applicationScope.customerList}" varStatus="loop">

                <c:if test="${customer.role.equals('customer')}">

                    <h3 align="center">#${loop.index+1}</h3>
                    <h5 align="center">Email: ${customer.email}</h5>

                    <c:forEach var="orderid" items="${customer.orderIdList}">

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">ID#</th>
                                <th scope="col">Topping</th>
                                <th scope="col">Bottom</th>
                                <th scope="col">Price</th>
                            </tr>
                            </thead>
                            <tbody>


                            <c:forEach var="cupcake" items="${customer.myCupcakes}" varStatus="loop">

                            <form action="${pageContext.request.contextPath}/fc/deleteordercommand" method="POST">
                                <c:if test="${cupcake.orderId == orderid}">

                                    <c:set var="topping" value="${cupcake.toppingName}"/>
                                    <c:set var="bottom" value="${cupcake.bottomName}"/>
                                    <c:set var="price" value="${cupcake.price}"/>

                                <tr>
                                    <th scope="row">${cupcake.cupcakeId}</th>
                                    <td><c:out value="${topping}"/></td>
                                    <td><c:out value="${bottom}"/></td>
                                    <td><c:out value="${price}"/></td>
                                </tr>
                                </c:if>

                                </c:forEach>
                        </table>

                        <div class="col text-center">
                            <input style="padding-left: 20px; padding-right: 20px" class="btn btn-primary"
                                   type="submit"
                                   value="Delete">
                            <input hidden name="customerid" value="${customer.id}">
                            <input hidden name="orderid" value="${orderid}">
                        </div>
                        <br><br>

                        </form>
                    </c:forEach>
                </c:if>
            </c:forEach>


            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>