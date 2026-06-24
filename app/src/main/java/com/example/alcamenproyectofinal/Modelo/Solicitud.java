package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Solicitudes")
public class Solicitud {

    @PrimaryKey
    @NonNull
    private String codigo_solicitud;

    private String movimiento, estado, codigo_operario, codigo_jefe, codigo_producto;

    private String fecha;

    public Solicitud() {

        codigo_solicitud = "";
    }

    public Solicitud(String codigo_solicitud, String movimiento, String estado, String codigo_operario, String codigo_jefe, String fecha, String codigo_producto) {
        this.codigo_solicitud = codigo_solicitud;
        this.movimiento = movimiento;
        this.estado = estado;
        this.codigo_operario = codigo_operario;
        this.codigo_jefe = codigo_jefe;
        this.fecha = fecha;
        this.codigo_producto = codigo_producto;
    }

    public String getCodigo_solicitud() {
        return codigo_solicitud;
    }

    public void setCodigo_solicitud(String codigo_solicitud) {
        this.codigo_solicitud = codigo_solicitud;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo_operario() {
        return codigo_operario;
    }

    public void setCodigo_operario(String codigo_operario) {
        this.codigo_operario = codigo_operario;
    }

    public String getCodigo_jefe() {
        return codigo_jefe;
    }

    public void setCodigo_jefe(String codigo_jefe) {
        this.codigo_jefe = codigo_jefe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "codigo_solicitud='" + codigo_solicitud + '\'' +
                ", movimiento='" + movimiento + '\'' +
                ", estado='" + estado + '\'' +
                ", codigo_operario='" + codigo_operario + '\'' +
                ", codigo_jefe='" + codigo_jefe + '\'' +
                ", fecha=" + fecha + '\'' +
                "codigo_productod='" + codigo_producto +
                '}';
    }
}
