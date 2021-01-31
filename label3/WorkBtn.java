package label3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WorkBtn
{
	public Pane pane = new Pane();
	private Button work = new Button("產生標籤");
	static EachScene[] eachscene = new EachScene[24];
	private Label load = new Label("");
	private int fullnum = 0;
	private int count = 0;
	private int countdone = 0;
	private int pagenum = 0;
//	private int allnum = 0;
	public PrinterJob job;
	private ImageView pages = new ImageView();
	private String savepath;
	
	public WorkBtn()
	{
		pane.setPrefSize(150, 60);
		pane.setLayoutX(665);
		pane.setLayoutY(620);
		
		pages.setFitHeight(1485);
		pages.setFitWidth(1050);
		
		work.setPrefSize(150, 60);
		work.setFont(new Font(24));
		pane.getChildren().add(work);
		work.setLayoutX(0);
		work.setLayoutY(0);
		
		pane.getChildren().add(load);
		load.setLayoutX(155);
		load.setLayoutY(10);
		load.setFont(new Font(30));
		
		for(int i=0; i<eachscene.length; i++)
			eachscene[i] = new EachScene();
		
		done.setCycleCount(10);
		
		for(int k=0; k<eachscene.length; k++)
		{
			eachscene[k].clear();
			eachscene[k].renewBg(LabelMain.errornum.getWidth(0), LabelMain.errornum.getWidth(1));
		}
		
		work.setOnAction
		(
			e->
			{
				savepath = LabelMain.errornum.getPath();
				if(LabelMain.errornum.HaveToDel())
					del();
				work.setDisable(true);
				work.setText("製作中...");
				fullnum = LabelMain.labelkind.getFullNum();
				preparePrint();
				save();
				print();
				done.play();
			}
		);
	}
	private void del()
	{
		Runtime r1 = Runtime.getRuntime ();
		for(int i=0; ; i++)
		{
			try
			{
				FileReader tmp = new FileReader(savepath+"\\"+i+".png");
				tmp.close();
				r1.exec ("cmd.exe /c"+ "del "+savepath+"\\"+String.valueOf(i)+".png");
			}
			catch(IOException e)
			{
				break;
			}
		}		
	}
	private void save()
	{
		work.setDisable(true);
		count = 0;
		pagenum=0;
//		int per = 0;
//		allnum = 0;
//		for(int i=1; i<LabelMain.alldata.rows.size(); i++)
//			allnum += Integer.valueOf(LabelMain.alldata.rows.get(i).getText(2));
		for(int k=0; k<eachscene.length; k++)
		{
			eachscene[k].clear();
			eachscene[k].renewBg(LabelMain.errornum.getWidth(0), LabelMain.errornum.getWidth(1));
		}
		for(int i=1; i<LabelMain.alldata.rows.size(); i++)
		{
			for(int j=0; j<Integer.valueOf(LabelMain.alldata.rows.get(i).getText(2)); j++)
			{
				eachscene[count].setAlltext(LabelMain.alldata.rows.get(i));

				count++;
				if(count>=fullnum)
				{
					try
					{
						count -= fullnum;
						ImageView img = new PageScene().makePage(eachscene);
						ImageIO.write(SwingFXUtils.fromFXImage(img.getImage(), null), "png", new File(LabelMain.errornum.getPath()+"//"+pagenum+".png"));
			    		pagenum++;
			    		for(int k=0; k<eachscene.length; k++)
							eachscene[k].clear();
//						if((count+pagenum*fullnum)*100/allnum>per)
//						{
//							per = (count+pagenum*fullnum)*100/allnum;
//							load.setText("Loading..."+per+"%");
//							
//						}

					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		}
		if(count!=0)
		{
			for(; count<fullnum; count++)
			{
				eachscene[count].setAlltext(null);
			}
			try
			{
				ImageView k = new PageScene().makePage(eachscene);
				ImageIO.write(SwingFXUtils.fromFXImage(k.getImage(), null), "png", new File(savepath+"\\"+pagenum+".png"));
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
    		pagenum++;
			for(int i=0; i<eachscene.length; i++)
				eachscene[i].clear();
		}
//		allnum=-1;
	}
	private void preparePrint()
	{
		Printer printer = Printer.getDefaultPrinter();
		PageLayout pl = printer.createPageLayout
				(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
		job = PrinterJob.createPrinterJob(printer);
		job.getJobSettings().setPageLayout(pl);
//		job.showPageSetupDialog(new Scene(new Pane(), 200, 200).getWindow());
//		job.showPrintDialog(new Scene(new Pane(), 200, 200).getWindow());
	}
	private void print()
	{
		pages.setFitWidth(job.getJobSettings().getPageLayout().getPrintableWidth());
		pages.setFitHeight(job.getJobSettings().getPageLayout().getPrintableHeight());
//		if(job!=null)
//		{
//			boolean success = true;
			for(int i=0; i<pagenum; i++)
			{
				pages.setImage(new Image("file:"+savepath+"\\"+i+".png"));
//				success = job.printPage(pages);//****
				Runtime r1 = Runtime.getRuntime ();
	    		try {
					r1.exec ("cmd.exe /c"+ "start "+savepath+"\\"+String.valueOf(i)+".png");
				} catch (IOException e)
	    		{
					e.printStackTrace();
				}
//				load.setText("Loading..."+((i*100)/pagenum) +"%");
			}

//			if(success)
//			{
//				job.endJob();
				work.setText("產生標籤");
				work.setDisable(false);
//			}
//		}
	}
	public double getJobValue(int k)
	{
		switch (k)
		{
		case 0:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getPrintableWidth();
		case 1:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getPrintableHeight();
		case 2:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getTopMargin();
		case 3:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getLeftMargin();
		case 4:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getBottomMargin();
		case 5:
			return LabelMain.workbtn.job.getJobSettings().getPageLayout().getRightMargin();
		default:
			return -1;
		}
	}
	public void disableWork(boolean b)
	{
		work.setDisable(b);
	}
	public int getPagenum()
	{
		return pagenum;
	}
	private Timeline done = new Timeline
	(
		new KeyFrame
		(
			Duration.millis(500),
			e->
			{
				if(countdone==0)
				{
					load.setText("完成");
				}
				countdone++;
				if(countdone==10)
				{
					countdone=0;
					load.setText("");
				}
			}
		)
	);
}
