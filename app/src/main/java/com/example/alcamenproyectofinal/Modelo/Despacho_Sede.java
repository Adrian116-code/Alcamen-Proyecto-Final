package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Despachos_Sede")
public class Despacho_Sede {

    @PrimaryKey
    @NonNull
    private String codigo_despacho;

    private String codigo_operario, codigo_producto, codigo_sede;

    private Integer cantidad;

    public Despacho_Sede() {

        codigo_despacho = "";
    }

    public Despacho_Sede(String codigo_despacho, String codigo_operario, String codigo_producto, String codigo_sede, Integer cantidad) {
        this.codigo_despacho = codigo_despacho;
        this.codigo_operario = codigo_operario;
        this.codigo_producto = codigo_producto;
        this.codigo_sede = codigo_sede;
        this.cantidad = cantidad;
    }

    public String getCodigo_despacho() {
        return codigo_despacho;
    }

    public void setCodigo_despacho(String codigo_despacho) {
        this.codigo_despacho = codigo_despacho;
    }

    public String getCodigo_operario() {
        return codigo_operario;
    }

    public void setCodigo_operario(String codigo_operario) {
        this.codigo_operario = codigo_operario;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getCodigo_sede() {
        return codigo_sede;
    }

    public void setCodigo_sede(String codigo_sede) {
        this.codigo_sede = codigo_sede;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Despacho_Sede{" +
                "codigo_despacho='" + codigo_despacho + '\'' +
                ", codigo_operario='" + codigo_operario + '\'' +
                ", codigo_producto='" + codigo_producto + '\'' +
                ", codigo_sede='" + codigo_sede + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
