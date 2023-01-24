package inube.mahdi.platformer;


import java.util.ArrayList;

import javafx.scene.Node;

public interface smartentitymouvement {
	public void moveX(int value,ArrayList<Node> platforms);
	public void moveY(int value,ArrayList<Node> platforms);
}
