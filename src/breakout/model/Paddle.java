package breakout.model;

public class Paddle {

    // attributes:
    private double x;
    private double y;

    private int width = 100;
    private int height  = 15;
    
    private double speed;

    //constuctor
    public Paddle (double x, double y , double speed) {
        this.x = x;
        this.y = y;

        this.speed = speed;
    }

    //methods
    
    public void moveLeft() {
        this.x = Math.max(0, this.x - this.speed);
    }

    public void moveRight() {
        this.x = Math.min(800 - this.width, this.x + this.speed);
    }

    // place the paddle in the mddle at first
    public void reset () {
        this.x = 350;
        this.y = 545;
    }
    
   public double getX() {
        return this.x;
   }
      
   public double getY() {
        return this.y;
   }

      public double getWidth() {
        return this.width;
   }

      public double getHeight() {
        return this.height;
   }
}

