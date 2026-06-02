package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameEvents;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world, GameEvents gameEvents);
        /*
        Preconditions:
            gameData and world contains values, so the method can fire
            Game/project is running
            Objects/entities are present
        
        Postconditions:
            Objects/entities have been affected
            Game is still running
        */
}
