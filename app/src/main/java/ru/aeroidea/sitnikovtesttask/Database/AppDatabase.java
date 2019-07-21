package ru.aeroidea.sitnikovtesttask.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.Data.ImageData;
import ru.aeroidea.sitnikovtesttask.Database.Dao.BannersDao;
import ru.aeroidea.sitnikovtesttask.Database.Dao.CollectionsDao;
import ru.aeroidea.sitnikovtesttask.Database.Dao.ImageDao;

@Database(entities = {
        BannersData.class,
        CollectionsData.class,
        ImageData.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BannersDao bannersDao();
    public abstract CollectionsDao collectionsDao();
    public abstract ImageDao imageDao();
}
