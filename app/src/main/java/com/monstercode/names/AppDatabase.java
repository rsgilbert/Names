package com.monstercode.names;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Name.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NameDao nameDao();
}
