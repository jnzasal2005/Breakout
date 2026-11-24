package breakout.controller;

import javax.swing.Timer;
import breakout.*;
import breakout.model.GameModel;
import breakout.model.GameState;
import breakout.view.BreakoutCanvas;
import breakout.model.Ball;
import breakout.model.Brick;
import breakout.model.Paddle;

public class GameEngine {

    // attributes
    private Timer timer;
    private GameModel gameModel;
    private BreakoutCanvas breakoutCanvas;

    // constructor
    public GameEngine (GameModel gameModel, BreakoutCanvas breakoutCanvas) {
        this.gameModel = gameModel;
        this.breakoutCanvas = breakoutCanvas;
        this.timer = new Timer(16, e -> gameLoop());
    }

    //methods

    public void start () {
        timer.start();
    }

    private void gameLoop() {
        if (gameModel.getGameState() != GameState.RUNNING) {
            return ;   
        }

    moveBall();
    checkWallCollision();
    checkPaddleCollision();
    checkBrickCollision();
    checkBottomCollision();
    checkGameStatus();
    redraw();
    }
    

    public void moveBall () {
        this.gameModel.getBall().move();
    }

    public void checkWallCollision (){
        //right wall
        if (gameModel.getBall().getX() + gameModel.getBall().getWidth() >= 800){
            gameModel.getBall().setVx(- gameModel.getBall().getVx());
            gameModel.getBall().setX(800 - gameModel.getBall().getWidth());
        }

        // left wall
        if (gameModel.getBall().getX() <= 0){
            gameModel.getBall().setVx(- gameModel.getBall().getVx());
            gameModel.getBall().setX(0);
        }
        // top wall
        if (gameModel.getBall().getY() <= 0){
            gameModel.getBall().setVy(- gameModel.getBall().getVy());
            gameModel.getBall().setY(0);
        }
    }


    public void checkPaddleCollision() {

    Ball ball = gameModel.getBall();
    Paddle paddle = gameModel.getPaddle();

    // check for collision
    if (ball.getX() + ball.getWidth() > paddle.getX() &&
        ball.getX() < paddle.getX() + paddle.getWidth() &&
        ball.getY() + ball.getHeight() > paddle.getY() &&
        ball.getY() < paddle.getY() + paddle.getHeight() &&
        ball.getVy() > 0) {

        // Calculate horizontal offset and half-width
        double paddleCenter = paddle.getX() + paddle.getWidth() / 2.0;
        double ballCenter = ball.getX() + ball.getWidth() / 2.0;
        double deltaX = ballCenter - paddleCenter;
        double halfWidth = paddle.getWidth() / 2.0;

        double offset = deltaX / halfWidth;
        offset = Math.max(-1.0, Math.min(1.0, offset));

        // Calculate bounce angle and preserve total velocity
        double angle = offset * Math.toRadians(60.0);
        double speed = Math.hypot(ball.getVx(), ball.getVy());

        // Apply new velocity components (always deflect upwards)
        ball.setVx(speed * Math.sin(angle));
        ball.setVy(-Math.abs(speed * Math.cos(angle)));

        // reposition ball above the paddle
        ball.setY(paddle.getY() - ball.getHeight());
    }
}


        // collision occure when ..
        //ballRight > brickLeft &
        // ballLeft < brickRight &
        // ballBottom > brickTop & 
        // ballTop < brickBottom
        
    public void checkBrickCollision() {

        Ball ball = gameModel.getBall();

        for (int i = 0; i < gameModel.getBricks().size(); i++) {

            Brick brick = gameModel.getBricks().get(i);

            // check if we have collision with an exsisting brick...
            if ((ball.getX() + ball.getWidth() > brick.getX()) &&
                (ball.getX() < brick.getX() + brick.getWidth()) &&                                               
                (ball.getY() < brick.getY() + brick.getHeight()) &&
                (ball.getY() + ball.getHeight() > brick.getY()) &&
                (brick.isDestroyed() == false)) {

                double overlapX = Math.min(ball.getX() + ball.getWidth(), brick.getX() + brick.getWidth())
                - Math.max(ball.getX(), brick.getX());

                double overlapY = Math.min(ball.getY() + ball.getHeight(), brick.getY() + brick.getHeight())
                - Math.max(ball.getY(), brick.getY());

                if (overlapX > overlapY) {

                    // vertical collision
                    if (ball.getVy() > 0) {
                        ball.setY(brick.getY() - ball.getHeight());
                    }
                    else {
                        ball.setY(brick.getY() + brick.getHeight());
                    }

                    ball.setVy(-ball.getVy());
                    }

                    else {

                    // horizontal collision
                    if (ball.getVx() > 0) {
                        ball.setX(brick.getX() - ball.getWidth());
                    }
                    else {
                        ball.setX(brick.getX() + brick.getWidth());
                    }

                    ball.setVx(-ball.getVx());
                }

                // destroy the brick
                brick.destroy();
                gameModel.addScore(100);
                // check if victory condition is satisfied...
            if (gameModel.getRemainingBricks() == 0) {
            gameModel.setGameState(GameState.VICTORY);
            }
        }
     }
    }
    public void checkBottomCollision() {

        Ball ball = gameModel.getBall();

        if (ball.getY() + ball.getHeight() >= 600) {

            gameModel.loseLife();

            if (gameModel.getLives() > 0) {
                ball.reset();
                gameModel.setGameState(GameState.READY);
            }
            else {
                gameModel.setGameState(GameState.GAME_OVER);
            }
        }
    }

    private void checkGameStatus() {

    GameState state = gameModel.getGameState();

    if (state == GameState.GAME_OVER || state == GameState.VICTORY) {
        timer.stop();
    }
}

private void redraw() {
    breakoutCanvas.removeAll();
    breakoutCanvas.drawGame();
}


}



