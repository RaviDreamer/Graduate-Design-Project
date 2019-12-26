/*
	This component displays the map for the player.
	Icons are used to indicate whether the player hasn't struck the particular location,
	whether the player missed a target, or whether the player has hit a target.
	The main panel needs to be revalidated in the end so new results can be shown to the player.
*/

//Packages used for icon and labels
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.color.*;

public class ShowMap implements ActionListener
{           		
	public static int [] rowNum;
	public static int [] colNum;
	public static void show(int[][] map)
    {
    	MissileStrike.biggerPanel.removeAll();
    	ImageIcon unknownGIF = new ImageIcon("unknown.gif");
    	ImageIcon missGIF = new ImageIcon("miss.gif");
    	ImageIcon hitGIF = new ImageIcon("hit.gif");
    	//

        for(int row=0 ; row < 10 ; row++ )
            for(int col=0 ; col < 10 ; col++ )
            {
            	if(map[row][col]==-1)
            	{  
            		JLabel unknown = new JLabel();
            		unknown.setBackground(Color.red);
            		unknown.setIcon(unknownGIF);
            		MissileStrike.biggerPanel.add(unknown);
            	}
                else if(map[row][col]==0)
                {
        			JLabel miss = new JLabel();
        			miss.setBackground(Color.red);
            		miss.setIcon(missGIF);
            		MissileStrike.biggerPanel.add(miss);
                }
                else if(map[row][col]==1)
                {
        			JLabel hit = new JLabel();
        			hit.setBackground(Color.red);
            		hit.setIcon(hitGIF);
            		MissileStrike.biggerPanel.add(hit);
                }
            }
        MissileStrike.biggerPanel.revalidate();

    }
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
