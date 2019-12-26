import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Title extends JFrame implements ActionListener
{
	public Title()
	{
		setTitle("Missile Strike");
		setSize(800, 600);
		setVisible(true);
		setLayout(new GridLayout(2,1));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		JLabel titleName = new JLabel();
		titleName.setText("MISSILE STRIKE");
		Font titleFont = new Font("Impact", Font.BOLD, 72);
		titleName.setFont(titleFont);
		top.add(titleName);
		top.setBackground(Color.RED);
		add(top);
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		bottom.setBackground(Color.BLACK);
		JButton start = new JButton();
		start.setText("Start");
		start.addActionListener(this);
		bottom.add(start);
		JButton rules = new JButton();
		rules.setText("Rules");
		rules.addActionListener(this);
		bottom.add(rules);
		JButton exit = new JButton();
		exit.setText("Exit");
		exit.addActionListener(this);
		bottom.add(exit);
		add(bottom);
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand == "Start")
		{
			new MissileStrike();
		}
		if(actionCommand == "Rules")
		{	
			JOptionPane.showMessageDialog(new JFrame(), "Rules:"
					+ "\n"
					+ "Player must choose difficulty to start game mode.\n"
					+ "You may strike location by inputting values and clicking strike.\n"
					+ "Alternatively you maye use the 'Enter' key to make strikes.\n"
					+ "To win, eliminate all aircrafts on the board.\n"
					+ "Good Luck!\n");
		}
		if(actionCommand == "Exit")
		{
			System.exit(0);
		}
		
	}
}

