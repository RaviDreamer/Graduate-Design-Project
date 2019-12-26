/*
  	This component determined whether an aircraft has been struck.
  	If it has then the strikeHit variable is incremented and the component returns true.
  	Otherwise it returns false.
*/
public class StrikeHit 
{
    public static boolean hit(int[] strike, int[][] aircrafts)
    {
        
        for(int aircraft=0 ; aircraft<MissileStrike.numberOfAircrafts ; aircraft++)
        {
            if( strike[0]==aircrafts[aircraft][0] && strike[1]==aircrafts[aircraft][1])
            {               
            	MissileStrike.strikeHit++;
                return true;

            }
        }
        return false;
    }
}
