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

public class AsteroidsGame extends Application implements EventHandler<InputEvent>{
	GraphicsContext gc;
	Image trooper;
	int x = 0;
	int y=0;
	AnimateObjects animate;
	Spaceship spaceship;
	ArrayList<Asteroids> asteroids;
	Image ship;
	Image ast;
	Image background;
	Canvas canvas;
	ImageView ss;
	ArrayList<Rectangle2D> rectAst;
	StackPane pane;
	Scene scene;
	Pane root;

	public static void main(String[] args){

		launch();
	}

	public void start(Stage stage){

		stage.setTitle("Asteroids");
		//Group root = new Group();
		root = new Pane();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();
		ship = new Image("Spaceship.JPG");
		ast  = new Image("Asteroid.JPG");
		background = new Image("background.jpg");

		spaceship = new Spaceship(300, 200, ship);
		asteroids = new ArrayList<Asteroids>();

		respawnAsteroids(asteroids);

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

			if(((KeyEvent)event).getCode() == KeyCode.LEFT)
				spaceship.setRotVel(-3.0);
			if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
				spaceship.setRotVel(3.0);
			if(((KeyEvent)event).getCode() == KeyCode.W){
				spaceship.setPosVelY(-3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.S){
				spaceship.setPosVelY(3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.A){
				spaceship.setPosVelX(-3.0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.D){
				spaceship.setPosVelX(3.0);

			}
			if(event.getEventType().toString().equals("KEY_RELEASED") && (((KeyEvent)event).getCode() == KeyCode.LEFT || ((KeyEvent)event).getCode() == KeyCode.RIGHT)){

				spaceship.setRotVel(0);
			}
			if(event.getEventType().toString().equals("KEY_RELEASED") && (((KeyEvent)event).getCode() == KeyCode.W || ((KeyEvent)event).getCode() == KeyCode.S)){
				spaceship.setPosVelY(0);
			}

			if(event.getEventType().toString().equals("KEY_RELEASED") && (((KeyEvent)event).getCode() == KeyCode.A || ((KeyEvent)event).getCode() == KeyCode.D)){

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
			gc.drawImage(background, 0, 0);

			spaceship.getShip().setRotate(spaceship.getRotAng());
			spaceship.getShip().setX(spaceship.getPosX());
			spaceship.getShip().setY(spaceship.getPosY());

			//Rectangle2D rectShip = new Rectangle2D(spaceship.getPosX(), spaceship.getPosY(), ship.getWidth()+20, ship.getHeight()+20);

			int bye = -1;
			for(Asteroids a: asteroids){
				if((a.getPosY() < -10 || a.getPosY() > 400) || (a.getPosX() < 0 || a.getPosX() > 800)){
					bye = asteroids.indexOf(a);
				}
			}
			if(bye != -1){
				root.getChildren().remove(asteroids.get(bye).getAsteroid());
				asteroids.remove(bye);
			}

			System.out.println(asteroids.size());
			for(Asteroids a : asteroids){
				a.getAsteroid().setX(a.getPosX());
				a.getAsteroid().setY(a.getPosY());
				a.update();
			}
			if(asteroids.size() < 4){
				respawnAsteroids(asteroids);
			}


			spaceship.update();
			spaceship.updateRotate();


			/*for(Rectangle2D r : rectAst){
				if(rectShip.intersects(r)){
				//	gc.setFill( Color.YELLOW);
					gc.setStroke( Color.RED );
					gc.setLineWidth(3);
					Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
					gc.setFont( font );
					gc.fillText( "Game Over", 100, 50 );
					gc.strokeText( "Game Over", 100, 50 );
				}
			}*/


		}
	}
	public void respawnAsteroids(ArrayList<Asteroids> asteroids){

		while(asteroids.size() < 4){
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

}