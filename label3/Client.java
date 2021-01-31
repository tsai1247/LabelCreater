package label3;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Client
{
	Pane pane = new Pane();
	Label title = new Label("���ҦW�١G");
	TextField text = new TextField("__���q");
	CheckBox noclient = new CheckBox("�L���ҦW��");
	public Client()
	{
		//pane(Year, Pane)
		pane.setPrefSize(600, 200);
		pane.setLayoutX(10);
		pane.setLayoutY(10);
		
		//title(���ҦW��, Label)
		pane.getChildren().add(title);
		title.setFont(new Font(32));
		title.setLayoutX(0);
		title.setLayoutY(0);
	
		
		//text(���ҦW�٤���, TextField)
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
					text.setText("�L���ҦW��");
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
			text.setText("�L���ҦW��");
		noclient.setSelected(b);
		text.setDisable(b);
		noclient.setDisable(b);
	}
}
