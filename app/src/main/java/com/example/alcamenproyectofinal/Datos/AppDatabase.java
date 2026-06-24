package com.example.alcamenproyectofinal.Datos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.alcamenproyectofinal.Interfaces.Despacho_SedeDao;
import com.example.alcamenproyectofinal.Interfaces.ProductoDao;
import com.example.alcamenproyectofinal.Interfaces.ProveedorDao;
import com.example.alcamenproyectofinal.Interfaces.SedeDao;
import com.example.alcamenproyectofinal.Interfaces.SolicitudDao;
import com.example.alcamenproyectofinal.Interfaces.UsuarioDao;
import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;
import com.example.alcamenproyectofinal.Modelo.Detalle_Solicitud;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.Modelo.Usuario;

@Database(entities = {
        Usuario.class,
        Producto.class,
        Proveedor.class,
        Sede.class,
        Solicitud.class,
        Detalle_Solicitud.class,
        Despacho_Sede.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    public abstract ProductoDao productoDao();
    public abstract ProveedorDao proveedorDao();
    public abstract SedeDao sedeDao();
    public abstract SolicitudDao solicitudDao();

    public abstract Despacho_SedeDao despachoSedeDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "hipercorp_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
