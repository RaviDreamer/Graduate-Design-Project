import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/*
  	This component changes the map whenever a location has been struck.
  	Also this component changes values on the map to indicate what icon should be displayed.
*/
public class ChangeMap 
{
    public static void change(int[] strike, int[][] aircrafts, int[][] map)
    {
        if(StrikeHit.hit(strike,aircrafts))
        {
            map[strike[0]][strike[1]]=1;
            AudioClip hitSound;
			try {
				hitSound = Applet.newAudioClip(new URL("file:hit.wav"));
				hitSound.play();

			} catch (Exception e1) {
			}
        }
        else
        {
            map[strike[0]][strike[1]]=0;
            AudioClip missSound;
			try {
				missSound = Applet.newAudioClip(new URL("file:miss.wav"));
				missSound.play();

			} catch (Exception e1) {
			}
        }
        ShowMap.show(map);
    }
}
