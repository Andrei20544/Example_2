package Model;

import java.util.Date;

public class News
{
    private long ID;
    private String DateNews;
    private String Title;
    private String Description;

    public News() {
    }

    public News(long ID, String dateNews, String title, String description) {
        this.ID = ID;
        DateNews = dateNews;
        Title = title;
        Description = description;
    }

    public long getID() {
        return ID;
    }

    public String getDateNews() {
        return DateNews;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDateNews(String dateNews) {
        DateNews = dateNews;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
