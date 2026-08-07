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
import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

public class frmEliminarSolicitudes extends AppCompatActivity {

    private MaterialButton btnIngresoSalida, btnSolicitudes, btnRegresar;

    EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_eliminar_solicitudes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtCodigoSolicitudEliminar);

        btnIngresoSalida = findViewById(R.id.btnIngresoSalida);
        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    public void btnEliminarSolicitud(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        String codigoAEliminar = codigo.getText().toString();

        Solicitud solicitudEncontrado = db.solicitudDao().obtenerPorId(codigoAEliminar);

        if (solicitudEncontrado != null) {
            db.solicitudDao().eliminarSolicitud(solicitudEncontrado);
            Toast.makeText(this, "Solicitud eliminado con éxito", Toast.LENGTH_LONG).show();

            codigo.setText("");
        } else {
            Toast.makeText(this, "El código no existe en la base de datos", Toast.LENGTH_LONG).show();
        }

    }

    private void configurarEventos() {
        if (btnIngresoSalida != null) {
            btnIngresoSalida.setOnClickListener(v ->
                    startActivity(new Intent(this, frmCantidadProductos.class)));
        }

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
