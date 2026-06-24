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

public class frmSalidaProductos extends AppCompatActivity {

    AutoCompleteTextView producto_salida;
    EditText cantidad_salida;
    AppDatabase db;
    List<Producto> listaProductosGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_salida_productos);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        producto_salida = findViewById(R.id.txtProductoSalida);
        cantidad_salida = findViewById(R.id.txtCantidadSalida);

        cargarProductosEnDropdown();
    }

    private void cargarProductosEnDropdown() {
        try {
            listaProductosGlobal = db.productoDao().obtenerProductos();
            List<String> nombresProductos = new ArrayList<>();

            for (Producto p : listaProductosGlobal) {
                nombresProductos.add(p.getNombre() + " (" + p.getCodigo_producto() + ")");
            }

            if (nombresProductos.isEmpty()) {
                nombresProductos.add("No hay productos disponibles");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, nombresProductos);
            producto_salida.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar productos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnReducirStock(View view){
        String seleccion = producto_salida.getText().toString();
        String cantidadStr = cantidad_salida.getText().toString();

        if (seleccion.isEmpty() || seleccion.equals("No hay productos disponibles") || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        int cantidadAReducir = Integer.parseInt(cantidadStr);
        String codigoProductoReal = "";
        int stockActual = 0;

        for (Producto p : listaProductosGlobal) {
            String formatoVisual = p.getNombre() + " (" + p.getCodigo_producto() + ")";
            if (formatoVisual.equals(seleccion)) {
                codigoProductoReal = p.getCodigo_producto();
                stockActual = p.getStock();
                break;
            }
        }

        if (stockActual < cantidadAReducir) {
            Toast.makeText(this, "Operación inválida. Stock insuficiente (Actual: " + stockActual + ")", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            db.productoDao().reducirStock(codigoProductoReal, cantidadAReducir);

            Toast.makeText(this, "Stock reducido correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error al procesar la salida: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}