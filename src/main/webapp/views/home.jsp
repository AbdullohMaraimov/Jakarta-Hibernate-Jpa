<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home Page</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        a {
            color: #3498db;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
            color: #2070b7;
        }

        button {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2070b7;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Created Time</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td><c:out value="${group.getId()}"/></td>
            <td><a href="/group/student/${group.getId()}"><c:out value="${group.getName()}"/></a></td>
            <td><c:out value="${group.getCreatedAt()}"/></td>
            <td>
                <a href="/group/delete/${group.getId()}">Delete</a> ||
                <a href="/group/update/${group.getId()}">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <form action="AddGroup">
        <button type="submit">Add Group</button>
    </form>
</table>
</body>
</html>
