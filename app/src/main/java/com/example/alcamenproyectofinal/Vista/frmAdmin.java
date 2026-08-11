package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.R;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executors;

public class frmAdmin extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;
    private TextView tvContadorUsuarios, tvContadorProductos, tvContadorSedes;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_admin);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        tvContadorUsuarios = findViewById(R.id.tvContadorUsuarios);
        tvContadorProductos = findViewById(R.id.tvContadorProductos);
        tvContadorSedes = findViewById(R.id.tvContadorSedes);

        db = AppDatabase.getDatabase(this);

        configurarEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarContadores();
    }

    private void cargarContadores() {
        Executors.newSingleThreadExecutor().execute(() -> {
            int totalUsuarios = db.usuarioDao().obtenerCantidadUsuarios();
            int totalProductos = db.productoDao().obtenerCantidadProductos();
            int totalSedes = db.sedeDao().obtenerCantidadSedes();

            runOnUiThread(() -> {
                if (tvContadorUsuarios != null) {
                    tvContadorUsuarios.setText(String.valueOf(totalUsuarios));
                }
                if (tvContadorProductos != null) {
                    tvContadorProductos.setText(String.valueOf(totalProductos));
                }
                if (tvContadorSedes != null) {
                    tvContadorSedes.setText(String.valueOf(totalSedes));
                }
            });
        });
    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirUsuarios() {
        startActivity(new Intent(this, frmListarUsuarios.class));
    }

    private void abrirProductos() {
        startActivity(new Intent(this, frmListarProductos.class));
    }

    private void abrirSedes() {
        startActivity(new Intent(this, frmListarSedes.class));
    }
}