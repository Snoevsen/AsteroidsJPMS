package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Service interface for processing game entities during the main game loop.
 */
public interface IEntityProcessingService {

    /**
     * Processes game entities and updates their state based on the current game data and events.
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>{@code gameData} and {@code world} must contain valid, initialized values.</li>
     * <li>The game project must be running.</li>
     * <li>Objects or entities must be present in the world.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>Entities in the world have been processed and updated.</li>
     * <li>The game continues to run.</li>
     * </ul>
     *
     * @param gameData   the current state and configuration data of the game
     * @param world      the game world containing all current entities
     * @param gameEvents the events triggered during this frame or game tick
     */
    void process(GameData gameData, World world, GameEvents gameEvents);
}