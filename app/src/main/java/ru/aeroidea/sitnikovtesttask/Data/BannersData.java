package ru.aeroidea.sitnikovtesttask.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "banners")
public class BannersData {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;

    @ColumnInfo(name = "title")
    private final String title;

    @ColumnInfo(name = "img")
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
