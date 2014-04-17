/**
 * 
 */
package controller;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ArtPanel;

/**
 * A pop-up menu for wiping, saving, and about.
 * 
 * @author stubing
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PopupMenu extends JPopupMenu {
    
    /**
     * menu item wipe.
     */
    private JMenuItem myWipeButton;
    
    /**
     * menu item save.
     */
    private JMenuItem mySaveButton;
    
    /**
     * menu item about.
     */
    private JMenuItem myAboutButton;
    
    /**
     * The file we want to save.
     */
    private JFileChooser myChooser;
    
    /**
     * The art panel for the drawing.
     */
    private ArtPanel myArtPanel;
    
    /**
     * The listener for when we want to use our pop up menu.
     */
    private PopupListener myPopupListener;
    
    /**
     * constructs a popup menu.
     * @custom.post popup menu with all components created
     * @param anArtPanel the art panel for the drawing.
     */
    public PopupMenu(final ArtPanel anArtPanel) {
        this.myArtPanel = anArtPanel;
        createPopupMenu();
    }
    
    /**
     * adds names and tooltips to popup menu items.
     * 
     * @custom.post names and tooltips added to a popup menu
     */
    private void createPopupMenu() {
        myPopupListener = new PopupListener();
        
        myWipeButton = new JMenuItem();
        mySaveButton = new JMenuItem();
        myAboutButton = new JMenuItem();
        
        myWipeButton.addActionListener(myPopupListener);
        mySaveButton.addActionListener(myPopupListener);
        myAboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                JOptionPane.showMessageDialog(null, "Developer's Name: Stephen Irving "
                                                  + "\nProject Name: stubing-final "
                                                  + "\nVersion: 1.0", 
                                                    "Program Information",
                                                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        add(myWipeButton);
        add(mySaveButton);
        add(myAboutButton);
        
        myWipeButton.setText("Wipe");
        mySaveButton.setText("Save");
        myAboutButton.setText("About");
    }
    
    /**
     * Listens for when you want to pull up the pop up menu.
     * 
     * @author stubing
     * @version 1.0
     */
    public class PopupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();
            if (source == myWipeButton) {
                //wipe all shapes off
                myArtPanel.getMyArtModel().clearShapes();
                myArtPanel.repaint();
            } else if (source == mySaveButton) { //save picture
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
                    imageSave(selected, extension);
                    
                }
                
            }         
            
        }
        
        /**
         * Saves to image to where ever you want on your computer.
         * 
         * @param aFile The final name.
         * @param anExtension The extention of the file.
         */
        private void imageSave(File aFile, String anExtension) {
            final File newF = new File(aFile.getPath() + "." + anExtension);
            final BufferedImage bi = new BufferedImage(
                    myArtPanel.getWidth(), myArtPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            final Graphics2D pen = bi.createGraphics();
            myArtPanel.paintAll(pen);
            
            try {
                ImageIO.write(bi, anExtension, newF);
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        }
        
    }


}
