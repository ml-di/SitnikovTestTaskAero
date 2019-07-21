package ru.aeroidea.sitnikovtesttask.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import ru.aeroidea.sitnikovtesttask.Data.ImageData;

@Dao
public interface ImageDao {

    @Insert
    void insert(ImageData... imageData);

    @Query("SELECT EXISTS(SELECT url FROM image WHERE url = :url)")
    boolean isExists(String url);

    @Query("SELECT img FROM image WHERE url = :url")
    byte[] getImage(String url);

}
