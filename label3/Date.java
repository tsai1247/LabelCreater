package label3;

import java.time.LocalDate;

import javafx.scene.control.CheckBox;
//import java.time.chrono.MinguoChronology;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Date
{
	Pane pane = new Pane();
	Label title = new Label("檢查日期：");
	DatePicker[] date = new DatePicker[2];
	Label script = new Label("~");
	CheckBox empty = new CheckBox("無日期");
	CheckBox sameasabove = new CheckBox("同上一份日期");
	CheckBox printword = new CheckBox("印出年月日");
	
	public Date()
	{
		//pane(Year, Pane)
		pane.setPrefSize(700, 200);
		pane.setLayoutX(10);
		pane.setLayoutY(130);
		
		//title(檢查日期, Label)
		pane.getChildren().add(title);
		title.setFont(new Font(32));
		title.setLayoutX(0);
		title.setLayoutY(0);

		pane.getChildren().add(empty);
		empty.setLayoutX(160);
		empty.setLayoutY(5);
		empty.setFont(new Font(24));
		
		pane.getChildren().add(sameasabove);
		sameasabove.setLayoutX(290);
		sameasabove.setLayoutY(5);
		sameasabove.setFont(new Font(24));
		
		pane.getChildren().add(printword);
		printword.setLayoutX(490);
		printword.setLayoutY(5);
		printword.setFont(new Font(24));
		printword.setDisable(true);
		
		//date(Y年M月D日, DatePicker)
		for(int i=0; i<date.length; i++)
		{
			date[i] = new DatePicker();
			pane.getChildren().add(date[i]);
			date[i].setLayoutX(40+330*i);
			date[i].setLayoutY(50);
			date[i].setPrefWidth(270);
			date[i].setValue(LocalDate.now());
			date[i].setStyle("-fx-font-size: 26px");
		}
		
		//script
		pane.getChildren().add(script);
		script.setLayoutX(327);
		script.setLayoutY(50);
		script.setFont(new Font(40));
		
		empty.setOnAction
		(
			e->
			{
				if(empty.isSelected())
				{
					printword.setDisable(false);
					date[0].setDisable(true);
					sameasabove.setDisable(true);
					date[0].setValue(null);

					date[1].setDisable(true);
					date[1].setValue(null);
				}
				else
				{
					printword.setDisable(true);
					printword.setSelected(false);
					if(LabelMain.alldata.getcur() == 0)
						sameasabove.setDisable(true);
					else
						sameasabove.setDisable(false);
					date[0].setDisable(false);
					date[1].setDisable(false);
				}
			}
		);
		sameasabove.setOnAction
		(
			e->
			{
				if(sameasabove.isSelected())
				{
					empty.setDisable(true);
					printword.setDisable(true);
					date[0].setDisable(true);
					date[1].setDisable(true);
				
					String tmp = LabelMain.alldata.getLastDate();
					if(tmp.equals("0"))
					{
						date[0].setValue(null);
						date[1].setValue(null);
					}
					else if(tmp.equals("1"))
					{
						date[0].setValue(null);
						date[1].setValue(null);
						printword.setSelected(true);
					}
					else
					{
						for(int i=0; i<2; i++)
							date[i].setValue(LocalDate.parse(tmp.split("~")[i]));
					}
				}
				else
				{
					empty.setDisable(false);
//					printword.setDisable(false);
					printword.setSelected(false);
					date[0].setDisable(false);
					date[1].setDisable(false);
				}
			}
		);
	}
	public String getText(int i, String s1, String s2, String s3, String s4)
	{
		return 	s1 + (Integer.valueOf(date[i].getValue().getYear())-1911)
			  + s2 + date[i].getValue().getMonthValue() 
			  + s3 + date[i].getValue().getDayOfMonth()
			  + s4;
	}
	public String getText()
	{
		if(printword.isSelected())
			return "自行填寫形式";
		else if(empty.isSelected())
			return "無日期形式";
		else
		{
			try
			{
				return date[0].getValue().toString() +"~"+ date[1].getValue().toString();
			}
			catch(Exception e)
			{
				return "無日期形式";
			}
		}
	}

	public void setAllText(String s1, String s2)
	{
		if(s1==null || s2==null)
		{
			date[0].setValue(null);
			date[1].setValue(null);
		}
		else
		{
			date[0].setValue(LocalDate.parse(s1));
			date[1].setValue(LocalDate.parse(s2));
		}
	}
	
	public void setSelected(boolean b, boolean y, boolean s)
	{
		sameasabove.setSelected(s);
		empty.setSelected(b);
		printword.setSelected(y);
	}
	public void setDisable(boolean b, boolean y, boolean s)
	{
		sameasabove.setDisable(s);
		empty.setDisable(b);
		date[0].setDisable(empty.isSelected());
		date[1].setDisable(empty.isSelected());
		
		printword.setDisable(y);
	}
}
