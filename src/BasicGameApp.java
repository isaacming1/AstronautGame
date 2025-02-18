//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;



//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

	//Variable Definition Section
	//Declare the variables used in the program
	//You can set their initial values too

	//Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;

	public BufferStrategy bufferStrategy;
	public Image astroPic;
	public Image spongebobPic;

	public Image pineapplePic;

	public Image backgroundPic;

	//Declare the objects used in the program
	//These are things that are made up of more than one variable type
	public Game astro;
	public Game spongebob;

	public Game pineapple;

	public boolean spongebobAstroCrashing = false;

	public boolean pineappleSpongebobCrashing = false;

	// Main method definition
	// This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


	// This section is the setup portion of the program
	// Initialize your variables and construct your program objects here.
	public BasicGameApp() { // BasicGameApp constructor

		setUpGraphics();

		canvas.addKeyListener(this);


		//variable and objects
		//create (construct) the objects needed for the game and load up
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		astro = new Game("astro", 10, 100); //construct the astronaut

		//adjust the size
		astro.width = 100;
		astro.height = 100;


		spongebobPic = Toolkit.getDefaultToolkit().getImage("spongebob.png");
		spongebob = new Game("spongebob", 200, 300);

		//adjust the size
		spongebob.width = 100;
		spongebob.height = 100;

		//call pineapple
		pineapplePic = Toolkit.getDefaultToolkit().getImage("pineapple.png");
		pineapple = new Game("pineapple", 300, 400);

		//adjust the size
		pineapple.width = 250;
		pineapple.height = 280;
		pineapple.dx = 5;
		pineapple.dy = 5;

		backgroundPic = Toolkit.getDefaultToolkit().getImage("background2.png");
	} // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

	// main thread
	// this is the code that plays the game after you set things up
	public void run() {

		//for the moment we will loop things forever.
		while (true) {
			moveThings();  //move all the game objects
			crash();
			render();  // paint the graphics
			pause(20); // sleep for 10 ms
		}
	}

	public void moveThings() {
		//calls the move( ) code in the objects

		astro.bounce();
		spongebob.bounce();
		pineapple.move();

	}

	//boolean so astronaut and spongebob do not glitch
	public void crash() {
		if (astro.rec.intersects(spongebob.rec) && spongebobAstroCrashing == false) {
			//change picture
			//make the direction change
			astro.dx = -astro.dx;
			spongebob.dx = -spongebob.dx;
			spongebobAstroCrashing = true;
		}
		if (astro.rec.intersects(spongebob.rec) == false) {
			spongebobAstroCrashing = false;
		}
		//boolean so pineapple and spongebob do not glitch

		if (pineapple.rec.intersects(spongebob.rec) && pineappleSpongebobCrashing == false) {

			spongebob.dx = -spongebob.dx;
			pineappleSpongebobCrashing = true;
		}

		if (pineapple.rec.intersects(spongebob.rec) == false) {
			pineappleSpongebobCrashing = false;

		}


	}

	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	//Graphics setup method
	private void setUpGraphics() {
		frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

		panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
		panel.setLayout(null);   //set the layout

		// creates a canvas which is a blank rectangular area of the screen onto which the application can draw
		// and trap input events (Mouse and Keyboard events)
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);  // adds the canvas to the panel.

		// frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
		frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
		frame.setResizable(false);   //makes it so the frame cannot be resized
		frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

		// sets up things so the screen displays images nicely.
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");
	}

	//Paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

		//draw the image of the astronaut
		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);

		g.drawImage(spongebobPic, spongebob.xpos, spongebob.ypos, spongebob.width, spongebob.height, null);
		g.drawImage(pineapplePic, pineapple.xpos, pineapple.ypos, pineapple.width, pineapple.height, null);
		//g.drawRect(pineapple.rec.x,pineapple.rec.y,pineapple.rec.width,pineapple.rec.height);

		g.dispose();
		bufferStrategy.show();
	}


	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		char key = event.getKeyChar();     //gets the character of the key pressed
		int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
		System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

		if (keyCode == 68) { //d
			pineapple.right = true;

		}
		if (keyCode == 83) { //s
			pineapple.down = true;
		}

		if (keyCode == 87) { // w
			pineapple.up = true;
		}

		if (keyCode == 65) { //a
			pineapple.left = true;
		}


	}

	@Override
	public void keyReleased(KeyEvent event) {

		char key = event.getKeyChar();
		int keyCode = event.getKeyCode();
		//This method will do something when a key is released
		if (keyCode == 68) {
			pineapple.right = false;
		}
		if (keyCode == 83) {
			pineapple.down = false;
		}

		if (keyCode == 65) {
			pineapple.left = false;

		}

		if (keyCode == 87) {
			pineapple.up = false;

		}

	}

}

