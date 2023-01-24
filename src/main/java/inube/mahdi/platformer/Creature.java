package inube.mahdi.platformer;




import javafx.geometry.Point2D;
import javafx.scene.Node;

import javafx.scene.layout.Pane;

public class Creature extends entity {
	
	public Pane gameRoot;
    public Point2D Velocity = new Point2D(0, 0);
    public world myworld;
    public boolean canJump = true;
    
    
    public Node getTransform()
    {
    	return transform;
    }
    public void setTransform(Node a)
    {
    	transform =a;
    }
    public boolean getcanJump()
    {
    	return canJump;
    }
    public void setcanJump(boolean a)
    {
    	canJump=a;
    }
    public Point2D getVelocity()
    {
    	return Velocity;
    }
    
    public void setVelocity(Point2D a)
    {
    	Velocity=a;
    }
    
    public Creature(world world)
    {
    		myworld=world;
    		gameRoot= myworld.gameRoot;
    }
}
