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

@WebServlet(name = "AddGroupServlet", value = "/AddGroup")
public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/add_group.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("name");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Group group = Group.builder()
                .name(groupName)
                .build();

        entityManager.persist(group);

        entityManager.getTransaction().commit();

        response.sendRedirect("/");

//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into student_groups(group_name) values(?);"
//            );
//            preparedStatement.setString(1, groupName);
//            preparedStatement.execute();
//
//            response.sendRedirect("/");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
