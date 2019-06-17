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

	private double rotAng;
	private double rotVel;

	public Spaceship(double x, double y, Image spaceship){
		super(x, y);
		this.spaceship = new ImageView(spaceship);
		this.rotAng = 0;
		this.rotVel =0;
	}
	public ImageView getShip(){
		return spaceship;
	}

	public double getRotAng(){
		return rotAng;
	}

	public double getVelRot(){
		return rotVel;
	}

	public void setRotVel(double rotVel){
		this.rotVel = rotVel;
	}

	public void setRotAng(double rotAng){
		this.rotAng = rotAng;
	}

	public void updateRotate(){
		rotAng += rotVel;
	}
}

