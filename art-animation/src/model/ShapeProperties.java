/**
 * 
 */

package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 * The fields for the shapes that get generator.
 * 
 * @author stubing
 * @version 1.0
 */
public class ShapeProperties {

    /**
     * Generates a random integer or boolean.
     */
    private Random myRandom;

    /**
     * The shape of the object.
     */
    private Shape myShape;

    /**
     * The color of the shape.
     */
    private Color myColor;

    /**
     * The thickness of the the stroke.
     */
    private int myStroke;

    /**
     * The X starting location of the shape.
     */
    private double myXStart;

    /**
     * The Y starting location of the shape.
     */
    private double myYStart;

    /**
     * The width of the shape object.
     */
    private double myWidth;

    /**
     * The height of the shape object.
     */
    private double myHeight;

    /**
     * If the shape is filled or not.
     */
    private boolean myFilled;

    /**
     * The type of shape of the shape object.
     */
    private char myTypeOfShape;

    /**
     * The horizontal speed of the shape object.
     */
    private int myHorizontalMove;

    /**
     * The vertical speed of the shape object.
     */
    private int myVerticalMove;

    /**
     * The constructor for our shape properties. It generates the random
     * parameters for our shape objects.
     * 
     * @param aChar Determines the type of shape we make.
     */
    public ShapeProperties(char aChar) {
        generateRandomFields(); 
        myTypeOfShape = aChar;
        myShape = generateShape();

    }
    
    public void generateRandomFields() {
        myRandom = new Random();
        this.myColor =
                new Color(myRandom.nextInt(256), myRandom.nextInt(256), myRandom.nextInt(256));
        this.myFilled = myRandom.nextBoolean();
        this.myStroke = myRandom.nextInt(20);
        this.myXStart = myRandom.nextInt(375);
        this.myYStart = myRandom.nextInt(375);
        this.myWidth = myRandom.nextInt(375);
        this.myHeight = myRandom.nextInt(375);

        myHorizontalMove = myRandom.nextInt(4);
        myVerticalMove = myRandom.nextInt(4);
    }

    /**
     * Makes our shape object depends on what random variables were generated in
     * the constructor.
     * 
     * @return A random shape object.
     */
    public Shape generateShape() {
        Shape tempShape;
        if (myTypeOfShape == 'R') {
            final Rectangle2D tempRect =
                    new Rectangle2D.Double(myXStart, myYStart, myWidth, myHeight);
            tempShape = (Shape) tempRect.clone();
        } else if (myTypeOfShape == 'E') {
            final Ellipse2D tempEllipse =
                    new Ellipse2D.Double(myXStart, myYStart, myWidth, myHeight);
            tempShape = (Shape) tempEllipse.clone();
        } else if (myTypeOfShape == 'L') {
            final Line2D tempLine =
                    new Line2D.Double(myXStart, myYStart, myXStart + myWidth, myYStart
                                                                              + myHeight);
            tempShape = (Shape) tempLine.clone();
        } else /* if (myTypeOfShape == 'O') */ {
            final RoundRectangle2D tempRound =
                    new RoundRectangle2D.Double(myXStart, myYStart, myWidth, myHeight, 40, 40);
            tempShape = (Shape) tempRound.clone();
        }
        return tempShape;

    }

    /**
     * The shape of our shape properties object.
     * 
     * @return The shape of our shape properties object
     */
    public Shape getMyShape() {
        return myShape;
    }

    /**
     * Sets the shape to a new shape passed in.
     * 
     * @param aGeneratedShape The new shape you want it to be.
     */
    public void setMyShape(Shape aGeneratedShape) {
        myShape = aGeneratedShape;
    }

    /**
     * Gets the color of the shape.
     * 
     * @return The color of the shape.
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * Gets the stroke of the shape.
     * 
     * @return The stroke of the shape.
     */
    public int getMyStroke() {
        return myStroke;
    }

    /**
     * If the shape is filled.
     * 
     * @return If the shape is filled.
     */
    public boolean isMyFilled() {
        return myFilled;
    }

    /**
     * Gets the type of shape of the object.
     * 
     * @return The type of shape of the object.
     */
    public char getMyTypeOfShape() {
        return myTypeOfShape;
    }

    /**
     * The starting X point of the object.
     * 
     * @return Starting X point of the object.
     */
    public double getMyXStart() {
        return myXStart;
    }

    /**
     * The starting Y point of the object.
     * 
     * @return Starting Y point of the object.
     */
    public double getMyYStart() {
        return myYStart;
    }

    /**
     * Sets the starting X point of the object.
     * 
     * @param aXStart The new starting X point of the object.
     */
    public void setMyXStart(double aXStart) {
        this.myXStart = aXStart;
    }

    /**
     * Sets the starting Y point of the object.
     * 
     * @param aYStart The new starting Y point of the object.
     */
    public void setMyYStart(double aYStart) {
        this.myYStart = aYStart;
    }

    /**
     * Sets the horizontal speed.
     * 
     * @param aHorizontalMove The new horizontal speed.
     */
    public void setHorizontalMove(int aHorizontalMove) {
        this.myHorizontalMove = aHorizontalMove;
    }

    /**
     * Sets the vertical speed.
     * 
     * @param aVerticalMove The new vertical speed.
     */
    public void setVerticalMove(int aVerticalMove) {
        this.myVerticalMove = aVerticalMove;
    }

    /**
     * Gets the horizontal speed.
     * 
     * @return The horizontal speed.
     */
    public int getHorizontalMove() {
        return myHorizontalMove;
    }

    /**
     * Gets the vertical speed.
     * 
     * @return The vertical speed.
     */
    public int getVerticalMove() {
        return myVerticalMove;
    }

    /**
     * Gets the bottom left X coordinate point of the shape object.
     * 
     * @return The bottom left X coordinate point of the shape object.
     */
    public double getMaxX() {
        return myXStart + myWidth;
    }

    /**
     * Gets the bottom left Y coordinate point of the shape object.
     * 
     * @return The bottom left Y coordinate point of the shape object.
     */
    public double getMaxY() {
        return myYStart + myHeight;
    }

}
