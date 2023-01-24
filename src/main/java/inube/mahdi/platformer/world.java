package inube.mahdi.platformer;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


	public class world extends entity{
	private boolean bossdied=false;
	public Node finishMark;
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private ArrayList<Node> coins = new ArrayList<Node>();
    public Pane gameRoot;
    public int levelWidth;
    public LevelData data;
    public Point2D initialplayerposition = new Point2D(0,500);
    private Rectangle purplescreen;
    private ImageView deathscreenlabel;
    private ImageView deathscreenlabeltemps;
    private ImageView deathscreen;
    private ImageView bosspic;
    private ImageView deadbosspic;
    private Pane uiRoot;
    private ImageView finishtxt;
    public ArrayList<pleb> monsters = new ArrayList<pleb>();
	private Image platform;
	public Boss boss;
	private Label bosslives;
	public boolean hasboss=false;
    public world(LevelData a,Pane gameRoot,Rectangle purplescreen,ImageView deathscreenlabel,Pane uiRoot,ImageView finishtxt,Image image,ImageView deathscreenLabeltime,ImageView bosspic,Label bosslives,ImageView deadbosspic)
    {
    	this.deadbosspic=deadbosspic;
		this.bosslives = bosslives;
    	this.bosspic=bosspic;
    	this.deathscreenlabeltemps=deathscreenLabeltime;
    	this.platform=image;
    	this.finishtxt = finishtxt;
    	purplescreen.setOpacity(0.5);
    	this.uiRoot=uiRoot;
    	this.deathscreenlabel=deathscreenlabel;
    	this.purplescreen=purplescreen;
    	this.data = a;
    	levelWidth = a.LEVEL[0].length() * 60;
    	this.gameRoot = gameRoot;
        for (int i = 0; i < a.LEVEL.length; i++) 
        {//still there is a small problem with timer    	
            String line = a.LEVEL[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
				
	
						//it works
						
						//System.out.println(System.getProperty("user.dir")+"\\platform.jpg");
						ImageView imageView = new ImageView(platform); 
						addtoworld(imageView,j*60, i*60);
						platforms.add(imageView);
				
           			 
                    	
                        break;
                    case '2':
							Coin mycoin = new Coin();
							mycoin.transform.setTranslateY(i*60);
                          mycoin.transform.setTranslateX(j*60);
                          mycoin.transform.getProperties().put("alive", true);
                    
                       // Node coin = createEntity(j*60, i*60, 60, 60, Color.GOLD);
                        coins.add(mycoin.transform);
                        
                        gameRoot.getChildren().add(mycoin.transform);
                        break;
                    case '3':
                    	image = new Image(getClass().getResource("finishmark.png").toExternalForm());//it works
						ImageView finishlineimage = new ImageView(image); 
                    	finishMark = finishlineimage;
                    	finishMark.setTranslateY(i*60);
                    	finishMark.setTranslateX(j*60);
                    	if(!hasboss)
                        finishMark.getProperties().put("alive", true);   
                    	else
                    	{
                    		
                    	finishMark.getProperties().put("alive", false);  
                    	finishMark.setVisible(false);
                    	}
                    	gameRoot.getChildren().add(finishMark);
                    	    
                    	break;
                    case '4':
                    	pleb monster=new pleb(this);                	
                    	monsters.add(monster);
                    	monster.transform.setTranslateY(i*60);
                    	monster.transform.setTranslateX(j*60);
                    	addtoworld(monster.transform);
                    	break;
                    case '5':
                     hasboss=true;
                   	 this.boss = new Boss(this,bosspic);
                   	 boss.transform.setTranslateY(i*60);
                   	 boss.transform.setTranslateX(j*60);
                  	 if(finishMark!= null)
                     finishMark.getProperties().put("alive",false);
                  	 addtoworld(boss.transform);
                	 System.out.println("found a boss");
                }
            }
        }	
    }
    
    public void rebuild()
    {
    	uiRoot.getChildren().clear();
    	monsters.clear();
    	gameRoot.getChildren().clear();
    	platforms.clear();
    	coins.clear();
    	
    	 for (int i = 0; i < data.LEVEL.length; i++) 
         {//still there is a small problem with timer
    		 String line = data.LEVEL[i];
             for (int j = 0; j < line.length(); j++) {
                 switch (line.charAt(j)) {
                     case '0':
                         break;
                     case '1':
                        // Node platform = createEntity(j*60, i*60, 60, 60, Color.BROWN);
 					Image image;

 						image = new Image(getClass().getResource("platform.jpg").toExternalForm());//it works
 						
 						//System.out.println(System.getProperty("user.dir")+"\\platform.jpg");
 						ImageView imageView = new ImageView(image); 
 						addtoworld(imageView,j*60, i*60);
 						platforms.add(imageView);

                     	
                         break;
                     case '2':
                    	 Coin mycoin = new Coin();
 
                    	 mycoin.transform.setTranslateY(i*60);
                         mycoin.transform.setTranslateX(j*60);
                         mycoin.transform.getProperties().put("alive", true);
                      // Node coin = createEntity(j*60, i*60, 60, 60, Color.GOLD);
                       coins.add(mycoin.transform);
     
                       
                       gameRoot.getChildren().add(mycoin.transform);
                       break;
             
                     case '3':
                     	image = new Image(getClass().getResource("finishmark.png").toExternalForm());//it works
 						ImageView finishlineimage = new ImageView(image); 
                     	finishMark = finishlineimage;
                     	finishMark.setTranslateY(i*60);
                     	finishMark.setTranslateX(j*60);
                     	if(!hasboss)
                        finishMark.getProperties().put("alive", true);
                     	else
                     	finishMark.getProperties().put("alive", false);	
                     	gameRoot.getChildren().add(finishMark);
                     	break;
                     case '4':
                    ;
                     	pleb monster=new pleb(this);                	
                     	monsters.add(monster);
                     	monster.transform.setTranslateY(i*60);
                     	monster.transform.setTranslateX(j*60);
                     	monster.dirisRight=false;
                     	addtoworld(monster.transform);
                     	break;
                     case '5':
                    	 hasboss=true;
                    	 this.boss = new Boss(this,bosspic);
                    	 boss.transform.setTranslateY(i*60);
                    	 boss.transform.setTranslateX(j*60);
                    	 addtoworld(boss.transform);
                    	 if(finishMark!= null)
                    	 finishMark.getProperties().put("alive",false);
                    	 System.out.println("found a boss");
                 }
             }
         }
    }
       
    public void deathscreen(boolean tempsepuise)
    {    	
    	uiRoot.getChildren().add(purplescreen);
    	if(tempsepuise)
    		deathscreen = deathscreenlabeltemps;
    	else
    		deathscreen = deathscreenlabel;
    	uiRoot.getChildren().add(deathscreen);   		
    }
    public void undeathscreen()
    {
    	uiRoot.getChildren().remove(purplescreen);
    	uiRoot.getChildren().remove(deathscreen);
    }   
    public Node createEntity(int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        entity.getProperties().put("alive", true);      
        gameRoot.getChildren().add(entity);
        return entity;
    }  
    public void addtoworld(Node entity)
    {
        entity.getProperties().put("alive", true);
        
        gameRoot.getChildren().add(entity);
    }
    public void addtoworld(Node entity,int x,int y)
    {
        entity.getProperties().put("alive", true);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        gameRoot.getChildren().add(entity);
    }
	public ArrayList<Node> getWorldplatforms()
    {
    	return platforms;
    }
    public ArrayList<Node> getWorldcoins()
    {
    	return coins;
    }
    public void winscreen(int mysocre)
    {
       	uiRoot.getChildren().add(purplescreen);
       	uiRoot.getChildren().add(finishtxt);
       	
    }
    public void coinsCheck()
    {
    	 for (Iterator<Node> it = coins.iterator(); it.hasNext(); ) {
             Node coin = it.next();
             if ((Boolean)coin.getProperties().get("alive")==false) {
                 it.remove();
                 gameRoot.getChildren().remove(coin);
             }
         }
    }   
    public void animateMonsters()
    {
    	if(hasboss)
    	bosslives.setText(""+boss.lives);
    	for(pleb mypleb : monsters)
    	{ 
    		//System.out.println(mypleb.istriggered);
    		if(mypleb.istriggered ){
    		
    		//aplying gravity over time
    			if (mypleb.getVelocity().getY() < 8) {
   
    				mypleb.setVelocity(mypleb.getVelocity().add(0, 1));
           
    			}
    			mypleb.moveY( (int)mypleb.Velocity.getY(),platforms);//moving according to gravity vs jumping
    			mypleb.moveX(platforms);
            
    		}
    	}
    }   
    public void animateBoss()
    {
    	if(boss.isAlive)
    	{
    	
	    	//aplying gravity
	    	if (boss.ischarging)
	    	{
	    		boss.moveX((int)(boss.playertarget.getTranslateX()-boss.transform.getTranslateX()),platforms);
	    	}
	    	if (boss.getVelocity().getY() < 8) {
	    		   
				boss.setVelocity(boss.getVelocity().add(0, 0.5));
				boss.moveY( (int)boss.Velocity.getY(),platforms);
			}
	    	boss.moveY( (int)boss.Velocity.getY(),platforms);
	        
	        
	        if(!boss.isinaction() && boss.isalert)
	        {
	        	Random randomaction = new Random();
	        	Random randomjump = new Random();
	            int randaction = randomaction.nextInt(3);
	            int randjump = randomjump.nextInt(8);
	           
	        switch(randaction)
	        {
	        	case 0:
	        		boss.rest(platforms);
	        		break;
	        	case 1:
	        		boss.charge(platforms);
	        		break;
	        }
	        	if(randjump >= 3)
	        	{
	        		boss.jump();
	        	}
	        }
	        
    	}
    	else
    	{
    		if(!bossdied)
    		{
    			bossdied=true;
    		
    		System.out.println("dead");
    		deadbosspic.setTranslateX(boss.transform.getTranslateX());
    		deadbosspic.setTranslateY(boss.transform.getTranslateY());
    		gameRoot.getChildren().remove(boss.transform);
    		boss.transform= deadbosspic;
    		gameRoot.getChildren().add(boss.transform);
    	
    		}
    	}
    }

    

}
