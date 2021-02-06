package sql_gui_op_nationalWay;
import java.util.*;
import java.sql.*;
/*
    数据操作类
 */
public class NationalWayOp {
        private Connection con1;
        public NationalWayOp()
        {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String conStr="jdbc:sqlserver://localhost:1433; DatabaseName=NationalWay";
                String user="sa";
                String password="wang0604";
                con1=DriverManager.getConnection(conStr,user,password);
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                GUI_ex gui_ex = new GUI_ex(e.getMessage());
            }
            catch(SQLException ex)
            {
                GUI_ex gui_ex = new GUI_ex(ex.getMessage());
            }
        }

        public void addWay(NationalWayInfo way) throws WayException
        {
            try {
                if(way.getName().equals("") || way.getOne_end().equals("") || way.getAnother_end().equals(""))
                {
                    throw new WayException("有信息为空");
                }
                else if(way.getName().charAt(0)!='G')
                {
                    throw new WayException("名称格式错误");
                }
                    else
                    {
                        StringBuffer strSql=new StringBuffer();
                        strSql.append("insert into wayInfo values('");
                        strSql.append(way.getName());
                        strSql.append("','");
                        strSql.append(way.getOne_end());
                        strSql.append("','");
                        strSql.append(way.getAnother_end());
                        strSql.append("',");
                        strSql.append(way.getLength());
                        strSql.append(")");
                        Statement st=con1.createStatement();
                        //创建SQL语句执行对象
                        st.execute(strSql.toString());
                        //执行SQL语句
                        st.close();
                        con1.close();
                        GUI_sure gui_sure = new GUI_sure();
                    }

            }
            catch(SQLException ex)
            {
                GUI_ex gui_ex = new GUI_ex(ex.getMessage());
            }
        }

        public void delWay(String name)
        {
            try {
                StringBuffer strSql=new StringBuffer();
                strSql.append("delete from wayInfo where name='");
                strSql.append(name);
                strSql.append("'");
                Statement st=con1.createStatement();
                //创建SQL语句执行对象
                st.execute(strSql.toString());
                //执行SQL语句
                st.close();
                con1.close();

            }
            catch(SQLException ex)
            {
                GUI_ex gui_ex = new GUI_ex(ex.getMessage());
            }
        }

        public void updateWay(NationalWayInfo way) throws WayException
        {
            try {
                if(way.getName().equals("") || way.getOne_end().equals("") || way.getAnother_end().equals("") || String.valueOf(way.getLength()).equals(""))
                {
                    throw new WayException("有信息为空");
                }
                else if(way.getName().charAt(0)!='G')
                {
                    throw new WayException("名称格式错误");
                }
//                else if(way.getLength())
//                {
//                    throw new WayException("名称格式错误");
//                }
                    else
                    {
                        StringBuffer strSql=new StringBuffer();
                        strSql.append("update wayInfo set name='");
                        strSql.append(way.getName());
                        strSql.append("', one_end='");
                        strSql.append(way.getOne_end());
                        strSql.append("', another_end='");
                        strSql.append(way.getAnother_end());
                        strSql.append("', length=");
                        strSql.append(way.getLength());
                        strSql.append(" where name='");
                        strSql.append(way.getName());
                        strSql.append("'");
                        Statement st=con1.createStatement();
                        //创建SQL语句执行对象
                        System.out.println(strSql.toString());
                        st.execute(strSql.toString());
                        //执行SQL语句
                        st.close();
                        con1.close();
                        GUI_sure gui_sure = new GUI_sure();
                    }

            }
            catch(SQLException ex)
            {
                GUI_ex gui_ex = new GUI_ex(ex.getMessage());
            }
        }

    public NationalWayInfo[] findAllWays()
    {
        ArrayList<NationalWayInfo> ways=new ArrayList<NationalWayInfo>();
        String strSql="select * from wayInfo";
        //创建SQL语句执行对象
        try
        {
            Statement st=con1.createStatement();
            ResultSet rs=st.executeQuery(strSql);
            //执行查询语句，把查询的结果保存到结果集对象中
            while(rs.next())//如果结果集中还有记录
            {
                String name=rs.getString(1);
                String end1=rs.getString(2);
                String end2=rs.getString(3);
                double len=rs.getDouble(4);
                NationalWayInfo way=new NationalWayInfo(name,end1,end2,len);
                ways.add(way);
            }
            rs.close();
            st.close();
            con1.close();
        }
        catch(SQLException ex)
        {
            GUI_ex gui_ex = new GUI_ex(ex.getMessage());
        }
        return (NationalWayInfo[])ways.toArray(new NationalWayInfo[ways.size()]);
    }


    public NationalWayInfo findWayByName(String name)
    {
        String strSql="select * from wayInfo where name='"+name+"' ";
        NationalWayInfo result=null;
        try
        {
            Statement st=con1.createStatement();
            ResultSet rs=st.executeQuery(strSql.toString());
            if(rs.next())
            {
                String name1=rs.getString(1);
                String end1=rs.getString(2);
                String end2=rs.getString(3);
                double len=rs.getDouble(4);
                result=new NationalWayInfo(name1,end1,end2,len);
            }
        }
        catch(SQLException ex)
        {
            GUI_ex gui_ex = new GUI_ex(ex.getMessage());
        }
        return result;
    }

    public NationalWayInfo[] findWayssByEnd(String end)
    {
        ArrayList<NationalWayInfo> ways=new ArrayList<NationalWayInfo>();
        String strSql1="select * from wayInfo where one_end like '%"+end+"%' ";
        String strSql2="select * from wayInfo where another_end like '%"+end+"%' ";
        try
        {
            Statement st1=con1.createStatement();
            ResultSet rs1=st1.executeQuery(strSql1);
            while(rs1.next())
            {
                String name=rs1.getString(1);
                String end1=rs1.getString(2);
                String end2=rs1.getString(3);
                double len=rs1.getDouble(4);
                NationalWayInfo way=new NationalWayInfo(name,end1,end2,len);
                ways.add(way);
            }
            rs1.close();
            st1.close();
            Statement st2=con1.createStatement();
            ResultSet rs2=st2.executeQuery(strSql2);
            while(rs2.next())//如果结果集中还有记录
            {
                String name=rs2.getString(1);
                String end1=rs2.getString(2);
                String end2=rs2.getString(3);
                double len=rs2.getDouble(4);
                NationalWayInfo way2=new NationalWayInfo(name,end1,end2,len);
                ways.add(way2);
            }
            rs2.close();
            st2.close();
            con1.close();
        }
        catch(SQLException ex)
        {
            GUI_ex gui_ex = new GUI_ex(ex.getMessage());
        }
        return (NationalWayInfo[])ways.toArray(new NationalWayInfo[ways.size()]);
    }
    public NationalWayInfo[] findWayssBySectionLength(String len1, String len2)
    {
        ArrayList<NationalWayInfo> ways=new ArrayList<NationalWayInfo>();
        String strSql1="select * from wayInfo where length between "+len1+" and "+len2;
        String strSql2="select * from wayInfo where length="+len2;
        //创建SQL语句执行对象
        try
        {
            Statement st1=con1.createStatement();
            ResultSet rs1=st1.executeQuery(strSql1);
            while(rs1.next())
            {
                String name=rs1.getString(1);
                String end1=rs1.getString(2);
                String end2=rs1.getString(3);
                double len=rs1.getDouble(4);
                NationalWayInfo way=new NationalWayInfo(name,end1,end2,len);
                ways.add(way);
            }
            rs1.close();
            st1.close();
            Statement st2=con1.createStatement();
            ResultSet rs2=st2.executeQuery(strSql2);
            while(rs2.next())//如果结果集中还有记录
            {
                String name=rs2.getString(1);
                String end1=rs2.getString(2);
                String end2=rs2.getString(3);
                double len=rs2.getDouble(4);
                NationalWayInfo way2=new NationalWayInfo(name,end1,end2,len);
                ways.add(way2);
            }
            rs2.close();
            st2.close();
            con1.close();
        }
        catch(SQLException ex)
        {
            GUI_ex gui_ex = new GUI_ex(ex.getMessage());
        }
        return (NationalWayInfo[])ways.toArray(new NationalWayInfo[ways.size()]);
    }
}
