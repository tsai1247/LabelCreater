package label3;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Row
{
	public Pane pane = new Pane();
	private HBox hb = new HBox();
	private Label[] label = new Label[3];
	private boolean istitle = false;
	public Row(String[] string, boolean b)
	{
		istitle = b;
		pane.getChildren().add(hb);
		pane.setPrefSize(860, 40);
		
		for(int i=0; i<label.length; i++)
		{
			label[i] = new Label(string[i]);
			hb.getChildren().add(label[i]);
			label[i].setBorder(new Border(new BorderStroke(
				Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)	)));
			label[i].setFont(new Font(28));
			label[i].setAlignment(Pos.CENTER);
			label[i].setPrefHeight(40);
		}
		label[0].setPrefWidth(355);
		label[1].setPrefWidth(355);
		label[2].setPrefWidth(150);
		
		pane.setOnMouseClicked
		(
			e->
			{
				LabelMain.alldata.setallBorder(null);
				if(!istitle)
					pane.setBorder(new Border(new BorderStroke(
						Color.RED, BorderStrokeStyle.SOLID,
							null, new BorderWidths(3)	)));
			}
		);
	}

	public void setPaneBorder(Border border)
	{
		pane.setBorder(border);
	}

	public boolean isBorder()
	{
		if(pane.getBorder()==null)
			return false;
		else
			return true;
	}

	public String getText(int i)
	{
		return label[i].getText();
	}
}
