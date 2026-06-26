package com.example.alcamenproyectofinal.Vista;

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

import java.util.ArrayList;
import java.util.List;

public class frmModificarProductos extends AppCompatActivity {

    EditText codigo, nombre, descripcion, ubicacion;

    AutoCompleteTextView codigo_proveedor;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_productos);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtCodigoProductoMod);
        nombre = findViewById(R.id.txtNombreProductoMod);
        descripcion = findViewById(R.id.txtDescripcionProductoMod);
        ubicacion = findViewById(R.id.txtUbicacionProductoMod);

        // 2. Mapeamos el ID correcto de tu XML para el AutoCompleteTextView
        codigo_proveedor = findViewById(R.id.txtCodigoProveedorProductoMod);

        // 3. Cargamos los proveedores disponibles en el dropdown
        cargarProveedoresEnDropdown();
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

    public void btnModificarProducto(View view){
        Producto productoEditado = new Producto();

        productoEditado.setCodigo_producto(codigo.getText().toString());
        productoEditado.setNombre(nombre.getText().toString());
        productoEditado.setDescripcion(descripcion.getText().toString());
        productoEditado.setUbicacion(ubicacion.getText().toString());

        productoEditado.setCodigo_proveedor(codigo_proveedor.getText().toString());

        db.productoDao().actualizarProducto(productoEditado);

        Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_LONG).show();
    }

    public void btnRegresar(View view){
        finish();
    }
}