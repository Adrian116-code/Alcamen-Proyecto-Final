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
import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;
import com.example.alcamenproyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class frmSalidaProductos extends AppCompatActivity {

    AutoCompleteTextView producto_salida, sede_destino;
    EditText cantidad_salida;
    AppDatabase db;
    List<Producto> listaProductosGlobal;
    List<String> listaCodigosSedesGlobal;

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
        sede_destino = findViewById(R.id.txtSedeSalida);

        cargarProductosEnDropdown();
        cargarSedesEnDropdown();
    }

    private void cargarProductosEnDropdown() {
        try {
            listaProductosGlobal = db.productoDao().obtenerProductos();
            List<String> nombresProductos = new ArrayList<>();

            if (listaProductosGlobal != null) {
                for (Producto p : listaProductosGlobal) {
                    nombresProductos.add(p.getNombre() + " (" + p.getCodigo_producto() + ")");
                }
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

    private void cargarSedesEnDropdown() {
        try {
            listaCodigosSedesGlobal = db.sedeDao().obtenerCodigosDeSedes();

            if (listaCodigosSedesGlobal == null || listaCodigosSedesGlobal.isEmpty()) {
                listaCodigosSedesGlobal = new ArrayList<>();
                listaCodigosSedesGlobal.add("No hay sedes disponibles");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, listaCodigosSedesGlobal);
            sede_destino.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar sedes: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnReducirStock(View view) {
        String seleccionProducto = producto_salida.getText().toString();
        String seleccionSede = sede_destino.getText().toString();
        String cantidadStr = cantidad_salida.getText().toString().trim();

        // Validaciones básicas de selección
        if (seleccionProducto.isEmpty() || seleccionProducto.equals("No hay productos disponibles") ||
                seleccionSede.isEmpty() || seleccionSede.equals("No hay sedes disponibles") ||
                cantidadStr.isEmpty()) {

            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        // Validación y parseo seguro de la cantidad
        int cantidadAReducir;
        try {
            cantidadAReducir = Integer.parseInt(cantidadStr);
            if (cantidadAReducir <= 0) {
                Toast.makeText(this, "La cantidad debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        String codigoProductoReal = "";
        int stockActual = 0;

        if (listaProductosGlobal != null) {
            for (Producto p : listaProductosGlobal) {
                String formatoVisual = p.getNombre() + " (" + p.getCodigo_producto() + ")";
                if (formatoVisual.equals(seleccionProducto)) {
                    codigoProductoReal = p.getCodigo_producto();
                    stockActual = p.getStock();
                    break;
                }
            }
        }

        if (stockActual < cantidadAReducir) {
            Toast.makeText(this, "Operación inválida. Stock insuficiente (Actual: " + stockActual + ")", Toast.LENGTH_LONG).show();
            return;
        }

        final String finalCodigoProducto = codigoProductoReal;
        final int finalCantidadAReducir = cantidadAReducir;

        try {
            // Transacción para asegurar consistencia en la BD
            db.runInTransaction(() -> {
                db.productoDao().reducirStock(finalCodigoProducto, finalCantidadAReducir);

                Despacho_Sede nuevoDespacho = new Despacho_Sede();
                nuevoDespacho.setCodigo_producto(finalCodigoProducto);
                nuevoDespacho.setCodigo_sede(seleccionSede);
                nuevoDespacho.setCantidad(finalCantidadAReducir);

                db.despachoSedeDao().registrarDespacho(nuevoDespacho);
            });

            Toast.makeText(this, "Salida procesada y registrada con éxito", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "Error al procesar la salida: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // ==================== MÉTODOS DE NAVEGACIÓN (FOOTER / REGRESAR) ====================

    public void btnIngresoSalida(View view) {
        // Pantalla actual (Movimientos)
    }

    public void btnSolicitudes(View view) {
        // Intent a tu pantalla de Solicitudes
        // Intent intent = new Intent(this, frmSolicitudes.class);
        // startActivity(intent);
    }

    public void btnRegresar(View view) {
        finish();
    }
}
