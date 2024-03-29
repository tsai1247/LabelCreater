package label3;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Client
{
	Pane pane = new Pane();
	Label title = new Label("場所名稱：");
	TextField text = new TextField("__公司");
	CheckBox noclient = new CheckBox("無場所名稱");
	public Client()
	{
		//pane(Year, Pane)
		pane.setPrefSize(600, 200);
		pane.setLayoutX(10);
		pane.setLayoutY(10);
		
		//title(場所名稱, Label)
		pane.getChildren().add(title);
		title.setFont(new Font(32));
		title.setLayoutX(0);
		title.setLayoutY(0);
	
		
		//text(場所名稱內文, TextField)
		text.setFont(new Font(32));
		text.setPrefWidth(500);
		pane.getChildren().add(text);
		text.setLayoutX(40);
		text.setLayoutY(45);
		
		pane.getChildren().add(noclient);
		noclient.setLayoutX(180);
		noclient.setLayoutY(5);
		noclient.setFont(new Font(24));
		noclient.setOnAction
		(
			e->
			{
				if(noclient.isSelected())
				{
					text.setText("無場所名稱");
					text.setDisable(true);
				}
				else
				{
					text.setDisable(false);
				}
			}
		);
		
		
	}
	public String getText()
	{
			return text.getText();
	}
	public void setText(String string)
	{
		text.setText(string);
	}
	public void setDisable(boolean b)
	{
		if(b)
			text.setText("無場所名稱");
		noclient.setSelected(b);
		text.setDisable(b);
		noclient.setDisable(b);
	}
}
