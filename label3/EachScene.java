package label3;


import javafx.scene.Scene;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EachScene
{
	int size = 52;
	Pane pane = new Pane();
	Scene scene = new Scene(pane, size*30, size*30); //630*6, 891*6	
	private Rectangle bg = new Rectangle(size*30, size*30, Color.WHITE);
	private GreenBg greenbg = new GreenBg();
	private RedBg redbg = new RedBg();
	private VariableText variabletext = new VariableText();
	
	public EachScene()
	{
		//rectangle
		bg.setLayoutX(0);
		bg.setLayoutY(0);
				
		//pane
		pane.setLayoutX(0);
		pane.setLayoutY(200);
		pane.setPrefSize(scene.getWidth(), scene.getHeight());
	}
	
/////////////////
	

	public void setAlltext(Row row)
	{
		//add
		pane.getChildren().add(bg);
		
		if(row!=null && !row.getText(0).equals("ªÅ¥Õ") && !row.getText(0).equals("°Ï¶ô") )
		{
			if(!LabelMain.labelkind.isbg.isSelected())
			{
				if(LabelMain.labelkind.isgreen())
					pane.getChildren().add(greenbg.pane);
				else
					pane.getChildren().add(redbg.pane);
			}
			pane.getChildren().add(variabletext.pane);
			variabletext.setText(row);
		}
	}

	public void clear()
	{
		pane.getChildren().clear();
	}

	public void renewBg(double in, double out)
	{
		greenbg.renew(in, out);
		redbg.renew(in, out);
	}
}
