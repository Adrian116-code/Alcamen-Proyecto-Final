package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.DespachoAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarDespacho extends AppCompatActivity {

    RecyclerView rvDespachos;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_despacho);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar Room DB
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // Vincular el RecyclerView con el nuevo ID del XML
        rvDespachos = findViewById(R.id.rvDespachos);
        rvDespachos.setLayoutManager(new LinearLayoutManager(this));

        cargarDespachos();
    }

    private void cargarDespachos() {
        try {
            List<Despacho_Sede> lista = db.despachoSedeDao().obtenerDespachos(); // Ajusta según tu método DAO

            if (lista != null && !lista.isEmpty()) {
                DespachoAdapter adapter = new DespachoAdapter(lista);
                rvDespachos.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No hay despachos registrados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar despachos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // ==================== NAVEGACIÓN DEL FOOTER ====================

    public void btnIngresoSalida(View view) {
        // Ya te encuentras en la pantalla de lista de movimientos/despachos
    }

    public void btnSolicitudes(View view) {
        // Intent hacia la pantalla de Solicitudes si la utilizas
    }

    public void btnRegresar(View view) {
        finish();
    }
}