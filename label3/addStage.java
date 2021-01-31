package label3;

import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addStage
{
	private Stage stage = new Stage();
	private Pane pane = new Pane();
	private Scene scene = new Scene(pane, 1000, 400);
	private Client client = new Client();
	private Date date = new Date();
	private Count count = new Count();
	private Work work = new Work();
	private CheckBox empty = new CheckBox("空白區塊");
	private Similar similar = new Similar();
	public addStage()
	{
		
		pane.getChildren().add(client.pane);
		pane.getChildren().add(date.pane);
		pane.getChildren().add(count.pane);
		pane.getChildren().add(work.pane);
		pane.getChildren().add(similar.pane);
		pane.getChildren().add(empty);
		empty.setLayoutX(405);
		empty.setLayoutY(280);
		empty.setFont(new Font(24));
		
		
        stage.setTitle("新增內容");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon3.0.png")));
        stage.setResizable(false);
	
        
        empty.setOnAction
        (
        	e->
        	{
        		if(empty.isSelected())
        		{
        			date.setDisable(true, true, true);
        			date.setSelected(true, false, false);
        			client.setDisable(true);
        		}
        		else
        		{
        			date.setDisable(false, false, false);
        			date.setSelected(false, false, false);
        			client.setDisable(false);
        		}
        	}
        );

    	stage.setOnCloseRequest
    	(
    		new EventHandler<WindowEvent>()
	    	{
	    		@Override
	    		public void handle(WindowEvent event)
	    		{
					LabelMain.alldata.setAddDisable(false);    		
	    		}
	    	}
    	);

	}

	public void showAndInitialize()
	{
		initialize();
		stage.setTitle("新增內容");
		stage.show();
	}
	public void showAndInitialize(Row row)
	{
		initialize(row);
		stage.setTitle("修改內容");
		stage.show();
	}

	private void initialize()
	{
		empty.setSelected(false);
		date.setSelected(false, false, false);
		if(LabelMain.alldata.getcur() == 0)
			date.setDisable(false, true, true);
		else
			date.setDisable(false, true, false);
					
		client.setDisable(false);
		client.setText("公司");
		date.setAllText(LocalDate.now().toString(), LocalDate.now().toString());
		count.setText("15");
	}
	private void initialize(Row row)
	{
		try
		{
			count.setText(row.getText(2));
			client.setText(row.getText(0));
			date.setAllText(row.getText(1).split("~")[0], row.getText(1).split("~")[1]);
		}catch(Exception e) {}
		
		if(row.getText(1).equals("區塊"))
		{
			empty.setSelected(true);
			date.setSelected(true, false, false);
			date.setDisable(true, true, true);
			client.setDisable(true);
			date.setAllText(null, null);
		}
		else
		{
			empty.setSelected(false);
			if(row.getText(0).equals("無場所名稱"))
				client.setDisable(true);
			else
				client.setDisable(false);

			if(row.getText(1).equals("無日期形式"))
			{
				date.setSelected(true, false, false);
				date.setDisable(false, false, true);
				date.setAllText(null, null);
			}
			else if(row.getText(1).equals("自行填寫形式"))
			{
				date.setSelected(true, true, false);
				date.setDisable(false, false, true);
				date.setAllText(null, null);
			}
			else
			{
				date.setSelected(false, false, false);
				date.setDisable(false, true, false);
				date.setAllText(row.getText(1).split("~")[0], row.getText(1).split("~")[1]);
			}
		}
	}
	public void closeStage()
	{
		stage.close();
	}
	public String getTitle()
	{
		return stage.getTitle();
	}
	public void addRowRelay()
	{
		String[] recall = {client.getText(), date.getText(), count.getText()};
		if(empty.isSelected())
		{
			recall[0] = "空白";
			recall[1] = "區塊";
		}
		LabelMain.alldata.addRow(recall);
	}
	public void editRowRelay()
	{
		String[] recall = {client.getText(), date.getText(), count.getText()};
		if(empty.isSelected())
		{
			recall[0] = "空白";
			recall[1] = "區塊";
		}
		LabelMain.alldata.editRow(recall);
	}
	public String getData(int i)
	{
		if(i==0)
			return client.getText();
		else if(i==1)
			return date.getText();
		else if(i==2)
			return count.getText();
		else
			return null;
	}

	public boolean change(String curtext)
	{
		if(client==null)
			return false;
		else if(client.getText().equals(curtext))
			return false;
		else
		{
			similar.setcurText(client.getText());
			return true;
		}
	}

	public void setClient(String text)
	{
		client.setText(text);
	}

	public boolean isBoxContains(String string)
	{
		return similar.isBoxContains(string);
	}
}
