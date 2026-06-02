package dk.sdu.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;


import java.util.Random;


public class EnemySpawner implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        int enemyCount = world.getEntities(Enemy.class).size(); 
        
        if (enemyCount < 5) {
            Entity enemy = spawnEnemy(gameData);
            world.addEntity(enemy);
        }
    }

    public Entity spawnEnemy(GameData gameData) {
        Random rnd = new Random();
        
        Enemy enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setRadius(8);
        enemyShip.setLives(3);

        int side = rnd.nextInt(4);
        switch (side) {
            case 0: //top
                enemyShip.setX(rnd.nextInt(gameData.getDisplayWidth()));
                enemyShip.setY(0);
                break;
            case 1: //right side
                enemyShip.setX(gameData.getDisplayWidth());
                enemyShip.setY(rnd.nextInt(gameData.getDisplayHeight()));
                break;
            case 2: //bottom
                enemyShip.setX(rnd.nextInt(gameData.getDisplayWidth()));
                enemyShip.setY(gameData.getDisplayHeight());
                break;
            case 3: //left side
                enemyShip.setX(0);
                enemyShip.setY(rnd.nextInt(gameData.getDisplayHeight()));
                break;
        }

        return enemyShip;
    }
}