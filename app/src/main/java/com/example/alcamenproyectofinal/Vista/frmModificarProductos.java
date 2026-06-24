package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
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
import com.example.alcamenproyectofinal.R;

public class frmModificarProductos extends AppCompatActivity {

    EditText codigo, nombre, descripcion, ubicacion, codigo_proveedor;

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

        codigo = findViewById(R.id.txtCodigoProductoMod);
        nombre = findViewById(R.id.txtNombreProductoMod);
        descripcion = findViewById(R.id.txtDescripcionProductoMod);
        ubicacion = findViewById(R.id.txtUbicacionProductoMod);
        codigo_proveedor = findViewById(R.id.txtCodigoProveedorProductoMod);
    }

    public void btnModificarProducto(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

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