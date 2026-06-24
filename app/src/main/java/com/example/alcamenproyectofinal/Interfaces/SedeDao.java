package com.example.alcamenproyectofinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Sede;

import java.util.List;

@Dao
public interface SedeDao {

    @Insert
    void insertarSede(Sede sede);

    @Query("SELECT * FROM Sedes")
    List<Sede> obtenerSedes();

    @Query("SELECT * FROM Sedes WHERE codigo_sede = :codigo LIMIT 1")
    Sede obtenerPorCodigo(String codigo);

    @Query("SELECT * FROM Sedes WHERE codigo_sede = :id")
    Sede obtenerPorId(String id);

    @Update
    void actualizarSede(Sede sede);

    @Delete
    void eliminarSede(Sede sede);

}
