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
import models.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

@WebServlet(name = "AddStudentServlet", urlPatterns = "/AddStudent/*")
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/add_student.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        int groupId = Integer.parseInt(request.getPathInfo().substring(1));

        Student student = Student.builder()
                .fullName(fullName)
                .age(age)
                .groupId(groupId)
                .build();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Student>> validate = validator.validate(student);

        if (validate.size() == 0) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            response.sendRedirect("/group/student/" + groupId);
        } else {
            for (ConstraintViolation<Student> violation : validate) {
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
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into students(full_name, group_id, age) " +
//                            "values(?, ?, ?);"
//            );
//            preparedStatement.setString(1, fullName);
//            preparedStatement.setInt(2, groupId);
//            preparedStatement.setInt(3, age);
//            preparedStatement.execute();
//
//            response.sendRedirect("/group/student/" + groupId);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
