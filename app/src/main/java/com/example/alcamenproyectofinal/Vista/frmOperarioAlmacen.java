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
import com.google.android.material.button.MaterialButton;

public class frmOperarioAlmacen extends AppCompatActivity {

    private Button btnIngresoSalidaProductos, btnInsertarSolicitudes, btnRegresarCentral;
    private MaterialButton btnIngresoSalida, btnSolicitudes, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_operario_almacen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnIngresoSalidaProductos = findViewById(R.id.btnIngresoSalidaProductos);
        btnInsertarSolicitudes = findViewById(R.id.btnInsertarSolicitudes);
        btnRegresarCentral = findViewById(R.id.btnRegresarCentral);

        btnIngresoSalida = findViewById(R.id.btnIngresoSalida);
        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();

    }
    private void configurarEventos() {
        // Eventos para Movimientos / Cantidad de Productos
        btnIngresoSalidaProductos.setOnClickListener(v -> abrirIngresoSalida());
        btnIngresoSalida.setOnClickListener(v -> abrirIngresoSalida());

        // Eventos para Insertar Solicitudes
        btnInsertarSolicitudes.setOnClickListener(v -> abrirInsertarSolicitudes());
        btnSolicitudes.setOnClickListener(v -> abrirInsertarSolicitudes());

        // Eventos para Regresar
        btnRegresarCentral.setOnClickListener(v -> finish());
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirIngresoSalida() {
        Intent x = new Intent(this, frmCantidadProductos.class);
        startActivity(x);
    }

    private void abrirInsertarSolicitudes() {
        Intent x = new Intent(this, frmInsertarSolicitudes.class);
        startActivity(x);
    }

    public void btnCantidadProductos(View view){
        Intent x = new Intent(this, frmCantidadProductos.class);
        startActivity(x);
    }

    public void btnInsertarSolicitudes(View view){
        Intent x = new Intent(this, frmInsertarSolicitudes.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}