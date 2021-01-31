package label3;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class VariableText
{
	public Pane pane = new Pane();
	private Label[][] text = new Label[2][5];
	
	public VariableText()
	{

		//text(年度, 公司, 人員正字第, s日期e日期, 冠祥)
		for(int i=0; i<text.length; i++)
		for(int j=0; j<text[0].length; j++)
		{
			text[i][j] = new Label();
			text[i][j].setAlignment(Pos.CENTER_LEFT);
			text[i][j].setWrapText(true);
//			text[i][j].setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
//			text[i][j].setOpacity(0.5);
		}
		text[0][0].setAlignment(Pos.CENTER);
		text[0][4].setAlignment(Pos.CENTER);
		text[1][4].setAlignment(Pos.CENTER);

		//setRange and setFont
		setRange(0, 0, 592, 190, 802, 309, 76, "Microsoft JhengHei");
		setRange(0, 1, 465, 323, 1337, 639, 84, "Microsoft JhengHei");
		setRange(0, 2, 465, 649, 1484, 940, 84, "Microsoft JhengHei");
		setRange(0, 3, 465, 949, 1387, 1222, 84, "Microsoft JhengHei");
		setRange(0, 4, 364, 1250, 1174, 1350, 64, "Microsoft JhengHei");
		
		setRange(1, 0, 456,  271, 1343,  564, 84, "Microsoft JhengHei");
		setRange(1, 1, 456,  526, 1425,  803, 70, "Microsoft JhengHei");
		setRange(1, 2, 456,  756, 1462, 1033, 84, "Microsoft JhengHei");
		setRange(1, 3, 456,  985, 1391, 1258, 84, "Microsoft JhengHei");
		setRange(1, 4, 343, 1266, 1214, 1382, 72, "Microsoft JhengHei");
	}
	private void setRange(int i, int j, double startX, double startY, double endX, double endY, double size, String fonttype)
	{
		text[i][j].setLayoutX(startX);
		text[i][j].setLayoutY(startY);
		text[i][j].setPrefSize(endX-startX, endY-startY);
		text[i][j].setFont(Font.font(fonttype, size));
	}
	public void setText(Row row)
	{
		//setText
		if(LabelMain.labelkind.isgreen())
		{
			text[0][0].setText(LabelMain.year.getText());
			if(row.getText(0).equals("無場所名稱"))
				text[0][1].setText("");
			else
				text[0][1].setText(row.getText(0));
			
			text[0][2].setText(numberPredict(LabelMain.vendor.getText(0), LabelMain.vendor.getTitle(1), LabelMain.vendor.getText(1))); //設備師士&證號
			text[0][3].setText( toScriptedDate(row.getText(1)) );
			text[0][4].setText(LabelMain.vendor.getText(2));		
		}
		else
		{
			text[1][0].setText(row.getText(0));
			text[1][1].setText(LabelMain.vendor.getText(3));
			text[1][2].setText(numberPredict(LabelMain.vendor.getText(0), LabelMain.vendor.getTitle(1), LabelMain.vendor.getText(1))); //設備師士&證號
			text[1][3].setText( toScriptedDate(row.getText(1)) );
			text[1][4].setText(LabelMain.vendor.getText(2));		
		}
		pane.getChildren().clear();
		for(int i=0; i<text[0].length; i++)
		{
			if(LabelMain.labelkind.isgreen())
				pane.getChildren().add(text[0][i]);
			else
				pane.getChildren().add(text[1][i]);
		}	
	}
	private String numberPredict(String person, String title, String number)
	{
		String answer = "";
		String[] tmpPer = person.split("、");
		if(tmpPer.length==1)
			answer = person+"\n"+title+number+"號";
		else
		{
			String[] tmpNum = number.split("、");
			for(int i=0; i<tmpPer.length; i++)
			{
				if(i!=0)
					answer += "\n";
				answer += tmpPer[i] + " " + title + tmpNum[i] + "號";
			}
		}
		return answer;
	}
	private String toScriptedDate(String ori)
	{
		if(ori.equals("無日期形式"))
			return "";
		else if(ori.equals("自行填寫形式"))
			return 	"自         年         月         日\n" +
					"至         年         月         日\n";

		String[][] tmp = 
		{
			ori.split("~")[0].split("-"),
			ori.split("~")[1].split("-")
		};
		return "自"+(Integer.valueOf(tmp[0][0])-1911)+"年"+tmp[0][1]+"月"+tmp[0][2]+"日\n"+
			   "至"+(Integer.valueOf(tmp[1][0])-1911)+"年"+tmp[1][1]+"月"+tmp[1][2]+"日";
	}
}
