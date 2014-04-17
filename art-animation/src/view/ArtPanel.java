/**
 * 
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.ArtModel;
import model.ShapeProperties;

/**
 * @author stubing
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ArtPanel extends JPanel {

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width / 2;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height / 2;

    /** The default size for this JPanel. */
    private static final Dimension DEFUALT_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

    /**
     * Generates a random integer or boolean.
     */
    private Random myRandomNumber;

    /**
     * The first color used in creating a new gradiant paint.
     */
    private Color myColor1;

    /**
     * The second color used in creating a new gradiant paint.
     */
    private Color myColor2;

    /**
     * The art model holds all our shapes we need to create.
     */
    private ArtModel myArtModel;

    /**
     * Listens for when we touch the art panel with our mouse.
     */
    private MouseListener myMouseListener;

    /**
     * Clock for our frames per second. We need it since each shape object moves
     * in a certain direction per second.
     */
    private Timer myTimer;

    /**
     * A list for storing our shapes.
     */
    private List<ShapeProperties> myNewShapeArrays;

    /**
     * Construct this art panel.
     * 
     * @param anArtModel The art model holds all our shapes we need to create.
     * @custom.post white panel of a quarter of a screen size constructed
     */
    public ArtPanel(final ArtModel anArtModel) {
        super();
        setPreferredSize(DEFUALT_SIZE);
        setBackground(Color.WHITE);

        myMouseListener = new MouseListener();
        addMouseListener(myMouseListener);

        myTimer = new Timer(66, new TimerListener());

        this.myArtModel = anArtModel;

        myRandomNumber = new Random();
        myColor1 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));
        myColor2 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));

    }

    /** {@inheritDoc} */
    @Override
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);
        final Color newColor1 = myColor1;
        final Color newColor2 = myColor2;

        final Graphics2D randomShape = (Graphics2D) aGraphics;

        final Rectangle2D backgroundRect =
                new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        randomShape.setPaint(createGradiantColor(newColor1, newColor2));
        randomShape.draw(backgroundRect);
        randomShape.fill(backgroundRect);

        for (ShapeProperties demShapes : myArtModel.getShapeArrays()) {

            randomShape.setPaint(demShapes.getMyColor());
            randomShape.draw(demShapes.getMyShape());
            randomShape.setStroke(new BasicStroke(demShapes.getMyStroke()));
            if (demShapes.isMyFilled()) {
                randomShape.fill(demShapes.getMyShape());
            }
        }

    }

    /**
     * Creates a new gradient paint for us to use as our background.
     * 
     * @param aColor1 The top left color of our gradient paint.
     * @param aColor2 The bottom right color of our gradient paint.
     * @return a new gradient paint for us to use as our background.
     */
    public GradientPaint createGradiantColor(Color aColor1, Color aColor2) {

        final GradientPaint grad =
                new GradientPaint(0, 0, aColor1, getHeight(), getWidth(), aColor2, true);
        return grad;
    }

    /**
     * Gets the art model that holds our shapes.
     * 
     * @return the art model that holds our shapes.
     */
    public ArtModel getMyArtModel() {
        return myArtModel;
    }

    /**
     * Changes our colors used in our gradient paint.
     */
    public void setNewBackground() {
        myColor1 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));
        myColor2 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));
    }

    /**
     * The clock used for our frames per second.
     * 
     * @return The clock used for our frames per second.
     */
    public Timer getMyTimer() {
        return myTimer;
    }

    /**
     * Sets the timer speed between 0 and 30 frames per second.
     * 
     * @param aTimeSpeed The number of frames per second.
     */
    public void setMyTimer(double aTimeSpeed) {
        if (aTimeSpeed == 0) {
            aTimeSpeed = .00000000000001; 
            // so it will never move if the frames
        }
        final int framesPerScond = (int) (1000 / aTimeSpeed);
        myTimer = new Timer(framesPerScond, new TimerListener());
    }
    
    /**
     * Moves the shape object in what ever direct it is moving.
     */
    public void moveShapes() {
        myNewShapeArrays = new ArrayList<ShapeProperties>();

        for (ShapeProperties demShapes : myArtModel.getShapeArrays()) {
            updateHorizontalMovement(demShapes);
            updateVerticalMovement(demShapes);
            demShapes.setMyXStart(demShapes.getMyXStart() + demShapes.getHorizontalMove());
            demShapes.setMyYStart(demShapes.getMyYStart() + demShapes.getVerticalMove());
            demShapes.setMyShape(demShapes.generateShape());
            myNewShapeArrays.add(demShapes);

        }
        myArtModel.setMyShapeArrays(myNewShapeArrays);
        repaint();
    }

    /**
     * If the shape object hits the left or right side of the screen, it will
     * then go the opposite direction.
     * 
     * @param aShape The shape object that is moving.
     */
    private void updateHorizontalMovement(ShapeProperties aShape) {
        if (aShape.getMaxX() >= getWidth() || aShape.getMyXStart() <= 0) {
            aShape.setHorizontalMove(aShape.getHorizontalMove() * -1);
        }
    }

    /**
     * If the shape object hits the top or bottom side of the screen, it will
     * then go the opposite direction.
     * 
     * @param aShape The shape object that is moving.
     */
    private void updateVerticalMovement(ShapeProperties aShape) {
        if (aShape.getMaxY() >= getHeight() || aShape.getMyYStart() <= 0) {
            aShape.setVerticalMove(aShape.getVerticalMove() * -1);
        }
    }

    /**
     * A mouse listener that listeners for when some one clicks on the art
     * panel.
     * 
     * @author stubing
     * @version 1.0
     */
    private class MouseListener extends MouseAdapter {

        /**
         * Method that detects if some one clicks on a shape object. If they do,
         * it is removed from the art panel.
         * 
         * @param anEvent mouse click event.
         */
        public void mousePressed(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                final List<ShapeProperties> tempShapeArray = myArtModel.getShapeArrays();

                for (final Iterator<ShapeProperties> iterator = tempShapeArray.iterator(); 
                        iterator.hasNext();) {
                    final ShapeProperties demShapes = iterator.next();
                    if (anEvent.getX() > demShapes.getMyXStart()
                        && anEvent.getX() < demShapes.getMaxX()
                        && anEvent.getY() > demShapes.getMyYStart()
                        && anEvent.getY() < demShapes.getMaxY()) {
                        iterator.remove();
                        repaint();
                    }

                }
            }

        }

//        public void mouseDragged(MouseEvent anEvent) {
//            for (ShapeProperties demShapes : myArtModel.getShapeArrays()) {
//                if (anEvent.getX() > demShapes.getMyXStart()
//                    && anEvent.getX() < demShapes.getMaxX()
//                    && anEvent.getY() > demShapes.getMyYStart()
//                    && anEvent.getY() < demShapes.getMaxY()) {
//                    demShapes.setMyXStart(anEvent.getXOnScreen());
//                    demShapes.setMyYStart(anEvent.getYOnScreen());
//                    repaint();
//
//                    System.out.println("blab al");
//
//                }
//
//            }
//            System.out.println("blab al");
//        }

    }

    /**
     * Clock that ticks during a set interval.
     * 
     * @author stubing
     * @version 1.0
     */
    private class TimerListener implements ActionListener {

        /**
         * Moves the shape objects when ever the timer ticks.
         * 
         * @param anEvent Timer tick event.
         */
        public void actionPerformed(ActionEvent anEvent) {

            moveShapes();
        }

    }

}
