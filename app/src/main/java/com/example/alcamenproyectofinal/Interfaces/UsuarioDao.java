package com.example.alcamenproyectofinal.Interfaces;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alcamenproyectofinal.Modelo.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("SELECT COUNT(*) FROM Usuarios")
    int obtenerCantidadUsuarios();

    @Query("SELECT * FROM Usuarios WHERE username = :user AND password = :pass LIMIT 1")
    Usuario login(String user, String pass);

    @Query("SELECT nombres FROM Usuarios")
    List<String> obtenerCodigosDeOperarios();


    @Insert
    void insertarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Delete
    void eliminarUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuarios")
    List<Usuario> obtenerUsuarios();

    @Query("SELECT * FROM Usuarios")
    Cursor obtenerUsuariosJson();

    @Query("SELECT * FROM Usuarios WHERE codigo_usuario = :id")
    Usuario obtenerPorId(String id);
}
