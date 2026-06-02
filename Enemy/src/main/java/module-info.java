import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Enemy {
    exports dk.sdu.cbse.enemysystem;
    requires Common;
    requires CommonBullet;   
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemysystem.EnemyControlSystem;
    provides IPostEntityProcessingService with dk.sdu.cbse.enemysystem.EnemySpawner;
}
