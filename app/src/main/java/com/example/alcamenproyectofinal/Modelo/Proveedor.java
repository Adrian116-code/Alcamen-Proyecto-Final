package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Proveedores")
public class Proveedor {

    @PrimaryKey
    @NonNull
    private String codigo_proveedor;

    private String razon_social, contacto;

    private Integer telefono;

    public Proveedor() {

        codigo_proveedor = "";
    }

    public Proveedor(String codigo_proveedor, String razon_social, String contacto, Integer telefono) {
        this.codigo_proveedor = codigo_proveedor;
        this.razon_social = razon_social;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    public String getCodigo_proveedor() {
        return codigo_proveedor;
    }

    public void setCodigo_proveedor(String codigo_proveedor) {
        this.codigo_proveedor = codigo_proveedor;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "codigo_proveedor='" + codigo_proveedor + '\'' +
                ", razon_social='" + razon_social + '\'' +
                ", contacto='" + contacto + '\'' +
                ", telefono=" + telefono +
                '}';
    }

}
