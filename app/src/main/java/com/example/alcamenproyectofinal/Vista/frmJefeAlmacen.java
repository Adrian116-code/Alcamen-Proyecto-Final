package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.R;

public class frmJefeAlmacen extends AppCompatActivity {

    private Button btnGestionarProductos, btnGestionarSolicitudes, btnGestionarProveedores, btnListarDespacho, btnRegresarCentral;
    private ImageButton btnProductos, btnSolicitudes, btnProveedores, btnDespacho, btnRegresar;

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

        btnGestionarProductos = findViewById(R.id.btnGestionarProductos);
        btnGestionarSolicitudes = findViewById(R.id.btnGestionarSolicitudes);
        btnGestionarProveedores = findViewById(R.id.btnGestionarProveedores);
        btnListarDespacho = findViewById(R.id.btnListarDespacho);
        btnRegresarCentral = findViewById(R.id.btnRegresarCentral);

        btnProductos = findViewById(R.id.btnProductos);
        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    private void configurarEventos() {
        btnGestionarProductos.setOnClickListener(v -> abrirProductos());
        btnProductos.setOnClickListener(v -> abrirProductos());

        btnGestionarSolicitudes.setOnClickListener(v -> abrirSolicitudes());
        btnSolicitudes.setOnClickListener(v -> abrirSolicitudes());

        btnGestionarProveedores.setOnClickListener(v -> abrirProveedores());
        btnProveedores.setOnClickListener(v -> abrirProveedores());

        btnListarDespacho.setOnClickListener(v -> abrirDespacho());
        btnDespacho.setOnClickListener(v -> abrirDespacho());

        btnRegresarCentral.setOnClickListener(v -> finish());
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirProductos() {
        Toast.makeText(this, "Navegando a Productos", Toast.LENGTH_SHORT).show();
    }

    private void abrirSolicitudes() {
        Toast.makeText(this, "Navegando a Solicitudes", Toast.LENGTH_SHORT).show();
    }

    private void abrirProveedores() {
        Toast.makeText(this, "Navegando a Proveedores", Toast.LENGTH_SHORT).show();
    }

    private void abrirDespacho() {
        Toast.makeText(this, "Navegando a Despacho", Toast.LENGTH_SHORT).show();
    }

    public void btnGestionarProductos(View view){
        Intent x = new Intent(this, frmGestionProductos.class);
        startActivity(x);
    }

    public void btnGestionarSolicitudes(View view){
        Intent x = new Intent(this, frmGestionSolicitudes.class);
        startActivity(x);
    }

    public void btnGestionarProveedores(View view){
        Intent x = new Intent(this, frmGestionProveedores.class);
        startActivity(x);
    }

    public void btnListarDespacho(View view){
        Intent x = new Intent(this,frmListarDespacho.class);
        startActivity(x);

    }

    public void btnRegresar(View view){
        finish();
    }
}