package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private int lives;
    
    private int shootingCooldown = 0;            

    private double dx;
    private double dy;
    
    public int getLives() { return lives; }
    public double getDx() { return dx; }
    public double getDy() { return dy; }
    public void setLives(int amount) { this.lives = amount; }
    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public String getID() {
        return ID.toString();
    }

    public int getShootingCooldown() {
        return shootingCooldown;
    }

    public void setShootingCooldown(int shootingCooldown) {
        this.shootingCooldown = shootingCooldown;
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       
    public void lifeDecrease() {
        if (lives > 0) lives--;
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
    
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }
}
