/*
 	This component creates the Map.  It is called whenever a new game or difficulty is selected.
 	The component loops the row and columns from 0-10 to initialize it to a starting value
*/
public class CreateMap 
{
	public static void create(int[][] map)
    {
        for(int row=0 ; row < 10 ; row++ )
            for(int col=0 ; col < 10 ; col++ )
                map[row][col]=-1;//Initializes map to -1 for all locations
    }
}
