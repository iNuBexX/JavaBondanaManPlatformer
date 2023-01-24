package inube.mahdi.platformer;


import javafx.scene.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerController 
{
	FileOutputStream fos = null;
    File file;
	private boolean updated=false;
	boolean deathscreen = false;
	boolean winscreen = false;
	boolean canmove = true;
	public BooleanProperty ingame =new SimpleBooleanProperty(true);
	private Pane uiroot;
	public MyPlayer player;
	int levelWidth;
	public LevelData level;
	public Pane gameRoot;
	public world myworld;
	public Scene myscene;
	public AnimationTimer timer ;
	private levelpickercontroller lc;
	private Label livestxt;
	private ImageView livesView;
	private Label coinslabel;
	private ImageView coinsview;
	private countdown countdown;
	public boolean haswon = false;
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private ArrayList<Node> coins = new ArrayList<Node>();
    public PlayerController(MyPlayer player,LevelData level,Pane gameRoot,Scene scene,world world,Pane uiroot,levelpickercontroller lc,Label lives,ImageView livesView,Label coinslabel,ImageView coinsview,countdown countdown)
	{
    
    	this.countdown=countdown;
    	this.coinslabel=coinslabel;
    	this.coinsview=coinsview;
    	this.livesView=livesView;
    	this.livestxt = lives;
    	this.lc=lc;
    	this.uiroot = uiroot;
    	this.coins=world.getWorldcoins();
		this.myworld=world;
		this.gameRoot=gameRoot;
		this.level=level;
		this.levelWidth = level.LEVEL[0].length() * 60;
		this.player=player;
		this.myscene=scene;
		this.platforms=world.getWorldplatforms();
		
		 scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
	     scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
	     
	     livestxt.setText(""+player.lives);
	    //needs fixing livesview and lives txt too not here the rest aswell infact i only need the labels and not added here just a refrence
	    uiroot.getChildren().add(livesView);
	    uiroot.getChildren().add(livestxt);
	    uiroot.getChildren().add(coinsview);
	    uiroot.getChildren().add(coinslabel);
	}
   
	private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

     public void update() {
    	 
    	 livestxt.setText(""+player.lives);
    	 coinslabel.setText(""+player.coins);
    	//checks if player won
    	 if(player.lives<0 || countdown.epuise)
    		 
    		 {
    		 player.isdead=true;
    		
    		 }
    	 
    
    	 if(haswon) 
    	 {
    		 
    		 if(lc.progression<3 && !updated)
    		 {
    			 lc.progression++;
    			 
    			 lc.updatebuttons(lc.progression);
    			 updated=true;
    		 }
    		 countdown.stoptime();
    		 
    		 if(!winscreen)
    		 {
    			 
    			 
    			 
    			 
    			 player.score+= countdown.seconds*10;
        		 countdown.finalscore();
    		 winscreen= true;
    		 myworld.winscreen(player.score);
    		 }
    		 if(isPressed(KeyCode.ESCAPE))
    		 {
    			lc.gameon.set(false);
    		 }
    		 return;
    		 }
    	 //must add code
    	 if(player.isdead) 
    	 {
    		 countdown.stoptime();
    		 if (!deathscreen)	 
    		 {	 
    			 System.out.println(countdown.epuise);
    			 myworld.deathscreen(countdown.epuise);
    			 deathscreen=true;
    		 }
    	 
    		 else
    			 if(isPressed(KeyCode.SPACE))
    			 {
    				myworld.rebuild();
    				myworld.undeathscreen();
    				deathscreen=false;
    		 		player.respawn();
    		 		player.isdead=false;
    		 		player.lives=2;
    		 	    countdown.doTime(uiroot);
            	    uiroot.getChildren().add(livesView);
            	    uiroot.getChildren().add(livestxt);
            	    uiroot.getChildren().add(coinsview);
            	    uiroot.getChildren().add(coinslabel);
    			 }
    		 	if(isPressed(KeyCode.ESCAPE))
    		 	{
    		 		lc.gameon.set(false);
    		 	}
    		 	return;
    		}
    		 
    	 
    	 
    		 
    		 
    		 
    	 if(player.getTransform().getTranslateX()> myworld.finishMark.getTranslateX()  && (Boolean)myworld.finishMark.getProperties().get("alive")==true )
    	 {

    		
    		 TimerTask task = new TimerTask() {
    		        public void run() {
    		            haswon = true;
    		        }
    		    };
    		    Timer timer = new Timer("Timer");
    		    
    		    long delay = 500L;
    		    timer.schedule(task, delay);
    		   
    		    
    		    
    		    
    		 
    		 } 
    	 player.collectcoins(coins);
    	 
    	for(pleb mypleb : myworld.monsters)
    	{
    		if(player.transform.getBoundsInParent().intersects(mypleb.transform.getBoundsInParent()))
    		if(player.lives>0)
    		{
        		player.lives--;
        		System.out.println("dead");
        		player.respawn();
        		
    		}
    		else 
    			player.isdead=true;
    	}
    	 
    	 
    	//myworld.coinsCheck();
    
    		 
    	if(player.getTransform().getTranslateY()>1500 && player.lives > 0  )
    	{
    		
    	//	myworld.rebuild();
    		player.lives--;
    		System.out.println("dead");
    		player.respawn();
    	}
    	if(player.getTransform().getTranslateY()>1500 && player.lives ==0)
    	{
    	player.isdead = true;	
    	
    		//death screen
    	//	System.out.println("out of lives");   	
    	
    	}
    	 
    	 
    	 
    	 
        if (isPressed(KeyCode.Z) && player.getTransform().getTranslateY() >= 5) {
    
        	player.jumpPlayer();
           
        }

        if (isPressed(KeyCode.Q) && player.getTransform().getTranslateX() >= 5) {
            player.movePlayerX(-7,platforms);
 
        }

        if (isPressed(KeyCode.D) && player.getTransform().getTranslateX() + 40 <= levelWidth - 5) {
            player.movePlayerX(7,platforms);
        
        }

        if (player.getVelocity().getY() < 8) {
            player.setVelocity(player.getVelocity().add(0, 1));
       
        }
        if (player.getVelocity().getX() < 0) {
            player.setVelocity(player.getVelocity().add(1, 0));
    
       
        }
        player.movePlayerY( (int)player.Velocity.getY(),platforms);
        player.movePlayerX( (int)player.Velocity.getX(),platforms);
        	/*
        if( isPressed(KeyCode.SPACE) && player.isdead)
        		{
        		myworld.rebuild();
        		player.respawn();
        		player.lives=3;
        	    uiroot.getChildren().add(livesView);
        	    uiroot.getChildren().add(livestxt);
        	    uiroot.getChildren().add(coinsview);
        	    uiroot.getChildren().add(coinslabel);
        	    countdown.doTime(uiroot);
        		}*/
        //triguering these plebs is something i love
        for(pleb mypleb : myworld.monsters)
        {
        	if ( Math.abs(mypleb.transform.getTranslateX()-player.transform.getTranslateX())<1260.0)
        		{
        		mypleb.istriggered=true;
        		
        		}
        	
        }
        //handling boss part
        if (myworld.boss != null) {
        	
        
        if ( Math.abs(myworld.boss.transform.getTranslateX()-player.transform.getTranslateX())<1000.0  )
		{
		myworld.boss.isalert=true;
		
		}
        if(player.transform.getBoundsInParent().intersects(myworld.boss.transform.getBoundsInParent())&& myworld.boss.isAlive)
        if(myworld.boss.transform.getTranslateY()>player.transform.getTranslateY()+10 )
        {
        	System.out.println("touched his head");
        	player.setVelocity(player.Velocity.add(-10, -35));
        	if(myworld.boss.lives>0)
        		myworld.boss.lives--;
        	if(myworld.boss.lives==0)
        	{myworld.boss.isAlive =false;
      
        	
        		myworld.boss.isAlive =false;
        		myworld.finishMark.getProperties().put("alive",true);
        		myworld.finishMark.setVisible(true);
        	}
        	
        }
        else
        {
        	if(player.lives>0)
        	{
        		player.lives--;
        		player.respawn();
            	myworld.boss.aquiretarget(player.transform);
        	}
        	else player.isdead=true;
        	
        }
        
        }
		
   
        	
    }
   
    public void initialize()
    {
     System.out.println("initializing");
        
    }



}
