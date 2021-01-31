package label3;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Candidate
{
	public Pane pane = new Pane();
	private Label text = new Label();
	
	public Candidate(String string)
	{
		pane.setPrefSize(295, 40);

		pane.getChildren().add(text);
		text.setFont(new Font(24));
		text.setText(string);
		text.setPrefSize(295, 40);
		pane.setBorder(new Border(new BorderStroke(
				Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)	)));
		pane.setOnMouseClicked
		(
			e->
			{
				LabelMain.alldata.addstage.setClient(text.getText());
			}
		);
	}
	public String getText()
	{
		return text.getText();
	}
}
