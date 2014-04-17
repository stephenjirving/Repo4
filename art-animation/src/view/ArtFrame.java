/**
 * 
 */

package view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.AnimationToolbar;
import controller.ArtToolbar;
import controller.PopupMenu;
import model.ArtModel;

/**
 * The frame for the whole program.
 * 
 * @author stubing
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ArtFrame extends JFrame {

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = (int) (SCREEN_SIZE.width * .80);

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = (int) (SCREEN_SIZE.height * .80);

    /** The default size for this JPanel. */
    private static final Dimension DEFUALT_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

    /**
     * Art tool bar for adding, removing, and saving.
     */
    private ArtToolbar myArtToolbar;

    /**
     * Art Panel for drawing all our images on.
     */
    private ArtPanel myArtPanel;

    /**
     * Animation tool bar for controlling and moving our shapes.
     */
    private AnimationToolbar myAnimationToolbar;

    /**
     * Pop up menu for wiping, saving, and telling you what the program is
     * about.
     */
    private PopupMenu myPopupMenu;

    /**
     * The model for creating and storing all our shapes.
     */
    private ArtModel myArtModel;

    /**
     * Construct the JPanel art frame. This is where everything is pieced
     * together.
     * 
     * @custom.post white panel of a 80% of a screen size constructed
     */
    public ArtFrame() {
        super("CCS 305 Computer Art");
        setSize(DEFUALT_SIZE);
        setBackground(Color.WHITE);

        fillFrame();
    }

    /**
     * Method that adds all our panels to the frame. It pieces everything
     * together.
     */
    public void fillFrame() {
        myArtModel = new ArtModel();
        myArtPanel = new ArtPanel(myArtModel);
        myArtToolbar = new ArtToolbar(myArtPanel);
        myAnimationToolbar = new AnimationToolbar(myArtPanel);
        myPopupMenu = new PopupMenu(myArtPanel);

        add(myArtPanel, BorderLayout.CENTER);
        myArtPanel.setComponentPopupMenu(myPopupMenu);
        add(myArtToolbar, BorderLayout.WEST);
        add(myAnimationToolbar, BorderLayout.SOUTH);
    }

}
