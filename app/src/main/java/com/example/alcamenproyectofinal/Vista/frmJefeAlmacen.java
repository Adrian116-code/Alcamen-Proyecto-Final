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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executors;

public class frmJefeAlmacen extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnProveedores, btnDespacho, btnRegresar;

    private TextView tvContadorSolicitudes, tvContadorProveedores;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_jefe_almacen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        tvContadorSolicitudes = findViewById(R.id.tvContadorSolicitudes);
        tvContadorProveedores = findViewById(R.id.tvContadorProveedores);

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
            int totalSolicitudes = db.solicitudDao().obtenerCantidadSolicitudes();
            int totalProveedores = db.proveedorDao().obtenerCantidadProveedores();

            runOnUiThread(() -> {
                if (tvContadorSolicitudes != null) {
                    tvContadorSolicitudes.setText(String.valueOf(totalSolicitudes));
                }
                if (tvContadorProveedores != null) {
                    tvContadorProveedores.setText(String.valueOf(totalProveedores));
                }
            });
        });
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
        Intent intent = new Intent(this, frmListarProveedores.class);
        startActivity(intent);
    }

    private void abrirDespacho() {
        Intent intent = new Intent(this, frmListarDespacho.class);
        startActivity(intent);
    }
}