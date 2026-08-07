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
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmEliminarProductos extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;
    EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_eliminar_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        codigo = findViewById(R.id.txtCodigoProductoEliminar);

        configurarEventos();
    }

    public void btnEliminarProducto(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        String codigoAEliminar = codigo.getText().toString();

        Producto productoEncontrado = db.productoDao().obtenerPorId(codigoAEliminar);

        if (productoEncontrado != null) {
            db.productoDao().eliminarProducto(productoEncontrado);
                Toast.makeText(this, "Producto eliminado con éxito", Toast.LENGTH_LONG).show();

            codigo.setText("");
        } else {
            Toast.makeText(this, "El código no existe en la base de datos", Toast.LENGTH_LONG).show();
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

    public void btnRegresar(View view){
        finish();
    }
}