package label3;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LabelMain extends Application{
	static Vendor vendor = new Vendor();
	static ErrorNum errornum = new ErrorNum();
	static AllData alldata = new AllData();
	static Year year = new Year();
	static LabelKind labelkind = new LabelKind();
	static WorkBtn workbtn = new WorkBtn();
	static Help help = new Help();
	@Override
    public void start(Stage stage)
    {
		Pane mainpane = new Pane();
		
		mainpane.getChildren().add(alldata.pane);
		mainpane.getChildren().add(year.pane);
		mainpane.getChildren().add(vendor.pane);
		mainpane.getChildren().add(workbtn.pane);
		mainpane.getChildren().add(errornum.pane);
		mainpane.getChildren().add(labelkind.pane);
		mainpane.getChildren().add(help.pane);
		
    	Scene scene = new Scene(mainpane, 900, 700);
        stage.setTitle("ň砞称浪禟籹 v3.8.1");
        //add save location into ErrorNum class
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon3.0.png")));
        stage.setResizable(false);
        stage.show();
    

    	stage.setOnCloseRequest
    	(
    		new EventHandler<WindowEvent>()
	    	{
	    		@Override
	    		public void handle(WindowEvent event)
	    		{
	    			Runtime r1 = Runtime.getRuntime ();
					for(int i=0; ; i++)
					{
						try
	    				{
							FileReader tmp = new FileReader(".\\"+i+".png");
							tmp.close();
							r1.exec ("cmd.exe /c"+ "del "+".\\"+String.valueOf(i)+".png");
	    				}
						catch(IOException e)
						{
							break;
						}
					}
					try
					{
						FileWriter fw = new FileWriter(".\\error.txt");
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(String.valueOf(errornum.getText(0))+"\r\n");
						bw.write(String.valueOf(errornum.getText(1))+"\r\n");
						bw.write(String.valueOf(errornum.getWidth(0))+"\r\n");
						bw.write(String.valueOf(errornum.getWidth(1))+"\r\n");
						bw.write(errornum.getPath()+"\r\n");
						if(errornum.HaveToDel())
							bw.write("1\r\n");
						else
							bw.write("0\r\n");
						bw.write("玜不ň\r\n");
						bw.write("玜不ň\r\n");
						bw.close();
						fw.close();
					}
					catch(IOException ee)
					{
						try
						{
							FileWriter fw2 = new FileWriter(".\\error.txt");
							BufferedWriter bw2 = new BufferedWriter(fw2);
							bw2.write("0.0\r\n");
							bw2.write("0.0\r\n");
							bw2.write("0.0\r\n");
							bw2.write("0.0\r\n");
							bw2.write("D:\\\r\n");
							bw2.write("1\r\n");
							bw2.write("玜不ň\r\n");
							bw2.write("玜不ň\r\n");
							bw2.close();
							fw2.close();
						}catch(Exception eee) {}
					}
	    		}
	    	}
    	);
        
 
		
    }
    public static void main(String[] args)
    { launch(args); }
}
