package com.monstercode.names;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NameDao {

    @Query("SELECT * FROM name")
    List<Name> getAll();

    @Insert
    void insert(Name name);


}

