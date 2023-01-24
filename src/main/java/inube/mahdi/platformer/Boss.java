package inube.mahdi.platformer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boss extends Creature implements smartentitymouvement {


	Node playertarget;
	public int lives=7 ;
	public boolean isalert=false;
	public boolean isjumping=false;
	public boolean canjump=true;
	public boolean isAlive=true;
	public boolean ischarging=false;
	public boolean isresting=false;
	public boolean targetaquired=false;
    public Timer timer = new Timer("actionsimer");

	public Boss(world myworld,ImageView bosspic)
	{	
	super(myworld);
	
	this.transform=bosspic;
	}
	public void aquiretarget(Node playertarget)
	{
		this.playertarget=playertarget;
		targetaquired=true;

	}
	public void charge(ArrayList<Node> platforms)
	{
	
		Long chargingtime = 4000L;
		ischarging = true;
		
		TimerTask quitcharging = new TimerTask() {public void run() {ischarging = false;return;}};
		timer.schedule(quitcharging,chargingtime);
	}
	public void rest(ArrayList<Node> platforms)
	{
		
		TimerTask rest = new TimerTask() {public void run() {isresting=false;}};
		isresting=true;
	    long delay = 500L;
	    timer.schedule(rest, delay);
	}
	

    public void jump() {
    
        if (canJump) {
            setVelocity(Velocity.add(0, -35));
            isjumping=true;
            setcanJump(false);
        }
    }
	public boolean isinaction()
	{
		return ischarging || isresting;
	}
	
	
	
	
	@Override
	public void moveY(int value,ArrayList<Node> platforms) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (transform.getTranslateY() + 280 == platform.getTranslateY()) {
                        	transform.setTranslateY(transform.getTranslateY() - 1);
                        	isjumping=false;
                            setcanJump(true);
                            return;
                        }
                    }
                    else {
                        if (transform.getTranslateY() == platform.getTranslateY() + 280) {
                            return;
                        }
                    }
                }
            }
            transform.setTranslateY(transform.getTranslateY() + (movingDown ? 0.5 : -0.5));
        }
    }			
	@Override
	public void moveX(int value,ArrayList<Node> platforms) {
        boolean movingRight = value > 0;

            for (Node platform : platforms) {
                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (transform.getTranslateX() + 150 == platform.getTranslateX())  {
                     
                            return;
                        }
                    }
                    else {
                        if (transform.getTranslateX() == platform.getTranslateX() + 60) {
                        
                            return;
                        }
                    }
                }
            }
            transform.setTranslateX(transform.getTranslateX() + (movingRight ? 2: -2));
        //    player.setTransform();
        
    }

	

	
	
	
	
}
