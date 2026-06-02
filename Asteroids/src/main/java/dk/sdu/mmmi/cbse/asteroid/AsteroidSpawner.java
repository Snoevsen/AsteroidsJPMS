package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.ScoreServiceClient;

import static dk.sdu.mmmi.cbse.common.util.PolygonFactory.rockPolygon;

import java.util.Random;

public class AsteroidSpawner implements IPostEntityProcessingService {
    
    private final AsteroidSplitterImpl splitter = new AsteroidSplitterImpl();

    private final ScoreServiceClient scoreClient = new ScoreServiceClient("http://localhost:8081");

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        Entity[] entities = gameEvents.getCollisionEntities();
        int asteroidCount = world.getEntities(Asteroid.class).size(); 
        int asteroidAmount = 9;

        for (int i = 0; i < entities.length; i++) {
            if (entities[i] instanceof Asteroid) {
                new Thread(() -> {
                    try { scoreClient.addPoints(1); }
                    catch (Exception ignored) {}
                }).start();

                world.removeEntity(entities[i]);
                splitter.createSplitAsteroid(entities[i], world);

                if (i == 0) gameEvents.clearCollisionEntity0();
                else gameEvents.clearCollisionEntity1();
            }
        }
        
        if (asteroidCount < asteroidAmount) {
            Entity asteroid = spawnAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    public Entity spawnAsteroid(GameData gameData) {
        Random rnd = new Random();

        int size = rnd.nextInt(20) + 5;
        int w = gameData.getDisplayWidth();
        int h = gameData.getDisplayHeight();
        int spread = 35;

        Asteroid a = new Asteroid();
        a.setPolygonCoordinates(rockPolygon(size));
        a.setRadius(size);
        a.setSpeed(size);

        int side = rnd.nextInt(4);
        switch (side) {
            case 0: // top - down
                a.setX(rnd.nextInt(w)); 
                a.setY(0); 
                a.setRotation(randAngle(rnd, 180, spread));
                break;
            case 1: // right - left
                a.setX(w); 
                a.setY(rnd.nextInt(h)); 
                a.setRotation(randAngle(rnd, 270, spread));
                break;
            case 2: // bottom - up
                a.setX(rnd.nextInt(w)); 
                a.setY(h); 
                a.setRotation(randAngle(rnd,  90, spread));
                break; 
            default: // left - right
                a.setX(0); 
                a.setY(rnd.nextInt(h)); 
                a.setRotation(randAngle(rnd, 0, spread));
                break;
        }

        return a;
    }

    private static int randAngle(Random rnd, int base, int spread) {
        return (base + rnd.nextInt(spread * 2 + 1) - spread + 360) % 360;
    }
}