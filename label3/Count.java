package label3;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Count
{
	public Pane pane = new Pane();
	private Label title = new Label("印製數量：");
	private TextField text = new TextField("15");
	private Label script = new Label("張");
	public Count()
	{
		pane.setPrefSize(600, 200);
		pane.setLayoutX(10);
		pane.setLayoutY(250);
		
		pane.getChildren().add(title);
		title.setFont(new Font(32));
		title.setLayoutX(0);
		title.setLayoutY(0);
		
		pane.getChildren().add(text);
		text.setAlignment(Pos.CENTER);
		text.setFont(new Font(32));
		text.setLayoutX(40);
		text.setLayoutY(45);
		text.setPrefWidth(100);
		
		pane.getChildren().add(script);
		script.setFont(new Font(36));
		script.setLayoutX(145);
		script.setLayoutY(55);
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
