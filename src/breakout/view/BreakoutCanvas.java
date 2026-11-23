package breakout.view;

import java.awt.Color;

import acm.graphics.*;
import breakout.model.GameModel;
import breakout.model.GameState;

public class BreakoutCanvas extends GCanvas {

    //attributes
    private GameModel gameModel;

    // constructor
    public BreakoutCanvas(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    // methods

    public void drawBall () {

        GOval ballGraphic = new GOval (
            gameModel.getBall().getX() , 
            gameModel.getBall().getY() , 
            gameModel.getBall().getWidth() , 
            gameModel.getBall().getHeight()
        );   

        ballGraphic.setFilled(true);
        add (ballGraphic);
    }

    public void drawPaddle () {
        
        GRect paddleGraphic = new GRect(
            gameModel.getPaddle().getX(),
            gameModel.getPaddle().getY(),
            gameModel.getPaddle().getWidth(),
            gameModel.getPaddle().getHeight()
        );

        paddleGraphic.setFilled(true);
        add (paddleGraphic);

    }

    public void drawBricks() {

        for (int i = 0; i < gameModel.getBricks().size(); i++) {

            if (!gameModel.getBricks().get(i).isDestroyed()) {

                GRect brickGraphic = new GRect(
                    gameModel.getBricks().get(i).getX(),
                    gameModel.getBricks().get(i).getY(),
                    gameModel.getBricks().get(i).getWidth(),
                    gameModel.getBricks().get(i).getHeight()
                );

                brickGraphic.setFilled(true);
                add(brickGraphic);
            }
        }
    }

    public void drawStatusBar() {
        GRect headGraphic = new GRect (0, 0, 800, 35);
        headGraphic.setColor(Color.BLACK);
        headGraphic.setFilled(true);
        headGraphic.setFillColor(Color.LIGHT_GRAY);
        add(headGraphic);

        GLabel scoreLabel = new GLabel("Score: " + gameModel.getScore(), 20, 25);
        scoreLabel.setColor(Color.BLACK);
        add(scoreLabel);

        GLabel livesLabel = new GLabel("Lives: " + gameModel.getLives(), 650, 25);
        livesLabel.setColor(Color.BLACK);
        add(livesLabel);
}

    public void drawGameState() {

        if (gameModel.getGameState() == GameState.READY) {
            GLabel gameStateGraphic = new GLabel("READY TO PLAY!", 300, 320);
            add(gameStateGraphic);
        }
        
        if (gameModel.getGameState() == GameState.PAUSED) {
            GLabel gameStateGraphic = new GLabel("PAUSED", 300, 320);
            add(gameStateGraphic);
        }

        if (gameModel.getGameState() == GameState.GAME_OVER) {
            GLabel gameStateGraphic = new GLabel("GAME OVER!", 300, 320);
            add(gameStateGraphic);

        }

        if (gameModel.getGameState() == GameState.VICTORY) {
            GLabel gameStateGraphic = new GLabel("YOU WON!", 300, 320);
            add(gameStateGraphic);
        }
    }

    public void drawGame() {
        drawBricks();
        drawPaddle();
        drawBall();
        drawStatusBar();
        drawGameState();
    }
}
    







