package Model;

public class Service
{
    private String Name;
    private long ID;
    private double Cost;

    public Service(String name, long ID, double cost) {
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
