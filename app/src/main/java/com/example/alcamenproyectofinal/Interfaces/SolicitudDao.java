package com.example.alcamenproyectofinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Solicitud;

import java.util.List;

@Dao
public interface SolicitudDao {

    @Query("SELECT COUNT(*) FROM solicitudes")
    int obtenerCantidadSolicitudes();

    @Insert
    void insertarSolicitud(Solicitud solicitud);

    @Query("SELECT * FROM Solicitudes")
    List<Solicitud> obtenerSolicitudes();

    @Query("UPDATE Solicitudes SET estado = 'Aprobado' WHERE codigo_solicitud = :idSol")
    void aprobarSolicitud(String idSol);

    @Query("SELECT * FROM Solicitudes WHERE codigo_solicitud = :id")
    Solicitud obtenerPorId(String id);

    @Query("SELECT * FROM Solicitudes")
    List<Solicitud> obtenerTodasLasSolicitudes();

    @Update
    void actualizarSolicitud(Solicitud solicitud);

    @Delete
    void eliminarSolicitud(Solicitud solicitud);
}
