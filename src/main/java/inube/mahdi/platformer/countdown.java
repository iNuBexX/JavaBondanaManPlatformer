package inube.mahdi.platformer;



import java.util.ArrayList;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class countdown {

	
	private final Integer  startTime = 60;
	public Integer seconds = startTime;	
	public boolean epuise ;
	public 	Timeline time = new Timeline();
	public Label label1,label2;
	
    public void doTime(Pane uiRoot ) {
    //u need to initialize this timer 
    	time.getKeyFrames().clear();
    	/// the line above fixes a bug that occures if u don't use it basically everytime u redo dotime after pressing space ...
    	epuise =false;
    	seconds = startTime;
    	
    	label1 = new Label(" Starting..");
    	label1.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
    	label2 = new Label("ready...");
    	label2.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
    	HBox counting = new HBox(20);
    	counting.getChildren().addAll(label1,label2);
    	uiRoot.getChildren().add(counting);
    	String msg =MyPlayer.getscore().toString() ;
		label2.setText("Score :" + msg );	
		label1.setId("countdown");
		label2.setId("countdown");
		
	
		time.setCycleCount(Animation.INDEFINITE);
		
		//if(time != null ) {time.stop();}
		
		KeyFrame frame = new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				seconds--;
				label1.setText("countdown :"+ seconds.toString());
				if(seconds==0) {
					epuise=true;
					time.stop();
	
				}
				String msg =MyPlayer.getscore().toString() ;
				label2.setText("Score :" + msg );
				
				
			}
				
			
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
}
    public void stoptime()
    {
    	
    	time.stop();
    }
    public void finalscore()
    {
    	
    	label2.setText("Score :" +MyPlayer.getscore());	
    }
   
}
