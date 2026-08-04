package com.example.alcamenproyectofinal.Interfaces;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.Modelo.Usuario;

import java.util.List;

@Dao
public interface ProductoDao {

    @Query("SELECT COUNT(*) FROM productos")
    int obtenerCantidadProductos();

    @Insert
    void insertarProducto(Producto producto);

    @Query("SELECT * FROM Productos")
    List<Producto> obtenerProductos();

    @Query("UPDATE Productos SET stock = stock - :cantidad WHERE codigo_producto = :codigo")
    void reducirStock(String codigo, int cantidad);

    @Query("SELECT * FROM Productos WHERE codigo_producto = :id")
    Producto obtenerPorId(String id);

    @Query("SELECT nombre FROM Productos")
    List<String> obtenerCodigosDeProductos();

    @Update
    void actualizarProducto(Producto producto);

    @Query("UPDATE productos SET stock = stock + :Cantidad WHERE codigo_producto = :codigo")
    void actualizarStock(String codigo, int Cantidad);

    @Delete
    void eliminarProducto(Producto producto);
}
