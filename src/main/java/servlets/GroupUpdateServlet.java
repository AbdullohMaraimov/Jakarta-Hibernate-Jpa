package servlets;

import com.mysql.jdbc.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Group;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Set;

@WebServlet(name = "GroupUpdateServlet", urlPatterns = "/group/update/*")
public class GroupUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId = Integer.parseInt(req.getPathInfo().substring(1));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, groupId);

        req.setAttribute("group", group);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/update.jsp");
        requestDispatcher.forward(req, resp);

        entityManager.getTransaction().commit();


    }

    //    String url = "jdbc:mysql://localhost:3306/demo";
//    String password = "student";
//    String user = "student";
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        int id = Integer.parseInt(request.getPathInfo().substring(1));
//
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//            PreparedStatement statement = connection.prepareStatement("select * from student_groups where id = ?");
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                Group group = Group.builder()
//                        .name(resultSet.getString("group_name"))
//                        .build();
//
//                request.setAttribute("group", group);
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/update.jsp");
//                requestDispatcher.forward(request, response);
//
//            } else {
//                response.getWriter().println("Error");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        String groupName = request.getParameter("name");

        int groupId = Integer.parseInt(request.getPathInfo().substring(1));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, id);
        group.setName(groupName);

        Set<ConstraintViolation<Group>> validate = validator.validate(group);
        if (validate.size() == 0) {
            entityManager.merge(group);
            entityManager.getTransaction().commit();
            response.sendRedirect("/");
        } else {
            for (ConstraintViolation<Group> violation : validate) {
                PrintWriter writer = response.getWriter();

                writer.println(violation.getMessage());
            }
        }





//        String url = "jdbc:mysql://localhost:3306/demo";
//        String password = "student";
//        String user = "student";
//
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//            PreparedStatement statement = connection.prepareStatement(
//                    "update student_groups set group_name = ? where id = ?"
//            );
//            statement.setString(1, groupName);
//            statement.setInt(2, id);
//            statement.execute();
//
//            response.sendRedirect("/");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
