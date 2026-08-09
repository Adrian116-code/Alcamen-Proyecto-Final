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
import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class frmInsertarSolicitudes extends AppCompatActivity {

    private MaterialButton btnIngresoSalida, btnRegresar;

    EditText codigo, fecha;

    AutoCompleteTextView codigo_producto, codigo_operario;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_insertar_solicitudes);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtCodigoSolicitud);
        codigo_producto = findViewById(R.id.txtCodigoProductoSolicitud);
        codigo_operario = findViewById(R.id.txtCodigoOperarioSolicitud);
        fecha = findViewById(R.id.txtFecha);

        cargarDropdownProductos();
        cargarDropdownOperarios();

        btnIngresoSalida = findViewById(R.id.btnIngresoSalida);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    private void cargarDropdownProductos() {
        try {
            List<String> listaProductos = db.productoDao().obtenerCodigosDeProductos();
            if (listaProductos == null || listaProductos.isEmpty()) {
                listaProductos = new ArrayList<>();
                listaProductos.add("No hay productos");
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaProductos);
            codigo_producto.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error productos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarDropdownOperarios() {
        try {
            List<String> listaOperarios = db.usuarioDao().obtenerCodigosDeOperarios();
            if (listaOperarios == null || listaOperarios.isEmpty()) {
                listaOperarios = new ArrayList<>();
                listaOperarios.add("No hay operarios");
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaOperarios);
            codigo_operario.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error operarios: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnInsertarSolicitud(View view){
        String prodSeleccionado = codigo_producto.getText().toString();
        String operSeleccionado = codigo_operario.getText().toString();

        if (prodSeleccionado.isEmpty() || prodSeleccionado.equals("No hay productos") ||
                operSeleccionado.isEmpty() || operSeleccionado.equals("No hay operarios")) {
            Toast.makeText(this, "Por favor seleccione un producto y operario de las listas.", Toast.LENGTH_SHORT).show();
            return;
        }

        Solicitud nuevaSolicitud = new Solicitud();
        nuevaSolicitud.setCodigo_solicitud(codigo.getText().toString());
        nuevaSolicitud.setCodigo_producto(prodSeleccionado);
        nuevaSolicitud.setCodigo_operario(operSeleccionado);
        nuevaSolicitud.setFecha(fecha.getText().toString());
        nuevaSolicitud.setEstado("pendiente");

        db.solicitudDao().insertarSolicitud(nuevaSolicitud);

        Toast.makeText(this, "Solicitud guardada en Room", Toast.LENGTH_LONG).show();
        finish();
    }

    private void configurarEventos() {

        if (btnIngresoSalida != null) {
            btnIngresoSalida.setOnClickListener(v ->
                    startActivity(new Intent(this, frmCantidadProductos.class)));
        }

        if (btnRegresar != null) {
            btnRegresar.setOnClickListener(v -> finish());
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}