package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmModificarProveedores extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnProveedores, btnDespacho, btnRegresar;
    private EditText codigo, razon_social, contacto, telefono;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_proveedores);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        codigo = findViewById(R.id.txtCodigoProveedorMod);
        razon_social = findViewById(R.id.txtRazonSocialMod);
        contacto = findViewById(R.id.txtContactoMod);
        telefono = findViewById(R.id.txtNumeroMod);

        if (getIntent().hasExtra("CODIGO_PROVEEDOR")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_PROVEEDOR");
            cargarDatosProveedor(codigoRecibido);
        }

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    private void cargarDatosProveedor(String codigoProveedor) {
        try {
            Proveedor proveedorEncontrado = db.proveedorDao().obtenerPorId(codigoProveedor);

            if (proveedorEncontrado != null) {
                codigo.setText(proveedorEncontrado.getCodigo_proveedor());
                razon_social.setText(proveedorEncontrado.getRazon_social());
                contacto.setText(proveedorEncontrado.getContacto());

                telefono.setText(String.valueOf(proveedorEncontrado.getTelefono()));

                codigo.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar datos del proveedor: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnModificarProveedor(View view) {
        try {
            Proveedor proveedorEditado = new Proveedor();

            proveedorEditado.setCodigo_proveedor(codigo.getText().toString());
            proveedorEditado.setRazon_social(razon_social.getText().toString());
            proveedorEditado.setContacto(contacto.getText().toString());

            String telTexto = telefono.getText().toString().trim();
            if (!telTexto.isEmpty()) {
                proveedorEditado.setTelefono(Integer.parseInt(telTexto));
            } else {
                proveedorEditado.setTelefono(0);
            }

            db.proveedorDao().actualizarProveedor(proveedorEditado);

            Toast.makeText(this, "Proveedor actualizado correctamente", Toast.LENGTH_LONG).show();
            finish(); // Cierra la pantalla para retornar al listado

        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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