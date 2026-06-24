package com.example.alcamenproyectofinal.Modelo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Usuarios")
public class Usuario {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "codigo_usuario")
    private String codigo_usuario;

    private String dni, nombres, apellidos, rol, username, password;

    private Integer edad;

    public Usuario() {

        codigo_usuario = "";
    }

    public Usuario(String codigo_usuario, String dni, String nombres, String apellidos, String rol, String username, String password, Integer edad) {
        this.codigo_usuario = codigo_usuario;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.edad = edad;
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo_usuaroi='" + codigo_usuario + '\'' +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", rol='" + rol + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", edad=" + edad +
                '}';
    }
}
