/*
	This component generates the aircrafts for the map.
	Each aircraft is randomly placed on an integer 0-10 for both row and column.
	These integers locate the placing of the aircraft on the map.
*/

//Package used for randomization
import java.util.Random;

public class CreateAircrafts 
{
	public static void create(int[][] aircrafts)
    {
    	
        Random random = new Random();
        //Generates random locations for aircrafts on map
        for(int aircraft=0 ; aircraft < MissileStrike.numberOfAircrafts ; aircraft++)
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
}
