package sql_gui_op_nationalWay;

import javafx.scene.SnapshotResult;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
    图形化界面显示及事件监听
 */
public class GUI extends JFrame implements ActionListener ,MouseListener{
    JLabel nameLa, one_endLa, another_endLa, lenthLa;
    JTextField nameText, one_endText, another_endText, lenthText, se_nameText, se_endText, startText, tailText;
    JButton ser_allBt, updBt, delBt, addBt, clearBt, ser_nameBt, ser_endBt, selectBt, sectionBt;
    static JTable infoTable;
    JLabel lb1;
    JScrollPane showScrollPane;
    String[] columnNames = {"name", "one_end", "another_end", "length"};
    JComboBox sexBox;
    JMenuBar menuBar1;
    JMenu jMenu1;
    JMenuItem jMenuItem1, jMenuItem2, jMenuItem3;

    private void initControler() {
        this.setLayout(null);
        nameLa = new JLabel("国道名");
        nameLa.setLocation(90, 50);
        nameLa.setSize(60, 30);
        this.add(nameLa);

        nameText = new JTextField();
        nameText.setLocation(160, 50);
        nameText.setSize(140, 30);
        this.add(nameText);

        one_endLa = new JLabel("地点一");
        one_endLa.setLocation(320, 50);
        one_endLa.setSize(60, 30);
        this.add(one_endLa);

        one_endText = new JTextField();
        one_endText.setLocation(390, 50);
        one_endText.setSize(140, 30);
        this.add(one_endText);

        another_endLa = new JLabel("地点二");
        another_endLa.setLocation(550, 50);
        another_endLa.setSize(60, 30);
        this.add(another_endLa);

        another_endText = new JTextField();
        another_endText.setLocation(620, 50);
        another_endText.setSize(140, 30);
        this.add(another_endText);

        lenthLa = new JLabel("长度（千米）");
        lenthLa.setLocation(800, 50);
        lenthLa.setSize(120, 30);
        this.add(lenthLa);

        lenthText = new JTextField();
        lenthText.setLocation(900, 50);
        lenthText.setSize(140, 30);
        this.add(lenthText);

        clearBt = new JButton("清空");
        clearBt.setLocation(390, 100);
        clearBt.setSize(70, 30);
        this.add(clearBt);
        clearBt.addActionListener(this);

        updBt = new JButton("更新");
        updBt.setLocation(480, 100);
        updBt.setSize(70, 30);
        this.add(updBt);
        updBt.addActionListener(this);

        delBt = new JButton("删除");
        delBt.setLocation(570, 100);
        delBt.setSize(70, 30);
        this.add(delBt);
        delBt.addActionListener(this);

        addBt = new JButton("添加");
        addBt.setLocation(660, 100);
        addBt.setSize(70, 30);
        this.add(addBt);
        addBt.addActionListener(this);

        selectBt = new JButton("选中");
        selectBt.setLocation(750, 100);
        selectBt.setSize(70, 30);
        this.add(selectBt);
        selectBt.addActionListener(this);

        ser_allBt = new JButton("查询(all)");
        ser_allBt.setLocation(400, 150);
        ser_allBt.setSize(95, 30);
        this.add(ser_allBt);
        ser_allBt.addActionListener(this);

        ser_nameBt = new JButton("查询(name)");
        ser_nameBt.setLocation(515, 150);
        ser_nameBt.setSize(100, 30);
        this.add(ser_nameBt);
        ser_nameBt.addActionListener(this);

        se_nameText = new JTextField();
        se_nameText.setLocation(620, 150);
        se_nameText.setSize(130, 30);
        this.add(se_nameText);

        ser_endBt = new JButton("查询(end)");
        ser_endBt.setLocation(770, 150);
        ser_endBt.setSize(95, 30);
        this.add(ser_endBt);
        ser_endBt.addActionListener(this);

        se_endText = new JTextField();
        se_endText.setLocation(880, 150);
        se_endText.setSize(130, 30);
        this.add(se_endText);

        sectionBt = new JButton("区间查询");
        sectionBt.setLocation(400, 200);
        sectionBt.setSize(110, 30);
        this.add(sectionBt);
        sectionBt.addActionListener(this);

        startText = new JTextField();
        startText.setLocation(540, 200);
        startText.setSize(100, 30);
        this.add(startText);

        lb1 = new JLabel();
        lb1.setText("—————");
        lb1.setSize(50, 30);
        lb1.setLocation(640, 200);
        this.add(lb1);

        tailText = new JTextField();
        tailText.setLocation(680, 200);
        tailText.setSize(100, 30);
        this.add(tailText);

        NationalWayOp nationalWayOp1 = new NationalWayOp();
        NationalWayInfo[] wayInfos = nationalWayOp1.findAllWays();
        String[][] data = new String[wayInfos.length][4];
        for (int i = 0; i < wayInfos.length; i++) {
            data[i] = new String[]
                    {
                            wayInfos[i].getName(),
                            wayInfos[i].getOne_end(),
                            wayInfos[i].getAnother_end(),
                            String.valueOf(wayInfos[i].getLength())
                    };
        }
        infoTable = new JTable(data, columnNames);
        infoTable.addMouseListener((MouseListener) this);
        showScrollPane = new JScrollPane();
        showScrollPane.setViewportView(infoTable);
        showScrollPane.setSize(1100, 550);
        showScrollPane.setLocation(50, 250);
        this.add(showScrollPane);

        menuBar1 = new JMenuBar();
        jMenu1 = new JMenu("背景");
        jMenuItem1 = new JMenuItem("红色");
        jMenuItem2 = new JMenuItem("黄色");
        jMenuItem3 = new JMenuItem("蓝色");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        menuBar1.add(jMenu1);
        setJMenuBar(menuBar1);
        jMenuItem1.addActionListener(e -> this.setBackground(Color.red));
        jMenuItem2.addActionListener(e -> this.setBackground(Color.yellow));
        jMenuItem3.addActionListener(e -> this.setBackground(Color.blue));
        this.add(menuBar1);
    }

    public GUI() {
        this.setSize(1200, 800);
        this.setTitle("国道信息系统");
        initControler();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton bt = (JButton) e.getSource();
        if (bt.getText().equals("添加")) {
            try {
                if (this.nameText.getText().toString().equals("") || this.one_endText.getText().toString().equals("") ||
                        this.another_endText.getText().toString().equals("") || this.lenthText.getText().toString().equals("")) {
                    throw new WayException("有信息为空");
                } else if (this.nameText.getText().toString().charAt(0) != 'G') {
                    throw new WayException("名称格式错误");
                } else if (!isNumeric(this.lenthText.getText().toString())) {
                    throw new WayException("长度类型错误，应为double");
                } else if (this.one_endText.getText().toString().contains("0") || this.one_endText.getText().toString().contains("1") ||
                        this.one_endText.getText().toString().contains("2") || this.one_endText.getText().toString().contains("3") ||
                        this.one_endText.getText().toString().contains("4") || this.one_endText.getText().toString().contains("5") ||
                        this.one_endText.getText().toString().contains("6") || this.one_endText.getText().toString().contains("7") ||
                        this.one_endText.getText().toString().contains("8") || this.one_endText.getText().toString().contains("9") ||
                        this.another_endText.getText().toString().contains("0") || this.another_endText.getText().toString().contains("1") ||
                        this.another_endText.getText().toString().contains("2") || this.another_endText.getText().toString().contains("3") ||
                        this.another_endText.getText().toString().contains("4") || this.another_endText.getText().toString().contains("5") ||
                        this.another_endText.getText().toString().contains("6") || this.another_endText.getText().toString().contains("7") ||
                        this.another_endText.getText().toString().contains("8") || this.another_endText.getText().toString().contains("9")) {
                    throw new WayException("地点输入有数字");
                } else {
                    String[] strs = new String[3];
                    strs[0] = this.nameText.getText().toString();
                    strs[1] = this.one_endText.getText().toString();
                    strs[2] = this.another_endText.getText().toString();
                    double len = Double.parseDouble(this.lenthText.getText().toString());

                    NationalWayInfo wayInfo1 = new NationalWayInfo(strs[0], strs[1], strs[2], len);
                    NationalWayOp nationalWayOp1 = new NationalWayOp();
                    nationalWayOp1.addWay(wayInfo1);

                }
            } catch (WayException e1) {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());

            }


        } else if (bt.getText().equals("清空")) {
            this.nameText.setText("");
            this.one_endText.setText("");
            this.another_endText.setText("");
            this.lenthText.setText("");
            this.se_endText.setText("");
            this.se_nameText.setText("");
            this.tailText.setText("");
            this.startText.setText("");
        } else if (bt.getText().equals("选中")) {
            int row = infoTable.getSelectedRow();
            String[] strs1 = new String[4];
            strs1[0] = infoTable.getValueAt(row, 0).toString();
            strs1[1] = infoTable.getValueAt(row, 1).toString();
            strs1[2] = infoTable.getValueAt(row, 2).toString();
            strs1[3] = infoTable.getValueAt(row, 3).toString();
            this.nameText.setText(strs1[0]);
            this.one_endText.setText(strs1[1]);
            this.another_endText.setText(strs1[2]);
            this.lenthText.setText(strs1[3]);
        } else if (bt.getText().equals("更新")) {
            try {
                if (this.nameText.getText().toString().equals("") || this.one_endText.getText().toString().equals("") ||
                        this.another_endText.getText().toString().equals("") || this.lenthText.getText().toString().equals("")) {
                    throw new WayException("有信息为空");
                } else if (this.nameText.getText().toString().charAt(0) != 'G') {
                    throw new WayException("名称格式错误");
                } else if (!isNumeric(this.lenthText.getText().toString())) {
                    throw new WayException("长度类型错误，应为double");
                } else if (this.one_endText.getText().toString().contains("0") || this.one_endText.getText().toString().contains("1") ||
                        this.one_endText.getText().toString().contains("2") || this.one_endText.getText().toString().contains("3") ||
                        this.one_endText.getText().toString().contains("4") || this.one_endText.getText().toString().contains("5") ||
                        this.one_endText.getText().toString().contains("6") || this.one_endText.getText().toString().contains("7") ||
                        this.one_endText.getText().toString().contains("8") || this.one_endText.getText().toString().contains("9") ||
                        this.another_endText.getText().toString().contains("0") || this.another_endText.getText().toString().contains("1") ||
                        this.another_endText.getText().toString().contains("2") || this.another_endText.getText().toString().contains("3") ||
                        this.another_endText.getText().toString().contains("4") || this.another_endText.getText().toString().contains("5") ||
                        this.another_endText.getText().toString().contains("6") || this.another_endText.getText().toString().contains("7") ||
                        this.another_endText.getText().toString().contains("8") || this.another_endText.getText().toString().contains("9")) {
                    throw new WayException("地点输入有数字");
                } else {
                    String[] strs2 = new String[3];
                    strs2[0] = this.nameText.getText().toString();
                    strs2[1] = this.one_endText.getText().toString();
                    strs2[2] = this.another_endText.getText().toString();
                    double len2 = Double.parseDouble(this.lenthText.getText().toString());
                    NationalWayInfo wayInfo1 = new NationalWayInfo(strs2[0], strs2[1], strs2[2], len2);
                    NationalWayOp nationalWayOp1 = new NationalWayOp();
                    nationalWayOp1.updateWay(wayInfo1);

                }

            } catch (WayException e1) {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());
            }
        } else if (bt.getText().equals("删除")) {

            int row = infoTable.getSelectedRow();
            String str = infoTable.getValueAt(row, 0).toString();
            NationalWayOp nationalWayOp1 = new NationalWayOp();
            nationalWayOp1.delWay(str);
            GUI_sure gui_sure = new GUI_sure();


        } else if (bt.getText().equals("查询(all)")) {
            try
            {
                NationalWayOp nationalWayOp1 = new NationalWayOp();
                NationalWayInfo[] wayInfos = nationalWayOp1.findAllWays();
                if(wayInfos.length==0)
                {
                    throw new WayException("查询数据为空");
                }
                else
                {
                    this.remove(showScrollPane);
                    String[][] data = new String[wayInfos.length][4];
                    for (int i = 0; i < wayInfos.length; i++) {
                        data[i] = new String[]
                                {
                                        wayInfos[i].getName(),
                                        wayInfos[i].getOne_end(),
                                        wayInfos[i].getAnother_end(),
                                        String.valueOf(wayInfos[i].getLength())
                                };
                    }
                    infoTable = new JTable(data, columnNames);
                    infoTable.addMouseListener((MouseListener) this);
                    showScrollPane.setViewportView(infoTable);
                    this.add(showScrollPane);
                }
            }
            catch (WayException e1)
            {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());
            }
        } else if (bt.getText().equals("查询(end)")) {
            try {

                if (this.se_endText.getText().toString().equals("")) {
                    throw new WayException("输入信息为空");
                }

                else {
                    NationalWayOp nationalWayOp1 = new NationalWayOp();
                    String str = this.se_endText.getText().toString();
                    NationalWayInfo[] wayInfos = nationalWayOp1.findWayssByEnd(str);
                    if(wayInfos.length==0)
                    {
                        throw new WayException("查询数据为空");
                    }
                    else
                    {
                        this.remove(showScrollPane);
                        System.out.println(wayInfos.length);
                        String[][] data = new String[wayInfos.length][4];
                        for (int i = 0; i < wayInfos.length; i++) {
                            data[i] = new String[]
                                    {
                                            wayInfos[i].getName(),
                                            wayInfos[i].getOne_end(),
                                            wayInfos[i].getAnother_end(),
                                            String.valueOf(wayInfos[i].getLength())
                                    };
                        }
                        infoTable = new JTable(data, columnNames);
                        infoTable.addMouseListener((MouseListener) this);
                        showScrollPane.setViewportView(infoTable);
                        this.add(showScrollPane);
                    }
                }

            } catch (WayException e1) {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());
            }
        } else if (bt.getText().equals("查询(name)")) {
            try {

                if (this.se_nameText.getText().toString().equals("")) {
                    throw new WayException("输入信息为空");
                }

                else {
                    String str = this.se_nameText.getText().toString();
                    NationalWayOp nationalWayOp1 = new NationalWayOp();
                    NationalWayInfo wayInfo = nationalWayOp1.findWayByName(str);
                    if(wayInfo == null)
                    {
                        throw new WayException("查询数据为空");
                    }
                    else
                    {
                        this.remove(showScrollPane);
                        String[][] data = new String[1][4];
                        for (int i = 0; i < 1; i++) {
                            data[i] = new String[]
                                    {
                                            wayInfo.getName(),
                                            wayInfo.getOne_end(),
                                            wayInfo.getAnother_end(),
                                            String.valueOf(wayInfo.getLength())
                                    };
                        }
                        infoTable = new JTable(data, columnNames);
                        infoTable.addMouseListener((MouseListener) this);
                        showScrollPane.setViewportView(infoTable);
                        this.add(showScrollPane);
                    }
                }
            } catch (WayException e1) {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());
            }

        } else if (bt.getText().equals("区间查询")) {
            try {
                if (this.startText.getText().toString().equals("") || this.tailText.getText().toString().equals("")) {
                    throw new WayException("输入信息为空");
                }
                else if (!isNumeric(this.startText.getText().toString()) || !isNumeric(this.tailText.getText().toString()))
                {
                    throw new WayException("长度类型错误，应为double");
                }
                else if(Double.parseDouble(this.startText.getText().toString())>Double.parseDouble(this.tailText.getText().toString()))
                {
                    throw new WayException("数据区间有误");
                }
                else
                {
                    NationalWayOp nationalWayOp1 = new NationalWayOp();
                    NationalWayInfo[] wayInfos = nationalWayOp1.findWayssBySectionLength(this.startText.getText().toString(),this.tailText.getText().toString());
                    if(wayInfos.length==0)
                    {
                        throw new WayException("查询数据为空");
                    }
                    else
                    {
                        this.remove(showScrollPane);
                        String[][] data = new String[wayInfos.length][4];
                        for (int i = 0; i < wayInfos.length; i++) {
                            data[i] = new String[]
                                    {
                                            wayInfos[i].getName(),
                                            wayInfos[i].getOne_end(),
                                            wayInfos[i].getAnother_end(),
                                            String.valueOf(wayInfos[i].getLength())
                                    };
                        }
                        infoTable = new JTable(data, columnNames);
                        infoTable.addMouseListener((MouseListener) this);
                        showScrollPane.setViewportView(infoTable);
                        this.add(showScrollPane);
                    }

                }
            } catch (WayException e1) {
                GUI_ex gui_ex = new GUI_ex(e1.getMessage());
            }
        }
    }
        public static boolean isNumeric (String str)
        {

            Pattern pattern = Pattern.compile("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");

            Matcher isNum = pattern.matcher(str);

            if (!((Matcher) isNum).matches())
            {
                return false;
            }
            else
                {
                return true;
            }
        }

    public void mouseClicked(MouseEvent e)
    {
        int row = infoTable.rowAtPoint(e.getPoint());
        String[] strs1 = new String[4];
        strs1[0] = infoTable.getValueAt(row, 0).toString();
        strs1[1] = infoTable.getValueAt(row, 1).toString();
        strs1[2] = infoTable.getValueAt(row, 2).toString();
        strs1[3] = infoTable.getValueAt(row, 3).toString();
        this.nameText.setText(strs1[0]);
        this.one_endText.setText(strs1[1]);
        this.another_endText.setText(strs1[2]);
        this.lenthText.setText(strs1[3]);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


