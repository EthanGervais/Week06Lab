/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ethan Gervais
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                session = request.getSession();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }

        String username = (String) session.getAttribute("username");
        if (username != null) {
            request.setAttribute("username", username);
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> shoppingList = (ArrayList<String>) session.getAttribute("shoppingList");

        String username = request.getParameter("username");

        switch (action) {
            case "register":
                if (username == null || username.equals("")) {
                    response.sendRedirect("ShoppingList");
                } else {
                    username = request.getParameter("username");
                    session.setAttribute("username", username);
                    session.setAttribute("shoppingList", shoppingList);
                    response.sendRedirect("ShoppingList");
                }
                break;
            case "add":
                if (shoppingList == null) {
                    shoppingList = new ArrayList<>();

                    String item = request.getParameter("item");
                    shoppingList.add(item);

                    session.setAttribute("shoppingList", shoppingList);
                    response.sendRedirect("ShoppingList");
                } else {
                    String item = request.getParameter("item");
                    shoppingList.add(item);

                    session.setAttribute("shoppingList", shoppingList);
                    response.sendRedirect("ShoppingList");
                }
                break;
            case "delete":
                String radio = request.getParameter("items");
                shoppingList.remove(shoppingList.indexOf(radio));

                session.setAttribute("shoppingList", shoppingList);
                response.sendRedirect("ShoppingList");
                break;
        }
    }

}
