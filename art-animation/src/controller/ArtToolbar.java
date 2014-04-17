/**
 * 
 */

package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ArtPanel;

/**
 * @author stubing
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ArtToolbar extends JToolBar {

    /**
     * The max amount of specific shapes we can add to the screen at one time. I
     * make it static and final because I don't want anything to change it.
     */
    private static final int MAX_SHAPES = 15;

    /**
     * Panel that is constraint towards the North of the tool bar..
     */
    private JPanel myNorthPanel;

    /**
     * Title for rectangles.
     */
    private JLabel myTitle1;

    /**
     * Title for ellipses.
     */
    private JLabel myTitle2;

    /**
     * Titles for lines.
     */
    private JLabel myTitle3;

    /**
     * Title for ovals.
     */
    private JLabel myTitle4;

    /**
     * Spinner for the number of rectangles to be added.
     */
    private JSpinner mySpinnerBoxes;

    /**
     * Spinner for the number of ellipses to be added.
     */
    private JSpinner mySpinnerEllipses;

    /**
     * Spinner for the number of lines to be added.
     */
    private JSpinner mySpinnerLines;

    /**
     * Spinner for the number of ovals to be added.
     */
    private JSpinner mySpinnerOvals;

    /**
     * Add to drawing button.
     */
    private JButton myButton1;

    /**
     * Get new background button.
     */
    private JButton myButton2;

    /**
     * Panel that is in the tool bar.
     */
    private JPanel myPanel;

    /**
     * Wipe button.
     */
    private JButton myButton3;

    /**
     * Save button.
     */
    private JButton myButton4;

    /**
     * About button.
     */
    private JButton myButton5;

    /**
     * Parameter for the size of buttons.
     */
    private JButton myDummyButton;

    /**
     * Sets the size of blank buttons.
     */
    private Dimension myBoxSize;

    /**
     * Listens for changes to the spinners.
     */
    private SpinnerListener mySpinnerListener;

    /**
     * Listens to when buttons are being pressed.
     */
    private ButtonListener myButtonListener;

    /**
     * The file we pick.
     */
    private JFileChooser myChooser;

    /**
     * The panel that holds the picture.
     */
    private ArtPanel myArtPanel;

    /**
     * Generates a random int or boolean.
     */
    private Random myRandomNumber;

    /**
     * First color of our gradient paint.
     */
    private Color myColor1;

    /**
     * Second color of our gradient paint.
     */
    private Color myColor2;

    /**
     * Construct the ToolBar.
     * 
     * @param anArtPanel The art panel passed to this class so we can change the
     *            art panel.
     * @custom.post toolbar with all its components created
     */
    public ArtToolbar(final ArtPanel anArtPanel) {
        super("Art Tool Bar");

        this.myArtPanel = anArtPanel;

        myPanel = new JPanel(new GridLayout(10, 1));

        createNorth();
        createSouth();
    }

    /**
     * Adds buttons to a group, and assigns names to tooltips to the toolbar
     * items.
     * 
     * @custom.post buttons added to the toobar, each one with a name and a
     *              tooltip
     */
    private void createNorth() {
        myNorthPanel = new JPanel(new GridLayout(2, 4));
        myTitle1 = new JLabel("Boxes:    ");
        myTitle2 = new JLabel("Ellipses: ");
        myTitle3 = new JLabel("Lines:    ");
        myTitle4 = new JLabel("Oval:     ");
        mySpinnerBoxes = new JSpinner();
        mySpinnerEllipses = new JSpinner();
        mySpinnerLines = new JSpinner();
        mySpinnerOvals = new JSpinner();

        mySpinnerListener = new SpinnerListener();
        myButtonListener = new ButtonListener();

        mySpinnerBoxes.addChangeListener(mySpinnerListener);
        mySpinnerEllipses.addChangeListener(mySpinnerListener);
        mySpinnerLines.addChangeListener(mySpinnerListener);
        mySpinnerOvals.addChangeListener(mySpinnerListener);

        myNorthPanel.add(myTitle1);
        myNorthPanel.add(mySpinnerBoxes);
        myNorthPanel.add(myTitle2);
        myNorthPanel.add(mySpinnerEllipses);
        myNorthPanel.add(myTitle3);
        myNorthPanel.add(mySpinnerLines);
        myNorthPanel.add(myTitle4);
        myNorthPanel.add(mySpinnerOvals);

        myPanel.add(myNorthPanel, BorderLayout.NORTH);

    }

    /**
     * Adds buttons to a group, and assigns names to tooltips to the toolbar
     * items.
     * 
     * @custom.post buttons added to the toobar, each one with a name and a
     *              tooltip
     */
    private void createSouth() {
        myButton1 = new JButton("Add to Drawing");
        myButton2 = new JButton("Get New Background");
        myButton3 = new JButton("Wipe");
        myButton4 = new JButton("Save");
        myButton5 = new JButton("About");
        myDummyButton = new JButton("                                         ");

        myButton1.addActionListener(myButtonListener);
        myButton2.addActionListener(myButtonListener);
        myButton3.addActionListener(myButtonListener);
        myButton4.addActionListener(myButtonListener);

        myRandomNumber = new Random();
        myColor1 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));
        myColor2 =
                new Color(myRandomNumber.nextInt(256), myRandomNumber.nextInt(256),
                          myRandomNumber.nextInt(256));

        myButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                JOptionPane.showMessageDialog(null, "Developer's Name: Stephen Irving "
                                                    + "\nProject Name: stubing-final "
                                                    + "\nVersion: 1.0", "Program Information",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        });

        myButton1.setBackground(Color.YELLOW);
        myButton2.setBackground(Color.YELLOW);

        myButton1.setPreferredSize(myDummyButton.getPreferredSize());
        myButton1.setMinimumSize(myDummyButton.getMinimumSize());
        myButton1.setMaximumSize(myDummyButton.getMaximumSize());

        myButton2.setPreferredSize(myDummyButton.getPreferredSize());
        myButton2.setMinimumSize(myDummyButton.getMinimumSize());
        myButton2.setMaximumSize(myDummyButton.getMaximumSize());

        myBoxSize = new Dimension(myButton2.WIDTH, myButton2.HEIGHT);

        myPanel.add(myButton1);
        myPanel.add(Box.createRigidArea(myBoxSize));
        myPanel.add(Box.createRigidArea(myBoxSize));
        myPanel.add(myButton2);
        myPanel.add(Box.createRigidArea(myBoxSize));
        myPanel.add(Box.createRigidArea(myBoxSize));

        myButton3.setToolTipText("Wipe all the shapes off the art panel");
        myButton4.setToolTipText("Saves the image to a jpg, png, or gif");
        myButton5.setToolTipText("Lists details about the program");

        myButton3.setBackground(Color.LIGHT_GRAY);
        myButton4.setBackground(Color.LIGHT_GRAY);
        myButton5.setBackground(Color.LIGHT_GRAY);

        myButton3.setPreferredSize(myDummyButton.getPreferredSize());
        myButton3.setMinimumSize(myDummyButton.getMinimumSize());
        myButton3.setMaximumSize(myDummyButton.getMaximumSize());

        myButton4.setPreferredSize(myDummyButton.getPreferredSize());
        myButton4.setMinimumSize(myDummyButton.getMinimumSize());
        myButton4.setMaximumSize(myDummyButton.getMaximumSize());

        myButton5.setPreferredSize(myDummyButton.getPreferredSize());
        myButton5.setMinimumSize(myDummyButton.getMinimumSize());
        myButton5.setMaximumSize(myDummyButton.getMaximumSize());

        myPanel.add(myButton3);
        myPanel.add(myButton4);
        myPanel.add(myButton5);

        add(myPanel, BorderLayout.SOUTH);

    }

    /**
     * Gets the first color of the gradient paint.
     * 
     * @return The first color of the gradient paint.
     */
    public Color getColor1() {
        return myColor1;
    }

    /**
     * Gets the second color of the gradient paint.
     * 
     * @return The second color of the gradient paint.
     */
    public Color getColor2() {
        return myColor2;
    }

    /**
     * Listens for when the buttons on the tool bar are pressed.
     * 
     * @author stubing
     * @version 1.0
     */
    public class ButtonListener implements ActionListener {

        /**
         * Listens for when buttons are pressed.
         * 
         * @param anEvent When a button is pressed.
         */
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();
            if (source == myButton1) { // add to drawing
                myArtPanel.getMyArtModel().setNumRect((int) mySpinnerBoxes.getValue());
                myArtPanel.getMyArtModel().setNumEll((int) mySpinnerEllipses.getValue());
                myArtPanel.getMyArtModel().setNumLine((int) mySpinnerLines.getValue());
                myArtPanel.getMyArtModel().setNumRound((int) mySpinnerOvals.getValue());

                myArtPanel.getMyArtModel().createMoreShapes();

                myArtPanel.getMyArtModel().setMyOriginalShapeArrays(
                                                  myArtPanel.getMyArtModel().getShapeArrays());

                // reset value after added.
                mySpinnerBoxes.setValue(0);
                mySpinnerEllipses.setValue(0);
                mySpinnerLines.setValue(0);
                mySpinnerOvals.setValue(0);

                // myArtPanel.getMyArtModel().notify();
                myArtPanel.repaint();
            } else if (source == myButton2) {
                // get new background
                myArtPanel.setNewBackground();
                myArtPanel.repaint();
            } else if (source == myButton3) {
                // wipe all shapes off
                myArtPanel.getMyArtModel().clearShapes();
                myArtPanel.repaint();
            } else if (source == myButton4) { // save picture
                if (myChooser == null) {
                    myChooser = new JFileChooser();
                    myChooser.setCurrentDirectory(new File("."));
                }

                int choice;

                myChooser.setFileFilter(new FileNameExtensionFilter("GIF (*.gif)", "gif"));
                myChooser.setFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
                myChooser.setFileFilter(new FileNameExtensionFilter("JPG (*.jpg)", "jpg"));

                choice = myChooser.showSaveDialog(myArtPanel);

                File selected;
                if (choice == JFileChooser.APPROVE_OPTION) {
                    selected = myChooser.getSelectedFile();
                    if (selected == null) {
                        return;
                    }

                    final String extension =
                            myChooser.getFileFilter().getDescription().substring(0, 3);
                    saveImage(selected, extension);

                }

            }

        }

        /**
         * Saves to image to where ever you want on your computer.
         * 
         * @param aFile The final name.
         * @param anExtension The extention of the file.
         */
        private void saveImage(File aFile, String anExtension) {
            final File newF = new File(aFile.getPath() + "." + anExtension);
            final BufferedImage bi =
                    new BufferedImage(myArtPanel.getWidth(), myArtPanel.getHeight(),
                                      BufferedImage.TYPE_INT_RGB);
            final Graphics2D pen = bi.createGraphics();
            myArtPanel.paintAll(pen);

            try {
                ImageIO.write(bi, anExtension, newF);
            } catch (final IOException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
        }

    }

    /**
     * Adds listeners to the spinners.
     * 
     * @custom.post a listener is added to each toolbar button
     */
    public class SpinnerListener implements ChangeListener {

        /**
         * When something changes to a JSpinner, have to make sure the value
         * stays above or equal to 0 and below 16.
         * 
         * @param anEvent Something changes with the spinners.
         */
        public void stateChanged(ChangeEvent anEvent) {
            if ((int) mySpinnerBoxes.getValue() < 0) {
                mySpinnerBoxes.setValue(0);
            } else if ((int) mySpinnerEllipses.getValue() < 0) {
                mySpinnerEllipses.setValue(0);
            } else if ((int) mySpinnerLines.getValue() < 0) {
                mySpinnerLines.setValue(0);
            } else if ((int) mySpinnerOvals.getValue() < 0) {
                mySpinnerOvals.setValue(0);
            } else if ((int) mySpinnerBoxes.getValue() > MAX_SHAPES) {
                mySpinnerBoxes.setValue(MAX_SHAPES);
            } else if ((int) mySpinnerEllipses.getValue() > MAX_SHAPES) {
                mySpinnerEllipses.setValue(MAX_SHAPES);
            } else if ((int) mySpinnerLines.getValue() > MAX_SHAPES) {
                mySpinnerLines.setValue(MAX_SHAPES);
            } else if ((int) mySpinnerOvals.getValue() > MAX_SHAPES) {
                mySpinnerOvals.setValue(MAX_SHAPES);
            }
            
        }

        
    }
    

}
