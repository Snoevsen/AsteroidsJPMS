package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.ships.Player;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world, GameEvents gameEvents) {
        List<Entity> all = new ArrayList<>(world.getEntities());

        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                Entity e1 = all.get(i);
                Entity e2 = all.get(j);

                if (e1.getID().equals(e2.getID()) || e1.getClass() == e2.getClass()) {
                    continue;
                }

                if (collides(e1, e2)) {
                    gameEvents.setCollisionEntities(e1, e2);
                    if((e1 instanceof Player || e2 instanceof Player) && (e1 instanceof Asteroid || e2 instanceof Asteroid)) {
                        gameEvents.setPlayerAlive(false);
                    }
                    return;
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
