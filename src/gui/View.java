package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ImageMatrix;
import model.Algorithms.Algorithm;
import model.Algorithms.KMeansAlgorithm;
public class View extends Application {

	private int sceneWidth = 800, sceneHeight = 300;
	private ImageView originalImageView, alteredImageView;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of clusters you want: ");
		int num = scan.nextInt();
		
		originalImageView = new ImageView();
		originalImageView.setFitHeight(sceneHeight);
		originalImageView.setFitWidth(sceneWidth/2);
		
		alteredImageView = new ImageView();
		alteredImageView.setFitHeight(sceneHeight);
		alteredImageView.setFitWidth(sceneWidth/2);

		BorderPane pane = new BorderPane();
		pane.setLeft(originalImageView);
		pane.setRight(alteredImageView);
		
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		
		primaryStage.setTitle("Image Segmentation App");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		BufferedImage originalImg = ImageIO.read(new File("./images/cat.jpg"));
		originalImageView.setImage(SwingFXUtils.toFXImage(originalImg, null));

		int numOfClusters = num;
		
		ImageMatrix imageMatrix = new ImageMatrix(originalImg, numOfClusters);
		Algorithm algorithm = new KMeansAlgorithm(imageMatrix);
		
		algorithm.process();
		BufferedImage segmentedImg = imageMatrix.getSegmentedImage();
		
		alteredImageView.setImage(SwingFXUtils.toFXImage(segmentedImg, null));
		scan.close();
	}


	public static void main(String[] args) {
	
		Application.launch(args);
	}
	
}
