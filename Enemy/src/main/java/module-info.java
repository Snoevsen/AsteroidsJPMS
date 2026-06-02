import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Enemy {
    exports dk.sdu.mmmi.cbse.ships;
    requires Common;
    requires CommonBullet;   
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.cbse.ships.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.ships.EnemyControlSystem;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.ships.EnemySpawner;
}
