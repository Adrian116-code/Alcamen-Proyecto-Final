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

import java.util.List;

public class frmListarProveedores extends AppCompatActivity {

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

        // Inicializar Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // Configurar RecyclerView
        rvProveedores = findViewById(R.id.rvProveedores); // Revisa el ID en tu XML
        rvProveedores.setLayoutManager(new LinearLayoutManager(this));

        // Cargar proveedores al iniciar
        cargarListaProveedores();
    }

    private void cargarListaProveedores() {
        try {
            listaProveedores = db.proveedorDao().obtenerProveedores();

            // Pasamos la lista Y el OnItemClickListener que ahora requiere el adaptador
            adapter = new ProveedorAdapter(listaProveedores, new ProveedorAdapter.OnItemClickListener() {
                @Override
                public void onEditarClick(Proveedor proveedor, int position) {
                    // Clic en el LÁPIZ: Abre la pantalla de modificación enviando la clave primaria
                    Intent intent = new Intent(frmListarProveedores.this, frmModificarProveedores.class);
                    intent.putExtra("CODIGO_PROVEEDOR", proveedor.getCodigo_proveedor());
                    startActivity(intent);
                }

                @Override
                public void onEliminarClick(Proveedor proveedor, int position) {
                    // Clic en la X: Pide confirmación para eliminar de Room
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

                        // Remover de la lista local con animación
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

    public void btnListarProveedor(View view) {
        cargarListaProveedores();
    }

    public void btnRegresar(View view) {
        finish();
    }
}