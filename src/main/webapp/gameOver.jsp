<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Кінець гри</title>
  <link rel="stylesheet" type="text/css" href="static/styles.css"> <!-- Підключення CSS -->
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      color: #333;
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #4CAF50;
      color: white;
      padding: 20px;
      text-align: center;
    }

    .game-over-container {
      text-align: center;
      padding: 20px;
      background-color: #e9f5e9;
      border-radius: 8px;
      margin: 20px auto;
      max-width: 800px;
    }

    .game-over-container h2 {
      color: #28a745;
    }

    .button {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      border-radius: 5px;
      margin-top: 20px;
    }

    .button:hover {
      background-color: #0056b3;
    }

    .button-back {
      background-color: #28a745;
      border-radius: 5px;
      margin-top: 20px;
    }

    .button-back:hover {
      background-color: #218838;
    }
  </style>
</head>
<body>

<main>
  <section class="game-over-container">
    <c:choose>
      <c:when test="${param.result == 'win'}">
        <h2>Ти успішно вийшов з лісу! Вітаю!</h2>
        <c:if test="${!game.getPlayer().isHasPrinces()}">
          <p>Але принцеса все ще не врятована 😔</p>
        </c:if>
      </c:when>
      <c:otherwise>
        <h2>На жаль, ти програв. Твоє здоров'я вичерпалося.</h2>
      </c:otherwise>
    </c:choose>

    <p><strong>Твоє здоров'я:</strong> <strong>${game.getPlayer().getHealth()}</strong></p>

    <p><strong>Твої речі:</strong>
      <c:if test="${game.getPlayer().hasWeapon()}">Зброя ⚔️</c:if>
      <c:if test="${game.getPlayer().hasTreasure()}">Скарб 💎</c:if>
      <c:if test="${game.getPlayer().isHasPrinces()}">Врятована принцеса 👸🏼</c:if>
    </p>

    <c:if test="${!game.getPlayer().isHasPrinces()}">
      <button class="button button-back" onclick="window.history.back();">Врятувати принцесу</button>
    </c:if>

    <form action="start" method="get">
      <button class="button" type="submit">Почати нову гру</button>
    </form>
  </section>
</main>

</body>
</html>
