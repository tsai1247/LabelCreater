package label3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Work
{
	public Pane pane = new Pane();
	private HBox hb = new HBox();
	private Button[] workbtn =
	{
		new Button("完成"),
		new Button("取消"),
	};
	
	public Work()
	{
		pane.setPrefSize(130, 50);
		pane.setLayoutX(400);
		pane.setLayoutY(320);
		
		pane.getChildren().add(hb);
		hb.setSpacing(10);
		hb.setLayoutX(0);
		hb.setLayoutY(0);
		
		for(int i=0; i<workbtn.length; i++)
		{
			hb.getChildren().add(workbtn[i]);
			workbtn[i].setPrefSize(130, 50);
			workbtn[i].setFont(new Font(30));
		}

		workbtn[0].setOnAction
		(
			e->
			{
				if(LabelMain.alldata.addstage.getTitle().equals("新增內容"))
				{
					if(!LabelMain.alldata.addstage.isBoxContains(LabelMain.alldata.addstage.getData(0)))
					{
						try
						{
							FileWriter fw= new FileWriter(".\\history.txt", true);
							BufferedWriter bw = new BufferedWriter(fw);
							bw.append(LabelMain.alldata.addstage.getData(0)+"\r\n");
							bw.close();
							fw.close();
						}
						catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}
					LabelMain.alldata.addstage.addRowRelay();
				}
				else
					LabelMain.alldata.addstage.editRowRelay();
				LabelMain.alldata.setAddDisable(false);
				LabelMain.alldata.addstage.closeStage();
			}
		);
		
		workbtn[1].setOnAction
		(
			e->
			{
				LabelMain.alldata.setAddDisable(false);
				LabelMain.alldata.addstage.closeStage();
			}
		);
	}
}
