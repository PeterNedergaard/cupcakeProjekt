<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         View specific customers' orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div style="margin-top: 5em;">
            <h1>Customers</h1>

            <form action="${pageContext.request.contextPath}/fc/viewcustomerorderscommand" method="POST">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">ID#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="customer" items="${applicationScope.customerList}">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.email}</td>
                            <td><button class="btn btn-primary" type="submit" name="vieworder" value="${customer.id}">View orders</button></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </form>

            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>


