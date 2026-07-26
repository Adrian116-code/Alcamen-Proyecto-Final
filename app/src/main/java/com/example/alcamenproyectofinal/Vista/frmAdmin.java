package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class frmAdmin extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_admin);

        // Ajuste de insets (evita el crash asignando listener solo si la vista existe)
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 2. Enlazamos los componentes por su ID en el XML
        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        // 3. Asignamos los eventos de clic
        configurarEventos();
    }

    private void configurarEventos() {
        if (btnUsuarios != null) {
            btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        }

        if (btnProductos != null) {
            btnProductos.setOnClickListener(v -> abrirProductos());
        }

        if (btnSedes != null) {
            btnSedes.setOnClickListener(v -> abrirSedes());
        }

        if (btnRegresar != null) {
            btnRegresar.setOnClickListener(v -> finish());
        }
    }

    private void abrirUsuarios() {
        Intent x = new Intent(this, frmGestionUsuarios.class);
        startActivity(x);
    }

    private void abrirProductos() {
        Intent x = new Intent(this, frmGestionProductos.class);
        startActivity(x);
    }

    private void abrirSedes() {
        Intent x = new Intent(this, frmGestionSedes.class);
        startActivity(x);
    }

    public void btnGestionUsuarios(View view){
        Intent x = new Intent(this, frmGestionUsuarios.class);
        startActivity(x);
    }

    public void btnGestionProductos(View view){
        Intent x = new Intent(this, frmGestionProductos.class);
        startActivity(x);
    }

    public void btnGestionarSedes(View view){
        Intent x = new Intent(this, frmGestionSedes.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}