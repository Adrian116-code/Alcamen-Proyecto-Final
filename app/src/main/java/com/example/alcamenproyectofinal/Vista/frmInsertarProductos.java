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

public class frmInsertarProductos extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;

    EditText codigo, nombre, descripcion, ubicacion;

    AutoCompleteTextView codigo_proveedor;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_insertar_productos);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        codigo = findViewById(R.id.txtCodigoProducto);
        nombre = findViewById(R.id.txtNombre);
        descripcion = findViewById(R.id.txtDescripcion);
        ubicacion = findViewById(R.id.txtUbicacion);
        codigo_proveedor = findViewById(R.id.autoCompleteProveedor);

        cargarProveedoresEnDropdown();

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

    public void btnInsertarProducto(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodigo_producto(codigo.getText().toString());
        nuevoProducto.setNombre(nombre.getText().toString());
        nuevoProducto.setDescripcion(descripcion.getText().toString());
        nuevoProducto.setUbicacion(ubicacion.getText().toString());
        nuevoProducto.setCodigo_proveedor(codigo_proveedor.getText().toString());
        nuevoProducto.setStock(0);
        db.productoDao().insertarProducto(nuevoProducto);
        Toast.makeText(this, "Producto guardado en Room", Toast.LENGTH_LONG).show();
    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirUsuarios() {
        startActivity(new Intent(this, frmGestionUsuarios.class));
    }

    private void abrirProductos() {
        startActivity(new Intent(this, frmGestionProductos.class));
    }

    private void abrirSedes() {
        startActivity(new Intent(this, frmGestionSedes.class));
    }

    public void btnRegresar(View view){
        finish();
    }
}