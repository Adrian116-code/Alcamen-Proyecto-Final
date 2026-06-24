package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sedes")
public class Sede {

    @PrimaryKey
    @NonNull
    private String codigo_sede;

    private String nombre, direccion;

    public Sede() {

        codigo_sede = "";
    }

    public Sede(String codigo_sede, String nombre, String direccion) {
        this.codigo_sede = codigo_sede;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getCodigo_sede() {
        return codigo_sede;
    }

    public void setCodigo_sede(String codigo_sede) {
        this.codigo_sede = codigo_sede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "codigo_sede='" + codigo_sede + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
