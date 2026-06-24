package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

public class frmListarSedes extends AppCompatActivity {


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
    }

    public void btnListarSede(View view) {

        TextView txtListado = findViewById(R.id.txtListarSede);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Sede> lista = db.sedeDao().obtenerSedes();

        StringBuilder cadenaSedes = new StringBuilder();

        for (Sede s : lista) {

            cadenaSedes.append("Código: ").append(s.getCodigo_sede()).append("\n")
                    .append(" - Nombre: ").append(s.getNombre()).append("\n")
                    .append(" - Dirección: ").append(s.getDireccion()).append("\n")
                    .append("\n");
        }
        txtListado.setText(cadenaSedes.toString());
    }


    public void btnRegresar(View view){
        finish();
    }
}