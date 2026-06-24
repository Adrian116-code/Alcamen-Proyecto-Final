package com.example.alcamenproyectofinal.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.alcamenproyectofinal.Modelo.Detalle_Solicitud;

import java.util.List;

@Dao
public interface Detalle_SolicitudDao {

    @Insert
    void insertarDetalle(Detalle_Solicitud detalle);

    @Query("SELECT * FROM Detalle_Solicitudes WHERE codigo_solicitud = :idSolicitud")
    List<Detalle_Solicitud> obtenerDetallesPorSolicitud(String idSolicitud);

    @Query("SELECT * FROM Detalle_Solicitudes")
    List<Detalle_Solicitud> obtenerDetalle_Solicitudes();
}
