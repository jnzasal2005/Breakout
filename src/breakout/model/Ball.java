package breakout.model;

public class Ball {

    //attributes:

    private double x;
    private double y;

    private int width;
    private int height;

    private double vx;
    private double vy;

    //constructor 
    public Ball (double x , double y , double vx , double vy ){
        this.vx = vx;
        this.vy = vy;

        this.x = x;
        this.y = y;

        this.width = 16;
        this.height = 16;
    }
    
    // methods:
    public void move() {
        x += vx;
        y += vy;
    }

    public void reset() {
        x = 392;
        y = 529;
        vx = 0;
        vy = 0;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public double getVx() {
        return this.vx;
    }

    public double getVy() {
        return this.vy;
    }
}
