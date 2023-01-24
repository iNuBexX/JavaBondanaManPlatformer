package inube.mahdi.platformer;


//import java.util.HashMap;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	//progresssion needed from data base
	Scene pickinglevel ;
	Scene profile;
	public int progression = 1;
	//////////////////////////////////////////////////////////////////////////keys controlls 
	//private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	public IntegerProperty currentlvl= new SimpleIntegerProperty(1);
	//////////////////////////////////////////////////////////////////////////gameworld scene model
	private countdown time = new countdown();
	
	//////////////////////////////////////////////////////////////////////////gameworld scene  graphics
	private StackPane root = new StackPane(); //profile root
	private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private Label lives =new Label("Mahdi is smarter then raed");//signature des devloppeures 
    private Label coins  =new Label("but raed is a much better person");//devs signature
	private Image heart = new Image(getClass().getResource("heart.png").toExternalForm());
	private Image coin = new Image(getClass().getResource("coinsUI.png").toExternalForm());
	private ImageView heartview= new ImageView(heart);
	private ImageView coinview= new ImageView(coin);
	private Image bosspic = new Image(getClass().getResource("boss.png").toExternalForm());
	private ImageView bosspicview= new ImageView(bosspic);
    public Image bosslives = new Image (getClass().getResource("bosslivesUI.png").toExternalForm());
    public ImageView bosslivesui= new ImageView(bosslives);
    public Label bossliveslabel= new Label("hellow");
    public Image deadbosspic = new Image(getClass().getResource("deadboss.png").toExternalForm());
    public ImageView deadbosspicView= new ImageView(deadbosspic);
    ///////////////////////////////////////////////////////////////////////////levelpicker scene graphics
    
    private AnchorPane levelpickerroot = new AnchorPane();
    private GridPane buttons = new GridPane(); 
    
   ///// getting level data
    private LevelData CurrentLevelData;
    
    //our world
    private world myworld;
    
    // our player
    private MyPlayer player;
    
    //the player controller
    private PlayerController playercontroller;
    private Image platform = new Image(getClass().getResource("platform.jpg").toExternalForm());

    private Image bondanaman=new Image(getClass().getResource("bondanaMan.jpg").toExternalForm());
    
    
    //innitializing lvlpicker + world graphics
    
    private void initContent(Scene a,levelpickercontroller lc) {
    	
    	
    	gameRoot.getChildren().clear();
    	uiRoot.getChildren().clear();
    	appRoot.getChildren().clear();
    	if(lc.currentlevel.get()==3)
    	{
    		 bossliveslabel.setTranslateX(1154);
    		 bossliveslabel.setTranslateY(142);
    		  bosslivesui.setTranslateX(1120);
    		  bosslivesui.setTranslateY(60);
    		  bossliveslabel.setMinWidth(150);
    		  bossliveslabel.setMinHeight(150);
    		  uiRoot.getChildren().add(bosslivesui);
    		  uiRoot.getChildren().add(bossliveslabel);
    	}
    	time.doTime(uiRoot);
    	gameRoot.setLayoutX(0);
    	lives.setId("lives");
    	coins.setId("coins");
    	bossliveslabel.setId("lives");
    	heartview.setTranslateX(1170);//setting up the heart and txt on top of it
    	heartview.setTranslateY(40);
    	lives.setMinWidth(150);
    	lives.setMinHeight(150);
    	lives.setTranslateX(1195);
    	lives.setTranslateY(0);
    	
    	coinview.setTranslateX(1000);//setting up the heart and txt on top of it
    	coinview.setTranslateY(40);
    	coins.setMinWidth(50);
    	coins.setMinHeight(50);
    	coins.setTranslateY(50);
    	coins.setTranslateX(980);
    	
    	
       	Rectangle purplescreen = new Rectangle(2000, 2000);
        purplescreen.setFill(Color.DARKSLATEBLUE);
        purplescreen.setId("purplescreen");
    	Image deathlabel = new Image(getClass().getResource("DEATHSCREEN.PNG").toExternalForm());
    	ImageView deathscreenlabel= new ImageView(deathlabel);
    	Image finishtxt = new Image(getClass().getResource("finishtxt.png").toExternalForm());
    	ImageView finishtxtview = new ImageView(finishtxt);
    	Image deathscreenlabeltimeimage=new Image(getClass().getResource("DEATHSCREENtime.png").toExternalForm());
    	ImageView deathscreenlabeltime =new ImageView(deathscreenlabeltimeimage);
    	
    	
    	
    	
    	
    	
        Rectangle bg = new Rectangle(1280, 720);
        bg.setFill(Color.LIGHTBLUE);
        CurrentLevelData =new LevelData(currentlvl.get());
        
        myworld = new world(CurrentLevelData,gameRoot,purplescreen,deathscreenlabel,uiRoot,finishtxtview,platform,deathscreenlabeltime,bosspicview,bossliveslabel,deadbosspicView);
        player = new MyPlayer(myworld,bondanaman);
        
        playercontroller=new PlayerController(player,CurrentLevelData,gameRoot,a,myworld,uiRoot,lc,lives,heartview,coins,coinview,time);
        
        appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
  
    }

    //
    @Override
    public void start(Stage primaryStage) throws Exception {

    	
    	
    	
    	
    	
    	
    	
    	
////////////////////////////////////////////////////////////////initializing our level picker scene
		currentlvl.set(0); //
		Button lvl1button = new Button("niveau 1");
		Button lvl2button = new Button("niveau 2");
		Button lvl3button = new Button("niveau boss");
		Button start = new Button(" Start ");
		Image lvl2image =new Image(getClass().getResource("lvl2.jpg").toExternalForm(),0,0,false,false);
		Image lvl1image= new Image(getClass().getResource("lvl1.jpg").toExternalForm(),0, 0, false,false);
		Image lvl3image= new Image(getClass().getResource("bossfight.jpg").toExternalForm(),0, 0, false,false);
		
		//lvl2button.setDisable(true); 
		
		//adding images to represent lvls behind the buttons to the level picker
		
		ImageView lvl1imageview = new ImageView(lvl1image);
		ImageView lvl2imageview = new ImageView(lvl2image);
		ImageView lvl3imageview = new ImageView(lvl3image);
		levelpickerroot.getChildren().addAll(lvl3imageview,lvl2imageview,lvl1imageview);				
		//stilizing our buttons with lvl1button innitialized as the first selected
		lvl1button.setId("selectedbutton");
		lvl2button.setId("button");
		lvl3button.setId("button");
		start.setId("startbutton");
		//lvl2button.setStyle( " -fx-background-color: darkslateblue;");  	 	 	  			
		//hooking up the controller
		levelpickercontroller mylvlcontroller = new levelpickercontroller(start,lvl1button,lvl2button,lvl3button,lvl1imageview,lvl2imageview,lvl3imageview,progression);
		
		currentlvl.bind(mylvlcontroller.currentlevel);
		buttons.setPadding(new Insets(10,10,10,10));
		buttons.setVgap(10); 
		buttons.setHgap(10);
		
		//levelpicker.setConstraints(lvl1button, 0, 2);
		buttons.setPrefWidth(400);//gotta change this
		
		//adding the buttons to lvl picker buttons gridpane
		buttons.add(lvl1button, 0, 0);
		buttons.add(lvl2button, 1, 0);
		buttons.add(lvl3button, 2, 0);
		
		buttons.add(start, 1, 3);
		
		// 	buttons.getChildren().addAll(lvl1button,lvl2button,lvl3button,start);
		
		AnchorPane.setTopAnchor(buttons, 100.0);
		AnchorPane.setLeftAnchor(buttons, 200.0);
		AnchorPane.setRightAnchor(buttons, 400.0);
		AnchorPane.setBottomAnchor(buttons, 400.0);
		
		levelpickerroot.getChildren().addAll(buttons);
		
		// lvl picking scene
		Scene pickinglevel = new Scene(levelpickerroot);
		
		
		pickinglevel.getStylesheets().add(getClass().getResource("stylecheat.css").toExternalForm()) ; // getting the stylesheet for the lvl picker
		
		
////////////////////////////////////////////////////////////////////////profile
    
			boolean b2;// to be innitialized from base 
			boolean b3; 
			
			
			Scene profile = new Scene(root, 1000, 600);//profile root scene
			
			profile.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ; //getting css
			// CHOOSE AVATAR TO PLAY WITH + START
			ToggleButton ava1 = new ToggleButton();//toggle buttons
			ToggleButton ava2 = new ToggleButton();
			ToggleButton ava3 = new ToggleButton();
			ToggleGroup picker = new ToggleGroup();
			ImageView uicoin = new ImageView(new Image(getClass().getResource("coinsUI.png").toExternalForm()));//coin ui
		
		
			uicoin.setTranslateX(430);
			uicoin.setTranslateY(-40);	
			

			
			Button buybutton1 = new Button("acheter à 100 piece");
			Button buybutton2 = new Button("acheter à 200 piece");
			//root.getChildren().addAll(buybutton1,buybutton2);
			buybutton1.setTranslateX(0);
			buybutton1.setTranslateY(0);
			buybutton1.setScaleX(0.6);
			buybutton1.setScaleY(0.6);
			buybutton2.setTranslateX(0);
			buybutton2.setTranslateY(0);
			buybutton2.setScaleX(0.6);
			buybutton2.setScaleY(0.6);
			GridPane infozone=new GridPane();
			infozone.setPadding(new Insets(10,10,10,10));
			infozone.setVgap(10); 
			infozone.setHgap(10);

			
			//adding the buttons to lvl picker buttons gridpane
			
			
			ava1.setToggleGroup(picker);
			ava2.setToggleGroup(picker);
			ava3.setToggleGroup(picker);
			//ava3.setDisable(true);
			Text commande = new Text("chosir votre bondanaman !");
			commande.setId("choice");
			commande.setTranslateX(0);
			commande.setTranslateY(0);
			commande.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
			commande.setFill(Color.BLACK);
		//	infozone.getChildren();
			
			
			ImageView view1 = new ImageView(new Image(getClass().getResource("bondanaMan.jpg").toExternalForm()));
			ImageView view2 = new ImageView(new Image(getClass().getResource("bondanagirl.jpg").toExternalForm()));
			ImageView view3 = new ImageView(new Image(getClass().getResource("badbondanaman.png").toExternalForm()));
			view3.setFitHeight(50);
	
	
	
			ava3.setPrefSize(60, 60);
			ava3.setGraphic(view3);
			
			
			
			
			ava1.setPrefSize(60, 60);
			ava1.setGraphic(view1);
			
			view2.setFitHeight(50);
			
		
			
			ava2.setPrefSize(60, 60);
			ava2.setGraphic(view2);
			ImageView view4 = new ImageView(getClass().getResource("bondanaMan.jpg").toExternalForm());
		
			view4.setFitHeight(50);
	

			Button starter = new Button("commencer");
			
			starter.setTranslateX(-50);
			infozone.add(commande, 0, 0);
			infozone.add(ava1, 0, 2);
			infozone.add(ava2, 0, 3);
			infozone.add(ava3, 0, 4);
			infozone.add(view4, 0, 1);
			infozone.add(starter, 1, 2);
			
			
			// PROFILE CHANGER ; login class have : username - psw - score - coins

			String username = null;
			Integer score = 1;
			Integer coins = 1;
			
			
			

			Label showuser = new Label("name" + username);
			Label showscore = new Label("score" + score.toString());
			Label showcoins = new Label("pieces:" + coins.toString());
			
			showuser.setId("info");
			showscore.setId("info");
			showcoins.setId("info");

			showcoins.setTranslateY(-40);
			showcoins.setTranslateX(280);
			infozone.setTranslateY(280);
			 //the horizontal box containning the avatars
			
		
		
			root.getChildren().addAll(infozone);

			

			ava1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {view4.setImage(new Image(getClass().getResource("bondanaMan.jpg").toExternalForm()));
				bondanaman = new Image(getClass().getResource("bondanaMan.jpg").toExternalForm());
				}
			});
			ava2.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {view4.setImage(new Image(getClass().getResource("bondanagirl.jpg").toExternalForm())); bondanaman = new Image(getClass().getResource("bondanagirl.jpg").toExternalForm());}});
			ava3.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {view4.setImage(new Image(getClass().getResource("badbondanaman.png").toExternalForm())); bondanaman = new Image(getClass().getResource("badbondanaman.png").toExternalForm());}});
			starter.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {primaryStage.setScene(pickinglevel);System.out.println("lvl picker");}});
			
	
    	

        	
    	// game scene
        Scene scene = new Scene(appRoot);
        
        uiRoot.getStylesheets().add("/platformer/stylecheat.css");
                                                      
        //initContent(scene);
        //playercontroller= new PlayerController(player,CurrentLevelData,gameRoot,scene,myworld);
        primaryStage.setTitle("Mahdi aka inube");
       // primaryStage.setScene(scene);
        
        //chosing first page
        //code needed
       // primaryStage.setScene(pickinglevel);
        
        
        
        
        primaryStage.setScene(profile);
		primaryStage.show();
        //primaryStage.show();
        // le boucle of the game 
        AnimationTimer timer = new AnimationTimer() {
			private long lastUpdate = 0 ;
            @Override
			public void handle(long now)
				{
					playercontroller.update();
					myworld.animateMonsters();
					//if(myworld.boss!= null)
					if(myworld.hasboss)

					 {
						myworld.animateBoss();
					 }


				}};


 	mylvlcontroller.gameon.addListener((obs, old, newValue) -> {
		   

		        if (newValue ==true)
		        {
		        	initContent(scene,mylvlcontroller);
		        	
		        	System.out.println("game starting" +currentlvl.get());
		        	primaryStage.setScene(scene);

		            timer.start();
		        }
		        if(newValue == false)
		        {
		        	primaryStage.setScene(pickinglevel);
		        }
		    });

 }

    public static void main(String[] args) {
        launch(args);
    
        
    }
}