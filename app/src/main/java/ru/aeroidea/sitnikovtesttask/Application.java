package ru.aeroidea.sitnikovtesttask;

import androidx.room.Room;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;

public class Application extends android.app.Application {

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "AeroTaskDB").build();
    }
}
