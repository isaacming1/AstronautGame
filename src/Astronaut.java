import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Astronaut {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rec;


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Astronaut(String pName, int pXpos, int pYpos) { // Astronaut constructor
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 5;
        dx = (int) (Math.random()*6+1);
        dy = 5;
        dy = (int) (Math.random()*6+1);
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos,ypos,width,height);

    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);


    } // end move

    public Boolean intersect(Astronaut other)
    {
        if (xpos+100 >= other.xpos && xpos -100 <= other.xpos && ypos+100 >=other.ypos && ypos-100<= other.ypos)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void bounce() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000 - width || xpos <= 0) { //right and left wall
            dx = -dx;
        }

        if (ypos >= 700 - height || ypos <= 0) { //top and bottom wa;;
            dy = -dy;
        }
        rec = new Rectangle(xpos,ypos,width,height);

    }

    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000 && dx > 0) { // right to left
            xpos = 0 - width;
        }

        if (xpos <= 0 - width && dx < 0) { //left to right
            xpos = 1000;
        }

        if (ypos >= 700 && dy > 0) { //teleport from bottom to top
            ypos = -height;
        }

        if (ypos <= -height && dy <0) {// teleport top to bottom
            ypos = 700;
        }
        rec = new Rectangle(xpos,ypos,width,height);

    }
}







