
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The art model holds the shape properties of our shape objects.
 * 
 * @author stubing
 * @version 1.0
 */
public class ArtModel {

    /**
     * A new random shape.
     */
    private ShapeProperties myNewShape;

    /**
     * The list that holds the shapes.
     */
    private List<ShapeProperties> myShapeArrays = new ArrayList<ShapeProperties>();

    /**
     * A copy of the list that holds the shapes in case we want to revert back
     * to the original set up of the shapes.
     */
    private List<ShapeProperties> myOriginalShapeArrays = new ArrayList<ShapeProperties>();

    /**
     * THe numbers of rectangles to be generated.
     */
    private int myNumRect;

    /**
     * The numbers of ellipses to be generated.
     */
    private int myNumEll;

    /**
     * The numbers of lines to be generated.
     */
    private int myNumLine;

    /**
     * The numbers of round rectangles to be generated.
     */
    private int myNumRound;

    /**
     * Adds all our specific shapes to our array list of shapes.
     */
    public void createMoreShapes() {
        for (int i = 0; i < myNumRect; i++) {
            myNewShape = new ShapeProperties('R');
            myShapeArrays.add(myNewShape);
        }
        for (int i = 0; i < myNumEll; i++) {
            myNewShape = new ShapeProperties('E');
            myShapeArrays.add(myNewShape);
        }
        for (int i = 0; i < myNumLine; i++) {
            myNewShape = new ShapeProperties('L');
            myShapeArrays.add(myNewShape);
        }
        for (int i = 0; i < myNumRound; i++) {
            myNewShape = new ShapeProperties('O');
            myShapeArrays.add(myNewShape);
        }

    }

    /**
     * Getter for the shape properties.
     * 
     * @return the shape properties array list.
     */
    public List<ShapeProperties> getShapeArrays() {
        return myShapeArrays;
    }

    /**
     * Setter for the shape properties.
     * 
     * @param aShapeArrays What we want the new shape properties array list to
     *            be.
     */
    public void setMyShapeArrays(List<ShapeProperties> aShapeArrays) {
        this.myShapeArrays = aShapeArrays;
    }

    /**
     * Getter for the original shape properties.
     * 
     * @return the original shape properties array list.
     */
    public List<ShapeProperties> getOriginalShapeArrays() {
        return myOriginalShapeArrays;
    }

    /**
     * Setter for the original shape properties.
     * 
     * @param aOrgShapeArrays What we want the new original shape properties
     *            array list to be.
     */
    public void setMyOriginalShapeArrays(List<ShapeProperties> aOrgShapeArrays) {
        myOriginalShapeArrays = aOrgShapeArrays;
    }

    /**
     * Deletes all our shape objects.
     */
    public void clearShapes() {
        myShapeArrays = new ArrayList<ShapeProperties>();
    }

    /**
     * Sets the number of rectangles we want to create.
     * 
     * @param aNumRect the number of rectangles we want to create.
     */
    public void setNumRect(int aNumRect) {
        this.myNumRect = aNumRect;
    }

    /**
     * Sets the number of ellipses we want to create.
     * 
     * @param aNumEll Sets the number of ellipses we want to create.
     */
    public void setNumEll(int aNumEll) {
        this.myNumEll = aNumEll;
    }

    /**
     * Sets the number of lines we want to create.
     * 
     * @param aNumLine Sets the number of lines we want to create.
     */
    public void setNumLine(int aNumLine) {
        this.myNumLine = aNumLine;
    }

    /**
     * Sets the number of round rectangles we want to create.
     * 
     * @param aNumRound Sets the number of round rectangles we want to create.
     */
    public void setNumRound(int aNumRound) {
        this.myNumRound = aNumRound;
    }

}
