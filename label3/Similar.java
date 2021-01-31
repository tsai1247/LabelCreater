package label3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Similar
{
	public Pane pane = new Pane();
	private Label title = new Label("候選場所名稱：");
	private ScrollPane spane = new ScrollPane();
	private VBox vb = new VBox(); // add Candidate
	private String curtext = "";
	private ArrayList<String> history = new ArrayList<String>();
	private ArrayList<Double> jaccard = new ArrayList<Double>();
	private ArrayList<Integer> contain = new ArrayList<Integer>();
	
	public Similar()
	{
		pane.setPrefSize(310, 400);
		pane.setLayoutX(690);
		pane.setLayoutY(0);
		
		pane.getChildren().add(title);
		title.setLayoutX(0);
		title.setLayoutY(10);
		title.setFont(new Font(30));
		
		pane.getChildren().add(spane);
		spane.setPrefSize(310, 350);
		spane.setLayoutX(0);
		spane.setLayoutY(50);
		
			
		spane.setContent(vb);
		vb.setPrefSize(295, 335);
		
		search.setCycleCount(Timeline.INDEFINITE);
		search.play();
	}
	private Timeline search = new Timeline
	(
		new KeyFrame
		(
			Duration.millis(200),
			e->
			{
				
				if(LabelMain.alldata.addstage.change(curtext) )
				{
					vb.getChildren().clear();
					if(!LabelMain.alldata.addstage.getData(0).equals("無場所名稱"))
					{
						try
						{
							//send history.txt to ArrayList history
							FileReader fr = new FileReader(".\\history.txt");
							BufferedReader br = new BufferedReader(fr);
							String tmp;
							history.clear();
							while((tmp=br.readLine())!=null)
								history.add(tmp); //history: txt裡面所有東西的 ArrayList
							br.close();
							fr.close();
							
							//check if each in ArrayList history has to add in the vb.getChildren 
							//compare with LabelMain.alldata.addstage.getData(0)
							//count the Jaccard value
							tmp = LabelMain.alldata.addstage.getData(0);
							jaccard.clear();
							for(int i=0; i<history.size(); i++)
							{
								int intersection = 0;
								for(int k=0; k<LabelMain.alldata.addstage.getData(0).length(); k++)
								for(int j=0; j<history.get(i).length(); j++)
										if(history.get(i).charAt(j) == tmp.charAt(k))
										{
											intersection++;
											break;
										}
	
								jaccard.add
								(
									intersection*1.0 /
									(history.get(i).length()
										+ tmp.length()
										-intersection)
								);
							}
							contain.clear();
							for(int index=0; index<8; index++)
							{
								int record = -1;
								for(int i=0; i<jaccard.size(); i++)
								{
									if(record == -1 && !ContainInVb(i))
									{
										record = i;
									}
									else if(!ContainInVb(i))
									{
										if(jaccard.get(record)<jaccard.get(i))
											record = i;
									}							
								}
								if(record==-1 || jaccard.get(record)==0 || ContainInVb(record))
									break;
								vb.getChildren().add(new Candidate(history.get(record)).pane);
								contain.add(record);
								
							}	
						}
						catch(IOException ee)
						{
							FileWriter fw;
							try
							{
								fw = new FileWriter(".\\history.txt");
								fw.close();
							}catch(IOException e1){e1.printStackTrace();}
						}
					}
				}
			}
		)
		
	);
	public void setcurText(String text)
	{
		curtext = text;
	}
	private boolean ContainInVb(int num)
	{
		for(int i=0; i<contain.size(); i++)
			if(num==contain.get(i))
				return true;
		return false;
	}
	public boolean isBoxContains(String string)
	{
		for(var i : history)
			if(i.equals(string))
				return true;
		return false;
	}
}
