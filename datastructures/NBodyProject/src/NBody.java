import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

    public static double readRadius(String fname) {

        try {
            Scanner scan = new Scanner(new File(fname));
            scan.nextInt();
            double radius = scan.nextDouble();
            scan.close();
            return radius;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Planet[] readPlanets(String fname) {

        Planet[] planets;
        try {
            Scanner scan = new Scanner(new File(fname));
            int numPlanets = scan.nextInt();
            planets = new Planet[numPlanets];
            scan.next();

            for (int i = 0; i < planets.length; i++) {
                double xPos = scan.nextDouble();
                double yPos = scan.nextDouble();
                double xVel = scan.nextDouble();
                double yVel = scan.nextDouble();
                double mass = scan.nextDouble();
                String fileName = scan.next();

                Planet planet = new Planet(xPos, yPos, xVel, yVel, mass, fileName);

                planets[i] = planet;

            }
            return planets;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        double totalTime = 157788000.0;
        double dt = 25000.0;
        String pfile = "./data/planets.txt";
        double radius = readRadius(pfile);
        StdDraw.setScale(-radius, radius);

        double time;
        Planet[] planets = readPlanets(pfile);

        for (time = 0; time < totalTime; time += dt) {

            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {

                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Planet planet : planets) {
                StdDraw.picture(planet.myXPos, planet.myYPos, "./images/" + planet.myFileName);
            }

            StdDraw.show(10);
            time += dt;
        }

        readRadius(pfile);
        readPlanets(pfile);

        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", radius);

        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].myXPos, planets[i].myYPos,
                    planets[i].myXVel, planets[i].myYVel,
                    planets[i].myMass, planets[i].myFileName);
        }


    }
}