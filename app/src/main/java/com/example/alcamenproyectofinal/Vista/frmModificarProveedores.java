package com.example.alcamenproyectofinal.Vista;

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

public class frmModificarProveedores extends AppCompatActivity {

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

        // 1. Inicializar Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // 2. Mapear vistas del XML
        codigo = findViewById(R.id.txtCodigoProveedorMod);
        razon_social = findViewById(R.id.txtRazonSocialMod);
        contacto = findViewById(R.id.txtContactoMod);
        telefono = findViewById(R.id.txtNumeroMod);

        // 3. Recibir la clave primaria mediante el Intent y autocompletar
        if (getIntent().hasExtra("CODIGO_PROVEEDOR")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_PROVEEDOR");
            cargarDatosProveedor(codigoRecibido);
        }
    }

    private void cargarDatosProveedor(String codigoProveedor) {
        try {
            // Buscamos el proveedor en Room mediante su DAO
            Proveedor proveedorEncontrado = db.proveedorDao().obtenerPorId(codigoProveedor);

            if (proveedorEncontrado != null) {
                // Rellenar automáticamente los campos
                codigo.setText(proveedorEncontrado.getCodigo_proveedor());
                razon_social.setText(proveedorEncontrado.getRazon_social());
                contacto.setText(proveedorEncontrado.getContacto());

                // Convertir el entero del teléfono a String para mostrarlo en el EditText
                telefono.setText(String.valueOf(proveedorEncontrado.getTelefono()));

                // Bloquear la clave primaria para proteger la integridad en Room
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

            // Validación rápida para evitar un NumberFormatException si el teléfono está vacío
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

    public void btnRegresar(View view) {
        finish();
    }
}