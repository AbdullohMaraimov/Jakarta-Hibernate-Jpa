package servlets;

import com.mysql.jdbc.Driver;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Group;
import models.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GroupStudentHomeServlet", urlPatterns = "/group/student/*")
public class GroupStudentHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int groupId = Integer.parseInt(pathInfo.substring(1));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM students WHERE group_id = :groupIdd", Student.class);
        nativeQuery.setParameter("groupIdd", groupId);
        List<Student> students = nativeQuery.getResultList();

        request.setAttribute("students", students);
        request.setAttribute("groupId", groupId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/students.jsp");
        requestDispatcher.forward(request, response);

        entityManager.getTransaction().commit();




//        String url = "jdbc:mysql://localhost:3306/demo";
//        String password = "student";
//        String user = "student";
//        String pathInfo = request.getPathInfo();
//        int groupId = Integer.parseInt(pathInfo.substring(1));
//
//        List<Student> studentList = new ArrayList<>();
//
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "select * from students where group_id = ? order by id"
//            );
//            preparedStatement.setInt(1, groupId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                Student student = Student.builder()
//                        .id(resultSet.getInt("id"))
//                        .fullName(resultSet.getString("full_name"))
//                        .createdAt(resultSet.getTimestamp("created_at"))
//                        .groupId(resultSet.getInt("group_id"))
//                        .age(resultSet.getInt("age"))
//                        .build();
//
//                studentList.add(student);
//            }
//
//            request.setAttribute("students", studentList);
//            request.setAttribute("groupId", groupId);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/students.jsp");
//            requestDispatcher.forward(request, response);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
