package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Detalle_Solicitudes")
public class Detalle_Solicitud {

    @PrimaryKey
    @NonNull
    private String codigo_detalle;

    private String codigo_solicitud, codigo_producto;

    private Integer cantidad;

    public Detalle_Solicitud() {

        codigo_detalle = "";
    }

    public Detalle_Solicitud(String codigo_detalle, String codigo_solicitud, String codigo_producto, Integer cantidad) {
        this.codigo_detalle = codigo_detalle;
        this.codigo_solicitud = codigo_solicitud;
        this.codigo_producto = codigo_producto;
        this.cantidad = cantidad;
    }

    public String getCodigo_detalle() {
        return codigo_detalle;
    }

    public void setCodigo_detalle(String codigo_detalle) {
        this.codigo_detalle = codigo_detalle;
    }

    public String getCodigo_solicitud() {
        return codigo_solicitud;
    }

    public void setCodigo_solicitud(String codigo_solicitud) {
        this.codigo_solicitud = codigo_solicitud;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Detalle_Solicitud{" +
                "codigo_detalle='" + codigo_detalle + '\'' +
                ", codigo_solicitud='" + codigo_solicitud + '\'' +
                ", codigo_producto='" + codigo_producto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
