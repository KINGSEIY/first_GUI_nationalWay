package sql_gui_op_nationalWay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
    异常窗口类
 */
public class GUI_ex extends JFrame implements ActionListener
{

    public GUI_ex(String str)
    {
        this.setSize(400, 300);
        this.setTitle("Exception");
        this.setLayout(null);
        JLabel ExLb = new JLabel();
        ExLb.setText(str);
        ExLb.setSize(300,50);
        ExLb.setLocation(50,50);
        this.add(ExLb);
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
