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

public class frmIngresoProductos extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnRegresar;

    AutoCompleteTextView producto_ingreso;
    EditText cantidad_ingreso;
    AppDatabase db;
    List<Producto> listaProductosGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_ingreso_productos);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        producto_ingreso = findViewById(R.id.txtProductoIngreso);
        cantidad_ingreso = findViewById(R.id.txtCantidadIngreso);

        cargarProductosEnDropdown();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    private void cargarProductosEnDropdown() {
        try {
            listaProductosGlobal = db.productoDao().obtenerProductos();
            List<String> nombresProductos = new ArrayList<>();

            for (Producto p : listaProductosGlobal) {
                nombresProductos.add(p.getNombre() + " (" + p.getCodigo_producto() + ")");
            }

            if (nombresProductos.isEmpty()) {
                nombresProductos.add("No hay productos en almacén");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, nombresProductos);
            producto_ingreso.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar productos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnAumentarStock(View view){

        String seleccion = producto_ingreso.getText().toString();
        String cantidadStr = cantidad_ingreso.getText().toString();

        if (seleccion.isEmpty() || seleccion.equals("No hay productos en almacén") || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        int cantidadAAumentar = Integer.parseInt(cantidadStr);
        String codigoProductoSeleccionado = "";

        for (Producto p : listaProductosGlobal) {
            String formatoVisual = p.getNombre() + " (" + p.getCodigo_producto() + ")";
            if (formatoVisual.equals(seleccion)) {
                codigoProductoSeleccionado = p.getCodigo_producto();
                break;
            }
        }

        try {
            db.productoDao().actualizarStock(codigoProductoSeleccionado, cantidadAAumentar);
            Toast.makeText(this, "Stock actualizado correctamente", Toast.LENGTH_LONG).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void configurarEventos() {

        if (btnSolicitudes != null) {
            btnSolicitudes.setOnClickListener(v ->
                    startActivity(new Intent(this, frmInsertarSolicitudes.class)));
        }

        if (btnRegresar != null) {
            btnRegresar.setOnClickListener(v -> finish());
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}