package inube.mahdi.platformer;



import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;





public class Coin extends entity {
	
	public Image coin =  new Image(getClass().getResource("coin.png").toExternalForm());
	public ImageView mycoin = new ImageView(coin);
	private static Integer coinvalue = 100;
	public Coin()
	{
		transform=mycoin;	
		
	}
	public static Integer getCoinvalue() {
		return coinvalue;
	}

	


}
