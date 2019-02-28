<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book List</title>
    <style>
        table, tr, td {
            border: 1px solid brown;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>Description</td>
        <td>Price</td>
        <td></td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>$${product.price}</td>
            <td><a href = "/product_input?&id=${product.id}">Edit</a></td>
        </tr>
    </c:forEach></table>
<br/>
</body>
</html>