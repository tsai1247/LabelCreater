package label3;

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

public class SampleBg
{
	public Pane pane = new Pane();
	private int size = 50;
	private double outadd=1, inadd=2;
	private int times = 5;
	private Circle green = new Circle(size*times/2, size*times/2, ((size+outadd*2)/2-(outadd+inadd)/2.0)*times, Color.web("#39CE22"));
	private Circle gray = new Circle(50*times/2, 50*times/2, 50*times/2, Color.GRAY);
	
	private Rectangle[] textrange =
	{
		new Rectangle(7*times, 2.33*times, Color.WHITE),
		new Rectangle(27*times, 3.33*times, Color.WHITE)
	};
	private Line[] sepgreen =
	{
		new Line(15*times, 10*times, 15*times, 41.33*times),
		new Line(0.8*times, 21.48*times, 49.3*times, 21.48*times),
		new Line(0.8*times, 31.49*times, 49.3*times, 31.49*times),
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
	public SampleBg()
	{
		pane.setLayoutX(270);
		pane.setLayoutY(70);
		
		
		sepgreen[0].setStroke(Color.web("#39CE22"));
		sepgreen[1].setStroke(Color.web("#39CE22"));
		sepgreen[2].setStroke(Color.web("#39CE22"));
		

		for(int i=0; i<disabletext.length; i++)
			disabletext[i].setAlignment(Pos.CENTER_RIGHT);
		disabletext[0].setAlignment(Pos.CENTER);
		disabletext[5].setAlignment(Pos.CENTER);
		setRange(0, 432/30.0*times, 62/30.0*times, 1107/30.0*times, 157/30.0*times, 90/30.0*times, "Microsoft JhengHei", Color.WHITE);
		setRange(1, 592/30.0*times, 170/30.0*times, 947/30.0*times, 290/30.0*times, 70/30.0*times, "Microsoft JhengHei", Color.WHITE);
		setRange(2, 153/30.0*times, 323/30.0*times, 435/30.0*times, 638/30.0*times, 62/30.0*times, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(3, 105/30.0*times, 650/30.0*times, 435/30.0*times, 938/30.0*times, 62/30.0*times, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(4, 153/30.0*times, 950/30.0*times, 435/30.0*times, 1222/30.0*times, 62/30.0*times, "Microsoft JhengHei", Color.web("#39CE22"));
		setRange(5, 543/30.0*times, 1371/30.0*times, 997/30.0*times, 1489/30.0*times, 84/30.0*times, "Microsoft JhengHei", Color.WHITE);
		
		disabletext[0].setStyle("-fx-font-weight: bold;");
		disabletext[1].setStyle("-fx-font-weight: bold;");
		disabletext[5].setStyle("-fx-font-weight: bold;");
	
		
		
		gray.setFill(null);
		gray.setStroke(Color.GRAY);
		gray.setStrokeWidth(2);
		gray.getStrokeDashArray().add(10d);
	    gray.setStrokeDashOffset(10);
		
		renew(1, 2);
	}

	public void renew(double out, double in)
	{
		outadd = out; inadd = in;
		pane.getChildren().clear();
		green = new Circle(size*times/2, size*times/2, ((size+outadd*2)/2-(outadd+inadd)/2.0)*times, Color.web("#39CE22"));
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
		green.setStroke(Color.web("#39CE22"));

		pane.getChildren().add(green);
		green.setStrokeWidth((outadd+inadd)*times);
		
		for(int i=0; i<sepgreen.length; i++)
		{
			pane.getChildren().add(sepgreen[i]);
			sepgreen[i].setStrokeWidth(0.33*times);
		}
		
		for(int i=0; i<textrange.length; i++)
			pane.getChildren().add(textrange[i]);
		textrange[0].setLayoutX(19.73*times);
		textrange[0].setLayoutY(6.33*times);
		textrange[1].setLayoutX(12.13*times);
		textrange[1].setLayoutY(42.5*times);
		
		
		
		
		
		for(int i=0; i<disabletext.length; i++)
			pane.getChildren().add(disabletext[i]);
		
		
		
		
		
		
		
		pane.getChildren().add(gray);
	}
	private void setRange(int i, double startX, double startY, double endX, double endY, double size, String fonttype, Paint color)
	{
		disabletext[i].setLayoutX(startX);
		disabletext[i].setLayoutY(startY);
		disabletext[i].setPrefSize(endX-startX, endY-startY);
		disabletext[i].setFont(Font.font(fonttype, size));
		disabletext[i].setTextFill(color);;
	}
}
