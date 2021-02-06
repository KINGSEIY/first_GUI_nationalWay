package sql_gui_op_nationalWay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
    操作成功提示窗口
 */
public class GUI_sure extends JFrame implements ActionListener
{
    public GUI_sure()
    {
        this.setSize(400, 300);
        this.setTitle("sucess");
        this.setLayout(null);
        JButton bt1 = new JButton("确定");
        bt1.setLocation(150,135);
        bt1.setSize(100,30);
        this.add(bt1);
        bt1.addActionListener(this);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        JButton bt = (JButton) e.getSource();
        if (bt.getText().equals("确定"))
        {

            this.dispose();
        }
    }

}
