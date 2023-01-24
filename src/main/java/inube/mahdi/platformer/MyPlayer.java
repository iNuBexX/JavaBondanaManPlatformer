package inube.mahdi.platformer;



import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;


public class MyPlayer extends Creature{
	 
	public int speed=5;
	private world myworld;
	public int lives =3;
	public boolean isdead=false;
	public int coins=0;
	public static Integer score=0;
	public Image bondanaman;
	
	public MyPlayer(world world,Image bondanaman)
	{
		super(world);
		this.bondanaman=bondanaman;
		this.myworld = world;
	
		
			// image = new Image("/platformerproject/bondanaMan.jpg");
			 ImageView imageView = new ImageView(bondanaman); 
			 transform = imageView;
			
		    	transform.translateXProperty().addListener((obs, old, newValue) -> {
		        int offset = newValue.intValue();

		        if (offset > 640 && offset < world.levelWidth - 640) {
		            gameRoot.setLayoutX(-(offset - 640));
		        }
		    });
 
	   world.addtoworld(transform);
	   
	   if(world.boss != null)
	   world.boss.aquiretarget(transform);		
	}
	public void movePlayerY(int value,ArrayList<Node> platforms) {
	        boolean movingDown = value > 0;

	        for (int i = 0; i < Math.abs(value); i++) {
	            for (Node platform : platforms) {
	                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {
	                    if (movingDown) {
	                        if (transform.getTranslateY() + 40 == platform.getTranslateY()) {
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
	public void movePlayerX(int value,ArrayList<Node> platforms) {
	        boolean movingRight = value > 0;

	        for (int i = 0; i < Math.abs(value); i++) {
	            for (Node platform : platforms) {
	                if (transform.getBoundsInParent().intersects(platform.getBoundsInParent())) {
	                    if (movingRight) {
	                        if (transform.getTranslateX() + 40 == platform.getTranslateX()) {
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
	            transform.setTranslateX(transform.getTranslateX() + (movingRight ? 1 : -1));
	        //    player.setTransform();
	        }
	    }
	public void respawn()
	{
	
		
			// could be useless
			 gameRoot.getChildren().remove(transform);
			 ImageView imageView = new ImageView(bondanaman); 
			 transform = imageView;
			 transform.setTranslateX(myworld.initialplayerposition.getX());
			 transform.setTranslateY(myworld.initialplayerposition.getY());
			 gameRoot.setLayoutX(0);
			 transform.translateXProperty().addListener((obs, old, newValue) -> {
			        int offset = newValue.intValue();

			        if (offset > 640 && offset < myworld.levelWidth - 640) {
			            gameRoot.setLayoutX(-(offset - 640));
			        }
			    });
			 System.out.println("done respawning");
			 
		 
		myworld.addtoworld(transform);
	}
	public void collectcoins(ArrayList<Node> worldcoins)
	{
		   for (Node coin : worldcoins) {
               if (transform.getBoundsInParent().intersects(coin.getBoundsInParent())) {
            	   
            	   coin.getProperties().put("alive", false);
            	   coins++;
            	   score+=Coin.getCoinvalue();
            	   System.out.println("coins!!:"+coins);
            	  
            	   
               	}
              }
		   myworld.coinsCheck();
	}
	
	
	
    public void jumpPlayer() {
        if (canJump) {
            setVelocity(Velocity.add(0, -34));
            setcanJump(false);
        }
    }
    public static Integer getscore() {return score;};
   


}
