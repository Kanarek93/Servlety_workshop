package pl.coderslab.web;

import pl.coderslab.dao.UserDAO;
import pl.coderslab.entity.User;
import pl.coderslab.utils.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "UserAddServlet", value = "/user/add")
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/users/addUserForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, username, password);
        try (Connection conn =  DbUtil.getConnection()) {
            userDao.create(user);
        } catch (SQLException e){
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}
