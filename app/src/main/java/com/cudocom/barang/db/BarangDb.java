package com.cudocom.barang.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cudocom.barang.model.Barang;

@Database(entities = {Barang.class}, version = 1, exportSchema = false)
public abstract class BarangDb extends RoomDatabase {
    public abstract BarangDao barangDao();
}
