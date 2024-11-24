package com.quest.servlet;

import com.quest.model.services.ObjectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Створюємо список іконок героїв
        List<ObjectType> heroIcons = new ArrayList<>();
        for (ObjectType type : ObjectType.values()) {
            if (type.name().startsWith("HERO")) {
                heroIcons.add(type);
            }
        }

        request.setAttribute("heroIcons", heroIcons);

        request.getRequestDispatcher("/start.jsp").forward(request, response);
    }
}
