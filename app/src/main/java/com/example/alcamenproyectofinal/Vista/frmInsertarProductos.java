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

public class frmInsertarProductos extends AppCompatActivity {

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

        codigo = findViewById(R.id.txtCodigoProducto);
        nombre = findViewById(R.id.txtNombre);
        descripcion = findViewById(R.id.txtDescripcion);
        ubicacion = findViewById(R.id.txtUbicacion);
        codigo_proveedor = findViewById(R.id.autoCompleteProveedor);

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

    public void btnRegresar(View view){
        finish();
    }
}