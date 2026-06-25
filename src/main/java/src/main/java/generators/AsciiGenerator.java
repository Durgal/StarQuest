package src.main.java.generators;

import src.main.java.utilities.Math;

import java.util.ArrayList;
import java.util.Random;

public class AsciiGenerator {
    private static final char[] PALETTE = {'.', ',', 'o', 'O', '@'};
    private static final int OCTAVES = 5;
    private static final double PERSISTENCE = 0.5;
    private static final double SCALE = 0.2;
    private static final Random rand = Math.getRandom();

    public static void drawAsciiPlanet(int numContinents) {
        String texture = generateAsciiPlanetTexture(80,40,numContinents);

        String[] t = texture.split("\n");
        int mW = t[0].length() - 1;
        int mH = t.length - 1;

        int SphR = 8;
        int tableSize = 1000;
        double[] sin = new double[tableSize];
        double[] cos = new double[tableSize];
        for (int i = 0; i < tableSize; i++) {
            sin[i] = java.lang.Math.sin(i * 0.006283185307179);
            cos[i] = java.lang.Math.cos(i * 0.006283185307179);
        }

        class Star { int x, y, s; }
        Star[] stars = new Star[10];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
            stars[i].x = rand.nextInt(79) + 1;
            stars[i].y = rand.nextInt(39) + 1;
            stars[i].s = rand.nextInt(4) + 1;
        }

        double aA = 0; // rotation angle
        double rotationSpeed = 1.0; // radians per second (tune this)

        long lastTime = System.nanoTime();

        // Animation loop
        while (true) {
            // --- Time step ---
            long now = System.nanoTime();
            double delta = (now - lastTime) / 1e9; // elapsed seconds
            lastTime = now;

            aA += rotationSpeed * delta; // angle based on real time
            if (aA > java.lang.Math.PI * 2) aA -= java.lang.Math.PI * 2;

            // --- Draw frame ---
            char[] oA = new char[3520];
            for (int i = 0; i < oA.length; i++) oA[i] = ' ';

            // Stars
            for (Star str : stars) {
                str.x = (str.x - str.s) % 80;
                if (str.x < 0) str.x += 80;
                oA[str.x + 80 * str.y] = 'o';
            }

            double aY = 0;
            while (aY < 6.28) {
                double aX = 0;
                while (aX < 6.28) {
                    double x = SphR * sin[(int)((aY % 6.28) * 159.15) % tableSize] *
                            cos[(int)((aX % 6.28) * 159.15) % tableSize];
                    double y = SphR * sin[(int)((aY % 6.28) * 159.15) % tableSize] *
                            sin[(int)((aX % 6.28) * 159.15) % tableSize];
                    double z = SphR * cos[(int)((aY % 6.28) * 159.15) % tableSize];

                    double SaX = java.lang.Math.atan2(x, z);
                    double SaY = java.lang.Math.atan2(-y, java.lang.Math.sqrt(x * x + z * z));
                    int Co = (int)((SaX / (2 * java.lang.Math.PI) + 0.5) * mW);
                    int Ro = (int)(((SaY / java.lang.Math.PI) + 0.5) * mH);

                    char aC = ' ';
                    if (Ro >= 0 && Ro < mH - 1 && Co >= 0 && Co < mW - 1) {
                        aC = t[Ro].charAt(Co);
                    }

                    double xRX = x * cos[(int)((aA % 6.28) * 159.15) % tableSize] +
                            z * sin[(int)((aA % 6.28) * 159.15) % tableSize];
                    double zRX = -x * sin[(int)((aA % 6.28) * 159.15) % tableSize] +
                            z * cos[(int)((aA % 6.28) * 159.15) % tableSize];
                    x = xRX;
                    z = zRX;

                    double iD = 1.0 / 10;
                    int sX = (int)(40 + 30 * iD * x);
                    int sY = (int)(20 - 15 * iD * y);
                    if (z > 0 && sX >= 0 && sX < 80 && sY >= 0 && sY < 44) {
                        oA[sX + 80 * sY] = aC;
                    }
                    aX += 0.065;
                }
                aY += 0.065;
            }

            // --- Render to console ---
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(new String(oA));
        }
    }

    public static String generateAsciiPlanetTexture(int width, int height, int numContinents) {
        // Smaller minDist to allow closer seeds > smaller continents
        double minDist = java.lang.Math.min(width, height) / 6.0;
        ArrayList<double[]> centers = new ArrayList<>();

        while (centers.size() < numContinents) {
            double cx = rand.nextDouble() * width;
            double cy = rand.nextDouble() * height;
            boolean tooClose = false;
            for (double[] c : centers) {
                if (java.lang.Math.hypot(cx - c[0], cy - c[1]) < minDist) {
                    tooClose = true;
                    break;
                }
            }
            if (!tooClose) {
                centers.add(new double[]{cx, cy});
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Voronoi mask: distance to nearest continent center
                double minDistToCenter = Double.MAX_VALUE;
                for (double[] c : centers) {
                    double dx = x - c[0];
                    double dy = y - c[1];
                    double d = java.lang.Math.sqrt(dx*dx + dy*dy);
                    if (d < minDistToCenter) minDistToCenter = d;
                }
                double continentMask = java.lang.Math.max(0, 1 - minDistToCenter / (java.lang.Math.min(width, height) / 3.0));

                // Add fBm terrain variation
                double nx = x * SCALE;
                double ny = y * SCALE;
                double noiseValue = fbm(nx, ny, OCTAVES, PERSISTENCE);
                double value = 0.4 * continentMask + 0.6 * noiseValue;

                // Apply edge fade for natural ocean borders
                double edgeX = java.lang.Math.min(x, width - 1 - x) / (double)(width / 6.0);
                double edgeY = java.lang.Math.min(y, height - 1 - y) / (double)(height / 6.0);
                double edgeMask = java.lang.Math.min(1.0, java.lang.Math.min(edgeX, edgeY));
                edgeMask = java.lang.Math.max(0, java.lang.Math.min(1, edgeMask)); // clamp 0..1
                value *= edgeMask;

                sb.append(heightToChar(value));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static double fbm(double x, double y, int octaves, double persistence) {
        double total = 0;
        double frequency = 1.0;
        double amplitude = 1.0;
        double maxValue = 0;
        for (int i = 0; i < octaves; i++) {
            total += valueNoise(x * frequency, y * frequency) * amplitude;
            maxValue += amplitude;
            amplitude *= persistence;
            frequency *= 2.0;
        }
        return total / maxValue;
    }

    private static double valueNoise(double x, double y) {
        int xi = (int) java.lang.Math.floor(x);
        int yi = (int) java.lang.Math.floor(y);
        double xf = x - xi;
        double yf = y - yi;

        double v00 = hash(xi, yi);
        double v10 = hash(xi+1, yi);
        double v01 = hash(xi, yi+1);
        double v11 = hash(xi+1, yi+1);

        double i1 = lerp(v00, v10, smoothstep(xf));
        double i2 = lerp(v01, v11, smoothstep(xf));
        return lerp(i1, i2, smoothstep(yf));
    }

    private static double hash(int x, int y) {
        int n = x * 374761393 + y * 668265263;
        n = (n ^ (n >> 13)) * 1274126177;
        return ((n ^ (n >> 16)) & 0x7fffffff) / (double) 0x7fffffff;
    }

    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private static double smoothstep(double t) {
        return t * t * (3 - 2 * t);
    }

    private static char heightToChar(double h) {
        if (h < 0.25) return PALETTE[0];
        else if (h < 0.45) return PALETTE[1];
        else if (h < 0.55) return PALETTE[2];
        else if (h < 0.70) return PALETTE[3];
        else return PALETTE[4];
    }
}
