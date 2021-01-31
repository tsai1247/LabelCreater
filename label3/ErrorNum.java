package label3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ErrorNum
{
	public Pane pane = new Pane();
	private Button btn = new Button("設定");
	private Stage stage = new Stage();
	private Pane addpane = new Pane();
	private Scene scene = new Scene(addpane, 550, 550);
	private Label[] title = {new Label("影印內容上移"), new Label("影印內容左移"), new Label("標籤邊框寬度")};
	private TextField[] text = {new TextField("0"), new TextField("0")};
	private Label[] script = {new Label("mm"), new Label("mm")};
	
	private Label widthtitle = new Label("標籤邊框寬度");
	private TextField[] width = {new TextField("0"), new TextField("0")};
	private Label[] widthscript = {new Label("+"),new Label("=             mm")};
	private Label[] inandout = {new Label("外側"),new Label("內側")};
	private Label predict = new Label("預覽畫面：");
	private Label sum = new Label("0");
	private SampleBg gb = new SampleBg();
	
	private Label savetitle = new Label("圖片儲存位置");
	private TextField savepath = new TextField("D:\\");
	private Button savebtn = new Button("...");
	private CheckBox tmpsave = new CheckBox("定時刪除儲存位置內的所有圖片");
	
	private Button ret = new Button("預設值");
	
	public ErrorNum()
	{
		pane.setPrefSize(130, 50);
		pane.setLayoutX(25);
		pane.setLayoutY(630);
		
		pane.getChildren().add(btn);
		btn.setLayoutX(0);
		btn.setLayoutY(0);
		btn.setPrefSize(90, 50);
		btn.setFont(new Font(24));
		
		stage.setScene(scene);
		stage.setTitle(btn.getText());
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon3.0.png")));
        stage.setResizable(false);
        
        for(int i=0; i<2; i++)
        {
        	addpane.getChildren().add(title[i]);
        	addpane.getChildren().add(text[i]);
        	addpane.getChildren().add(script[i]);
        	title[i].setLayoutX(10);
        	title[i].setLayoutY(10+130*i);
        	text[i].setLayoutX(30);
        	text[i].setLayoutY(60+140*i);
        	script[i].setLayoutX(135);
        	script[i].setLayoutY(65+140*i);
        	title[i].setFont(new Font(30));
        	text[i].setFont(new Font(30));
        	script[i].setFont(new Font(36));
        	text[i].setPrefWidth(100);
        	text[i].setAlignment(Pos.CENTER);
        }
        
        addpane.getChildren().add(widthtitle);
        widthtitle.setLayoutX(10);
        widthtitle.setLayoutY(280);
        widthtitle.setFont(new Font(30));

        for(int i=0; i<2; i++)
        {
        	addpane.getChildren().add(width[i]);
        	width[i].setLayoutX(30+130*i);
        	width[i].setLayoutY(340);
        	width[i].setFont(new Font(30));
        	width[i].setPrefWidth(80);
        	width[i].setAlignment(Pos.CENTER);
        }
        for(int i=0; i<2; i++)
        {
          	addpane.getChildren().add(widthscript[i]);
        	widthscript[i].setLayoutX(120+130*i);
        	widthscript[i].setLayoutY(345);
        	widthscript[i].setFont(new Font(36));
        }
        for(int i=0; i<2; i++)
        {
          	addpane.getChildren().add(inandout[i]);
        	inandout[i].setLayoutX(50+130*i);
        	inandout[i].setLayoutY(398);
        	inandout[i].setFont(new Font(20));
        }
        
        addpane.getChildren().add(predict);
        predict.setLayoutX(250);
        predict.setLayoutY(15);
        predict.setFont(new Font(36));
        
        addpane.getChildren().add(sum);
        sum.setLayoutX(285);
        sum.setLayoutY(345);
        sum.setFont(new Font(36));
        sum.setPrefWidth(100);
        sum.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        sum.setAlignment(Pos.CENTER);
        
        
        setSum.setCycleCount(Timeline.INDEFINITE);
        setSum.play();

        
        addpane.getChildren().add(savetitle);
        savetitle.setLayoutX(10);
        savetitle.setLayoutY(430);
        savetitle.setFont(new Font(30));
        
        addpane.getChildren().add(savepath);
        savepath.setLayoutX(30);
        savepath.setLayoutY(470);
        savepath.setFont(new Font(24));
        savepath.setPrefWidth(430);
        
        addpane.getChildren().add(savebtn);
        savebtn.setLayoutX(460);
        savebtn.setLayoutY(470);
        savebtn.setFont(new Font(24));
        savebtn.setPrefSize(60, 48);
        
        addpane.getChildren().add(tmpsave);
        tmpsave.setLayoutX(30);
        tmpsave.setLayoutY(520);
        tmpsave.setLayoutX(250);
        tmpsave.setLayoutY(440);
        tmpsave.setFont(new Font(18));
        tmpsave.setDisable(true);
        
        addpane.getChildren().add(ret);
        ret.setLayoutX(450);
        ret.setLayoutY(10);
        ret.setPrefSize(90, 50);
        ret.setFont(new Font(20));
        
        try
		{
			FileReader fr = new FileReader(".\\error.txt");
			BufferedReader br = new BufferedReader(fr);
			text[0].setText(br.readLine());
			text[1].setText(br.readLine());
			width[0].setText(br.readLine());
			width[1].setText(br.readLine());
			String tmplast = br.readLine();
			if(tmplast!=null)
				savepath.setText(tmplast);
			else
				savepath.setText(".\\");
			tmplast = br.readLine();
			if(tmplast!=null && tmplast.equals("1"))
				tmpsave.setSelected(true);
			else
				tmpsave.setSelected(!false);//
			br.close();
			fr.close();
		}
		catch(IOException ee)
		{
			text[0].setText("0");
			text[1].setText("0");
			width[0].setText("1");
			width[1].setText("2");
			savepath.setText(".\\");
			tmpsave.setSelected(true);
		}
        LabelMain.vendor.setVendor();
        ret.setOnAction
        (
        	e->
        	{
        		text[0].setText("0");
				text[1].setText("0");
				width[0].setText("1");
				width[1].setText("2");
				savepath.setText("D:\\");
				tmpsave.setSelected(true);
        	}
        );
        
        savebtn.setOnAction
        (
        	e->
        	{
        		DirectoryChooser fc = new DirectoryChooser();
				if(!savepath.getText().equals(""))
				try
				{
					fc.setInitialDirectory(new File(savepath.getText()));
				}
				catch(Exception ioe)
				{
					fc.setInitialDirectory(new File("D:\\"));
				}
				
				File sf = fc.showDialog(new Stage());
				savepath.setText(sf.getPath());
        	}
        );
        
        
        addpane.getChildren().add(gb.pane);
        
        
        stage.setOnCloseRequest
    	(
    		new EventHandler<WindowEvent>()
	    	{
	    		@Override
	    		public void handle(WindowEvent event)
	    		{
	    			try
					{
						FileWriter fw = new FileWriter(".\\error.txt");
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(text[0].getText()+"\r\n");
						bw.write(text[1].getText()+"\r\n");
						bw.write(width[0].getText()+"\r\n");
						bw.write(width[1].getText()+"\r\n");
						bw.write(savepath.getText()+"\r\n");
						if(tmpsave.isSelected())
							bw.write("1\r\n");
						else
							bw.write("0\r\n");
						bw.write(LabelMain.vendor.getText(2));
						bw.write(LabelMain.vendor.getText(3));
						bw.close();
						fw.close();
					}
					catch(IOException ee){}
					btn.setDisable(false);
					LabelMain.workbtn.disableWork(false);
	    		}
	    	}
    	);

        
		btn.setOnAction
		(
			e->
			{
				try
				{
					FileReader fr = new FileReader(".\\error.txt");
					BufferedReader br = new BufferedReader(fr);
					text[0].setText(br.readLine());
					text[1].setText(br.readLine());
					width[0].setText(br.readLine());
					width[1].setText(br.readLine());
					String tmplast = br.readLine();
					if(tmplast!=null)
						savepath.setText(tmplast);
					else
						savepath.setText("D:\\");
					tmplast = br.readLine();
					if(tmplast!=null && tmplast.equals("1"))
						tmpsave.setSelected(true);
					else
						tmpsave.setSelected(!false);
					tmplast = br.readLine();
					tmplast = br.readLine();
					br.close();
					fr.close();
				}
				catch(IOException ee)
				{
					text[0].setText("0.0");
					text[1].setText("0.0");
					width[0].setText("0.0");
					width[1].setText("0.0");
					savepath.setText(".\\");
					tmpsave.setSelected(true);
				}
				btn.setDisable(true);
				LabelMain.workbtn.disableWork(true);
				stage.show();
			}
		);
	}
	Timeline setSum = new Timeline
	(
		new KeyFrame
		(
			Duration.millis(500),
			e->
			{
				try
				{
					sum.setText
					(
						String.format("%.2f", Double.valueOf(width[0].getText())+Double.valueOf(width[1].getText()) )
					);
					gb.renew(Double.valueOf(width[0].getText()), Double.valueOf(width[1].getText()));
				}
				catch(Exception excption)
				{
					sum.setText("0.00");
					gb.renew(1, 2);
				}
			}
		)
	);
	public double getWidth(int a)
	{
		try
		{
			if(a==0 || a==1)
				return Double.valueOf(width[a].getText());
			else
				return 0;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	public double getText(int a)
	{
		try
		{
			if(a==0 || a==1)
				return Double.valueOf(text[a].getText());
			else
				return 0;
		}
		catch(Exception e)
		{
			return 0;
		}

	}
	public boolean HaveToDel()
	{
		return tmpsave.isSelected();
	}
	public String getPath()
	{
		return savepath.getText();
	}
}
