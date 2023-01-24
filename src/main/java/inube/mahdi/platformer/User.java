package inube.mahdi.platformer;


public class User {

	public boolean bought1;
	public boolean bought2;
	public int coins;
	public int progression;
	public int totalscore;
	public String nickname;
	public User(boolean bought1 ,boolean bought2,int coins,int progression,int totalscore,String nickname)
	{
	this.bought1=bought1;
	this.bought2=bought2;
	this.coins=coins;
	this.progression=progression;
	this.totalscore=totalscore;
	this.nickname=nickname;
	}
	
}
