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

@WebServlet(name = "UserShowServlet", value = "/user/show")
public class UserShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idS = request.getParameter("id");
        UserDAO udao = new UserDAO();

        //próba sprasowania parametru z linku na liczbę
        try {

            int id = Integer.parseInt(idS);
            try (Connection conn = DbUtil.getConnection()){
                User u = udao.read(id);
                if (u.getId() == null){
                    throw new NumberFormatException("Nie ma takiego id");
                }
                else   {
                    request.setAttribute("user", u);
                    request.getRequestDispatcher("/users/userDetails.jsp").forward(request, response);
                }
            } catch (SQLException e){
                System.out.println("Błąd pobrania używnika");
            }
        } catch (NumberFormatException ex){
            System.out.println("Niepoprawne id");
            response.sendRedirect("/user/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
