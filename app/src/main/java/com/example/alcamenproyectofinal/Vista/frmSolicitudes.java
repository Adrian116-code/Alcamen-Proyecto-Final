package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmSolicitudes extends AppCompatActivity {

    private MaterialButton btnIngresoSalida, btnSolicitudes, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_solicitudes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnIngresoSalida = findViewById(R.id.btnIngresoSalida);
        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    public void btnInsertarSolicitudes(View view){
        Intent x = new Intent(this, frmInsertarSolicitudes.class);
        startActivity(x);
    }

    public void btnEliminarSolicitudes(View view){
        Intent x = new Intent(this, frmEliminarSolicitudes.class);
        startActivity(x);
    }

    private void configurarEventos() {
        if (btnIngresoSalida != null) {
            btnIngresoSalida.setOnClickListener(v ->
                    startActivity(new Intent(this, frmCantidadProductos.class)));
        }

        if (btnSolicitudes != null) {
            btnSolicitudes.setOnClickListener(v ->
                    startActivity(new Intent(this, frmInsertarSolicitudes.class)));
        }

        if (btnRegresar != null) {
            btnRegresar.setOnClickListener(v -> finish());
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}