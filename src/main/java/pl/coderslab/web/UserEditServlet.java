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

@WebServlet(name = "UserEditServlet", value = "/user/edit")
public class UserEditServlet extends HttpServlet {

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
                    request.getRequestDispatcher("/users/editUserForm.jsp").forward(request, response);
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
        UserDAO udao = new UserDAO();

        //pobieranie parametrów
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String idS = request.getParameter("id");


        try {
            int id = Integer.parseInt(idS);

            try (Connection conn = DbUtil.getConnection()) {
                //sprawdzamy czy użytkownik o danym id istnieje
                User u = udao.read(id);
                if (u.getId() == null){
                    //jeżeli nie to go dodajemy (ale z innym id!)
                    u = new User(email, username, password);
                    udao.create(u);
                }
                else {
                    //jeżeli istnieje to robimy update
                   u.setUsername(username);
                   u.setEmail(email);
                   u.setPassword(password);
                   udao.update(u);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e){
            System.out.println("Niepoprawne id");
        }

        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}
