/*
    CS335 Graphics and Multimedia
    Author: Chelina Ortiz Montanez
    Title: Exercise 3 part 1
    Description: A java program that draws the five vertices are
        the points of the star and then plays an animation of the
        polygon rotating through 360 degrees, one degree per frame.
         -- Have a reset (set back at original position) and stop
            (stop the rotation at any point) button

    ** Starter code provided by Dr. Brent Seales
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Part1 extends JFrame {
    // original points of the star polygon
    private int x[] = {170, 200, 120, 220, 140};
    private int y[] = {120, 220, 160, 160, 220};
    private int theta = 0; // angle of rotation

    private Polygon poly  = new Polygon(x,y,5); // Polygon generated from above coordinates
    private Container c;
    private drawPanelR panel; // Star panel

    private JPanel buttons = new JPanel(); // Buttons panel
    private JButton reset = new JButton("Reset"), // Reset button
        stop = new JButton("Stop"); // Stop button

    private Timer rotateTick; // Rotation timer
    private Point currClick; // Point coordinates of click

    public Part1() {
        super("Rotating Star");
        c = getContentPane();
        add(buttons, BorderLayout.NORTH);

        //create a new drawPanelR with the banding boolean set
        panel = new drawPanelR(poly);

        // reset the polygon back to the origin when the button is clicked
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theta = 0; // reset the angle to zero
                currClick.setLocation(200, 200); // Reset the current click to the middle of the frame
                panel.setResetStar(true); // reset flag is set to true to repaint the polygon back to origin
                panel.setPoly(poly); // Re-draw the polygon
                panel.repaint();
            }
        });
        buttons.add(reset); // add reset buttons to the button panel

        // stop the timer every time the stop button is clicked
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotateTick.stop();
            }
        });
        buttons.add(stop); // add stop buttons to the button panel

        // Timer will tick every 100 milliseconds to change the angle Theta to which it is rotating to
        rotateTick = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theta++; // add one to the angle every 100 milliseconds
                panel.newCenter(currClick); // set the click's coordinates to be center of rotation
                panel.getTheta(theta); // set the updated angle of rotation
                repaint();
            }
        });

        //create a mouse listener to tell when the mouse is clicked and released
        panel.addMouseListener(new MouseListener(){
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseReleased(MouseEvent e){  }
            public void mousePressed(MouseEvent e) { // when the mouse is pressed, store the point
                currClick = e.getPoint();  // coordinate of the click is stored to become axis of rotation
                rotateTick.start(); // start timer
            }
            public void mouseClicked(MouseEvent e){}
        });

        c.add(panel, BorderLayout.CENTER);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main (String args[]) {
        Part1 starRotate = new Part1(); // Generate a new star panel
        starRotate.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        starRotate.setSize( 200, 200 ); // set frame size
        starRotate.setVisible( true ); // display frame
    }
}
