package label3;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AllData
{
	public Pane pane = new Pane();
	private ScrollPane spane = new ScrollPane();
	private VBox vb = new VBox();
	private HBox hb = new HBox();
	private Button add = new Button("新增");
	private Button wri = new Button("修改");
	private Button del = new Button("刪除");
	private int cur = 1;
	public ArrayList<Row> rows = new ArrayList<Row>();
	public addStage addstage = new addStage();
	public AllData()
	{
		pane.setLayoutX(20);
		pane.setLayoutY(20);
		pane.setPrefSize(900, 500);
		
		pane.getChildren().add(spane);
		spane.setPrefSize(862, 300);
		
		
		String[] titletext = {"場所名稱", "檢查日期", "印製張數"};
		Row title = new Row(titletext, true);
		rows.add(title);
		
		spane.setContent(vb);
		vb.getChildren().add(title.pane);

		pane.getChildren().add(hb);
		hb.setLayoutX(10);
		hb.setLayoutY(310);
		hb.setSpacing(15);
		
		hb.getChildren().add(add);
		add.setPrefSize(130, 50);
		add.setFont(new Font(30));
		add.setOnAction
		(
			e->
			{
				add.setDisable(true);
//				vb.getChildren().add(new Row(new addStage().getData()).pane);
				cur = rows.size()-1;
				addstage.showAndInitialize();
				
			}
		);
		
		hb.getChildren().add(wri);
		wri.setPrefSize(130, 50);
		wri.setFont(new Font(30));
		wri.setOnAction
		(
			e->
			{
				for(int i=0; i<rows.size(); i++)
				{
					if(rows.get(i).isBorder())
					{
						cur = i;
						addstage.showAndInitialize(rows.get(i));
						break;
					}
				}
			}
		);
		
		hb.getChildren().add(del);
		del.setPrefSize(130, 50);
		del.setFont(new Font(30));
		del.setOnAction
		(
			e->
			{
				for(int i=0; i<rows.size(); i++)
				{
					if(rows.get(i).isBorder())
					{
						rows.get(i).setPaneBorder(null);
						vb.getChildren().remove(i);
						rows.remove(i);
						break;
					}
				}
			}
		);
		
	}
	public void setAddDisable(boolean b)
	{
		add.setDisable(b);
	}
	public void addRow(String[] recall)
	{
		rows.add(new Row(recall, false));
		vb.getChildren().add(rows.get(rows.size()-1).pane);
	}
	public void setallBorder(Border border)
	{
		for(int i=0; i<rows.size(); i++)
		{
			rows.get(i).setPaneBorder(border);
		}
	}
	public void editRow(String[] recall)
	{
		for(int i=0; i<rows.size(); i++)
		{
			if(rows.get(i).isBorder())
			{
				rows.set(i, new Row(recall, false));
				vb.getChildren().set(i, rows.get(i).pane);
				break;
			}
		}
	}
	public String getLastDate()
	{
		String date = rows.get(cur).getText(1);
		if(date.equals("區塊"))
			return "0";
		else if(date.equals("無日期形式"))
			return "0";
		else if(date.equals("自行填寫形式"))
			return "1";
		else
			return date;
	}
	public int getcur()
	{
		return cur;
	}
}
