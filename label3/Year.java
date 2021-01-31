package label3;

import java.time.LocalDate;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Year
{
	Pane pane = new Pane();
	Label title = new Label("檢修年度：");
	TextField text = new TextField(String.valueOf(LocalDate.now().getYear()-1911));

	
	public Year()
	{
		//pane(Year, Pane)
		pane.setPrefSize(150, 100);
		pane.setLayoutX(20);
		pane.setLayoutY(420);
		
		//title(檢修年度, Label)
		pane.getChildren().add(title);
		title.setFont(new Font(32));
		title.setLayoutX(0);
		title.setLayoutY(10);
		
		//text(檢修年度內文, TextField)
		pane.getChildren().add(text);
		text.setFont(new Font(32));
		text.setLayoutX(160);
		text.setLayoutY(0);
		text.setPrefWidth(100);
	}


	public String getText()
	{
		return text.getText();
	}


	public void setText(String string)
	{
		text.setText(string);
	}


	
}
