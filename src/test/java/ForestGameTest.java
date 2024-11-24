import com.quest.model.entity.Monster;
import com.quest.model.services.ActionType;
import com.quest.model.ForestGame;
import com.quest.model.services.InteractionResult;
import com.quest.model.locastions.*;
import com.quest.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForestGameTest {

    private ForestGame game;
    private Player player;

    @BeforeEach
    public void setUp() {
        game = new ForestGame();
        player = game.getPlayer();
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, player.getHealth(), "Initial health should be 100");
    }

    @Test
    void testCaveLocationMonsterPresent() {
        CaveLocation cave = new CaveLocation();
        cave.enterCave(player);
        if (cave.isHasMonster()) {
            assertEquals(InteractionResult.MONSTER_PRESENT, cave.enterCave(player));
        }
    }

    @Test
    void testEmptyLocationEating() {
        EmptyLocation location = new EmptyLocation();
        player.setHealth(80);
        InteractionResult result = location.eat(player);
        if (location.isHasWater()) {
            assertEquals(InteractionResult.DRINK_WATER, result);
            assertEquals(85, player.getHealth());
        } else if (location.isHasBerry()) {
            assertEquals(InteractionResult.EAT_BERRY, result);
            assertEquals(83, player.getHealth());
        } else if (location.isHasMushroom()){
            assertEquals(InteractionResult.EAT_MUSHROOM, result);
            assertEquals(85, player.getHealth());
        } else if (location.isHasNuts()) {
            assertEquals(InteractionResult.EAT_NUTS, result);
            assertEquals(84, player.getHealth());
        }

    }

    @Test
    void testTreeLocationClimbingAndExitVisible() {
        TreeLocation tree = new TreeLocation(5, 5);
        player.setX(3);
        player.setY(4);
        InteractionResult result = tree.climbTree(player);
        assertTrue(result.getTextDescription().contains("↘️"));
    }

    @Test
    void testTreeLocationJumpingDecreasesHealth() {
        TreeLocation tree = new TreeLocation(10, 10);
        player.setHealth(100);
        InteractionResult result = tree.jumpFromTree(player);
        assertEquals(InteractionResult.JUMP_FORM_TREE_SUCCESS, result);
        assertEquals(80, player.getHealth());
    }

    @Test
    void testCaveFightMonster() {
        CaveLocation cave = new CaveLocation();
        cave.setMonster(new Monster());
        cave.setHasMonster(true);
        player.setWeapon(true);
        InteractionResult result = cave.fightMonster(player);
        assertTrue(result.getTextDescription().contains("Здоров'я монстра:"));
    }

    @Test
    void testPersonLocationTradeWeapon() {
        PersonLocation person = new PersonLocation(10, 10);
        player.setTreasure(true);
        InteractionResult result = person.tradeWeapon(player);
        assertEquals(InteractionResult.BUY_WEAPON, result);
        assertTrue(player.hasWeapon());
        assertFalse(player.hasTreasure());
    }

    @Test
    void testCaveLocationLeaveCave() {
        CaveLocation cave = new CaveLocation();
        player.setInCave(true);
        InteractionResult result = cave.leaveCave(player);
        assertEquals(InteractionResult.CAVE_EXITED, result);
        assertFalse(player.isInCave());
    }
}
