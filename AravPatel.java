import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.scene.SnapshotParameters;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.util.Duration;

public class AravPatel extends Application implements EventHandler<InputEvent>{
	GraphicsContext gc;
	Image trooper;
	int x = 0;
	int y=0;
	AnimateObjects animate;
	Spaceship spaceship;

	ArrayList<Asteroids> asteroids;
	ArrayList<Rectangle2D> rectAst;

	ArrayList<Bullets> bullets;
	ArrayList<Rectangle2D> rectBul;

	Image ship;
	Image ast;
	Image bul;
	Image background;
	Canvas canvas;
	ImageView ss;
	Rectangle2D rectShip;
	AudioClip destroy;
	AudioClip shoot;
	AudioClip point;
	StackPane pane;
	Scene scene;
	Pane root;
	boolean play;
	int lives;
	int score;
	boolean R = false;

	public static void main(String[] args){

		launch();
	}

	public void start(Stage stage){

		stage.setTitle("Asteroids");
		//Group root = new Group();
		root = new Pane();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);
		play = true;
		score = 0;
		lives = 3;
		gc = canvas.getGraphicsContext2D();
		ship = new Image("Spaceship.JPG");
		ast  = new Image("Asteroid.JPG");
		bul = new Image("Bullets.JPG");
		background = new Image("background.jpg");
		URL resource = getClass().getResource("Destroy.MP3");
		destroy = new AudioClip(resource.toString());

		URL resource1 = getClass().getResource("Shoot.MP3");
		shoot = new AudioClip(resource1.toString());

		URL resource2 = getClass().getResource("Point.MP3");
		point = new AudioClip(resource2.toString());

		spaceship = new Spaceship(300, 200, ship);
		bullets = new ArrayList<Bullets>();
		rectBul = new ArrayList<Rectangle2D>();

		asteroids = new ArrayList<Asteroids>();
		rectAst = new ArrayList<Rectangle2D>();

		//pane = new StackPane();
		scene = new Scene(root, Color.BLACK);

		//ss.setFitWidth(50);
		root.getChildren().add(spaceship.getShip());
		//root.getChildren().add(a);

		stage.setScene(scene);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, this);

		animate = new AnimateObjects();
		animate.start();

		stage.show();
	}
	public void handle(final InputEvent event){

		if(event instanceof KeyEvent){

			if(lives == 0){
				if(((KeyEvent)event).getCode() == KeyCode.R){
					R = true;
				}
			}

			if(((KeyEvent)event).getCode() == KeyCode.W){
				double rads = Math.toRadians(spaceship.getRotAng());

				spaceship.setPosVelY(Math.sin(rads)*2.8);
				spaceship.setPosVelX(Math.cos(rads)*2.8);
			}
			if(((KeyEvent)event).getCode() == KeyCode.A){
				spaceship.setRotVel(-3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.D){

				spaceship.setRotVel(3.0);
			}
			/*if(((KeyEvent)event).getCode() == KeyCode.S){
				spaceship.setPosVelY(3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.A){
				spaceship.setPosVelX(-3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.D){
				spaceship.setPosVelX(3.0);

			}*/

			if(((KeyEvent)event).getCode() == KeyCode.SPACE){
				shootBullet();
				shoot.play();
			}

			if(event.getEventType().toString().equals("KEY_RELEASED") && (((KeyEvent)event).getCode() == KeyCode.A || ((KeyEvent)event).getCode() == KeyCode.D)){

				spaceship.setRotVel(0);
			}
			if(event.getEventType().toString().equals("KEY_RELEASED") && (((KeyEvent)event).getCode() == KeyCode.W)){
				spaceship.setPosVelY(0);
				spaceship.setPosVelX(0);
			}

		}
		if(event instanceof MouseEvent){
			System.out.println(((MouseEvent)event).getX());
			System.out.println(((MouseEvent)event).getY());
		}
	}


	public class AnimateObjects extends AnimationTimer{
		public void handle(long now){
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			if(play){

				gc.drawImage(background, 0, 0);
				if(lives == 3){
					gc.drawImage(ship, 0, 0);
					gc.drawImage(ship, 0, 30);
					gc.drawImage(ship,0, 60);
				}
				else if(lives == 2){
					gc.drawImage(ship, 0, 0);
					gc.drawImage(ship, 0, 30);
				}
				else if(lives == 1){
					gc.drawImage(ship, 0, 0);
				}

				gc.setFill( Color.WHITE);
				gc.setStroke( Color.WHITE );
				gc.setLineWidth(3);
				Font font = Font.font( "Arial", FontWeight.NORMAL, 20 );
				gc.setFont( font );
				gc.fillText( "Score: "+score, 650, 390 );


				while(rectAst.size() != 0){
					rectAst.remove(0);
				}
				while(rectBul.size() != 0){
					rectBul.remove(0);
				}

				for(Asteroids a : asteroids){
					rectAst.add(new Rectangle2D(a.getPosX(), a.getPosY(), ast.getWidth(), ast.getHeight()));
				}
				for(Bullets b : bullets){
					rectBul.add(new Rectangle2D(b.getPosX(), b.getPosY(), bul.getWidth(), bul.getHeight()));
				}

				spaceship.getShip().setRotate(spaceship.getRotAng());
				spaceship.getShip().setX(spaceship.getPosX());
				spaceship.getShip().setY(spaceship.getPosY());

				rectShip = new Rectangle2D(spaceship.getPosX(), spaceship.getPosY(), ship.getWidth(), ship.getHeight());

				for(Bullets b : bullets){
					b.getBullet().setX(b.getPosX());
					b.getBullet().setY(b.getPosY());
					b.update();
				}

				int bye = -1;
				for(Asteroids a: asteroids){
					if((a.getPosY() < -100 || a.getPosY() > 500) || (a.getPosX() < -100 || a.getPosX() > 900)){
						bye = asteroids.indexOf(a);
					}
				}
				if(bye != -1){
					root.getChildren().remove(asteroids.get(bye).getAsteroid());
					asteroids.remove(bye);

				}

				for(Asteroids a : asteroids){
					a.getAsteroid().setX(a.getPosX());
					a.getAsteroid().setY(a.getPosY());
					a.update();
				}
				if(asteroids.size() < 6){
					respawnAsteroids();
				}


				spaceship.update();
				spaceship.updateRotate();

				for(int i=rectBul.size()-1; i>=0; i--){
					for(int j=0;j< rectAst.size();j++){
						if(rectBul.get(i).intersects(rectAst.get(j))){
							root.getChildren().remove(asteroids.get(j).getAsteroid());
							asteroids.remove(j);

							root.getChildren().remove(bullets.get(i).getBullet());
							bullets.remove(i);
							score+=10;
							point.play();


						}
					}
				}

				for(int i=0;i<rectAst.size();i++){
					if(rectShip.intersects(rectAst.get(i))){
						spaceship.setPosX(300);
						spaceship.setPosY(200);
						lives--;
						destroy.play();
						root.getChildren().remove(asteroids.get(i).getAsteroid());
						asteroids.remove(i);
					}
				}
				if((spaceship.getPosX() < -20 || spaceship.getPosX() > 810) || (spaceship.getPosY() < -10 || spaceship.getPosY() > 410)){
					spaceship.setPosX(300);
					spaceship.setPosY(200);
					lives--;
					destroy.play();
				}

				if(lives == 0){
					play = false;
				}
			}
			else{
				for(int i=1; i < root.getChildren().size(); i++){
					root.getChildren().remove(1);
				}
				for(int i=0;i<asteroids.size();i++){
					asteroids.remove(0);
				}
				gc.drawImage(background, 0, 0);

				gc.setFill( Color.RED);
				gc.setStroke( Color.RED );
				gc.setLineWidth(3);
				Font font = Font.font( "Courier New", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 280, 100 );


				gc.setFill(Color.YELLOW);
				gc.fillText("Score: "+score, 280, 250);

				gc.setFill(Color.RED);
				gc.fillText("Press 'R' to restart", 125, 325);
				if(R){
					lives = 3;
					score = 0;
					R = false;
					root.getChildren().add(spaceship.getShip());
					play = true;
				}

			}

		}
	}


	public void respawnAsteroids(){

		while(asteroids.size() < 6){
			int randChoose = (int)(Math.random()*2)+1;
			double randAstX = (int)(Math.random()*750);
			double randAstY;


			if(randChoose == 1){
				randAstY = 5;
			}
			else{
				randAstY = 325;
			}

			Asteroids a = new Asteroids(randAstX, randAstY, ast);
			asteroids.add(a);
			root.getChildren().add(a.getAsteroid());

		}


		for(Asteroids a : asteroids){
			a.setPosVelX((Math.random()*3)-1.5);
			if(a.getPosY() == 5)
				a.setPosVelY((Math.random()*3));
			else if(a.getPosY() == 325)
				a.setPosVelY((Math.random()*1.5)-1.5);

		}
	}
	public void shootBullet(){

		bullets.add(new Bullets(spaceship.getPosX()+10, spaceship.getPosY()+10, bul));
		root.getChildren().add(bullets.get(bullets.size()-1).getBullet());
		double rads = Math.toRadians(spaceship.getRotAng());
		bullets.get(bullets.size()-1).setPosVelX(Math.cos(rads)*3.5);
		bullets.get(bullets.size()-1).setPosVelY(Math.sin(rads)*3.5  );

	}


}