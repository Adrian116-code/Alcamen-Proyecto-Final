package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
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
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class frmListarDespacho extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnProveedores, btnDespacho, btnRegresar;
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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        rvDespachos = findViewById(R.id.rvDespachos);
        rvDespachos.setLayoutManager(new LinearLayoutManager(this));

        cargarDespachos();

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
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

    public void btnIngresoSalida(View view) {

    }

    public void btnSolicitudes(View view) {

    }

    private void configurarEventos() {
        if (btnSolicitudes != null) btnSolicitudes.setOnClickListener(v -> abrirSolicitudes());
        if (btnProveedores != null) btnProveedores.setOnClickListener(v -> abrirProveedores());
        if (btnDespacho != null) btnDespacho.setOnClickListener(v -> abrirDespacho());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirSolicitudes() {
        Intent intent = new Intent(this, frmListarSolicitudes.class);
        startActivity(intent);
    }

    private void abrirProveedores() {
        Intent intent = new Intent(this, frmGestionProveedores.class);
        startActivity(intent);
    }

    private void abrirDespacho() {
        Intent intent = new Intent(this, frmListarDespacho.class);
        startActivity(intent);
    }

    public void btnRegresar(View view) {
        finish();
    }
}