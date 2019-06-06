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
import javafx.scene.layout.StackPane;
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
	AnimateObjects animate;
	Image spaceship;
	Image asteroid;
	Canvas canvas;
	ImageView ss;
	ImageView a;
	StackPane pane;
	Scene scene;

	public static void main(String[] args){

		launch();
	}

	public void start(Stage stage){

		stage.setTitle("Asteroids");
		Group root = new Group();
		//StackPane root = new StackPane();
		canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();
		spaceship = new Image("Spaceship.JPG");
		asteroid  = new Image("Asteroid.JPG");
		a = new ImageView(asteroid);
		ss = new ImageView(spaceship);

		//pane = new StackPane();
		scene = new Scene(root, Color.BLACK);

		//ss.setFitWidth(50);
		//root.getChildren().add(ss);
		//pane.getChildren().add(a);*/

		stage.setScene(scene);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		animate = new AnimateObjects();
		animate.start();

		stage.show();
	}
	public void handle(final InputEvent event){
		if(event instanceof KeyEvent){
			if(((KeyEvent)event).getCode() == KeyCode.LEFT)
				x--;
			if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
				x++;

		}
		if(event instanceof MouseEvent){
			System.out.println(((MouseEvent)event).getX());
			System.out.println(((MouseEvent)event).getY());
		}
	}


	public class AnimateObjects extends AnimationTimer{
		public void handle(long now){
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.rotate(x);
			gc.drawImage(spaceship, 180, 200);
			gc.drawImage(asteroid, 30, 30);

		}
	}
}