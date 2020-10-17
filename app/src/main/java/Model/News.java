package Model;

import java.util.Date;

public class News
{
    private int ID;
    private Date DateNews;
    private String Title;
    private String Description;

    public News(int ID, Date dateNews, String title, String description) {
        this.ID = ID;
        DateNews = dateNews;
        Title = title;
        Description = description;
    }

    public int getID() {
        return ID;
    }

    public Date getDateNews() {
        return DateNews;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }
}
