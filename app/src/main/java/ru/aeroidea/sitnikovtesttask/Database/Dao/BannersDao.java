package ru.aeroidea.sitnikovtesttask.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import ru.aeroidea.sitnikovtesttask.Data.BannersData;

@Dao
public interface BannersDao {

    @Insert
    void insert(BannersData... bannersData);

    @Query("DELETE FROM banners")
    void deleteAll();

    @Query("SELECT * FROM banners")
    List<BannersData> getAllBanners();

    @Query("SELECT COUNT(*) FROM banners")
    int getCount();
}
