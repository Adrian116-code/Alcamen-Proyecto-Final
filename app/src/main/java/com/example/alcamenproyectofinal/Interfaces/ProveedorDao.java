package com.example.alcamenproyectofinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alcamenproyectofinal.Modelo.Proveedor;

import java.util.List;

@Dao
public interface ProveedorDao {

    @Query("SELECT COUNT(*) FROM proveedores")
    int obtenerCantidadProveedores();

    @Insert
    void insertarProveedor(Proveedor proveedor);

    @Query("SELECT * FROM Proveedores")
    List<Proveedor> obtenerProveedores();

    @Query("SELECT * FROM Proveedores WHERE codigo_proveedor = :id")
    Proveedor obtenerPorId(String id);

    @Query("SELECT razon_social FROM Proveedores")
    List<String> obtenerCodigosDeProveedores();

    @Update
    void actualizarProveedor(Proveedor proveedor);

    @Delete
    void eliminarProveedor(Proveedor proveedor);
}
