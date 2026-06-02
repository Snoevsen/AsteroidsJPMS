package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Service Provider Interface (SPI) for creating bullet entities within the game.
 */
public interface BulletSPI {

    /**
     * Instantiates a new bullet entity originating from a specific shooter entity
     * (e.g., a player ship or an enemy).
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>The shooter {@code entity} must be valid and possess a position/orientation
     * to determine the bullet's spawn point and trajectory.</li>
     * <li>The {@code gameData} must contain valid, initialized values.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>A new bullet {@link Entity} is created and configured, ready to be added
     * to the game world.</li>
     * </ul>
     *
     * @param e the origin entity firing the bullet (used for position and direction)
     * @param gameData the current state and configuration data of the game
     * @return a newly created bullet {@link Entity}
     */
    Entity createBullet(Entity e, GameData gameData);
}