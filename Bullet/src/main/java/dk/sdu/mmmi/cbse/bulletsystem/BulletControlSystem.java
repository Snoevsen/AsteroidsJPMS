package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.ships.Enemy;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.ScoreServiceClient;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    private final ScoreServiceClient scoreClient = new ScoreServiceClient("http://localhost:8080");

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        Entity[] e = gameEvents.getCollisionEntities();
        Entity a = e[0], b = e[1];

        if (a instanceof Bullet && b instanceof Enemy) {
            world.removeEntity(a);
            gameEvents.clearCollisionEntity0();

            b.lifeDecrease();
            if (b.getLives() == 0) {
                new Thread(() -> {
                    try { scoreClient.addPoints(3); }
                    catch (Exception ignored) {}
                }).start();
                world.removeEntity(b);
                gameEvents.clearCollisionEntity1();
            }
        } else if (b instanceof Bullet && a instanceof Enemy) {
            world.removeEntity(b);
            gameEvents.clearCollisionEntity1();

            a.lifeDecrease();
            if (a.getLives() == 0) {
                new Thread(() -> {
                    try { scoreClient.addPoints(3); }
                    catch (Exception ignored) {}
                }).start();
                world.removeEntity(a);
                gameEvents.clearCollisionEntity0();
            }
        }

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double rad = Math.toRadians(bullet.getRotation());
            bullet.setX(bullet.getX() + Math.cos(rad) * 3);
            bullet.setY(bullet.getY() + Math.sin(rad) * 3);

            double x = bullet.getX(), y = bullet.getY();
            int w = gameData.getDisplayWidth(), h = gameData.getDisplayHeight();

            if (x < 0 || x > w || y < 0 || y > h) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(5, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
