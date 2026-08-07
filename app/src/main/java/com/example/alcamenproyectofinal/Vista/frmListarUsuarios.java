package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
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
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class frmListarUsuarios extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnSedes, btnRegresar;

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

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));

        cargarListaUsuarios();

        configurarEventos();
    }

    private void cargarListaUsuarios() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Usuario> lista = db.usuarioDao().obtenerUsuarios();

        UsuarioAdapter adapter = new UsuarioAdapter(lista);
        rvUsuarios.setAdapter(adapter);
    }

    private void configurarEventos() {
        if (btnUsuarios != null) btnUsuarios.setOnClickListener(v -> abrirUsuarios());
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    public void btnInsertarUsuarios(View view){
        Intent x = new Intent(this, frmInsertarUsuarios.class);
        startActivity(x);
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
