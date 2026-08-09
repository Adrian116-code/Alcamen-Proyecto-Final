package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
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
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmEliminarUsuarios extends AppCompatActivity {

    private MaterialButton btnProductos, btnSedes, btnRegresar;
    EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_eliminar_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        codigo = findViewById(R.id.txtEliminarUsuario);

        configurarEventos();
    }

    public void btnEliminarUsuario(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        String codigoAEliminar = codigo.getText().toString();

        Usuario usuarioEncontrado = db.usuarioDao().obtenerPorId(codigoAEliminar);

        if (usuarioEncontrado != null) {
            db.usuarioDao().eliminarUsuario(usuarioEncontrado);
            Toast.makeText(this, "Usuario eliminado con éxito", Toast.LENGTH_LONG).show();

            codigo.setText("");
        } else {
            Toast.makeText(this, "El código no existe en la base de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void configurarEventos() {
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
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