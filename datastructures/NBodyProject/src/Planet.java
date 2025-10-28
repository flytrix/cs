public class Planet {

    double myXPos;
    double myYPos;
    double myXVel;
    double myYVel;
    double myMass;
    String myFileName;

    public Planet(double xp, double yp, double xv, double yv, double mass, String filename) {
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    public Planet(Planet p) {
        myXPos = p.myXPos;
        myYPos = p.myYPos;
        myXVel = p.myXVel;
        myYVel = p.myYVel;
        myMass = p.myMass;
        myFileName = p.myFileName;
    }

    public double calcDistance(Planet p) {
        double xdifference = p.myXPos - this.myXPos;
        double ydifference = p.myYPos - this.myYPos;
        double distance = Math.sqrt((xdifference * xdifference) + (ydifference * ydifference));
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double gravitationalConstant = 6.67 * 1e-11;
        double r = this.calcDistance(p);
        double force = (gravitationalConstant * this.myMass * p.myMass) / (r * r);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double dx = p.myXPos - this.myXPos;
        double r = this.calcDistance(p);
        double forceByX = (force * dx) / r;
        return forceByX;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double dy = p.myYPos - this.myYPos;
        double r = this.calcDistance(p);
        double forceByY = (force * dy) / r;
        return forceByY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                sum += calcForceExertedByX(p);
            }
        }
        return sum;

    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                sum += calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double seconds, double xforce, double yforce) {
        double xacceleration = xforce / this.myMass;
        double yacceleration = yforce / this.myMass;
        double xNewVelocity = myXVel + seconds * xacceleration;
        double yNewVelocity = myYVel + seconds * yacceleration;
        double xNewPosition = myXPos + seconds * xNewVelocity;
        double yNewPosition = myYPos + seconds * yNewVelocity;
        myXPos = xNewPosition;
        myYPos = yNewPosition;
        myXVel = xNewVelocity;
        myYVel = yNewVelocity;
    }


    public void draw() {

        StdDraw.picture(myXPos, myYPos, "./images/" + myFileName);
    }


}

