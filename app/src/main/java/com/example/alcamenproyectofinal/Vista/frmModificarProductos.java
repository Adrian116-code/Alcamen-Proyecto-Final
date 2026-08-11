package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class frmModificarProductos extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnSedes, btnRegresar;
    private EditText codigo, nombre, descripcion, ubicacion;
    private AutoCompleteTextView codigo_proveedor;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_productos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        codigo = findViewById(R.id.txtCodigoProductoMod);
        nombre = findViewById(R.id.txtNombreProductoMod);
        descripcion = findViewById(R.id.txtDescripcionProductoMod);
        ubicacion = findViewById(R.id.txtUbicacionProductoMod);
        codigo_proveedor = findViewById(R.id.txtCodigoProveedorProductoMod);

        cargarProveedoresEnDropdown();

        if (getIntent().hasExtra("CODIGO_PRODUCTO")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_PRODUCTO");
            cargarDatosProducto(codigoRecibido);
        }

        configurarEventos();
    }

    private void cargarProveedoresEnDropdown() {
        try {
            List<String> listaCodigos = db.proveedorDao().obtenerCodigosDeProveedores();

            if (listaCodigos == null || listaCodigos.isEmpty()) {
                listaCodigos = new ArrayList<>();
                listaCodigos.add("No hay proveedores disponibles");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_dropdown_item_1line,
                    listaCodigos
            );

            codigo_proveedor.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar proveedores: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarDatosProducto(String codigoProducto) {
        try {
            Producto productoEncontrado = db.productoDao().obtenerPorId(codigoProducto);

            if (productoEncontrado != null) {

                codigo.setText(productoEncontrado.getCodigo_producto());
                nombre.setText(productoEncontrado.getNombre());
                descripcion.setText(productoEncontrado.getDescripcion());
                ubicacion.setText(productoEncontrado.getUbicacion());

                codigo_proveedor.setText(productoEncontrado.getCodigo_proveedor(), false);

                codigo.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnModificarProducto(View view) {
        try {
            Producto productoEditado = new Producto();

            productoEditado.setCodigo_producto(codigo.getText().toString());
            productoEditado.setNombre(nombre.getText().toString());
            productoEditado.setDescripcion(descripcion.getText().toString());
            productoEditado.setUbicacion(ubicacion.getText().toString());
            productoEditado.setCodigo_proveedor(codigo_proveedor.getText().toString());

            db.productoDao().actualizarProducto(productoEditado);

            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_LONG).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirUsuarios() {
        startActivity(new Intent(this, frmListarUsuarios.class));
    }

    private void abrirSedes() {
        startActivity(new Intent(this, frmListarSedes.class));
    }

    public void btnRegresar(View view) {
        finish();
    }
}