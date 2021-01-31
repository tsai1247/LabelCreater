//unuseful
package label3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SaveLocation {

	Pane pane = new Pane();
	Label[] title =
	{
			new Label("存檔資料夾："),
			new Label("檔名："),
	};
	TextField[] text =
	{
		new TextField("D:\\"),
		new TextField("未命名"),
		
	};
	Button choose = new Button("...");
	Line script = new Line(0, 20, 500, 20);
	ComboBox<String> subname = new ComboBox<String>();
	ObservableList<String> options = FXCollections.observableArrayList(".jpg", ".png");
	CheckBox autoname = new CheckBox("與場所名稱相同");
	public SaveLocation()
	{
		//pane(SaveLocation, Pane)
		pane.setPrefSize(600, 200);
		pane.setLayoutX(12);
		pane.setLayoutY(480);

		//title(存檔位置, Label)
		for(int i=0; i<title.length; i++)
		{
			pane.getChildren().add(title[i]);
			title[i].setFont(new Font(26));
			title[i].setLayoutX(0);
			title[i].setLayoutY(40+90*i);
		}
		try
		{
			FileReader fr = new FileReader(".\\label\\data.txt");
			BufferedReader br = new BufferedReader(fr);
			text[0].setText(br.readLine());
			br.close();
			fr.close();
		}
		catch(IOException e1) {e1.printStackTrace();}
			
		//text(存檔路徑, TextField)
		for(int i=0; i<text.length; i++)
		{
			pane.getChildren().add(text[i]);
			text[i].setFont(new Font(22));
			text[i].setLayoutX(40);
			text[i].setLayoutY(80+90*i);
			text[i].setPrefWidth(400-140*i);
		}
		text[1].setAlignment(Pos.CENTER_RIGHT);
		
		//choose(存檔位置選擇器, Button)
		pane.getChildren().add(choose);
		choose.setLayoutX(439);
		choose.setLayoutY(80);
		choose.setFont(new Font(22));
		choose.setPrefSize(50, 40);
		
		//sepa(分隔線, Line)
		pane.getChildren().add(script);
		script.getStrokeDashArray().addAll(10d);
		script.setSmooth(true);
		
		//subname
		pane.getChildren().add(subname);
		subname.setLayoutX(300);
		subname.setLayoutY(170);
		subname.setStyle("-fx-font-size: 20px");
		subname.setPrefSize(120, 45);
		subname.getItems().addAll(options);
		subname.setValue(".jpg");
		choose.setOnAction
		(
			e->
			{
				DirectoryChooser fc = new DirectoryChooser();
//				fc.setInitialDirectory(new File(System.getProperty("")));
				File saveFile = fc.showDialog(new Stage());
				text[0].setText(saveFile.getPath());
			}
		);
		
		//autoname
		pane.getChildren().add(autoname);
		autoname.setLayoutX(40);
		autoname.setLayoutY(220);
		autoname.setFont(new Font(18));
		autoname.setSelected(true);
		checkAuto();
		autoname.setOnAction
		(
			e->
			{
				checkAuto();
			}
		);
	}
	private void checkAuto()
	{
		if(autoname.isSelected())
		{
//			text[1].textProperty().bind(LabelMain.client.text.textProperty());
			text[1].setDisable(true);
		}	
		else
		{
			text[1].textProperty().unbind();
			text[1].setDisable(false);
		}
	}
	public String getText()
	{
		return getText(0)+"\\"+getText(1) + subname.getValue();
	}
	public String getText(int i)
	{
		if(i<2)
			return text[i].getText();
		else
			return subname.getValue();
	}
	public void setLocation(String path, String string)
	{
		int i;
		for(i=path.length()-1; i>=0; i--)
		{
			if(path.charAt(i)=='/' || path.charAt(i)=='\\')
				break;
		}
		text[0].setText(path.substring(0, i));
		text[1].setText(path.substring(i+1, path.length()-4));
		subname.setValue(string);
	}
}
