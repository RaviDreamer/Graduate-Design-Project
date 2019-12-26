/*
  	This component gives helpful hints for the player.
  	The goal of this component is to indicate how many aircrafts are in a designated row and column.
  	It also indicated how many planes are hit and the number of attempts.
*/
public class Radar 
{
	public static void show(int[] strike, int[][] aircrafts)
    {
        int row=0,
            col=0;
        
        for(int line=0 ; line < MissileStrike.numberOfAircrafts ; line++)
        {
            if(aircrafts[line][0]==strike[0])
                row++;
            if(aircrafts[line][1]==strike[1])
                col++;
        }

        MissileStrike.rowRadar.removeAll();
        MissileStrike.rowRadar.setText("<html><font color = 'red'>Row " + (strike[0]+1) + ": " + row + " possible targets.</font></html>");
        MissileStrike.rowRadar.revalidate();
		
        MissileStrike.colRadar.removeAll();
        MissileStrike.colRadar.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Column " + (strike[1]+1) + ": " + col + " possible targets.</font></html>");
        MissileStrike.colRadar.revalidate();
   
		MissileStrike.attemptsLabel.removeAll();
		int attemptsRemaining = MissileStrike.tries-MissileStrike.attempts;
		MissileStrike.attemptsLabel.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Attempts Remaining: " + attemptsRemaining + "</font></html>");
		MissileStrike.attemptsLabel.revalidate();
		
		MissileStrike.strikeHitLabel.removeAll();
		int planesRemaining = MissileStrike.numberOfAircrafts-MissileStrike.strikeHit;
		MissileStrike.strikeHitLabel.setText("<html><font color = 'red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Planes Remaining: " + planesRemaining + "</font></html>");
		MissileStrike.strikeHitLabel.revalidate();

	
    }

}
