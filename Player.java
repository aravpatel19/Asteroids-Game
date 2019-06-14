public class Player{

	private double posX;
	private double posY;

	private double posVelX;
	private double posVelY;

	public Player(double x, double y){
		this.posX = x;
		this.posY = y;
		this.posVelX = 0;
		this.posVelY = 0;
	}

	public double getPosX(){
		return posX;
	}

	public void setPosX(double x){
		this.posX = x;
	}

	public double getPosY(){
		return posY;
	}

	public void setY(double y){
		this.posY = y;
	}

	public void setPosVelX(double posVelX){
		this.posVelX = posVelX;
	}

	public void setPosVelY(double posVelY){
		this.posVelY = posVelY;
	}

	public double getPosVelY(){
		return posVelY;
	}

	public void update(){
		posX += posVelX;
		posY += posVelY;
	}
}