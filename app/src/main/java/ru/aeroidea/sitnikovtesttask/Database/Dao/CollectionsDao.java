package ru.aeroidea.sitnikovtesttask.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;

@Dao
public interface CollectionsDao {

    @Insert
    void insert(CollectionsData... collectionsData);

    @Query("DELETE FROM collections")
    void deleteAll();

    @Query("SELECT * FROM collections")
    List<CollectionsData> getAllCollections();

    @Query("SELECT COUNT(*) FROM collections")
    int getCount();
}
