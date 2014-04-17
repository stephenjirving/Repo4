
package controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.ArtPanel;

/**
 * Controls when the shapes move or stop.
 * 
 * @author stubing
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AnimationToolbar extends JToolBar {

    /**
     * The panel of the JToolBar.
     */
    private JPanel myPanel;

    /**
     * The buttons that go on the JToolBar.
     */
    private JPanel myButtonPanel;

    /**
     * The art panel we draw our shape objects on.
     */
    private ArtPanel myArtPanel;

    /**
     * Dummy button for setting button sizes.
     */
    private JButton myDummyButton;

    /**
     * Sets the size of blank buttons.
     */
    private Dimension myBoxSize;

    /**
     * Toggle button for play.
     */
    private JToggleButton myToggleButton1;

    /**
     * Toggle button for pause.
     */
    private JToggleButton myToggleButton2;

    /**
     * Toggle button for stop.
     */
    private JToggleButton myToggleButton3;

    /**
     * Button for skip.
     */
    private JButton myButton4;

    /**
     * Button group to make sure onl play, pause, or stop is selected.
     */
    private ButtonGroup myButtonGroup;

    /**
     * Title of the frames per second.
     */
    private JLabel myFrameLabel;

    /**
     * The current frame rate of our image.
     */
    private int myCurrentFrameRate;

    /**
     * Slider for setting the frame rate.
     */
    private JSlider myJSlider;

    /**
     * Listens for when the slider has changed.
     */
    private AnimationActionListener myAnimationActionListener;

    /**
     * Listeners for when a button has been pressed.
     */
    private ButtonListener myButtonListener;
    
    /**
     * Sequence for the song.
     */
    private Sequence mySequence;
    
    /**
     * Sequencer for the song.
     */
    private Sequencer myCequencer;

    /**
     * Construct the ToolBar.
     * 
     * @custom.post toolbar with all its components created
     * @param anArtPanel The art panel we draw our image on.
     */
    public AnimationToolbar(final ArtPanel anArtPanel) {
        super("Art Tool Bar");

        this.myArtPanel = anArtPanel;
        
        generateToolBar();

    }
    
    /**
     * Generates the tool bar's components.
     */
    public void generateToolBar() {
        myPanel = new JPanel(new GridLayout(1, 5));
        myButtonPanel = new JPanel(new GridLayout(1, 4));

        myDummyButton = new JButton("          ");
        myBoxSize = new Dimension(myDummyButton.WIDTH, myDummyButton.HEIGHT);

        myToggleButton1 = new JToggleButton(new ImageIcon("play.png"));
        myToggleButton2 = new JToggleButton(new ImageIcon("pause.png"));
        myToggleButton3 = new JToggleButton(new ImageIcon("stop.png"));
        myButton4 = new JButton(new ImageIcon("step-forward.png"));

        myButtonListener = new ButtonListener();
        myToggleButton1.addActionListener(myButtonListener);
        myToggleButton2.addActionListener(myButtonListener);
        myToggleButton3.addActionListener(myButtonListener);
        myButton4.addActionListener(myButtonListener);

        myButtonGroup = new ButtonGroup();
        myButtonGroup.add(myToggleButton1);
        myButtonGroup.add(myToggleButton2);
        myButtonGroup.add(myToggleButton3);
        myButtonGroup.add(myButton4);

        myCurrentFrameRate = 15;
        myFrameLabel = new JLabel("   Frames per second : " + myCurrentFrameRate);
        myAnimationActionListener = new AnimationActionListener();

        myJSlider = new JSlider(0, 30, 0);
        myJSlider.setMajorTickSpacing(5);
        myJSlider.setPaintTicks(true);
        myJSlider.setPaintTrack(true);
        myJSlider.setValue(15);
        myJSlider.addChangeListener(myAnimationActionListener);

        myButtonPanel.add(myToggleButton1);
        myButtonPanel.add(myToggleButton2);
        myButtonPanel.add(myToggleButton3);
        myButtonPanel.add(myButton4);
        myPanel.add(Box.createRigidArea(myBoxSize));
        myPanel.add(myButtonPanel);
        myPanel.add(myFrameLabel);
        myPanel.add(myJSlider);
        myPanel.add(Box.createRigidArea(myBoxSize));

        add(myPanel);
    }

    /**
     * Listens for when the slider is changed, and then changes the framers per
     * second to the new value.
     * 
     * @author stubing
     * @version 1.0
     */
    public class AnimationActionListener implements ChangeListener {

        /**
         * Changes the framers per second to the new value.
         * 
         * @param anEvent Change to the spinner.
         */
        public void stateChanged(ChangeEvent anEvent) {
            if (myJSlider.getValueIsAdjusting()) {
                myArtPanel.getMyTimer().stop();
                myToggleButton2.setSelected(true);
                myCurrentFrameRate = myJSlider.getValue();
                myFrameLabel.setText("   Frames per second : " + myCurrentFrameRate);
                myArtPanel.setMyTimer(myCurrentFrameRate);

            }

        }

    }

    /**
     * Listens for when a button is pressed.
     * 
     * @author stubing
     * @version 1.0
     */
    public class ButtonListener implements ActionListener {

        /**
         * Listeners for play, pause, stop, and skip.
         * Code used from 
         * http://www.java-tips.org/java-se-tips/javax.sound.midi/
         * how-to-load-and-play-midi-audio-19.html
         * 
         * @param anEvent A button was pressed
         */
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();

            if (myToggleButton1.isSelected()) {
                myArtPanel.getMyTimer().start();
                
//                try {
//                    mySequence = MidiSystem.getSequence(new File("elliegoulding-burning.mid"));
//                    myCequencer = MidiSystem.getSequencer();
//                    myCequencer.open();
//                    myCequencer.setSequence(mySequence);
//                
//                    myCequencer.start();
//                } catch (final MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (final IOException e) {
//                    e.printStackTrace();
//                } catch (final MidiUnavailableException e) {
//                    e.printStackTrace();
//                } catch (final InvalidMidiDataException e) {
//                    e.printStackTrace();
//                }
                
            } else if (myToggleButton2.isSelected()) {
                myArtPanel.getMyTimer().stop();
            }
            if (myToggleButton3 == source) {
                myArtPanel.getMyTimer().stop();
                myArtPanel.getMyArtModel().setMyShapeArrays(
                                      myArtPanel.getMyArtModel().getOriginalShapeArrays());
            }
            if (myButton4 == source) {
                myArtPanel.moveShapes();
            }
        }

    }
}
