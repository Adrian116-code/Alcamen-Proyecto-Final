package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
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
import com.example.alcamenproyectofinal.Modelo.Despacho_Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarDespacho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_listar_despacho);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnListarDespachoss(View view){

        TextView txtListado = findViewById(R.id.txtListarDespacho);

        try {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

            List<Despacho_Sede> lista = db.despachoSedeDao().obtenerDespachos();

            if (lista.isEmpty()) {
                Toast.makeText(this, "No hay despachos registrados", Toast.LENGTH_SHORT).show();
                txtListado.setText("");
                return;
            }

            StringBuilder cadenaDespachos = new StringBuilder();

            for (Despacho_Sede d : lista) {
                cadenaDespachos.append("Código Despacho: ").append(d.getCodigo_despacho()).append("\n")
                        .append("Operario: ").append(d.getCodigo_operario()).append("\n")
                        .append("Producto: ").append(d.getCodigo_producto()).append("\n")
                        .append("Sede: ").append(d.getCodigo_sede()).append("\n")
                        .append("Cantidad: ").append(d.getCantidad()).append("\n")
                        .append("\n");
            }

            txtListado.setText(cadenaDespachos.toString());

        } catch (Exception e) {
            Toast.makeText(this, "Error al listar despachos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void btnRegresar(View view){
        finish();
    }
}