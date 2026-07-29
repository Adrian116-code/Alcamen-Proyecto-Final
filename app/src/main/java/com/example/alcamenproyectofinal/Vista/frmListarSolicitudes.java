package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import com.example.alcamenproyectofinal.Adaptador.SolicitudAdapter;
import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class frmListarSolicitudes extends AppCompatActivity {

    private RecyclerView rvSolicitudes;
    private AppDatabase db;
    private List<Solicitud> listaSolicitudesGlobal = new ArrayList<>();
    private SolicitudAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_solicitudes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        rvSolicitudes = findViewById(R.id.rvSolicitudes);
        rvSolicitudes.setLayoutManager(new LinearLayoutManager(this));

        cargarYMostrarSolicitudes();
    }

    public void btnListarSolicitud(View view) {
        cargarYMostrarSolicitudes();
    }

    private void cargarYMostrarSolicitudes() {
        try {
            listaSolicitudesGlobal = db.solicitudDao().obtenerSolicitudes();

            adapter = new SolicitudAdapter(listaSolicitudesGlobal, new SolicitudAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Solicitud solicitud) {
                    mostrarDialogoOpciones(solicitud);
                }

                @Override
                public void onEditarClick(Solicitud solicitud, int position) {
                    aprobarSolicitudDirecto(solicitud);
                }

                @Override
                public void onEliminarClick(Solicitud solicitud, int position) {
                    confirmarEliminacion(solicitud, position);
                }
            });

            rvSolicitudes.setAdapter(adapter);

            if (listaSolicitudesGlobal.isEmpty()) {
                Toast.makeText(this, "No hay solicitudes registradas en el sistema", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al refrescar listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Acción rápida para el Lápiz
    private void aprobarSolicitudDirecto(Solicitud solicitud) {
        try {
            db.solicitudDao().aprobarSolicitud(solicitud.getCodigo_solicitud());
            Toast.makeText(this, "Solicitud aprobada con éxito", Toast.LENGTH_SHORT).show();
            cargarYMostrarSolicitudes(); // Refrescar los cambios
        } catch (Exception e) {
            Toast.makeText(this, "Error al aprobar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Confirmación al presionar la X
    private void confirmarEliminacion(Solicitud solicitud, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Solicitud")
                .setMessage("¿Estás seguro de eliminar la solicitud " + solicitud.getCodigo_solicitud() + "?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    try {
                        db.solicitudDao().eliminarSolicitud(solicitud);
                        Toast.makeText(this, "Solicitud eliminada", Toast.LENGTH_SHORT).show();

                        // Removemos localmente para animación fluida sin recargar todo
                        listaSolicitudesGlobal.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, listaSolicitudesGlobal.size());

                    } catch (Exception e) {
                        Toast.makeText(this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarDialogoOpciones(Solicitud solicitudSeleccionada) {
        AlertDialog.Builder menuOpciones = new AlertDialog.Builder(frmListarSolicitudes.this);
        menuOpciones.setTitle("Gestión de Solicitud: " + solicitudSeleccionada.getCodigo_solicitud());
        menuOpciones.setMessage("¿Qué acción deseas realizar con esta solicitud?");

        menuOpciones.setPositiveButton("Aprobar", (dialog, which) -> {
            aprobarSolicitudDirecto(solicitudSeleccionada);
        });

        menuOpciones.setNegativeButton("Eliminar", (dialog, which) -> {
            try {
                db.solicitudDao().eliminarSolicitud(solicitudSeleccionada);
                Toast.makeText(frmListarSolicitudes.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                cargarYMostrarSolicitudes();
            } catch (Exception e) {
                Toast.makeText(frmListarSolicitudes.this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        menuOpciones.setNeutralButton("Cancelar", null);
        menuOpciones.show();
    }

    public void btnRegresar(View view) {
        finish();
    }
}