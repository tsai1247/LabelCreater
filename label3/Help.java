package label3;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Help
{
	public Pane pane = new Pane();
	public ImageView img = new ImageView(new Image(getClass().getResourceAsStream("help.png")));
	public Label title = new Label("¯È±i¤j¤p");
	public Label lb = new Label("");
	public Help()
	{
		pane.setLayoutX(750);
		pane.setLayoutY(459);
		pane.getChildren().add(img);
		img.setFitHeight(30);
		img.setFitWidth(30);
		
		pane.getChildren().add(title);
		title.setLayoutX(33);
		title.setLayoutY(0);
		title.setTextFill(Color.ORANGERED);
		title.setFont(Font.font(null, FontWeight.BOLD, 24));
		
		pane.getChildren().add(lb);
		lb.setLayoutX(0);
		lb.setLayoutY(30);
		lb.setTextFill(Color.RED);
		lb.setFont(Font.font(24));
		lb.setVisible(false);
		
		img.setOnMouseEntered
		(
			e->
			{
				int tmp = LabelMain.labelkind.getFullNum();
				if(tmp==15)
					lb.setText("210*297mm");
				else if(tmp==24)
					lb.setText("210*342mm");
				lb.setVisible(true);
			}
		);		
		img.setOnMouseExited
		(
			e->
			{
				lb.setVisible(false);
			}
		);
		title.setOnMouseEntered
		(
			e->
			{
				int tmp = LabelMain.labelkind.getFullNum();
				if(tmp==15)
					lb.setText("210*297mm");
				else if(tmp==24)
					lb.setText("210*342mm");
				lb.setVisible(true);
			}
		);		
		title.setOnMouseExited
		(
			e->
			{
				lb.setVisible(false);
			}
		);
	}
}
