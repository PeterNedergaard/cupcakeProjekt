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

            <form name="vieworder" action="${pageContext.request.contextPath}/fc/vieworderpage" method="POST">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Lastname</th>
                        <th scope="col">CustomerID</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Kurt</td>
                        <td>Hansen</td>
                        <td>1</td>
                        <td><input class="btn btn-primary" type="submit" value="View orders"></td>
                    </tr>
                    <tr>
                        <td>Jørgen</td>
                        <td>Jensen</td>
                        <td>2</td>
                        <td><input class="btn btn-primary" type="submit" value="View orders"></td>
                    </tr>
                    <tr>
                        <td>Bo</td>
                        <td>Ibsen</td>
                        <td>3</td>
                        <td><input class="btn btn-primary" type="submit" value="View orders"></td>
                    </tr>
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


