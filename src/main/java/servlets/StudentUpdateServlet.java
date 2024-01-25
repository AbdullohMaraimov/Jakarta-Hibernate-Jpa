package servlets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Group;
import models.Student;

import java.io.IOException;

@WebServlet(name = "StudentUpdateServlet", urlPatterns = "/student/update/*")
public class StudentUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getPathInfo().substring(1));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, studentId);

        req.setAttribute("student", student);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/studentUpdate.jsp");
        requestDispatcher.forward(req, response);

        entityManager.getTransaction().commit();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        String studentName = request.getParameter("name");
        int studentAge = Integer.parseInt(request.getParameter("age"));
        int groupId = 0;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, id);
        groupId = student.getGroupId();
        student.setFullName(studentName);
        student.setAge(studentAge);
        entityManager.merge(student);

        entityManager.getTransaction().commit();

        response.sendRedirect("/group/student/" + groupId);
    }
}
