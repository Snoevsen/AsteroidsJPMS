package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Service interface for managing the lifecycle of game plugins (e.g., adding or removing components).
 */
public interface IGamePluginService {

    /**
     * Initializes and starts the plugin, injecting its assets or entities into the game.
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>{@code gameData} and {@code world} must contain valid, initialized values.</li>
     * <li>The plugin must not already be running.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>The plugin is actively running and integrated into the game loop.</li>
     * </ul>
     *
     * @param gameData the current state and configuration data of the game
     * @param world    the game world where plugin entities will be added
     */
    void start(GameData gameData, World world);

    /**
     * Stops the plugin and cleans up any components or entities it introduced.
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     * <li>{@code gameData} and {@code world} must contain valid, initialized values.</li>
     * <li>The plugin must currently be running.</li>
     * </ul>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     * <li>All relevant plugin entities and data are removed from {@code gameData} and {@code world}.</li>
     * <li>The plugin no longer affects the running game.</li>
     * </ul>
     *
     * @param gameData the current state and configuration data of the game
     * @param world    the game world from which plugin entities will be removed
     */
    void stop(GameData gameData, World world);
}