package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alcamenproyectofinal.Adaptador.ProductoAdapter;
import com.example.alcamenproyectofinal.Adaptador.ProductoOperarioAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.alcamenproyectofinal.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class frmOperarioAlmacen extends AppCompatActivity {

    private MaterialButton btnIngresoSalida, btnSolicitudes, btnRegresar;
    private RecyclerView rvProductos;
    private TextView tvSinProductos;
    private ProductoOperarioAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_operario_almacen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnIngresoSalida = findViewById(R.id.btnIngresoSalida);
        btnSolicitudes = findViewById(R.id.btnSolicitudes);
        btnRegresar = findViewById(R.id.btnRegresar);

        rvProductos = findViewById(R.id.rvProductos);
        tvSinProductos = findViewById(R.id.tvSinProductos);

        if (rvProductos != null) {
            rvProductos.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ProductoOperarioAdapter(new ArrayList<>());
            rvProductos.setAdapter(adapter);
        }

        db = AppDatabase.getDatabase(this);

        configurarEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarListaProductos();
    }

    private void cargarListaProductos() {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (db == null || db.productoDao() == null) return;

            List<Producto> listaProductos = db.productoDao().obtenerProductos();

            runOnUiThread(() -> {
                if (listaProductos != null && !listaProductos.isEmpty()) {
                    adapter.setProductos(listaProductos);
                    if (tvSinProductos != null) tvSinProductos.setVisibility(View.GONE);
                    if (rvProductos != null) rvProductos.setVisibility(View.VISIBLE);
                } else {
                    adapter.setProductos(new ArrayList<>());
                    if (tvSinProductos != null) tvSinProductos.setVisibility(View.VISIBLE);
                    if (rvProductos != null) rvProductos.setVisibility(View.GONE);
                }
            });
        });
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
}