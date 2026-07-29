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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Adaptador.ProductoAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarProductos extends AppCompatActivity {

    private RecyclerView rvProductos;
    private AppDatabase db;
    private List<Producto> listaProductos;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_productos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializamos la base de datos una sola vez
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        rvProductos = findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        cargarListaProductos();
    }

    private void cargarListaProductos() {
        try {
            listaProductos = db.productoDao().obtenerProductos();

            adapter = new ProductoAdapter(listaProductos, new ProductoAdapter.OnItemClickListener() {
                @Override
                public void onEditarClick(Producto producto, int position) {
                    Intent intent = new Intent(frmListarProductos.this, frmModificarProductos.class);
                    intent.putExtra("CODIGO_PRODUCTO", producto.getCodigo_producto());
                    startActivity(intent);
                }

                @Override
                public void onEliminarClick(Producto producto, int position) {
                    confirmarEliminacion(producto, position);
                }
            });

            rvProductos.setAdapter(adapter);

            if (listaProductos.isEmpty()) {
                Toast.makeText(this, "No hay productos registrados", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar productos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void confirmarEliminacion(Producto producto, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Producto")
                .setMessage("¿Estás seguro de eliminar el producto " + producto.getNombre() + "?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    try {
                        // Eliminar de la base de datos Room
                        db.productoDao().eliminarProducto(producto);
                        Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show();

                        // Actualizar lista local con animación
                        listaProductos.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, listaProductos.size());

                    } catch (Exception e) {
                        Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void btnListarProducto(View view) {
        cargarListaProductos();
    }

    public void btnRegresar(View view) {
        finish();
    }
}