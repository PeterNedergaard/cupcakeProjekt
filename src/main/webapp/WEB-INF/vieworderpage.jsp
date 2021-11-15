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
            <h1>Viewing all orders from Kurt</h1>
            <br><br>

            <h3>1#</h3>
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
                <tr>
                    <th scope="row">1</th>
                    <td>Chocolate</td>
                    <td>Chocolate</td>
                    <td>5$</td>
                    <td><input class="btn btn-primary" type="submit" value="Delete" style="background-color: #B94444; border-color: #B94444"></td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Blueberry</td>
                    <td>Vanilla</td>
                    <td>5$</td>
                    <td><input class="btn btn-primary" type="submit" value="Delete" style="background-color: #B94444; border-color: #B94444"></td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>10$</td>
                </tr>
                </tbody>
            </table>
            <h5 align="center">Afhentning: Mandag kl. 14</h5>

            <br>

            <h3>#2</h3>
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
                <tr>
                    <th scope="row">1</th>
                    <td>Strawberry</td>
                    <td>Vanilla</td>
                    <td>5$</td>
                    <td><input class="btn btn-primary" type="submit" value="Delete" style="background-color: #B94444; border-color: #B94444"></td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Blueberry</td>
                    <td>Chocolate</td>
                    <td>5$</td>
                    <td><input class="btn btn-primary" type="submit" value="Delete" style="background-color: #B94444; border-color: #B94444"></td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>10$</td>
                </tr>
                </tbody>
            </table>
            <h5 align="center">Afhentning: Tirsdag kl. 14</h5>

            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>


