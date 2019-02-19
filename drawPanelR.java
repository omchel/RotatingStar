/*
    CS335 Graphics and Multimedia
    Author: Chelina Ortiz Montanez
    Title: Exercise 3 part 1
    Description:  java program to allow the user to “grab” a vertex
        of the polygon above and drag it.  Each vertex should be
        “draggable”.

    ** Starter code provided by Dr. Brent Seales
*/
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class drawPanelR extends JPanel{

    private int theta; // Angle of rotation
    private boolean resetStar; // Flag to check if we are resetting

    // polygons for drawing
    private Polygon poly; // original polygon
    private Point click = new Point(200,200);

    private Point2D points[] = new Point2D[5],
            transPoints[] = new Point2D[5];
    private int newPointX[] = {170, 200, 120, 220, 140};
    private int newPointY[] = {120, 220, 160, 160, 220};

    //constructor to draw the original polygon
    public drawPanelR(Polygon poly){
        this.poly = poly;
        setBackground (Color.BLACK);
    }

    //set a new polygon
    public void setPoly(Polygon poly){this.poly = poly;}

    public void newCenter(Point click) { this.click = click; }
    public void getTheta(int theta) { this.theta = theta; }
    public void setResetStar(boolean resetStar) {
        this.resetStar = resetStar;
    }


    public Polygon rotate(Polygon poly, int delta, Point click){
        AffineTransform transf = new AffineTransform();
        for (int i = 0; i < 5; i++) {
            points[i] = new Point2D.Double((double) poly.xpoints[i], (double) poly.ypoints[i]);
        }
        transf.rotate(delta, click.getX(), click.getY());
        transf.transform(points, 0, transPoints, 0, points.length);
        for (int i = 0; i < 5; i++) {
            newPointX[i] = (int)transPoints[i].getX();
            newPointY[i] = (int)transPoints[i].getY();
        }
        poly = new Polygon(newPointX, newPointY,5);
        return poly;
    }

    //where the drawing happens
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        Graphics2D picture = (Graphics2D) g;
        picture.setStroke(new BasicStroke(3));
        // use the polygon draw to draw the current poly position
        Polygon newPoly = rotate(poly, theta, click);
        picture.drawPolygon(newPoly);
    }
}