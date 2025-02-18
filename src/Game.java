import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Game {

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
    public Rectangle rec;             //declares the rectangle hitbox

    public boolean right;

    public boolean down;

    public boolean up;

    public boolean left;


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Game(String pName, int pXpos, int pYpos) { // Game constructor
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

    } // end Game constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
//        xpos = xpos + dx;
//        ypos = ypos + dy;


        if (right ==true){
            xpos = xpos+dx;
            if (xpos>1000-width){
                xpos=1000-width;
            }
        }

        if(down == true){
            ypos = ypos +dy;
            if(ypos>700-height){
                ypos = 700-height;
            }
        }

        if(up==true){
            ypos = ypos-dy;
            if(ypos<0){
                ypos= 0;
            }
        }

        if(left == true){
            xpos = xpos -dx;
            if(xpos<0){
                xpos = 0;
            }
        }

        //declares rectangle
        rec = new Rectangle(xpos,ypos,width,height);



    } // end move



    // bounce command if statements

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
//wrap command if statements
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







