<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Update group</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        form {
            text-align: center;
        }

        label {
            display: block;
            font-size: 18px;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 250px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
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
<form id="notificationForm" method="post">
    <label for="name">Enter Student Name</label>
    <input type="text" id="name" name="name" value="${student.getFullName()}">
    <br>
    <label for="name">Enter Student Age</label>
    <input type="text" id="age" name="age" value="${student.getAge()}">
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
