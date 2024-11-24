<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Лісовий квест</title>
  <link rel="stylesheet" type="text/css" href="static/styles.css"> <!-- Підключення CSS -->
</head>
<body>
<h2>🌲🌳🌲🌳 Ласкаво просимо до Лісового квесту! 🌳🌲🌳🌲</h2>
<main>
  <section class="intro">
    <p>Ти опинився в зачарованому лісі. На тебе чекає цікава пригода. Але будь обережним! В печерах може бути як скарб, так і монстр! З кожним кроком твоє здоров'я слабшає, не забувай про їжу!</p>
    <p><strong>Мета квесту:</strong> знайти вихід з лісу. Вихід знаходиться по периметру лісу на довільній локації.</p>
    <p><strong>Бонусне завдання:</strong> врятувати принцесу.</p>
    <p><strong>Як можна знайти вихід:</strong></p>
    <ul>
      <li>спитати у мандрівника;</li>
      <li>якщо вихід знаходиться недалеко - його можна побачити з дерева;</li>
      <li>принцеса знає, де вихід.</li>
    </ul>
    <p><strong>Підказки:</strong></p>
    <ul>
      <li>принцесу охороняє монстр;</li>
      <li>монстра голими руками не візьмеш;</li>
      <li>зброю можна знайти в печерах або купити у мандрівника.</li>
    </ul>
    <hr>
    <p>Ти готовий? Час починати!</p>

    <form action="game" method="post">
      <div class="form-group">
        <label for="playerName"><strong>Ім'я героя:</strong></label>
        <input type="text" id="playerName" name="playerName" required placeholder="Введіть ім'я">
      </div>

      <div class="form-group">
        <label><strong>Обери іконку героя:</strong></label>
        <div class="icon-selection">
          <c:forEach var="icon" items="${heroIcons}">
            <label>
              <input type="radio" name="icon" value="${icon.symbol}" required>
              <span class="icon">${icon.symbol}</span>
            </label>
          </c:forEach>
        </div>
      </div>

      <button type="submit" name="action" value="NEW_GAME">Розпочати гру</button>
    </form>
  </section>
</main>

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
  .intro {
    padding: 20px;
    background-color: #e9f5e9;
    border-radius: 8px;
    margin: 20px auto;
    max-width: 800px;
    line-height: 1.6;
  }
  .start-container {
    text-align: center;
    margin: 20px auto;
    max-width: 600px;
    padding: 20px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  .form-group {
    margin-bottom: 15px;
  }
  .icon-selection label {
    margin-right: 15px;
    cursor: pointer;
  }
  .icon {
    font-size: 35px;
    margin-left: 5px;
  }
  button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
  }
  button:hover {
    background-color: #45a049;
  }
</style>
</body>
</html>
