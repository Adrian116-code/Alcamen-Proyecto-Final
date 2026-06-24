package com.example.alcamenproyectofinal.Vista;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class frmListarUsuarios extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnListarUsuarioss(View view) {

        TextView txtListado = findViewById(R.id.txtListarUsuarios);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Usuario> lista = db.usuarioDao().obtenerUsuarios();

        StringBuilder cadenaUsuarios = new StringBuilder();

        for (Usuario u : lista) {
            cadenaUsuarios.append("Código: ").append(u.getCodigo_usuario()).append("\n")
                    .append("Dni: ").append(u.getDni()).append("\n")
                    .append("Nombre: ").append(u.getNombres()).append("\n")
                    .append("Apellido: ").append(u.getApellidos()).append("\n")
                    .append("Edad: ").append(u.getEdad()).append("\n")
                    .append("Rol: ").append(u.getRol()).append("\n")
                    .append("Username: ").append(u.getUsername()).append("\n")
                    .append("Password: ").append(u.getPassword()).append("\n")
                    .append("\n");
        }

        txtListado.setText(cadenaUsuarios.toString());
    }

    public void btnRegresar(View view){
        finish();
    }
}
