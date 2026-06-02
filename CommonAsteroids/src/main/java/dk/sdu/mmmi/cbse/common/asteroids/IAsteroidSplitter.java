package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Service Provider Interface (SPI) for handling the splitting mechanic of asteroid entities
 * when they are destroyed or damaged.
 */
public interface IAsteroidSplitter {

    /**
     * Creates smaller split asteroid entities based on the state of a parent asteroid
     * and adds them to the game world.
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>The parent {@code entity} must be a valid, existing asteroid entity.</li>
     * <li>The {@code world} must contain valid, initialized values.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>New, smaller asteroid entities are instantiated and added to the {@code world}
     * at the position of the original parent entity.</li>
     * </ul>
     *
     * @param e the parent asteroid entity being split
     * @param w the game world where the new split asteroids will be added
     */
    void createSplitAsteroid(Entity e, World w);
}