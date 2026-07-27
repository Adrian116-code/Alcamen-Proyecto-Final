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

        // Inicializar Room Database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        // Configurar RecyclerView
        rvSolicitudes = findViewById(R.id.rvSolicitudes);
        rvSolicitudes.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos al abrir la pantalla
        cargarYMostrarSolicitudes();
    }

    public void btnListarSolicitud(View view) {
        cargarYMostrarSolicitudes();
    }

    private void cargarYMostrarSolicitudes() {
        try {
            listaSolicitudesGlobal = db.solicitudDao().obtenerSolicitudes();

            // Pasamos la lista y la interfaz de clic al adaptador
            adapter = new SolicitudAdapter(listaSolicitudesGlobal, this::mostrarDialogoOpciones);
            rvSolicitudes.setAdapter(adapter);

            if (listaSolicitudesGlobal.isEmpty()) {
                Toast.makeText(this, "No hay solicitudes registradas en el sistema", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al refrescar listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarDialogoOpciones(Solicitud solicitudSeleccionada) {
        AlertDialog.Builder menuOpciones = new AlertDialog.Builder(frmListarSolicitudes.this);
        menuOpciones.setTitle("Gestión de Solicitud: " + solicitudSeleccionada.getCodigo_solicitud());
        menuOpciones.setMessage("¿Qué acción deseas realizar con esta solicitud?");

        menuOpciones.setPositiveButton("Aprobar", (dialog, which) -> {
            try {
                db.solicitudDao().aprobarSolicitud(solicitudSeleccionada.getCodigo_solicitud());
                Toast.makeText(frmListarSolicitudes.this, "Solicitud aprobada con éxito", Toast.LENGTH_SHORT).show();
                cargarYMostrarSolicitudes(); // Refrescar los cambios
            } catch (Exception e) {
                Toast.makeText(frmListarSolicitudes.this, "Error al aprobar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        menuOpciones.setNegativeButton("Eliminar", (dialog, which) -> {
            try {
                db.solicitudDao().eliminarSolicitud(solicitudSeleccionada);
                Toast.makeText(frmListarSolicitudes.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                cargarYMostrarSolicitudes(); // Refrescar los cambios
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