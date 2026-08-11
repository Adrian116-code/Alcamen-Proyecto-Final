package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

public class frmListarUsuarios extends AppCompatActivity {

    private MaterialButton btnProductos, btnSedes, btnRegresar;

    private RecyclerView rvUsuarios;

    private AppDatabase db;
    private UsuarioAdapter adapter;
    private List<Usuario> listaUsuarios = new ArrayList<>();

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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));

        cargarListaUsuarios();

        configurarEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarListaUsuarios();
    }

    public void onEditarClick(Usuario usuario, int position) {
        Intent intent = new Intent(frmListarUsuarios.this, frmModificarUsuarios.class);
        intent.putExtra("CODIGO_USUARIO", usuario.getCodigo_usuario());
        startActivity(intent);
    }

    private void cargarListaUsuarios() {
        try {
            listaUsuarios = db.usuarioDao().obtenerUsuarios();

            adapter = new UsuarioAdapter(listaUsuarios, new UsuarioAdapter.OnItemClickListener() {
                @Override
                public void onEditarClick(Usuario usuario, int position) {
                    Intent intent = new Intent(frmListarUsuarios.this, frmModificarUsuarios.class);
                    intent.putExtra("CODIGO_USUARIO", usuario.getCodigo_usuario());
                    startActivity(intent);
                }
                @Override
                public void onEliminarClick(Usuario usuario, int position) {
                    confirmarEliminacion(usuario, position);
                }
            });

            rvUsuarios.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar usuarios: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmarEliminacion(Usuario usuario, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Usuario")
                .setMessage("¿Estás seguro de eliminar el usuario " + usuario.getNombres() + " " + usuario.getApellidos() + "?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    try {
                        db.usuarioDao().eliminarUsuario(usuario);
                        Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();

                        listaUsuarios.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, listaUsuarios.size());

                    } catch (Exception e) {
                        Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void configurarEventos() {
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    public void btnInsertarUsuarios(View view){
        Intent x = new Intent(this, frmInsertarUsuarios.class);
        startActivity(x);
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
