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
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class frmListarSolicitudes extends AppCompatActivity {

    ListView listar_Solicitudes;
    AppDatabase db;
    List<Solicitud> listaSolicitudesGlobal;
    ArrayAdapter<String> adapter;

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

        listar_Solicitudes = findViewById(R.id.txtListarSolicitudes);

        listar_Solicitudes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solicitud solicitudSeleccionada = listaSolicitudesGlobal.get(position);

                AlertDialog.Builder menuOpciones = new AlertDialog.Builder(frmListarSolicitudes.this);
                menuOpciones.setTitle("Gestión de Solicitud: " + solicitudSeleccionada.getCodigo_solicitud());
                menuOpciones.setMessage("¿Qué acción deseas realizar con esta solicitud?");

                menuOpciones.setPositiveButton("Aprobar", (dialog, which) -> {
                    try {
                        db.solicitudDao().aprobarSolicitud(solicitudSeleccionada.getCodigo_solicitud());
                        Toast.makeText(frmListarSolicitudes.this, "Solicitud aprobada con éxito", Toast.LENGTH_SHORT).show();
                        cargarYMostrarSolicitudes(); // Refrescamos los cambios en pantalla
                    } catch (Exception e) {
                        Toast.makeText(frmListarSolicitudes.this, "Error al aprobar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                menuOpciones.setNegativeButton("Eliminar", (dialog, which) -> {
                    try {
                        db.solicitudDao().eliminarSolicitud(solicitudSeleccionada);
                        Toast.makeText(frmListarSolicitudes.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                        cargarYMostrarSolicitudes(); // Refrescamos los cambios en pantalla
                    } catch (Exception e) {
                        Toast.makeText(frmListarSolicitudes.this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                menuOpciones.setNeutralButton("Cancelar", null);

                menuOpciones.show();
            }
        });
    }

    public void btnListarSolicitud(View view){
        cargarYMostrarSolicitudes();
    }

    private void cargarYMostrarSolicitudes() {
        try {
            listaSolicitudesGlobal = db.solicitudDao().obtenerSolicitudes();
            List<String> datosVisuales = new ArrayList<>();

            for (Solicitud s : listaSolicitudesGlobal) {
                String item = "Código: " + s.getCodigo_solicitud() +
                        " | Prod: " + s.getCodigo_producto() +
                        " | Estado: " + s.getEstado().toUpperCase();
                datosVisuales.add(item);
            }

            if (datosVisuales.isEmpty()) {
                datosVisuales.add("No hay solicitudes registradas en el sistema");
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, datosVisuales);
            listar_Solicitudes.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al refrescar listado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}