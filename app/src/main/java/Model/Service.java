package Model;

public class Service
{
    private String Name;
    private long ID;
    private double Cost;

    public Service(long ID,String name, double cost) {
        Name = name;
        this.ID = ID;
        Cost = cost;
    }

    public String getName() {
        return Name;
    }

    public long getID() {
        return ID;
    }

    public double getCost() {
        return Cost;
    }
}
