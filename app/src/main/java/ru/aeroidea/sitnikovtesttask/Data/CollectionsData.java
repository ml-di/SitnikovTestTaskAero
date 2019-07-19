package ru.aeroidea.sitnikovtesttask.Data;

public class CollectionsData {

    private final int id;
    private final String name;
    private final String img;
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
