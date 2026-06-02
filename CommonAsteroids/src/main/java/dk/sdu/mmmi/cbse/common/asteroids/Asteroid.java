package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.util.Random;

public class Asteroid extends Entity {
    private double speed;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double size) {
        if (size > 20) {
            this.speed = 0.4;
        }
        else if (size > 15) {
            this.speed = 0.8;
        }
        else if (size > 10) {
            this.speed = 1.2;
        }
        else {
            this.speed = 1.6;
        }
    }
}