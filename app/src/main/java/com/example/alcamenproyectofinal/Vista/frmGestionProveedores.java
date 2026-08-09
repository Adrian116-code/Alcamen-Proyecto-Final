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

public class frmGestionProveedores extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnDespacho, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_proveedores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    public void btnInsertarProveedores(View view){
        Intent x = new Intent(this, frmInsertarProveedores.class);
        startActivity(x);
    }

    public void btnListarProveedores(View view){
        Intent x = new Intent(this, frmListarProveedores.class);
        startActivity(x);
    }

    public void btnModificarProveedores(View view){
        Intent x = new Intent(this, frmModificarProveedores.class);
        startActivity(x);
    }

    public void btnEliminarProveedores(View view){
        Intent x = new Intent(this, frmEliminarProveedores.class);
        startActivity(x);
    }

    private void configurarEventos() {
        if (btnSolicitudes != null) btnSolicitudes.setOnClickListener(v -> abrirSolicitudes());
        if (btnDespacho != null) btnDespacho.setOnClickListener(v -> abrirDespacho());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirSolicitudes() {
        Intent intent = new Intent(this, frmListarSolicitudes.class);
        startActivity(intent);
    }

    private void abrirDespacho() {
        Intent intent = new Intent(this, frmListarDespacho.class);
        startActivity(intent);
    }

    public void btnRegresar(View view){
        finish();
    }
}