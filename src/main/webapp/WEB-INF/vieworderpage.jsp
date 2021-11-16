<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Viewing orders from Kurt
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div style="margin-top: 5em;">
            <h1>Viewing all orders from: ${applicationScope.email}</h1>
            <br><br>

            <h3 align="center">ID#${applicationScope.id}</h3>
            <br>

            <c:forEach var="orderid" items="${applicationScope.orderidlist}" varStatus="loop">
                <c:set var="totalprice" value="${0}"/>

                <h5 align="center">Order #${loop.index+1}</h5>

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

                    <c:forEach var="cupcake" items="${applicationScope.cupcakelist}" varStatus="loop">

                    <c:if test="${cupcake.orderId == orderid}">

                        <c:set var="topping" value="${cupcake.toppingName}"/>
                        <c:set var="bottom" value="${cupcake.bottomName}"/>
                        <c:set var="price" value="${cupcake.price}"/>

                    <tr>
                        <th scope="row">${cupcake.cupcakeId}</th>
                        <td><c:out value="${topping}"/></td>
                        <td><c:out value="${bottom}"/></td>
                        <td><c:out value="${price}$"/></td>
                    </tr>
                        <c:set var="totalprice" value="${totalprice + price}"/>
                    </c:if>

                    </c:forEach>

                    <tr>
                        <th>Total</th>
                        <td></td>
                        <td></td>
                        <th>${totalprice}$</th>
                    </tr>

                </table>
                <br>
            </c:forEach>


            <h5 align="center">Afhentning: Mandag kl. 14</h5>


            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>


