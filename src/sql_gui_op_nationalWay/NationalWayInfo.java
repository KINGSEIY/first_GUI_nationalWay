package sql_gui_op_nationalWay;
/*
    数据信息类
 */
public class NationalWayInfo {
    private String name;
    private String one_end;
    private String another_end;
    private double length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOne_end() {
        return one_end;
    }

    public void setOne_end(String one_end) {
        this.one_end = one_end;
    }

    public String getAnother_end() {
        return another_end;
    }

    public void setAnother_end(String another_end) {
        this.another_end = another_end;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public NationalWayInfo(String name, String one_end, String another_end, double length)
    {
        setName(name);
        setOne_end(one_end);
        setAnother_end(another_end);
        setLength(length);
    }
}
