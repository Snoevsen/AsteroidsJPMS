package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Service interface for handling operations that must occur after the primary entity processing phase
 * (e.g., collision resolution, cleanup, or removing dead entities).
 */
public interface IPostEntityProcessingService {

    /**
     * Performs post-processing logic on game entities after the main game tick.
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>{@code gameData} and {@code world} must contain valid, initialized values.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>Entities flagged for removal (or meeting specific post-processing criteria) have been handled or removed.</li>
     * </ul>
     *
     * @param gameData the current state and configuration data of the game
     * @param world the game world containing all current entities
     * @param gameEvents the events triggered during this frame or game tick
     */
    void process(GameData gameData, World world, GameEvents gameEvents);
}