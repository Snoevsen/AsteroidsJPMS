package dk.sdu.mmmi.cbse.common.data;

public class GameEvents {
    private boolean playerAlive = true;
    private boolean playerHit;
    private boolean gameOver;

    private Entity entity1;
    private Entity entity2;


    public void setCollisionEntities(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }
    
    public void clearCollisionEntity0() {
        this.entity1 = null;
    }
    
    public void clearCollisionEntity1() {
        this.entity2 = null;
    }

    public Entity[] getCollisionEntities(){

        Entity[] list = new Entity[2];
        list[0] = entity1;
        list[1] = entity2;
        
        return list;
    }

    public boolean getPlayerAlive() {
        return playerAlive;
    }

    public void setPlayerAlive(boolean playerAlive) {
        this.playerAlive = playerAlive;
    }

    public void setPlayerHit(boolean playerHit){
        this.playerHit = playerHit;
    }
    
    public boolean getPlayerHit(){
        return playerHit;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
