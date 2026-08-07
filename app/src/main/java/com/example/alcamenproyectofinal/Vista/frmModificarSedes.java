package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmModificarSedes extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;
    private EditText codigo, nombre, direccion;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_sedes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        codigo = findViewById(R.id.txtCodigoSedeMod);
        nombre = findViewById(R.id.txtNombreSedeMod);
        direccion = findViewById(R.id.txtDireccionSedeMod);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        if (getIntent().hasExtra("CODIGO_SEDE")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_SEDE");
            cargarDatosSede(codigoRecibido);
        }

        configurarEventos();
    }

    private void cargarDatosSede(String codigoSede) {
        try {
            Sede sedeEncontrada = db.sedeDao().obtenerPorId(codigoSede);

            if (sedeEncontrada != null) {
                codigo.setText(sedeEncontrada.getCodigo_sede());
                nombre.setText(sedeEncontrada.getNombre());
                direccion.setText(sedeEncontrada.getDireccion());

                codigo.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnModificarSede(View view) {
        try {
            Sede sedeEditado = new Sede();

            sedeEditado.setCodigo_sede(codigo.getText().toString());
            sedeEditado.setNombre(nombre.getText().toString());
            sedeEditado.setDireccion(direccion.getText().toString());

            db.sedeDao().actualizarSede(sedeEditado);

            Toast.makeText(this, "Sede actualizada correctamente", Toast.LENGTH_LONG).show();
            finish(); // Cierra la pantalla para volver al listado

        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    public void btnRegresar(View view) {
        finish();
    }
}