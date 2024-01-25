<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Student Home Page</title>
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
    <th>Full name</th>
    <th>Created Time</th>
    <th>Group Id</th>
    <th>Age</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${students}" var="student">
    <tr>
      <td><c:out value="${student.getId()}"/></td>
      <td><c:out value="${student.getFullName()}"/></td>
      <td><c:out value="${student.getCreatedAt()}"/></td>
      <td><c:out value="${student.getGroupId()}"/></td>
      <td><c:out value="${student.getAge()}"/></td>
      <td>
        <a href="/student/delete/${student.getId()}">Delete</a> ||
        <a href="/student/update/${student.getId()}">Update</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
  <form action="/AddStudent/${groupId}">
    <button type="submit">Add Student</button>
  </form>
</table>
</body>
</html>
