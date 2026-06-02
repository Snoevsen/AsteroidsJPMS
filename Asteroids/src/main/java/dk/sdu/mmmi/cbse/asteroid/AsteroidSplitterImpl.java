package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.util.PolygonFactory;

import java.util.Random;

import static dk.sdu.mmmi.cbse.common.util.PolygonFactory.rockPolygon;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    
    @Override
    public void createSplitAsteroid(Entity e, World world) {
        float radius = e.getRadius();
        if (radius <= 5) return;

        Random rnd = new Random();
        float newRadius = radius / 2f;

        int asteroidAmount = rnd.nextInt(1, (int) newRadius);

        double rotation = e.getRotation();
        double totalSpread = Math.min(160, 40 + asteroidAmount * 15);
        double startAngle = rotation - totalSpread / 2.0;
        double step = (asteroidAmount == 1) ? 0 : (totalSpread / (asteroidAmount - 1));
        double spawnDist = radius + newRadius + 1;

        for (int i = 0; i < asteroidAmount; i++) {
            double angle = startAngle + i * step;
            angle += rnd.nextDouble(-5, 5);
            double rad = Math.toRadians(angle);

            Asteroid split = new Asteroid();
            split.setPolygonCoordinates(rockPolygon(newRadius));
            split.setRadius(newRadius);
            split.setX(e.getX() + Math.cos(rad) * spawnDist);
            split.setY(e.getY() + Math.sin(rad) * spawnDist);
            split.setRotation((angle + 360) % 360);
            split.setSpeed(newRadius);
            world.addEntity(split);
        }
    } 
}