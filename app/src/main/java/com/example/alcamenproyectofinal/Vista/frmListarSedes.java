package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;

import org.w3c.dom.Text;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.SedeAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarSedes extends AppCompatActivity {

    private RecyclerView rvSedes;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_sedes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // Configurar RecyclerView
        rvSedes = findViewById(R.id.rvSedes);
        rvSedes.setLayoutManager(new LinearLayoutManager(this));

        // Cargar sedes al abrir la pantalla
        cargarYMostrarSedes();
    }

    // Método enlazado al onClick del botón en el XML
    public void btnListarSede(View view) {
        cargarYMostrarSedes();
    }

    private void cargarYMostrarSedes() {
        try {
            List<Sede> lista = db.sedeDao().obtenerSedes();

            SedeAdapter adapter = new SedeAdapter(lista);
            rvSedes.setAdapter(adapter);

            if (lista.isEmpty()) {
                Toast.makeText(this, "No hay sedes registradas en el sistema", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al refrescar listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnRegresar(View view) {
        finish();
    }
}