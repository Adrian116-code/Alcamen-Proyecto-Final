package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
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
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmInsertarSedes extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnRegresar;

    EditText codigo, nombre, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_insertar_sedes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnRegresar = findViewById(R.id.btnRegresar);

        codigo = findViewById(R.id.txtCodigoSede);
        nombre = findViewById(R.id.txtNombreSede);
        direccion = findViewById(R.id.txtDireccionSede);

        configurarEventos();
    }

    public void btnInsertarSede(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        Sede nuevoSede = new Sede();
        nuevoSede.setCodigo_sede(codigo.getText().toString());
        nuevoSede.setNombre(nombre.getText().toString());
        nuevoSede.setDireccion(direccion.getText().toString());
        db.sedeDao().insertarSede(nuevoSede);
        Toast.makeText(this, "Sede guardado en Room", Toast.LENGTH_LONG).show();

    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirUsuarios() {
        startActivity(new Intent(this, frmGestionUsuarios.class));
    }

    private void abrirProductos() {
        startActivity(new Intent(this, frmGestionProductos.class));
    }

    public void btnRegresar(View view){
        finish();
    }
}