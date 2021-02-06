package sql_gui_op_nationalWay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InfoToSql
{
    public InfoToSql()
    {
        try
        {
            FileReader fr=new FileReader("E:\\文档\\大三上\\Java\\way.txt");
            BufferedReader br=new BufferedReader(fr);
            String temp=br.readLine();
            while(temp!=null)
            {
                String[] infos=temp.split(" ");
                NationalWayInfo wayInfo = new NationalWayInfo(infos[0],infos[1],infos[2],Double.parseDouble(infos[3]));
                NationalWayOp op = new NationalWayOp();
                op.addWay(wayInfo);
                temp=br.readLine();
                //再次读取一行数据
            }
            br.close();
            fr.close();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        catch (WayException e) {
            GUI_ex gui_ex = new GUI_ex("数据导入异常");
        }
    }

}
