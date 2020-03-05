import java.awt.*;
import java.awt.geom.*;

/**
 * Write a description of class BoxBounce here.
 *
 * @author Marc Weitze
 * @version 3/2/2020
 */
public class BoxBounce
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    //private final int groundPosition;      // y position of ground
    private int leftWall = 0;
    private int topWall = 0;
    private int rightWall = 600;
    private int bottomWall = 500;
    
    private Canvas canvas;
    private int ySpeed;                // initial downward speed
    private int xSpeed;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground
     *      (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     * @param speed 
     */
    public BoxBounce(int xPos, int yPos, int ballDiameter, Color ballColor,
                        Canvas drawingCanvas, int speed)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
        xSpeed = speed;
        ySpeed = speed;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     * This will also draw the walls.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
        //below are what draws the lines:
        canvas.drawLine(leftWall, topWall, rightWall, topWall);//top
        canvas.drawLine(leftWall, bottomWall, rightWall, bottomWall);//bottom
        canvas.drawLine(leftWall, topWall, leftWall, bottomWall);//left
        canvas.drawLine(rightWall, bottomWall, rightWall, bottomWall);//right
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        //ySpeed += GRAVITY;
        yPosition = yPosition + ySpeed;
        xPosition = xPosition + xSpeed;
        
        // check if it has hit the walls
        if(xPosition > rightWall)
        {
            xPosition = rightWall;
            xSpeed = -xSpeed;
        }
        
        if(yPosition > bottomWall)
        {
            yPosition = bottomWall;
            ySpeed = -ySpeed;
        }
        
        if(xPosition < leftWall)
        {
            xPosition = leftWall;
            xSpeed = -xSpeed;
        }
        
        if(yPosition < topWall)
        {
            yPosition = topWall;
            ySpeed = -ySpeed;
        }
        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
    
    public int getWall(String wall)
    {
        int wallPosition = 0;
        switch(wall)
        {
            case "left":
                return leftWall;
            case "right":
                return rightWall;
            case "top":
                return topWall;
            case "bottom":
                return bottomWall;
        }
        return wallPosition;
    }
}
