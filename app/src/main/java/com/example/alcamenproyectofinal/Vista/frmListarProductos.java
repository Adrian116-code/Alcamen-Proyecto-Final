package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.util.Log;
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

import com.example.alcamenproyectofinal.Adaptador.ProductoAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarProductos extends AppCompatActivity {

    private RecyclerView rvProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_productos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Vincular el RecyclerView del layout XML
        rvProductos = findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        // 2. Cargar los datos inmediatamente al iniciar la vista
        cargarListaProductos();
    }

    private void cargarListaProductos() {
        // Consulta a Room
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Producto> lista = db.productoDao().obtenerProductos();

        // Pasar la lista al adaptador
        ProductoAdapter adapter = new ProductoAdapter(lista);
        rvProductos.setAdapter(adapter);
    }

    public void btnListarProducto(View view) {
        cargarListaProductos();
    }

    public void btnRegresar(View view) {
        finish();
    }
}