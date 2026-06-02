package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class PlayerSpawner implements IPostEntityProcessingService {
    private int lives = 3;

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        Entity[] entities = gameEvents.getCollisionEntities();
        if (entities == null || entities.length < 2) return;

        Entity a = entities[0];
        Entity b = entities[1];
        if (a == null || b == null) return;

        final Player player;
        final Entity other;

        if (a instanceof Player) {
            player = (Player) a;
            other = b;
        } else if (b instanceof Player) {
            player = (Player) b;
            other = a;
        } else {
            return;
        }

        if (other instanceof Asteroid) {
            world.removeEntity(player);
            gameEvents.setPlayerAlive(false);
            gameEvents.setPlayerHit(true);
            clearCollisionBoth(gameEvents);
            return;
        }

        if (other instanceof Bullet) {
            lives--;

            world.removeEntity(player);
            world.removeEntity(other);
            gameEvents.setPlayerHit(true);
            clearCollisionBoth(gameEvents);

            if (lives <= 0) {
                gameEvents.setPlayerAlive(false);
                return;
            }

            Player newPlayer = spawnPlayer(gameData);
            world.addEntity(newPlayer);
        }
    }

    public void clearCollisionBoth(GameEvents gameEvents) {
        gameEvents.clearCollisionEntity0();
        gameEvents.clearCollisionEntity1();
    }

    public Player spawnPlayer(GameData gameData) {
        Player playerShip = new Player();
        playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        playerShip.setX(gameData.getDisplayHeight() / 2f);
        playerShip.setY(gameData.getDisplayWidth() / 2f);
        playerShip.setRadius(8);
        playerShip.setLives(lives);
        return playerShip;
    }
}