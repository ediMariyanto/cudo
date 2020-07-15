package com.cudocom.barang.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cudocom.barang.model.Barang;

import java.util.List;

@Dao
public interface BarangDao {

    @Query("SELECT * FROM barang")
    List<Barang> getAll();

    @Query("SELECT MAX(id) FROM barang")
    int getMaxSeq();

    @Insert
    void insertAll(Barang... barangs);

    @Delete
    public void deleteBarang(Barang... barangs);

    @Update
    public void updateBarang(Barang... barangs);
}

