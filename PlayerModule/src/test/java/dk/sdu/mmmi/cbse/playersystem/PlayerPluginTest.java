package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import static org.junit.jupiter.api.Assertions.*;
class PlayerPluginTest {
    World world = new World();
    GameData gameData = new GameData();
    PlayerPlugin playerPlugin = new PlayerPlugin();

    @org.junit.jupiter.api.Test
    void start() {
        playerPlugin.start(gameData, world);
        assertEquals(1, world.getEntities(Player.class).size());
    }

    @org.junit.jupiter.api.Test
    void stop() {
        playerPlugin.start(gameData, world);
        playerPlugin.stop(gameData, world);
        assertTrue(world.getEntities(Player.class).isEmpty());
    }
}