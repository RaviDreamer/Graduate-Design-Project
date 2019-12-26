import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Clock 
{
	public static Timer timer;
	public static int numSecond = 0;
	public static void start() 
	{
		
		timer = new Timer(1000, new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				numSecond++;
				MissileStrike.timerLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color = 'red'>Time spent:" + numSecond + "</font><html>");
				if (MissileStrike.difficulty == 1) 
				{
					if (numSecond == 181) 
					{
						String message = "You have failed to eliminate all targets in the time limit.";
						JOptionPane.showMessageDialog(new JFrame(), message,"Time is up!", JOptionPane.ERROR_MESSAGE);
						MissileStrike.timerLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color = 'red'>Planes gone!</font></html>");
	    				timer.stop();
	    				int playAgain = JOptionPane.showConfirmDialog(MissileStrike.frame, "Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);
	    				if(playAgain == JOptionPane.YES_OPTION)
	    				{
	    					MissileStrike.newGame();
	    				}
	    			}
				} 
				if (MissileStrike.difficulty == 2) 
				{
					if (numSecond == 121) 
					{
						String message = "You have failed to eliminate all targets in the time limit.";
						JOptionPane.showMessageDialog(new JFrame(), message,"Time is up!", JOptionPane.ERROR_MESSAGE);
						MissileStrike.timerLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color = 'red'>Planes gone!</font></html>");
	    				timer.stop();
	    				int playAgain = JOptionPane.showConfirmDialog(MissileStrike.frame, "Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);
	    				if(playAgain == JOptionPane.YES_OPTION)
	    				{
	    					MissileStrike.newGame();
	    				}
					}

				} 
				if (MissileStrike.difficulty == 3) 
				{
					if (numSecond == 61) 
					{
						String message = "You have failed to eliminate all targets in the time limit.";
						JOptionPane.showMessageDialog(new JFrame(), message,"Time is up!", JOptionPane.ERROR_MESSAGE);
						MissileStrike.timerLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color = 'red'>Planes gone!</font></html>");
	    				timer.stop();
	    				int playAgain = JOptionPane.showConfirmDialog(MissileStrike.frame, "Would you like to play again?", "You Lose!", JOptionPane.YES_NO_OPTION);
	    				if(playAgain == JOptionPane.YES_OPTION)
	    				{
	    					MissileStrike.newGame();
	    				}
					}

				} 
			}
		});
		timer.start();

	}
	public static void reset()
	{
		timer.stop();
		numSecond = 0;
	}
}
