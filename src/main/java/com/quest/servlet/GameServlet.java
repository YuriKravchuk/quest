package com.quest.servlet;

import com.quest.model.*;
import com.quest.model.entity.Player;
import com.quest.model.services.ActionType;
import com.quest.model.services.InteractionResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ForestGame game = (ForestGame) session.getAttribute("game");

        // Якщо гри ще немає в сесії, ініціалізуємо нову гру
        if (game == null) {
            game = new ForestGame();
            session.setAttribute("game", game);
        }

        // Перевірка стану гри
        if (game.getPlayer().getHealth() <= 0) {
            response.sendRedirect("gameOver.jsp?result=lose");
            return;
        }

        if (game.isExit()) {
            response.sendRedirect("gameOver.jsp?result=win");
            return;
        }

        request.setAttribute("description", game.getCurrentLocation().getDescription());
        request.setAttribute("miniMap", game.getMiniMap());
        request.setAttribute("player", game.getPlayer());
        request.setAttribute("personLocation", game.getCurrentLocation());

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        // Якщо натиснули кнопку "Почати нову гру"
        if ("NEW_GAME".equals(action)) {
            String playerName = request.getParameter("playerName");
            String heroIcon = request.getParameter("icon");

            ForestGame game = new ForestGame();
            Player player = game.getPlayer();
            player.setHeroName(playerName);
            player.setHeroIcon(heroIcon);

            session.setAttribute("game", game);
            response.sendRedirect("game"); // Перезавантажуємо гру
            return;
        }

        if ("NEW_GAME".equals(action)) {
            String playerName = request.getParameter("playerName");
            String heroIcon = request.getParameter("icon");

            ForestGame game = new ForestGame();
            Player player = game.getPlayer();
            player.setHeroName(playerName);
            player.setHeroIcon(heroIcon);

            session.setAttribute("game", game);
            response.sendRedirect("game"); // Перезавантажуємо гру
            return;
        }

        ForestGame game = (ForestGame) session.getAttribute("game");

        if (game == null) {
            game = new ForestGame();
            session.setAttribute("game", game);
        }

        ActionType actionType = ActionType.valueOf(action.toUpperCase());
        InteractionResult result = game.handleAction(actionType);

        request.setAttribute("message", (result != null) ? result.getTextDescription() : "");

        if (game.getPlayer().getHealth() <= 0) {
            response.sendRedirect("gameOver.jsp?result=lose");
            return;
        }

        if (game.isExit()) {
            response.sendRedirect("gameOver.jsp?result=win");
            return;
        }

        doGet(request, response);
    }

    // Обробка запиту на початок нової гри
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Очищаємо сесію

        response.sendRedirect("game"); // Перенаправляємо на нову гру
    }
}
