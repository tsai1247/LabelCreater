package label3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LabelKind
{
	Pane pane = new Pane();
	Label choose = new Label("���������G");
	Label title2 = new Label("���Ҥj�p�G");
	ToggleGroup TB = new ToggleGroup();
	RadioButton green = new RadioButton("���");
	RadioButton red = new RadioButton("����");
	ToggleGroup TBpage = new ToggleGroup();
	CheckBox isbg = new CheckBox("�ȦL�X��r");
	ComboBox<String> sizes = new ComboBox<String>();
	ObservableList<String> options = FXCollections.observableArrayList("3x5", "4x5", "4x6");
	
	
	public LabelKind()
	{
		//pane(LabelKind, Pane)
		pane.setPrefSize(400, 150);
		pane.setLayoutX(460);
		pane.setLayoutY(330);
		
		//choose(��������, Label)
		pane.getChildren().add(choose);
		choose.setFont(new Font(32));
		choose.setLayoutX(0);
		choose.setLayoutY(0);
		
		//green(���, RadioButton)
		pane.getChildren().add(green);
		green.setFont(new Font(32));
		green.setLayoutX(165);
		green.setLayoutY(0);
		TB.getToggles().add(green);
		green.setSelected(true);

		//red(����, RadioButton)
		pane.getChildren().add(red);
		red.setFont(new Font(32));
		red.setLayoutX(305);
		red.setLayoutY(0);
		TB.getToggles().add(red);
//		red.setDisable(true);
		
		//isbg
		pane.getChildren().add(isbg);
		isbg.setLayoutX(165);
		isbg.setLayoutY(60);
		isbg.setFont(new Font(30));
		
		//title2
		pane.getChildren().add(title2);
		title2.setLayoutX(0);
		title2.setLayoutY(120);
		title2.setFont(new Font(32));
		
		//size
		pane.getChildren().add(sizes);
		sizes.setLayoutX(165);
		sizes.setLayoutY(120);
		sizes.setStyle("-fx-font-size: 20px");
		sizes.setPrefSize(120, 45);
		sizes.getItems().addAll(options);
		sizes.setValue("3x5");
		
		
	}




	public boolean isgreen()
	{
		if(green.isSelected())	return true;
		else					return false;
	}




	public int getFullNum()
	{
		if(sizes.getValue().equals("3x5"))
			return 15;
		else if(sizes.getValue().equals("4x6"))
			return 24;
		else if(sizes.getValue().equals("4x5"))
			return 20;

		return -1;
	}

}
