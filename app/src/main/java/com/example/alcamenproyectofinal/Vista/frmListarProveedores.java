package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.ProveedorAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarProveedores extends AppCompatActivity {

    private RecyclerView rvProveedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_proveedores);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Vincular el RecyclerView del XML
        rvProveedores = findViewById(R.id.rvProveedores);
        rvProveedores.setLayoutManager(new LinearLayoutManager(this));

        // 2. Cargar los datos inmediatamente al iniciar
        cargarListaProveedores();
    }

    private void cargarListaProveedores() {
        // Consulta a la BD con Room
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Proveedor> lista = db.proveedorDao().obtenerProveedores();

        // Asignar el adaptador
        ProveedorAdapter adapter = new ProveedorAdapter(lista);
        rvProveedores.setAdapter(adapter);
    }

    public void btnListarProveedor(View view) {
        cargarListaProveedores();
    }

    public void btnRegresar(View view) {
        finish();
    }
}