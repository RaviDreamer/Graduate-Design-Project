/*
  This component displays the user interface.  
  In addition it includes the functionality of the strike button as well as the variables for the game.
  This component extends the main frame so that creating one for it is not necessary.
  Also the component implements an ActionListener and KeyListener for user actions.
  KeyListener is used only for the Enter key
*/

//Packages used
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;



//Main Class
public class MissileStrike extends JFrame implements ActionListener, KeyListener
{	
	public static int difficulty = 0;
	public static JLabel timerLabel = new JLabel();
	//Main frame variables
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static JPanel biggerPanel = new JPanel();
	public static JPanel radarPanel = new JPanel();
   	
	//Variables(Dynamically allocates map, aircraft, and striking into memory)
    private static int[][] map = new int[10][10];//First player's map
    private static int[][] aircrafts = new int[100][2];//First player's aircrafts
    private static int[] strike = new int[2];//First player's strike
    
    
    //Counter for attempts and successful strikes
    public static int attempts=0;
    public static int tries = 100;
    public static int strikeHit=0;
    public static int numberOfAircrafts = 10;
    
    //Labels for input, attempts, and radar
    public static JTextField rowInput = new JTextField(60);
	public static JTextField colInput = new JTextField(60);
	public static JLabel rowRadar = new JLabel();
    public static JLabel colRadar = new JLabel();
    public static JLabel attemptsLabel = new JLabel();
    public static JLabel strikeHitLabel = new JLabel();
    public static JLabel unknown = new JLabel();
    
    //Frame for pop up dialogs
    public static JFrame frame = new JFrame();
    
    //Constructor for the GUI
	public MissileStrike()
	{
		addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						if(Clock.numSecond>0)
						{
							Clock.timer.stop();
						}
					}
				});
        //Create Map
        CreateMap.create(map);
        
        //Create Aircrafts
        CreateAircrafts.create(aircrafts);
        
        //Color and size
		setSize(WIDTH, HEIGHT);
		setTitle("Missile Strike");
		getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		
		//Menu Bar
		JMenu startMenu = new JMenu("Start");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		startMenu.add(newGame);
		JMenuItem stat = new JMenuItem("Statistics");
		stat.addActionListener(this);
		startMenu.add(stat);
		JMenuItem exit= new JMenuItem("Exit");
		exit.addActionListener(this);
		startMenu.add(exit);
		JMenu difficultyMenu = new JMenu("Choose Difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		easy.addActionListener(this);
		difficultyMenu.add(easy);
		JMenuItem medium = new JMenuItem("Medium");
		medium.addActionListener(this);
		difficultyMenu.add(medium);
		JMenuItem hard = new JMenuItem("Hard");
		hard.addActionListener(this);
		difficultyMenu.add(hard);
		JMenuBar bar = new JMenuBar();
		bar.add(startMenu);
		bar.add(difficultyMenu);
		setJMenuBar(bar);
		
		//Main Panel
		biggerPanel.setLayout(new GridLayout(10, 10));
		biggerPanel.setBackground(Color.RED);
        JLabel row = new JLabel("<html><font color = 'red'>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;9 "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10"
        		+ "</font></html>");
        add(row, BorderLayout.NORTH);
        JLabel column = new JLabel("<html><font color = 'red'>1"
        		+ "<br><br><br>2"
        		+ "<br><br><br>3"
        		+ "<br><br><br>4"
        		+ "<br><br><br>5"
        		+ "<br><br><br>6"
        		+ "<br><br><br>7"
        		+ "<br><br><br>8"
        		+ "<br><br><br>9"
        		+ "<br><br><br>10</font></html>");
        add(column, BorderLayout.WEST);
        
        //Radar Panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBackground(Color.BLACK);
		add(bottomPanel, BorderLayout.SOUTH);
		radarPanel.setLayout(new FlowLayout());
		radarPanel.setBackground(Color.BLACK);
		bottomPanel.add(radarPanel, BorderLayout.NORTH);
		radarPanel.add(rowRadar);
		radarPanel.add(colRadar);
		radarPanel.add(attemptsLabel);
		radarPanel.add(strikeHitLabel);
		radarPanel.add(timerLabel);
		radarPanel.setVisible(false);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.setBackground(Color.BLACK);
		bottomPanel.add(inputPanel, BorderLayout.CENTER);
		JLabel choose = new JLabel();
		choose.setText("<html><font color = 'red'>Row<br>Column</html>");
		bottomPanel.add(choose, BorderLayout.WEST);
		inputPanel.add(rowInput, BorderLayout.NORTH);
		rowInput.addKeyListener(this);
		inputPanel.add(colInput, BorderLayout.SOUTH);
		colInput.addKeyListener(this);
		bottomPanel.add(inputPanel);
		JButton submit = new JButton("Strike!");
		submit.addActionListener(this);
		submit.addKeyListener(this);
		bottomPanel.add(submit, BorderLayout.EAST);
		add(biggerPanel, BorderLayout.CENTER);
		
		//Set frame visibility and fixed size
		setResizable(false);
		setVisible(true);
		newGame();
	}

	//New game
    public static void newGame()
    {
		if (Clock.numSecond>0)
		{
			Clock.reset();
		}
		timerLabel.setVisible(false);
    	radarPanel.setVisible(false);
    	JOptionPane.showMessageDialog(frame, "Welcome to Missile Strike!\nPlease select a difficulty.");
		CreateMap.create(map);
		CreateAircrafts.create(aircrafts);
		ShowMap.show(map);
		attempts=0;
		strikeHit=0;
		Radar.show(strike, aircrafts);
    }
    
    //Easy mode
	public void easy()
	{
		if (Clock.numSecond>0)
		{
			Clock.reset();
		}
		timerLabel.setVisible(true);
		radarPanel.setVisible(false);
		difficulty = 1;
		JOptionPane.showMessageDialog(frame, "You have 50 attempts to take down 10 aircrafts within 180 seconds.");
		CreateMap.create(map);
		CreateAircrafts.create(aircrafts);
		ShowMap.show(map);
		attempts=0;
		strikeHit=0;
		tries = 50;
		numberOfAircrafts = 10;
		Radar.show(strike, aircrafts);
		Clock.start();
	}
	
	//Medium mode
	public void medium()
	{
		if (Clock.numSecond>0)
		{
			Clock.reset();
		}
		timerLabel.setVisible(true);
		radarPanel.setVisible(false);
		difficulty = 2;
		JOptionPane.showMessageDialog(frame, "You have 40 attempts to take down 10 aircrafts within 120 seconds.");
		CreateMap.create(map);
		CreateAircrafts.create(aircrafts);
		ShowMap.show(map);		
		attempts=0;
		strikeHit=0;
		tries = 40;
		numberOfAircrafts = 10;
		Radar.show(strike, aircrafts);
		Clock.start();	
	}
	
	//Hard mode
	public void hard()
	{
		if (Clock.numSecond>0)
		{
			Clock.reset();
		}
		timerLabel.setVisible(true);
		radarPanel.setVisible(false);
		difficulty = 3;
		JOptionPane.showMessageDialog(frame, "You have 30 attempts to take down 10 aircrafts within 60 seconds.");
		CreateMap.create(map);
		CreateAircrafts.create(aircrafts);
		ShowMap.show(map);		
		attempts=0;
		strikeHit=0;
		tries = 30;
		numberOfAircrafts = 10;
		Radar.show(strike, aircrafts);
		Clock.start();
	}
	
	public void strike()
	{
		attempts++;
		if(attempts>tries)
		{
			int retry = JOptionPane.showConfirmDialog(frame, "Would you like to retry?", "You Lose :(", JOptionPane.YES_NO_OPTION);
			if(retry== JOptionPane.YES_OPTION)
			{
				newGame();
			}
			Clock.timer.stop();
		}
		Integer xValue = Integer.valueOf(rowInput.getText());
		strike[0] = xValue;
		strike[0]--;
		Integer yValue = Integer.valueOf(colInput.getText());
		strike[1] = yValue;
		strike[1]--;
		ChangeMap.change(strike, aircrafts, map);
		Radar.show(strike, aircrafts);
		if(strikeHit==numberOfAircrafts)
		{
			Clock.timer.stop();
			int playAgain = JOptionPane.showConfirmDialog(frame, "Would you like to play again?", "You Win!", JOptionPane.YES_NO_OPTION);
			if(playAgain == JOptionPane.YES_OPTION)
			{
				newGame();
			}
		}
		MissileStrike.radarPanel.setVisible(true);

	}
	
	
	//Fires action event
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("New Game"))
		{
			newGame();
		}
		if(actionCommand.equals("Statistics"))
		{
			
			frame.setBackground(Color.BLACK);
			JOptionPane.showMessageDialog(frame, "Attempts = " + attempts + "\nPlanes Hit = " + strikeHit);
		}
		if(actionCommand.equals("Exit"))
		{
			System.exit(0);
		}
		if(actionCommand.equals("Easy"))
		{
			easy();
		}
		if(actionCommand.equals("Medium"))
		{
			medium();
		}
		if(actionCommand.equals("Hard"))
		{
			hard();
		}
		if(actionCommand.equals("Strike!"))
		{	
			strike();
		}
			
	}
	//Fires key event
    public void keyPressed(KeyEvent evt)
    {
    	if(evt.getKeyCode() == KeyEvent.VK_ENTER)
    	{
    		 strike();
    	}
       
    }

	public void keyReleased(KeyEvent e) 
	{
		//Nothing to compute
		
	}

	public void keyTyped(KeyEvent e) 
	{
		//Nothing to compute
		
	}
}

