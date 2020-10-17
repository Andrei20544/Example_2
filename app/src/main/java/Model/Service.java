package Model;

public class Service
{
    private String Name;
    private int ID;
    private double Cost;

    public Service(String name, int ID, double cost) {
        Name = name;
        this.ID = ID;
        Cost = cost;
    }

    public String getName() {
        return Name;
    }

    public int getID() {
        return ID;
    }

    public double getCost() {
        return Cost;
    }
}
