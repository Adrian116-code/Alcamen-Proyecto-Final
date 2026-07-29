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

        // 1. Inicializar Room
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // 2. Vincular vistas del XML
        codigo = findViewById(R.id.txtCodigoProductoMod);
        nombre = findViewById(R.id.txtNombreProductoMod);
        descripcion = findViewById(R.id.txtDescripcionProductoMod);
        ubicacion = findViewById(R.id.txtUbicacionProductoMod);
        codigo_proveedor = findViewById(R.id.txtCodigoProveedorProductoMod);

        // 3. Cargar opciones del desplegable de proveedores
        cargarProveedoresEnDropdown();

        // 4. Recibir el Intent con la clave primaria y autocompletar
        if (getIntent().hasExtra("CODIGO_PRODUCTO")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_PRODUCTO");
            cargarDatosProducto(codigoRecibido);
        }
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
            // Buscamos el producto en Room mediante su DAO
            Producto productoEncontrado = db.productoDao().obtenerPorId(codigoProducto);

            if (productoEncontrado != null) {
                // Rellenar automáticamente las vistas
                codigo.setText(productoEncontrado.getCodigo_producto());
                nombre.setText(productoEncontrado.getNombre());
                descripcion.setText(productoEncontrado.getDescripcion());
                ubicacion.setText(productoEncontrado.getUbicacion());

                // Rellenar el AutoCompleteTextView sin desplegar automáticamente la lista
                codigo_proveedor.setText(productoEncontrado.getCodigo_proveedor(), false);

                // Bloquear la clave primaria
                codigo.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnModificarProducto(View view) {
        try {
            Producto productoEditado = new Producto();

            // Mantenemos la clave primaria y tomamos los valores modificados
            productoEditado.setCodigo_producto(codigo.getText().toString());
            productoEditado.setNombre(nombre.getText().toString());
            productoEditado.setDescripcion(descripcion.getText().toString());
            productoEditado.setUbicacion(ubicacion.getText().toString());
            productoEditado.setCodigo_proveedor(codigo_proveedor.getText().toString());

            // Si tienes el campo 'stock' en la entidad, es buena práctica volverlo a pasar si no lo editas aquí
            // productoEditado.setStock(stockActual);

            db.productoDao().actualizarProducto(productoEditado);

            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_LONG).show();
            finish(); // Cerramos la pantalla para regresar al listado

        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRegresar(View view) {
        finish();
    }
}