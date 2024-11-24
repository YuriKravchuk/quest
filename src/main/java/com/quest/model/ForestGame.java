package com.quest.model;

import com.quest.model.entity.Player;
import com.quest.model.locastions.*;
import com.quest.model.services.ActionType;
import com.quest.model.services.GameConfig;
import com.quest.model.services.InteractionResult;

import java.util.Random;

public class ForestGame {
    private Location[][] forest;
    private Player player;
    private static int exitX;
    private static int exitY;
    private GameConfig gameConfig;
    private Random random;

    public ForestGame() {
        random = new Random();
        gameConfig = new GameConfig();
        placeExitRandomly();
        initializeForest();
        placePlayerRandomly();
    }

    // Ініціалізація лісу
    private void initializeForest() {
        forest = new Location[gameConfig.getWidth()][gameConfig.getHeight()];

        for (int i = 0; i < gameConfig.getWidth(); i++) {
            for (int j = 0; j < gameConfig.getHeight(); j++) {
                forest[i][j] = new EmptyLocation(); // Заповнюємо порожніми локаціями
            }
        }

        // Розміщення об'єктів у лісі
        placeObjectsInForest(gameConfig.getNumberOfTrees(), gameConfig.getNumberOfCaves(), gameConfig.getNumberOfPeople());
    }

    // Випадкове розміщення гравця на мапі
    private void placePlayerRandomly() {
        int x, y;
        do {
            x = random.nextInt(gameConfig.getWidth());
            y = random.nextInt(gameConfig.getHeight());
        } while (!(forest[x][y] instanceof EmptyLocation));

        player = new Player(x, y);
    }

    public Player getPlayer() {
        return player;
    }

    // Випадкове розміщення виходу на периметрі
    private void placeExitRandomly() {
        if (random.nextBoolean()) { // Визначаємо, чи вихід буде по горизонталі або вертикалі
            exitX = random.nextInt(gameConfig.getWidth());
            exitY = random.nextBoolean() ? 0 : gameConfig.getHeight() - 1;
        } else {
            exitX = random.nextBoolean() ? 0 : gameConfig.getWidth() - 1;
            exitY = random.nextInt(gameConfig.getHeight());
        }
    }

    // Випадкове розміщення об'єкта у порожній локації
    private void placeRandomObject(Location location) {
        int x, y;
        do {
            x = random.nextInt(gameConfig.getWidth());
            y = random.nextInt(gameConfig.getHeight());
        } while (!(forest[x][y] instanceof EmptyLocation));

        forest[x][y] = location;
    }

    // Розміщення об'єктів у лісі
    private void placeObjectsInForest(int numberOfTrees, int numberOfCaves, int numberOfPeople) {
        for (int i = 0; i < numberOfTrees; i++) {
            placeRandomObject(new TreeLocation(exitX,exitY));
        }
        for (int i = 0; i < numberOfCaves; i++) {
            placeRandomObject(new CaveLocation());
        }
        for (int i = 0; i < numberOfPeople; i++) {
            placeRandomObject(new PersonLocation(exitX, exitY));
        }
    }

    // Обробка дій гравця
    public InteractionResult handleAction(ActionType action) {
        switch (action) {
            case MOVE_UP:
                player.move("up");
                break;
            case MOVE_DOWN:
                player.move("down");
                break;
            case MOVE_LEFT:
                player.move("left");
                break;
            case MOVE_RIGHT:
                player.move("right");
                break;
            case CLIMB_TREE:
                if (getCurrentLocation() instanceof TreeLocation) {
                    return ((TreeLocation) getCurrentLocation()).climbTree(player);
                }
                break;
            case ENTER_CAVE:
                if (getCurrentLocation() instanceof CaveLocation) {
                    return ((CaveLocation) getCurrentLocation()).enterCave(player);
                }
                break;
            case FIGHT_MONSTER:
                if (getCurrentLocation() instanceof CaveLocation) {
                    return ((CaveLocation) getCurrentLocation()).fightMonster(player);
                }
                break;
            case LEAVE_CAVE:
                if (getCurrentLocation() instanceof CaveLocation) {
                    return ((CaveLocation) getCurrentLocation()).leaveCave(player);
                }
                break;
            case DESCEND_TREE:
                if (getCurrentLocation() instanceof TreeLocation) {
                    return ((TreeLocation) getCurrentLocation()).descendTree(player);
                }
                break;
            case FLEE:
                if (getCurrentLocation() instanceof CaveLocation) {
                    return ((CaveLocation) getCurrentLocation()).fleeFromMonster(player);
                }
                break;
            case JUMP_FORM_TREE:
                if (getCurrentLocation() instanceof TreeLocation) {
                    return ((TreeLocation) getCurrentLocation()).jumpFromTree(player);
                }
                break;
            case TALK_PERSON:
                if (getCurrentLocation() instanceof PersonLocation) {
                    return ((PersonLocation) getCurrentLocation()).talkWithHuman(player);
                }
                break;
            case TRADE_WEAPON:
                if (getCurrentLocation() instanceof PersonLocation) {
                    return ((PersonLocation) getCurrentLocation()).tradeWeapon(player);
                }
                break;
            case SHOW_EXIT:
                if (getCurrentLocation() instanceof PersonLocation) {
                    return ((PersonLocation) getCurrentLocation()).giveExitDirection(player);
                }
                break;
            case STOP_TALKING:
                if (getCurrentLocation() instanceof PersonLocation) {
                    return ((PersonLocation) getCurrentLocation()).stopTalking(player);
                }
                break;
            case EAT:
                if (getCurrentLocation() instanceof EmptyLocation) {
                    return ((EmptyLocation) getCurrentLocation()).eat(player);
                }
                break;
        }
        return null;
    }

    // Перевіряє, чи гравець знаходиться на виході
    public boolean isExit() {
        return player.getX() == exitX && player.getY() == exitY;
    }

    // Отримання поточної локації гравця
    public Location getCurrentLocation() {
        return forest[player.getX()][player.getY()];
    }

    // Генерація мінікарти гри
    public String getMiniMap() {
        StringBuilder map = new StringBuilder("<table>");
        for (int i = 0; i < gameConfig.getWidth(); i++) {
            map.append("<tr>");
            for (int j = 0; j < gameConfig.getHeight(); j++) {
                String symbol;
                if (i == player.getX() && j == player.getY()) {
                    symbol = player.getHeroIcon(); // Символ гравця
                } else {
                    symbol = forest[i][j].getMapSymbol();
                }
                if (player.isHasPrinces() && i == exitX && j == exitY) {
                    symbol = "🚪";
                }
                map.append("<td>").append(symbol).append("</td>");
            }
            map.append("</tr>");
        }
        map.append("</table>");
        return map.toString();
    }
}
