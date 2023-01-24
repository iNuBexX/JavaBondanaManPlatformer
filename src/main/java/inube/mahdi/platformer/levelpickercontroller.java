package inube.mahdi.platformer;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;


public class levelpickercontroller {
	public int progression=3;
	//=1 ;
public Button b1 = new Button();
public Button b2 = new Button();
public Button b3 = new Button();
public Button start= new Button();



public ImageView lvl1= new ImageView();
public ImageView lvl2= new ImageView();
public ImageView lvl3= new ImageView();
public IntegerProperty currentlevel = new SimpleIntegerProperty(1);
public BooleanProperty gameon =new SimpleBooleanProperty(false);



public levelpickercontroller(Button start,Button b1,Button b2, Button b3,ImageView lvl1,ImageView lvl2,ImageView lvl3,int progression)
{
	// to be changed back
	this.progression = progression;
	//this.progression= 3;
	this.start = start;
	this.b1=b1;
	this.b2=b2;
	this.b3=b3;
	this.lvl1=lvl1;
	this.lvl2=lvl2;
	this.lvl3=lvl3;
	
	switch (this.progression)
	{
	case 1:
		b2.setDisable(true);
		b3.setDisable(true);
		break;
	case 2: 
		b2.setDisable(false);
		b3.setDisable(true);
		break;
	case 3:
		b2.setDisable(false);
		b3.setDisable(false);
		
	}
	
	
	
	
	start.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
			gameon.set(true);
	    }
	});
	
	b1.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
	    	lvl1.setVisible(true);
	    	lvl2.setVisible(false);
	    	lvl3.setVisible(false);
	    	b1.setId("selectedbutton");
	    	b2.setId("button");
	    	b3.setId("button");
	    	
	    	
	    	
	    	
	    	System.out.println("lvl1");
	    	currentlevel.set(1);
	    	
	    }
	});
	b2.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
	    	lvl1.setVisible(false);
	    	lvl2.setVisible(true);
	    	lvl3.setVisible(false);
	    	b2.setId("selectedbutton");
	    	b1.setId("button");
	    	b3.setId("button");
	    	System.out.println("lvl2");
	    	currentlevel.set(2);
	    	
	    }
	});

	b3.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
	    	lvl1.setVisible(false);
	    	lvl2.setVisible(false);
	    	lvl3.setVisible(true);
	    	b3.setId("selectedbutton");
	    	b1.setId("button");
	    	b2.setId("button");
	    	System.out.println("lvl3");
	    	currentlevel.set(3);
	    	
	    }
	});


}
public void updatebuttons(int myprogression)
{
	switch (myprogression)
	{
	case 1:
		b2.setDisable(true);
		b3.setDisable(true);
		break;
	case 2: 
		b2.setDisable(false);
		b3.setDisable(true);
		break;
	case 3:
		b2.setDisable(false);
		b3.setDisable(false);
		
	}
}



}
