package breakout.model;

import java.util.ArrayList;

public class GameModel {

    // attributes
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;

    private int score;
    private int lives;
    private GameState gamestate; 

    // constructor
    public GameModel (){

        // building the paddle
        this.paddle = new Paddle(350, 545, 8);

        // building the ball
        this.ball = new Ball(392, 529, 0, 0);

        // building 40 bricks
        initializeBricks();

        // initializa the score
        this.score = 0;

        // initializa the lives
        this.lives = 3;

        // set the game state READY
        this.gamestate = GameState.READY;
        
    }

    // methods
    private void initializeBricks() {
        
        bricks = new ArrayList<>();
        // x=39 + c*91
        // 60 + r * 31
       for (int r = 0; r < 5; r++) {

            for (int c = 0; c < 8; c++) {

                double x = 39 + c * 91;
                double y = 60 + r * 31;

                Brick brick = new Brick(x, y, 85, 25);
                bricks.add(brick);
            }
        }
    }

    public void addScore (int score) {
        this.score = this.score + score;
    }

    public void loseLife () {
        this.lives = this.lives - 1;
    }
    
    public int getRemainingBricks() {
        int total = 0;
        for (int i=0 ; i < bricks.size() ; i++) {
            if (this.bricks.get(i).isDestroyed() == false) {
                total++;
            }
        }
        return total;
    }

    public void reset () {
        this.ball.reset();
        this.paddle.reset();
        this.initializeBricks();
        this.score = 0;
        this.lives = 3;
        this.gamestate = GameState.READY;
    }

    public void setGameState (GameState gamestate) {
        this.gamestate =  gamestate;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Paddle getPaddle(){
        return this.paddle;
    }

    public ArrayList<Brick> getBricks() {
        return this.bricks;

    }
    public int getScore() {
        return this.score;
    }
    public int getLives (){
        return this.lives;
    } 

    public GameState getGameState() {
        return this.gamestate;
    }

}
