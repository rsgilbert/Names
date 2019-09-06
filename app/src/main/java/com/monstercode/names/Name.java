package com.monstercode.names;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "name")
public class Name implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int _ID;

    @ColumnInfo(name="firstName")
    private String firstName;

    @ColumnInfo(name="lastName")
    private String lastName;

    /**
     * Getters and setters
     */

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
