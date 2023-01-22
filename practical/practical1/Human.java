package practical.practical1;

public class Human {
    private final String name;
    private final String sex;
    private final boolean liveStatus;

    public Human (String n, String s, boolean l){
        this.name = n;
        this.sex = s;
        this.liveStatus = l;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public boolean getLiveStatus() {
        return liveStatus;
    }

}