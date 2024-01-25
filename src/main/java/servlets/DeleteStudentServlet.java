package servlets;

import com.mysql.jdbc.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Student;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteStudentServlet", urlPatterns = "/student/delete/*")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        int groupId = 0;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, id);
        groupId = student.getGroupId();
        entityManager.remove(student);
        entityManager.getTransaction().commit();

        response.sendRedirect("/group/student/" + groupId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String url = "jdbc:mysql://localhost:3306/demo";
//        String password = "student";
//        String user = "student";
//
//        int id = Integer.parseInt(request.getPathInfo().substring(1));
//        int groupId = 0;
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "select * from students where id = ?"
//            );
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()){
//                Student student = Student.builder()
//                        .fullName(resultSet.getString("full_name"))
//                        .groupId(resultSet.getInt("group_id"))
//                        .build();
//
//                groupId = student.getGroupId();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from students where id = ?"
//            );
//            preparedStatement.setInt(1, id);
//            preparedStatement.execute();
//
//            response.sendRedirect("/group/student/" + groupId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
