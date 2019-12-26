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
public class GUI extends JFrame implements ActionListener, KeyListener
{	
	//Main frame variables
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static JPanel biggerPanel = new JPanel();
	private static JPanel radarPanel = new JPanel();
   	
	//Variables(Dynamically allocates map, aircraft, and striking into memory)
    private static int[][] map = new int[10][10];//First player's map
    private static int[][] aircrafts = new int[100][2];//First player's aircrafts
    private static int[] strike = new int[2];//First player's strike
    //private static int targets;
    //private static int mapSize;
    
    
    //Counter for attempts and successful strikes
    private static int attempts=0;
    private static int strikeHit=0;
    private static int tries = 100;
    private static int numberOfAircrafts = 50;
    
    //private static JButton unknown = new JButton();
    private static JTextField rowInput = new JTextField(60);
	private static JTextField colInput = new JTextField(60);
	private static JLabel rowRadar = new JLabel();
    private static JLabel colRadar = new JLabel();
    private static JLabel attemptsLabel = new JLabel();
    private static JLabel strikeHitLabel = new JLabel();
    
    //Popup dialog
    private static JFrame frame = new JFrame();
    
    //GUI constructor
	public GUI()
	{
		//unknown.addActionListener(this);

        //Create Map
        createMap(map);//Creates first player's map
        
        //Create Aircrafts
        createAirCrafts(aircrafts);//Creates first player's aircrafts

		setSize(WIDTH, HEIGHT);
		setTitle("Missile Strike");
		getContentPane().setBackground(Color.BLACK);
    	
		
		setLayout(new BorderLayout());
		
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
		
		//JLabel label1 = new JLabel("Missile Strike");
		//add(label1, BorderLayout.NORTH);
		
		//JLabel label2 = new JLabel("Created by Ravi Gowda");
		//add(label2, BorderLayout.SOUTH);
		
		//JPanel biggerPanel = new JPanel();
		biggerPanel.setLayout(new GridLayout(10, 10));
		biggerPanel.setBackground(Color.RED);
		//biggerPanel.setSize(1, 1);
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
        //heading.setSize(800, 20);
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
		radarPanel.setVisible(false);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.setBackground(Color.BLACK);
		bottomPanel.add(inputPanel, BorderLayout.CENTER);
		
		JLabel choose = new JLabel();
		choose.setText("<html><font color = 'red'>Row<br>Column</html>");
		bottomPanel.add(choose, BorderLayout.WEST);
		
		
		//rowInput.addActionListener(this);
		inputPanel.add(rowInput, BorderLayout.NORTH);
		rowInput.addKeyListener(this);
		
		//colInput.addActionListener(this);
		inputPanel.add(colInput, BorderLayout.SOUTH);
		colInput.addKeyListener(this);
		
		bottomPanel.add(inputPanel);
		
		
		JButton submit = new JButton("Strike!");
		submit.addActionListener(this);
		submit.addKeyListener(this);
		bottomPanel.add(submit, BorderLayout.EAST);
		
	
		add(biggerPanel, BorderLayout.CENTER);
		
		showMap(map);
		setResizable(false);
		setVisible(true);
		
	}
	
	//Create Map
    public static void createMap(int[][] map)
    {
        for(int row=0 ; row < 10 ; row++ )
            for(int col=0 ; col < 10 ; col++ )
                map[row][col]=-1;//Initializes map to -1 for all locations
    }
   
    //Show Map
    public static void showMap(int[][] map)
    {
    	biggerPanel.removeAll();
    	ImageIcon unknownGIF = new ImageIcon("unknown.gif");
    	ImageIcon missGIF = new ImageIcon("miss.gif");
    	ImageIcon hitGIF = new ImageIcon("hit.gif");
    	//

        for(int row=0 ; row < 10 ; row++ )
            for(int col=0 ; col < 10 ; col++ )
            {
            	if(map[row][col]==-1)
            	{            		
            		//unknown.setBackground(Color.RED);
            		//unknown.setIcon(unknownGIF);
            		JLabel unknown = new JLabel();
            		unknown.setIcon(unknownGIF);
            		biggerPanel.add(unknown);
            	}
                else if(map[row][col]==0)
                {
            		JLabel miss = new JLabel();
            		miss.setIcon(missGIF);
            		biggerPanel.add(miss);
                }
                else if(map[row][col]==1)
                {
            		JLabel hit = new JLabel();
            		hit.setIcon(hitGIF);
            		biggerPanel.add(hit);
                }
            }
        biggerPanel.revalidate();
		if(strikeHit==numberOfAircrafts)
		{
			JOptionPane.showMessageDialog(frame, "You Win");
			createMap(map);
			showMap(map);
		}

    }
	
    //Create Aircrafts
	public static void createAirCrafts(int[][] aircrafts)
    {
    	
        Random random = new Random();
        //Generates random locations for aircrafts on map
        for(int aircraft=0 ; aircraft < numberOfAircrafts ; aircraft++)
        {
        	aircrafts[aircraft][0]=random.nextInt(10);
        	aircrafts[aircraft][1]=random.nextInt(10);
            
            //Checks to see that no two aircrafts are located in the same position
            for(int last=0 ; last < aircraft ; last++)
            {
                if( (aircrafts[aircraft][0] == aircrafts[last][0])&&(aircrafts[aircraft][1] == aircrafts[last][1]) )
                    do
                    {
                        aircrafts[aircraft][0]=random.nextInt(10);
                        aircrafts[aircraft][1]=random.nextInt(10);
                    }
                    while( (aircrafts[aircraft][0] == aircrafts[last][0])&&(aircrafts[aircraft][1] == aircrafts[last][1]) );
            }
            
        }
    }
	
	//Create Radar
    public static void radar(int[] strike, int[][] aircrafts)
    {
        int row=0,
            col=0;
        
        for(int line=0 ; line < numberOfAircrafts ; line++)
        {
            if(aircrafts[line][0]==strike[0])
                row++;
            if(aircrafts[line][1]==strike[1])
                col++;
        }
        rowRadar.removeAll();
		rowRadar.setText("<html><font color = 'red'>Row " + (strike[0]+1) + ": " + row + " possible targets.</font></html>");
		rowRadar.revalidate();
		
        colRadar.removeAll();
		colRadar.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Column " + (strike[1]+1) + ": " + col + " possible tatgets.</font></html>");
		colRadar.revalidate();
		
		attemptsLabel.removeAll();
		attemptsLabel.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Attempts: " + attempts + "</font></html>");
		attemptsLabel.revalidate();
		
		strikeHitLabel.removeAll();
		strikeHitLabel.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Planes Hit: " + strikeHit + "</font></html>");
		strikeHitLabel.revalidate();
		
		radarPanel.setVisible(true);
		
		
		
        //JOptionPane.showMessageDialog(frame, "Radar" + "\nRow: " + (strike[0]+1) + " has " + row + " possible targets." + "\nColumn: " + (strike[1]+1) + " has " + col + " possible tatgets.");
    }
    
    //Determine whether a target is hit
    public static boolean strikeHit(int[] strike, int[][] aircrafts)
    {
        
        for(int aircraft=0 ; aircraft<numberOfAircrafts ; aircraft++)
        {
            if( strike[0]==aircrafts[aircraft][0] && strike[1]==aircrafts[aircraft][1])
            {
                //System.out.printf("You hit an aircraft located in (%d,%d)\n",strike[0]+1,strike[1]+1);                
            	strikeHit++;
                return true;

            }
        }
        return false;
    }
   
    //Refresh the map
    public static void changeMap(int[] strike, int[][] aircrafts, int[][] map)
    {
        if(strikeHit(strike,aircrafts))
        {
            map[strike[0]][strike[1]]=1;
        }
        else
        {
            map[strike[0]][strike[1]]=0;
        }
        showMap(map);
    }
    
    //Easy mode
	public static void easy()
	{
		JOptionPane.showMessageDialog(frame, "You have 50 attempts to take down 10 aircrafts.");
		createMap(map);
		showMap(map);
		attempts=0;
		strikeHit=0;
		tries = 50;
		numberOfAircrafts = 10;
		radarPanel.setVisible(false);
	}
	
	//Medium mode
	public static void medium()
	{
		JOptionPane.showMessageDialog(frame, "You have 50 attempts to take down 20 aircrafts.");
		createMap(map);
		showMap(map);		
		attempts=0;
		strikeHit=0;
		tries = 50;
		numberOfAircrafts = 20;
		radarPanel.setVisible(false);
	}
	
	//Hard mode
	public static void hard()
	{
		JOptionPane.showMessageDialog(frame, "You have 50 attempts to take down 30 aircrafts.");
		createMap(map);
		showMap(map);		
		attempts=0;
		strikeHit=0;
		tries = 50;
		numberOfAircrafts = 30;
		radarPanel.setVisible(false);
	}
	
	//Fires action event
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("New Game"))
		{
			createMap(map);
			showMap(map);
			attempts=0;
			strikeHit=0;
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
			AudioClip miss;
			try {
				miss = Applet.newAudioClip(new URL("file:miss.wav"));
				miss.play();

			} catch (Exception e1) {
			}
			attempts++;
			if(attempts>tries)
			{
				JOptionPane.showMessageDialog(frame, "You Lose");
				createMap(map);
				showMap(map);
			}
			Integer xValue = Integer.valueOf(rowInput.getText());
			strike[0] = xValue;
			strike[0]--;
			Integer yValue = Integer.valueOf(colInput.getText());
			strike[1] = yValue;
			strike[1]--;
			changeMap(strike, aircrafts, map);
			radar(strike, aircrafts);
			

		}
	}
	
	//Fires key event
    public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
        	AudioClip miss;
			try {
				miss = Applet.newAudioClip(new URL("file:miss.wav"));
				miss.play();

			} catch (Exception e1) {
			}
			attempts++;
			if(attempts>tries)
			{
				JOptionPane.showMessageDialog(frame, "You Lose");
				createMap(map);
				showMap(map);
			}
			Integer xValue = Integer.valueOf(rowInput.getText());
			strike[0] = xValue;
			strike[0]--;
			Integer yValue = Integer.valueOf(colInput.getText());
			strike[1] = yValue;
			strike[1]--;
			changeMap(strike, aircrafts, map);
			radar(strike, aircrafts);
        }
    }

	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) 
{
		// TODO Auto-generated method stub
		
	}
	

}