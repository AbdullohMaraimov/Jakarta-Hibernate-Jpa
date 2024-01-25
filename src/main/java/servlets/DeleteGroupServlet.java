package servlets;

import com.mysql.jdbc.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Group;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteGroupServlet", urlPatterns = "/group/delete/*")
public class DeleteGroupServlet extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/demo";
    String password = "student";
    String user = "student";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, id);

        entityManager.getTransaction().commit();

        request.setAttribute("group", group);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/delete.jsp");
        requestDispatcher.forward(request, response);


//        String pathInfo = request.getPathInfo();
//        int id = Integer.parseInt(pathInfo.substring(1));
//
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "select * from student_groups where id = ?"
//            );
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Group group = Group.builder()
//                        .name(resultSet.getString("group_name"))
//                        .build();
//
//                request.setAttribute("group", group);
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/delete.jsp");
//                requestDispatcher.forward(request, response);
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);

        entityManager.getTransaction().commit();
        response.sendRedirect("/");

//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from student_groups where id = ?"
//            );
//            preparedStatement.setInt(1, id);
//            preparedStatement.execute();
//
//            response.sendRedirect("/");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
