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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.UsuarioAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class frmListarUsuarios extends AppCompatActivity {

    private RecyclerView rvUsuarios;

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

        // 1. Vincular el RecyclerView del XML y asignarle un Manager lineal
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));

        // 2. Cargar los datos inmediatamente al iniciar la pantalla
        cargarListaUsuarios();
    }

    private void cargarListaUsuarios() {
        // Consulta a Room
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Usuario> lista = db.usuarioDao().obtenerUsuarios();

        // Asignar el adaptador con los datos recuperados
        UsuarioAdapter adapter = new UsuarioAdapter(lista);
        rvUsuarios.setAdapter(adapter);
    }

    public void btnListarUsuarioss(View view) {
        cargarListaUsuarios();
    }

    public void btnRegresar(View view) {
        finish();
    }
}
