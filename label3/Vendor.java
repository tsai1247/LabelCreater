package label3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Vendor
{
	Pane pane = new Pane();
	Label[] title =
	{
		new Label("檢修人員："),
		new Label(""),
		new Label("檢修機構：")
	};
	ComboBox<String> NUM = new ComboBox<String>();
	ObservableList<String> options = FXCollections.observableArrayList("消師證字第", "消士證字第");
	TextField[] text = 
	{
		new TextField("左安祥"),
		new TextField("0464"),
		new TextField("冠祥消防安全設備有限公司"),
		new TextField("XXXXXXXXXXXXXXXXXXX")
	};
	Label script = new Label("號");
	public Vendor()
	{
		//pane(Vendor, Pane)
		pane.setPrefSize(500, 700);
		pane.setLayoutX(20);
		pane.setLayoutY(500);
		
		//title(檢修資料, Label[3])
		for(int i=0; i<3; i++)
		{
			pane.getChildren().add(title[i]);
			title[i].setFont(new Font(32));
			title[i].setLayoutX((i%2)*180+((i+1)/3)*500);
			title[i].setLayoutY(0);
		}
		
		pane.getChildren().add(NUM);
		NUM.setLayoutX(180);
		NUM.setLayoutY(0);
		NUM.setStyle("-fx-font-size: 24px");
		NUM.setPrefSize(205, 35);
		NUM.getItems().addAll(options);
		NUM.setValue("消師證字第");
		
		
		//text(檢修資料內文, TextField[3])
		for(int i=0; i<2; i++)
		{
			text[i].setFont(new Font(32));
			pane.getChildren().add(text[i]);
			text[i].setLayoutX(20+(i%2)*310+((i+1)/3)*400);
			text[i].setLayoutY(50);
		}

		text[3].setFont(new Font(32));
		pane.getChildren().add(text[3]);
		text[3].setLayoutX(100+40+(2%2)*180+((2+1)/3)*400);
		text[3].setLayoutY(50);
		text[3].setPrefWidth(-100+430);
		
		
		text[2].setDisable(true);
		text[0].setPrefWidth(300);
		text[1].setPrefWidth(150);
		text[2].setPrefWidth(430);
		
		//script
		pane.getChildren().add(script);
		script.setFont(new Font(36));
		script.setLayoutX(480);
		script.setLayoutY(58);
	}
	public String getText(int i)
	{
		if(text[i]!=null && text[i].getText()!=null)
			return text[i].getText();
		else
			return "冠祥消防";
	}
	public String getTitle(int i)
	{
		if(i==1)
			return NUM.getValue();
		else
			return "";
	}
	public void setVendor()
	{
		try
		{
			FileReader fr = new FileReader(".\\error.txt");
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<6; i++)
				br.readLine();
			setVendor(2, br.readLine());
			setVendor(3, br.readLine());
			br.close();
			fr.close();
		}catch(IOException e)
		{
			setVendor(2, "冠祥消防");
			setVendor(3, "冠祥消防");			
		}
	}
	private void setVendor(int i, String st)
	{
		text[i].setText(st);
	}
	
}
