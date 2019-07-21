package ru.aeroidea.sitnikovtesttask.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "collections")
public class CollectionsData {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "img")
    private final String img;

    @ColumnInfo(name = "products_count")
    private final int productsCount;

    public CollectionsData(final int id,
                           final String name,
                           final String img,
                           final int productsCount) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.productsCount = productsCount;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getImg() {
        return img;
    }
    public int getProductsCount() {
        return productsCount;
    }
}
