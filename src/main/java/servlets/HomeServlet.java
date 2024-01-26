package servlets;

import com.mysql.jdbc.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Group;
import models.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "servlets.HomeServlet", value = "")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Group> groupList = new ArrayList<>();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Group> groups = entityManager.createQuery("from Group t group by t.id", Group.class).getResultList();

        entityManager.getTransaction().commit();

        request.setAttribute("groups", groups);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/home.jsp");
        requestDispatcher.forward(request, response);

//        String url = "jdbc:mysql://localhost:3306/demo";
//        String password = "student";
//        String user = "student";
//
//        List<Group> groupList = new ArrayList<>();
//        List<Student> studentList = new ArrayList<>();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try(Connection connection = DriverManager.getConnection(url, user, password)) {
//            DriverManager.registerDriver(new Driver());
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "select * from student_groups order by id"
//            );
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                Group group = Group.builder()
//                        .id(resultSet.getInt("id"))
//                        .name(resultSet.getString("group_name"))
//                        .createdAt(resultSet.getTimestamp("created_at"))
//                        .build();
//
//                groupList.add(group);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        request.setAttribute("groups", groupList);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
