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
import java.util.List;

@WebServlet(name = "UserListServlet", value = "/user/list")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO udao = new UserDAO();
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = udao.readAll();
            if (users == null){
                System.out.println("Pusta lista");
            }
            request.setAttribute("userList", users);
        } catch (SQLException e){
            System.out.println("Błąd połączenia");
        }

        request.getRequestDispatcher("/users/userList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
