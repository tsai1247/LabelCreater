package label3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class RedBg
{
	int size = 51;
	DoubleProperty outadd = new SimpleDoubleProperty(0);
	DoubleProperty inadd = new SimpleDoubleProperty(0);
	public Pane pane = new Pane();
	private Circle red = new Circle((size+outadd.get()*2)*15, (size+outadd.get()*2)*15, (size/2+outadd.get()-(outadd.get()+inadd.get())/2.0)*30, Color.RED);
	private Rectangle textrange = new Rectangle(871, 116, Color.WHITE);
	private Line[] sepred =
		{
			new Line(450,  300,  450, 1240),
			new Line( 10,  550, 1560,  550),
			new Line(  0,  780, 1560,  780),
			new Line( 10, 1010, 1560, 1010),
	};

	
	private Label[] disabletext =
	{
		new Label("檢修完成標示"),
		new Label("場　　所\n"
				+ "名　　稱"),
		new Label("檢修機構"),
		new Label("檢修人員\n"
				+ "姓　　名"),
		new Label("本　　次\n"
				+ "檢查日期"),
		new Label("印製"),
		
	};
	
	public RedBg()
	{

		pane.getChildren().add(red);
		pane.setPrefSize(52*30, 52*30);
		
		red.centerXProperty().bind(Bindings.add(size, outadd.multiply(2)).multiply(15));
		red.centerYProperty().bind(Bindings.add(size, outadd.multiply(2)).multiply(15));
		red.radiusProperty().bind
		(
			(Bindings.add(size/2.0, outadd.divide(2)).add(inadd.divide(-2))).multiply(30)
		);
		red.strokeWidthProperty().bind
		((Bindings.add(inadd, outadd)).multiply(30));
		red.setStroke(Color.RED);
		red.setFill
		(
	        new LinearGradient
	        (
	        	0,0,0,1, true, CycleMethod.NO_CYCLE,
		        new Stop[]
		        {
		        	new Stop(0, Color.RED),
		        	new Stop(0.2, Color.RED),

		        	new Stop(0.2, Color.WHITE),
		        	new Stop(0.8, Color.WHITE),
		        	
		        	new Stop(0.8, Color.RED),
	        		new Stop(1, Color.RED),
		        }
	        )
		);
		
		for(int i=0; i<sepred.length; i++)
		{
			pane.getChildren().add(sepred[i]);
			sepred[i].setStroke(Color.RED);
			sepred[i].setStrokeWidth(10);
		}
		
		pane.getChildren().add(textrange);
		textrange.setLayoutX(343);
		textrange.setLayoutY(1264);

		for(int i=0; i<disabletext.length; i++)
		{
			pane.getChildren().add(disabletext[i]);
			disabletext[i].setAlignment(Pos.CENTER_RIGHT);
//			disabletext[i].setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
//			disabletext[i].setOpacity(0.5);
		}
		disabletext[0].setAlignment(Pos.CENTER);
		disabletext[5].setAlignment(Pos.CENTER);
		setRange(0, 412,  137, 1127,  232, 116, "Microsoft JhengHei", Color.WHITE);
		setRange(1, 153,  324,  438,  547,  62, "Microsoft JhengHei", Color.RED);
		setRange(2,  40,  549,  438,  779,  62, "Microsoft JhengHei", Color.RED);
		setRange(3,  40,  786,  438, 1009,  62, "Microsoft JhengHei", Color.RED);
		setRange(4, 153, 1013,  438, 1235,  62, "Microsoft JhengHei", Color.RED);
		setRange(5, 543, 1391,  997, 1509,  84, "Microsoft JhengHei", Color.WHITE);
		
		disabletext[0].setStyle("-fx-font-weight: bold;");
		disabletext[5].setStyle("-fx-font-weight: bold;");

		tlrenew.setCycleCount(Timeline.INDEFINITE);
		tlrenew.play();
	}

	public void renew(double out, double in)
	{
			outadd.set(out);
			inadd.set(in);
			red = new Circle((size+outadd.get()*2)*15, (size+outadd.get()*2)*15, (size/2+outadd.get()-(outadd.get()+inadd.get())/2.0)*30, Color.RED);
	}
	
	Timeline tlrenew = new Timeline
	(
		new KeyFrame
		(
			Duration.millis(500),
			e->
			{
				try
				{
					renew(LabelMain.errornum.getWidth(0), LabelMain.errornum.getWidth(1));
				}
				catch(Exception ee)
				{
					renew(0.0, 0.0);
				}
			}
		)
	);
	
	
	private void setRange(int i, double startX, double startY, double endX, double endY, double size, String fonttype, Paint color)
	{
		disabletext[i].setLayoutX(startX);
		disabletext[i].setLayoutY(startY);
		disabletext[i].setPrefSize(endX-startX, endY-startY);
		disabletext[i].setFont(Font.font(fonttype, size));
		disabletext[i].setTextFill(color);;
	}
}
