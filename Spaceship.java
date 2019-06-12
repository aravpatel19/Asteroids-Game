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

public class Spaceship extends Player{

	ImageView spaceship;

	public Spaceship(double x, double y, ImageView spaceship){
		super(x, y);
		this.spaceship = spaceship;
	}
	public ImageView getShip(){
		return spaceship;
	}
}

