package com.example.alcamenproyectofinal.Vista;

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

public class frmModificarSedes extends AppCompatActivity {

    EditText codigo, nombre, direccion;

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

        codigo = findViewById(R.id.txtCodigoSedeMod);
        nombre = findViewById(R.id.txtNombreSedeMod);
        direccion = findViewById(R.id.txtDireccionSedeMod);
    }

    public void btnModificarSede(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        Sede sedeEditado = new Sede();

        sedeEditado.setCodigo_sede((codigo.getText().toString()));
        sedeEditado.setNombre(nombre.getText().toString());
        sedeEditado.setDireccion(direccion.getText().toString());

        db.sedeDao().actualizarSede(sedeEditado);

        Toast.makeText(this, "Sede actualizado correctamente", Toast.LENGTH_LONG).show();

    }

    public void btnRegresar(View view){
        finish();
    }

}