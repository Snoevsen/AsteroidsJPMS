package dk.sdu.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.Random;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        
        for (Entity enemy : world.getEntities(Enemy.class)) {
            Random rnd = new Random();
            int mvmnt = rnd.nextInt(10)+1;
            switch (mvmnt % 2){
                case 0:
                    double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                    double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                    enemy.setX(enemy.getX() + changeX);
                    enemy.setY(enemy.getY() + changeY);
                    break;
                
                case 1: break;
            }

            switch (mvmnt % 3){
                case 0:
                    enemy.setRotation(enemy.getRotation() - 4);
                    break;
                
                case 1: 
                    enemy.setRotation(enemy.getRotation() + 4);
                    break;

                case 2: break;
            }

            if (enemy.getShootingCooldown() > 0) {
                enemy.setShootingCooldown(enemy.getShootingCooldown() - 1);
            } 
            else {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> 
                    { world.addEntity(spi.createBullet(enemy, gameData)); });

                enemy.setShootingCooldown(20); 
            }
            
            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }                         
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
