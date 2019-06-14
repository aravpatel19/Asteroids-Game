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
	StackPane pane;
	Scene scene;

	public static void main(String[] args){

		launch();
	}

	public void start(Stage stage){

		stage.setTitle("Asteroids");
		//Group root = new Group();
		Pane root = new Pane();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();
		ship = new Image("Spaceship.JPG");
		ast  = new Image("Asteroid.JPG");
		background = new Image("background.jpg");

		spaceship = new Spaceship(300, 200, new ImageView(ship));
		asteroids = new ArrayList<Asteroids>();
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
			if(event.getEventType().toString().equals("KEY_RELEASED") &&
				(((KeyEvent)event).getCode() == KeyCode.W ||
				((KeyEvent)event).getCode() == KeyCode.A ||
				((KeyEvent)event).getCode() == KeyCode.D ||
				((KeyEvent)event).getCode() == KeyCode.S)){

				spaceship.setPosVelX(0);
				spaceship.setPosVelY(0);
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
			Rectangle2D rectShip = new Rectangle2D(spaceship.getPosX(), spaceship.getPosY(), ship.getWidth(), ship.getHeight());
			spaceship.update();
			spaceship.updateRotate();
			Rectangle2D rectAsteroid = new Rectangle2D(30, 30, ast.getWidth(), ast.getHeight());
			System.out.println(spaceship.getPosVelY());
		}
	}
}