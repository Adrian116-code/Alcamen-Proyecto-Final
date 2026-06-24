package com.example.alcamenproyectofinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;

import java.util.List;

@Dao
public interface Despacho_SedeDao {

    @Insert
    void registrarDespacho(Despacho_Sede despacho);

    @Query("SELECT * FROM Despachos_Sede WHERE codigo_sede = :idSede")
    List<Despacho_Sede> obtenerDespachosPorSede(String idSede);

    @Query("SELECT * FROM Despachos_Sede")
    List<Despacho_Sede> obtenerDespachos();

    @Query("SELECT * FROM Despachos_Sede WHERE codigo_operario = :idUsuario")
    List<Despacho_Sede> obtenerDespachosPorUsuario(String idUsuario);

}
