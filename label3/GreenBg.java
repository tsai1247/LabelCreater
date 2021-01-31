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

public class GreenBg
{
	int size = 51;
	DoubleProperty outadd = new SimpleDoubleProperty(0);
	DoubleProperty inadd = new SimpleDoubleProperty(0);
	public Pane pane = new Pane();
	private Circle green = new Circle((size+outadd.get()*2)*15, (size+outadd.get()*2)*15, (size/2+outadd.get()-(outadd.get()+inadd.get())/2.0)*30, Color.web("#39CE22"));
	private Rectangle[] textrange =
		{
			new Rectangle(210, 70, Color.WHITE),
			new Rectangle(811, 100, Color.WHITE)
		};
	private Line[] sepgreen =
		{
			new Line(450, 300, 450, 1240),
			new Line(0, 644.3, 1560, 644.3),
			new Line(10, 944.7, 1560, 944.7),
	};
	
	private Label[] disabletext =
	{
		new Label("檢修完成標示"),
		new Label("年度"),
		new Label("場　　所\n"
				+ "名　　稱"),
		new Label("檢修人員\n"
				+ "姓　　名"),
		new Label("本　　次\n"
				+ "檢查日期"),
		new Label("印製"),
		
	};
	
	public GreenBg()
	{
		pane.getChildren().add(green);
		pane.setPrefSize(52*30, 52*30);
		
		green.centerXProperty().bind(Bindings.add(size, outadd.multiply(2)).multiply(15));
		green.centerYProperty().bind(Bindings.add(size, outadd.multiply(2)).multiply(15));
		green.radiusProperty().bind
		(
			(Bindings.add(size/2.0, outadd.divide(2)).add(inadd.divide(-2))).multiply(30)
		);
		green.strokeWidthProperty().bind
		((Bindings.add(inadd, outadd)).multiply(30));
		green.setStroke(Color.web("#39CE22"));
		green.setFill
		(
	        new LinearGradient
	        (
	        	0,0,0,1, true, CycleMethod.NO_CYCLE,
		        new Stop[]
		        {
		        	new Stop(0, Color.web("#39CE22")),
		        	new Stop(0.2, Color.web("#39CE22")),

		        	new Stop(0.2, Color.WHITE),
		        	new Stop(0.8, Color.WHITE),
		        	
		        	new Stop(0.8, Color.web("#39CE22")),
	        		new Stop(1, Color.web("#39CE22")),
		        }
	        )
		);
		
		for(int i=0; i<sepgreen.length; i++)
		{
			pane.getChildren().add(sepgreen[i]);
			sepgreen[i].setStroke(Color.web("#39CE22"));
			sepgreen[i].setStrokeWidth(10);
		}
		
		for(int i=0; i<textrange.length; i++)
			pane.getChildren().add(textrange[i]);
		textrange[0].setLayoutX(592);
		textrange[0].setLayoutY(215);
		textrange[1].setLayoutX(364);
		textrange[1].setLayoutY(1250);

		for(int i=0; i<disabletext.length; i++)
		{
			pane.getChildren().add(disabletext[i]);
			disabletext[i].setAlignment(Pos.CENTER_RIGHT);
//					disabletext[i].setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
//					disabletext[i].setOpacity(0.5);
		}
		disabletext[0].setAlignment(Pos.CENTER);
		disabletext[5].setAlignment(Pos.CENTER);
		setRange(0, 432, 102, 1107, 197, 90, "Microsoft JhengHei", Color.WHITE);
		setRange(1, 592, 190, 947, 310, 70, "Microsoft JhengHei", Color.WHITE);
		setRange(2, 153, 323, 435, 638, 62, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(3, 105, 650, 435, 938, 62, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(4, 153, 950, 435, 1222, 62, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(5, 543, 1351, 997, 1469, 84, "Microsoft JhengHei", Color.WHITE);
		
		disabletext[0].setStyle("-fx-font-weight: bold;");
		disabletext[1].setStyle("-fx-font-weight: bold;");
		disabletext[5].setStyle("-fx-font-weight: bold;");
		
		tlrenew.setCycleCount(Timeline.INDEFINITE);
		tlrenew.play();
	}

	private void setRange(int i, double startX, double startY, double endX, double endY, double size, String fonttype, Paint color)
	{
		disabletext[i].setLayoutX(startX);
		disabletext[i].setLayoutY(startY);
		disabletext[i].setPrefSize(endX-startX, endY-startY);
		disabletext[i].setFont(Font.font(fonttype, size));
		disabletext[i].setTextFill(color);;
	}
	public void renew(double out, double in)
	{
		outadd.set(out);
		inadd.set(in);
		green = new Circle((size+outadd.get()*2)*15, (size+outadd.get()*2)*15, (size/2+outadd.get()-(outadd.get()+inadd.get())/2.0)*30, Color.web("#39CE22"));
	}
	Timeline tlrenew = new Timeline
	(
		new KeyFrame
		(
			Duration.millis(500),
			e->
			{
				renew(LabelMain.errornum.getWidth(0), LabelMain.errornum.getWidth(1));
			}
		)
	);
}
