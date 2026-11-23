package breakout.model;

public class Brick {

    //attributes:

    private double x;
    private double y;

    private int width;
    private int height;

    private boolean destroyed;

    // constructor
    public Brick (double x, double y , int width, int height ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
    }
    // methods

    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed (){
        return this.destroyed;
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


}
