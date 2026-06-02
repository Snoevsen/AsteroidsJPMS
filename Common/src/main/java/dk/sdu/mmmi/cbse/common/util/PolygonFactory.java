package dk.sdu.mmmi.cbse.common.util;

import java.util.Random;

public final class PolygonFactory {
    private PolygonFactory() {}

    public static double[] rockPolygon(double baseRadius) {
        Random rnd = new Random();

        int corners = rnd.nextInt(10, 17);
        double bumpyness = rnd.nextDouble(0.18, 0.35);
        double baseRotation = rnd.nextDouble(0, 360);

        double[] coords = new double[corners * 2];

        double rotation = Math.toRadians(baseRotation);
        double step = 2.0 * Math.PI / corners;

        double[] r = new double[corners];
        for (int i = 0; i < corners; i++) {
            double factor = 1.0 + (rnd.nextDouble() * 2 - 1) * bumpyness;
            r[i] = baseRadius * factor;
        }

        for (int i = 0; i < corners; i++) {
            double prev = r[(i - 1 + corners) % corners];
            double next = r[(i + 1) % corners];
            r[i] = (prev + r[i] + next) / 3.0;
        }

        for (int i = 0; i < corners; i++) {
            double a = rotation + i * step;
            coords[i * 2] = Math.cos(a) * r[i];
            coords[i * 2 + 1] = Math.sin(a) * r[i];
        }

        return coords;
    }
}