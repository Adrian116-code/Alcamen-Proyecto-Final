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

public class frmGestionSedes extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_sedes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    public void btnInsertarSedes(View view){
        Intent x = new Intent(this, frmInsertarSedes.class);
        startActivity(x);
    }

    public void btnListarSedes(View view){
        Intent x = new Intent(this, frmListarSedes.class);
        startActivity(x);
    }

    public void btnModificarSedes(View view){
        Intent x = new Intent(this,frmModificarSedes.class);
        startActivity(x);
    }

    public void btnEliminarSedes(View view){
        Intent x = new Intent(this, frmEliminarSedes.class);
        startActivity(x);
    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirUsuarios() {
        startActivity(new Intent(this, frmGestionUsuarios.class));
    }

    private void abrirProductos() {
        startActivity(new Intent(this, frmGestionProductos.class));
    }

    public void btnRegresar(View view){
        finish();
    }
}