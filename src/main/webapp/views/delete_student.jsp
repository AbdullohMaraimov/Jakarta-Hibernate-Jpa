<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Delete Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }

        h1 {
            color: #333;
        }

        a, button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        a {
            background-color: #3498db;
            color: #fff;
        }

        button {
            background-color: #e74c3c;
            color: #fff;
        }

        a:hover, button:hover {
            background-color: #2070b7;
        }
    </style>
</head>
<body>
<div>
    <form method="post">
        <h1>Are you sure to delete this student: <c:out value = "${student.getFullName()}"/>?</h1>
        <a href="/group/student/${student.getGroupId}">Back</a>
        <button type="submit">Yes</button>
    </form>
</div>
</body>
</html>
