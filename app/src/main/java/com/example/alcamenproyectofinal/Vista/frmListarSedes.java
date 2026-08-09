package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
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
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;

import org.w3c.dom.Text;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.SedeAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class frmListarSedes extends AppCompatActivity {

    private MaterialButton btnUsuarios, btnProductos, btnRegresar;

    private RecyclerView rvSedes;
    private AppDatabase db;
    private List<Sede> listaSedes;
    private SedeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_sedes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnProductos = findViewById(R.id.btnProductos);
        btnRegresar = findViewById(R.id.btnRegresar);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        rvSedes = findViewById(R.id.rvSedes);
        rvSedes.setLayoutManager(new LinearLayoutManager(this));

        cargarYMostrarSedes();

        configurarEventos();
    }

    public void btnInsertarSedes(View view) {
        Intent x = new Intent(this, frmInsertarSedes.class);
        startActivity(x);
    }

    private void cargarYMostrarSedes() {
        try {
            listaSedes = db.sedeDao().obtenerSedes();

            adapter = new SedeAdapter(listaSedes, new SedeAdapter.OnItemClickListener() {
                @Override
                public void onEditarClick(Sede sede, int position) {
                    Intent intent = new Intent(frmListarSedes.this, frmModificarSedes.class);
                    intent.putExtra("CODIGO_SEDE", sede.getCodigo_sede());
                    startActivity(intent);
                }

                @Override
                public void onEliminarClick(Sede sede, int position) {
                    confirmarEliminacion(sede, position);
                }
            });

            rvSedes.setAdapter(adapter);

            if (listaSedes.isEmpty()) {
                Toast.makeText(this, "No hay sedes registradas en el sistema", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al refrescar listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void confirmarEliminacion(Sede sede, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Sede")
                .setMessage("¿Estás seguro de eliminar la sede " + sede.getNombre() + "?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    try {
                        db.sedeDao().eliminarSede(sede);
                        Toast.makeText(this, "Sede eliminada correctamente", Toast.LENGTH_SHORT).show();

                        listaSedes.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, listaSedes.size());

                    } catch (Exception e) {
                        Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
    public void btnEditarSolicitud(View view) {
        Intent x = new Intent(this, frmModificarSedes.class);
        startActivity(x);
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

    public void btnRegresar(View view) {
        finish();
    }
}