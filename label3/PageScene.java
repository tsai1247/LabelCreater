package label3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PageScene
{
	Pane pane = new Pane();
//	Scene scene = new Scene(pane, LabelMain.workbtn.getJobValue(0), LabelMain.workbtn.getJobValue(1));	
	Scene scene = new Scene(pane, 210*18, 342*18);	
	Rectangle bg = new Rectangle(scene.getWidth(), scene.getHeight(), Color.WHITE);
	ImageView[][] copyimg = new ImageView[6][4];
	double errX = 0;
	double errY = 0;
	int size = 52;
	public PageScene()
	{
		//pane
		pane.setLayoutX(0);
		pane.setLayoutY(0);
		pane.setPrefSize(scene.getWidth(), scene.getHeight());
	
		//rectangle
		pane.getChildren().add(bg);
		bg.setLayoutX(0);
		bg.setLayoutY(0);
		
		//copyimg
		for(int i=0; i<copyimg.length; i++) for(int j=0; j<copyimg[0].length; j++)
		{
			copyimg[i][j] = new ImageView();
			copyimg[i][j].setFitHeight(size*18);
			copyimg[i][j].setFitWidth(size*18);
			
//			copyimg[i][j].setFitHeight((int)(size*
//					(LabelMain.workbtn.getJobValue(0)+LabelMain.workbtn.getJobValue(3)+LabelMain.workbtn.getJobValue(5))
//					/(2100)));
//			copyimg[i][j].setFitWidth((int)(size*
//					(LabelMain.workbtn.getJobValue(1)+LabelMain.workbtn.getJobValue(2)+LabelMain.workbtn.getJobValue(4))
//					/(2970)));
		}
		try
		{
			FileReader fr = new FileReader(".\\error.txt");
			BufferedReader br = new BufferedReader(fr);
			errY = Double.valueOf(br.readLine());
			errX = Double.valueOf(br.readLine());
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			errX = 0;
			errY = 0;
		}
	}

	public ImageView makePage(EachScene[] eachscene)
	{
		pane.getChildren().clear();
		pane.getChildren().add(bg);
		ImageView[] each = new ImageView[24];
		for(int i=0; i<each.length; i++)
		{
			WritableImage writableImage = new WritableImage(size*30, size*30);
			eachscene[i].pane.snapshot(new SnapshotParameters(), writableImage);
			each[i] = new ImageView(writableImage);
		}
		if(LabelMain.labelkind.getFullNum()==15)
		{
			for(int i=0; i<5; i++) for(int j=0; j<3; j++)
			{
				pane.getChildren().add(copyimg[i][j]);
				copyimg[i][j].setImage(each[i*3+j].getImage());
				copyimg[i][j].setLayoutX((490-5*size+560*j-errX*10)*1.8);
				copyimg[i][j].setLayoutY((365-5*size+560*i-errY*10)*1.8);
				
	//			copyimg[i][j].setLayoutX((((490-5*size)+560*j)*
	//					(LabelMain.workbtn.getJobValue(0)+LabelMain.workbtn.getJobValue(3)+LabelMain.workbtn.getJobValue(5))
	//					/(2100) )
	//					- LabelMain.workbtn.getJobValue(3));
	//			copyimg[i][j].setLayoutY((((365-5*size)+560*i)*
	//					(LabelMain.workbtn.getJobValue(1)+LabelMain.workbtn.getJobValue(2)+LabelMain.workbtn.getJobValue(4))
	//					/(2970) )- LabelMain.workbtn.getJobValue(2));
			}
			WritableImage PageImg = new WritableImage(210*18, 297*18);
	    	pane.snapshot(new SnapshotParameters(), PageImg);
	    	return new ImageView(PageImg);
		}
		else if(LabelMain.labelkind.getFullNum()==20)
		{
			for(int i=0; i<5; i++) for(int j=0; j<4; j++)
			{
				pane.getChildren().add(copyimg[i][j]);
				copyimg[i][j].setImage(each[i*4+j].getImage());
				copyimg[i][j].setLayoutX(( 20+250 +520*j-errX*10-size*5)*1.8);
				copyimg[i][j].setLayoutY((195+250 -5*size+520*i-errY*10)*1.8);
				
	//			copyimg[i][j].setLayoutX((((490-5*size)+560*j)*
	//					(LabelMain.workbtn.getJobValue(0)+LabelMain.workbtn.getJobValue(3)+LabelMain.workbtn.getJobValue(5))
	//					/(2100) )
	//					- LabelMain.workbtn.getJobValue(3));
	//			copyimg[i][j].setLayoutY((((365-5*size)+560*i)*
	//					(LabelMain.workbtn.getJobValue(1)+LabelMain.workbtn.getJobValue(2)+LabelMain.workbtn.getJobValue(4))
	//					/(2970) )- LabelMain.workbtn.getJobValue(2));
			}
			WritableImage PageImg = new WritableImage(210*18, 297*18);
	    	pane.snapshot(new SnapshotParameters(), PageImg);
	    	return new ImageView(PageImg);
		}
		else if(LabelMain.labelkind.getFullNum()==24)
		{
			for(int i=0; i<6; i++) for(int j=0; j<4; j++)
			{
				pane.getChildren().add(copyimg[i][j]);
				copyimg[i][j].setImage(each[i*4+j].getImage());
				copyimg[i][j].setLayoutX((270+520*j-errX*10-size*5)*1.8);
				copyimg[i][j].setLayoutY((270+520*i-errY*10-size*5)*1.8);
				
	//			copyimg[i][j].setLayoutX((((490-5*size)+560*j)*
	//					(LabelMain.workbtn.getJobValue(0)+LabelMain.workbtn.getJobValue(3)+LabelMain.workbtn.getJobValue(5))
	//					/(2100) )
	//					- LabelMain.workbtn.getJobValue(3));
	//			copyimg[i][j].setLayoutY((((365-5*size)+560*i)*
	//					(LabelMain.workbtn.getJobValue(1)+LabelMain.workbtn.getJobValue(2)+LabelMain.workbtn.getJobValue(4))
	//					/(2970) )- LabelMain.workbtn.getJobValue(2));
			}
			WritableImage PageImg = new WritableImage(210*18, 342*18);
	    	pane.snapshot(new SnapshotParameters(), PageImg);
	    	return new ImageView(PageImg);
		}
		else
			return null;
	}


}
