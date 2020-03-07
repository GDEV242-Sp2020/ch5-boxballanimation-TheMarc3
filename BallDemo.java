import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @previous author Bill Crosbie
 * @version 2015-March-BB
 *
 * @original author Michael Kölling and David J. Barnes
 *
 * @author Marc Weitze
 * @version 3/1/2020
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }
    
    /**
     * Create a BoxBounce object that creates an array of balls
     * that can bounce; the balls are created with random positions
     * and a random size between 10-25 pixels
     * @param ballCount can have between 5 and 30 balls
     */
    public void boxBounce(int ballCount)
    {
        myCanvas.setVisible(true);
        BoxBounce ballArray[];
        Random rand = new Random();
        int numBalls;
        if(ballCount <= 5)
        {
            numBalls = 5;
        }
        else if(ballCount >= 30)
        {
            numBalls = 30;
        }
        else
        {
            numBalls = ballCount;
        }
        
        ballArray = new BoxBounce[(numBalls - 1)];
        
        for(int i = 0; i < ballArray.length; i++)
        {
            int xPos = rand.nextInt(getAxisLength('x'));
            int yPos = rand.nextInt(getAxisLength('y'));
            
            int diam = rand.nextInt(26);
            while(diam > 25 || diam < 10)
            {
                diam = rand.nextInt(26);
            }
            ballArray[i] = new BoxBounce(xPos, yPos, diam, Color.cyan,
                    myCanvas, 7);
        }
        boolean finished = false;
        while(!finished)
        {
            myCanvas.wait(50);
            for(int j = 0; j < ballArray.length; j++)
            {
                ballArray[j].move();
            }
        }
    }
    
    /**
     * Returns an int value for the length of the walls based on the param
     * @param axis takes a char of values 'x' 'y' and 'z' to return the values
     *      for the corresponding wall length ('z' is for the difference)
     */
    private int getAxisLength(char axis)
    {
        int numReturn = 0;
        BoxBounce myBoxBounce = new BoxBounce(0, 0, 0, Color.white,
                myCanvas, 0);
        int X = (myBoxBounce.getWall("right") - myBoxBounce.getWall("left"));
        int Y = (myBoxBounce.getWall("bottom") - myBoxBounce.getWall("top"));
        if(axis == 'x')
        {
            numReturn = X;
        }
        else if(axis == 'y')
        {
            numReturn = Y;
        }
        else if(axis == 'z')
        {
            if(X > Y)
            {
                numReturn = (X - Y);
            }
            else
                numReturn = (Y - X);
        }
        return numReturn;
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE,
            ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED,
            ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
