package ru.aeroidea.sitnikovtesttask.Data;

public class BannersData {

    private final int id;
    private final String title;
    private final String img;

    public BannersData(final int id,
                       final String title,
                       final String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImg() {
        return img;
    }
}
