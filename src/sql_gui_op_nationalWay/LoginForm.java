package sql_gui_op_nationalWay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
       登录窗口
 */
public class LoginForm extends JFrame implements ActionListener
//采用精确的定位方式
{
    JLabel userLa,pwdLa;//两个标签对象
    JTextField userText;//一个用户名文本框
    JPasswordField pwdText;//一个密码输入框
    JButton loginBt,quitBt;//两个按钮

    private void initControler()//初始化控件对象
    {
        this.setLayout(null);//设置窗体布局对象为空
        userLa=new JLabel("用户名");
        userLa.setLocation(70,50);
        userLa.setSize(60,30);
        this.add(userLa);

        userText=new JTextField();
        userText.setLocation(160,50);
        userText.setSize(140,30);
        this.add(userText);

        pwdLa=new JLabel("密    码");
        pwdLa.setLocation(70,120);
        pwdLa.setSize(60,30);
        this.add(pwdLa);

        pwdText=new JPasswordField();
        pwdText.setLocation(160,120);
        pwdText.setSize(140,30);
        this.add(pwdText);

        loginBt=new JButton("登录");
        loginBt.setLocation(110,180);
        loginBt.setSize(70,30);
        loginBt.addActionListener(this);
        //将事件源和事件处理对象绑定
        this.add(loginBt);

        quitBt=new JButton("退出");
        quitBt.setLocation(210,180);
        quitBt.setSize(70,30);
        quitBt.addActionListener(this);
        this.add(quitBt);




    }

    public LoginForm()
    {
        this.setSize(400, 300);//设置窗台大小
        this.setTitle("国道信息管理系统登录");//设置窗台标题
        initControler();//调用初始化控件的方法
        this.setVisible(true);//设置窗体可见
    }

    //具体实现按钮被点击后的操作
    public void actionPerformed(ActionEvent e)
    {
        JButton bt=(JButton)e.getSource();
        //获取事件源强转为按钮类型
        if(bt.getText().equals("登录"))
        {
            String user=userText.getText().trim();
            String pwd=pwdText.getText().trim();
            if((user.equals("root"))&&(pwd.equals("123")))
            {
                //如果判断用户名和密码都正确后将原先登录窗体中的所有控件移除
                this.getContentPane().removeAll();
                //重新去设置主窗体

                GUI gui=new GUI();
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "用户名或密码错误！","error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            System.exit(0);
            //程序正常退出
        }
    }
}
