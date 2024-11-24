<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Лісовий квест</title>
    <link rel="stylesheet" type="text/css" href="static/styles.css"> <!-- Підключення CSS -->
</head>
<body>
<h2>Лісовий квест</h2>
<div class="container">
    <div class="text-content">
        <p>Герой: <strong>${player.getHeroName()} ${player.getHeroIcon()}</strong></p>
        <p>Твоє здоров'я: <strong>${player.health}</strong></p>
        <p>Твої речі:
            <c:if test="${player.hasWeapon()}">Зброя ⚔️</c:if>
            <c:if test="${player.hasTreasure()}">Скарб 💎</c:if>
        </p>
        <c:if test="${player.isHasPrinces()}"><p>З тобою принцеса. 👸🏼</p></c:if>
        <p>Координати героя: <strong>(${player.x}, ${player.y})</strong></p>
        <p>Поточна локація: <strong>${description}</strong></p>
        <p>${message}</p>

        <form action="game" method="post">
            <!-- Джойстик -->
            <c:if test="${!player.inTree && !player.inCave && !player.isTalking}">
            <div class="joystick-container">
                        <div class="joystick">
                            <div class="joystick-row">
                                <button name="action" value="MOVE_UP">↑</button>
                            </div>
                            <div class="joystick-row">
                                <button name="action" value="MOVE_LEFT">←</button>
                                <button name="action" value="MOVE_RIGHT">→</button>
                            </div>
                            <div class="joystick-row">
                                <button name="action" value="MOVE_DOWN">↓</button>
                            </div>
                        </div>
            </div>
                <c:if test="${game.getCurrentLocation().getType() == 'EMPTY'}">
                    <c:if test="${game.getCurrentLocation().isHasMushroom()}">
                        <button name="action" value="EAT">З'їсти гриб</button>
                    </c:if>
                    <c:if test="${game.getCurrentLocation().isHasBerry()}">
                        <button name="action" value="EAT">З'їсти ягоди</button>
                    </c:if>
                    <c:if test="${game.getCurrentLocation().isHasNuts()}">
                        <button name="action" value="EAT">З'їсти горіхи</button>
                    </c:if>
                    <c:if test="${game.getCurrentLocation().isHasWater()}">
                        <button name="action" value="EAT">Випити води</button>
                    </c:if>
                </c:if>

            </c:if>
            <!-- Дії з деревом -->
            <c:if test="${game.getCurrentLocation().getType() == 'TREE' && !player.inTree}">
                <button name="action" value="CLIMB_TREE">Залізти на дерево</button>
            </c:if>

            <!-- Дії з печерою -->
            <c:if test="${game.getCurrentLocation().getType() == 'CAVE' && !player.inCave}">
                <button name="action" value="ENTER_CAVE">Ввійти в печеру</button>
            </c:if>

            <!-- Дії з людиною -->
            <c:if test="${game.getCurrentLocation().getType() == 'PERSON' && !player.isTalking}">
                <button name="action" value="TALK_PERSON">Поговорити</button>
            </c:if>

            <!-- Дії, коли гравець на дереві -->
            <c:if test="${player.inTree}">
                <button name="action" value="DESCEND_TREE">Спуститися з дерева</button>
                <button name="action" value="JUMP_FORM_TREE">Зістрибнути з дерева</button>
            </c:if>
            <!-- Дії, коли гравець у печері -->
            <c:if test="${player.inCave}">
                <c:if test="${game.getCurrentLocation().isHasMonster()}">
                    <button name="action" value="FIGHT_MONSTER">Битися з монстром</button>
                    <button name="action" value="FLEE">Втекти</button>
                </c:if>
                <c:if test="${!game.getCurrentLocation().isHasMonster()}">
                    <button name="action" value="LEAVE_CAVE">Вийти з печери</button>
                </c:if>
            </c:if>
            <!-- Дії, коли гравець говорить -->
            <c:if test="${player.isTalking}">
                <c:if test="${game.getCurrentLocation().isHasWeapon()}">
                    <button name="action" value="TRADE_WEAPON">Купити зброю</button>
                </c:if>
                <c:if test="${game.getCurrentLocation().isKnowsExit()}">
                    <button name="action" value="SHOW_EXIT">Запитати про вихід з лісу</button>
                </c:if>
                <button name="action" value="STOP_TALKING">Закінчити розмову</button>
            </c:if>
        </form>
    </div>

    <!-- Карта -->
    <div class="mini-map">
        <h3>Карта лісу</h3>
        <div>
            ${miniMap}
        </div>
    </div>
</div>
</body>
</html>
