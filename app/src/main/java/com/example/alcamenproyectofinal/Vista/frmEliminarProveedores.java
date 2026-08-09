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
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmEliminarProveedores extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnDespacho, btnRegresar;

    EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_eliminar_proveedores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtEliminarProveedor);

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    public void btnEliminarProveedor(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        String codigoAEliminar = codigo.getText().toString();

        Proveedor proveedorEncontrado = db.proveedorDao().obtenerPorId(codigoAEliminar);

        if (proveedorEncontrado != null) {
            db.proveedorDao().eliminarProveedor(proveedorEncontrado);
            Toast.makeText(this, "Proveedor eliminado con éxito", Toast.LENGTH_LONG).show();

            codigo.setText("");
        } else {
            Toast.makeText(this, "El código no existe en la base de datos", Toast.LENGTH_LONG).show();
        }

    }

    private void configurarEventos() {
        if (btnSolicitudes != null) btnSolicitudes.setOnClickListener(v -> abrirSolicitudes());
        if (btnDespacho != null) btnDespacho.setOnClickListener(v -> abrirDespacho());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirSolicitudes() {
        Intent intent = new Intent(this, frmListarSolicitudes.class);
        startActivity(intent);
    }

    private void abrirDespacho() {
        Intent intent = new Intent(this, frmListarDespacho.class);
        startActivity(intent);
    }

    public void btnRegresar(View view){
        finish();
    }
}