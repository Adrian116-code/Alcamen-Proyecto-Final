package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.alcamenproyectofinal.Adaptador.ProveedorAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class frmListarProveedores extends AppCompatActivity {

    private MaterialButton btnSolicitudes, btnDespacho, btnRegresar;
    private RecyclerView rvProveedores;
    private AppDatabase db;
    private List<Proveedor> listaProveedores;
    private ProveedorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_proveedores);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        rvProveedores = findViewById(R.id.rvProveedores);
        rvProveedores.setLayoutManager(new LinearLayoutManager(this));

        cargarListaProveedores();

        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnDespacho = findViewById(R.id.btnDespacho);
        btnRegresar = findViewById(R.id.btnRegresar);

        configurarEventos();
    }

    private void cargarListaProveedores() {
        try {
            listaProveedores = db.proveedorDao().obtenerProveedores();

            adapter = new ProveedorAdapter(listaProveedores, new ProveedorAdapter.OnItemClickListener() {
                @Override
                public void onEditarClick(Proveedor proveedor, int position) {
                    Intent intent = new Intent(frmListarProveedores.this, frmModificarProveedores.class);
                    intent.putExtra("CODIGO_PROVEEDOR", proveedor.getCodigo_proveedor());
                    startActivity(intent);
                }
                @Override
                public void onEliminarClick(Proveedor proveedor, int position) {
                    confirmarEliminacion(proveedor, position);
                }
            });

            rvProveedores.setAdapter(adapter);

            if (listaProveedores.isEmpty()) {
                Toast.makeText(this, "No hay proveedores registrados", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar proveedores: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void confirmarEliminacion(Proveedor proveedor, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Proveedor")
                .setMessage("¿Estás seguro de eliminar el proveedor " + proveedor.getRazon_social() + "?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    try {
                        db.proveedorDao().eliminarProveedor(proveedor);
                        Toast.makeText(this, "Proveedor eliminado correctamente", Toast.LENGTH_SHORT).show();

                        listaProveedores.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, listaProveedores.size());

                    } catch (Exception e) {
                        Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void btnInsertarProveedores(View view) {
        Intent intent = new Intent(this, frmInsertarProveedores.class);
        startActivity(intent);
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

    public void btnRegresar(View view) {
        finish();
    }
}