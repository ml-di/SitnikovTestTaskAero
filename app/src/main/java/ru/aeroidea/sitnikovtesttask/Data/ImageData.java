package ru.aeroidea.sitnikovtesttask.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image")
public class ImageData {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    private final String url;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "img")
    private final byte[] img;

    public ImageData(@NonNull final String url,
                     final byte[] img) {
        this.url = url;
        this.img = img;
    }

    @NonNull
    public String getUrl() {
        return url;
    }
    public byte[] getImg() {
        return img;
    }
}
