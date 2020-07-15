package com.cudocom.barang.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Barang {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_barang")
    String nama_barang;
    @ColumnInfo(name = "qty")
    int qty;
    @ColumnInfo(name = "harga")
    int harga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
