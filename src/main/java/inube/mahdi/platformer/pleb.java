package inube.mahdi.platformer;


import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class pleb extends Creature {
	boolean istriggered=false;
	
	public Image pleb=  new Image(getClass().getResource("pleb.png").toExternalForm());
	public ImageView mypleb = new ImageView(pleb);
	public boolean dirisRight=true;
	public pleb(world world) {
		
		super(world);
		transform = mypleb;
		System.out.println("hi negga");
	}

	public void moveY(int value,ArrayList<Node> platforms) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (transform.getTranslateY() + 50 == platform.getTranslateY()) {
                        	transform.setTranslateY(transform.getTranslateY() - 1);
                            setcanJump(true);
                            return;
                        }
                    }
                    else {
                        if (transform.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            transform.setTranslateY(transform.getTranslateY() + (movingDown ? 1 : -1));
        }
    }			

	public void moveX(ArrayList<Node> platforms) 
	{
            for (Node platform : platforms) {
                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {

                        if (transform.getTranslateX() + 60 >= platform.getTranslateX()) {
                        	dirisRight=!dirisRight;
                        	
                        //    return;
                        }
          
                    }
                }
            
            transform.setTranslateX(transform.getTranslateX() + (dirisRight ? 0.5 : -0.5));
        //    player.setTransform();
      
    }
	


}
