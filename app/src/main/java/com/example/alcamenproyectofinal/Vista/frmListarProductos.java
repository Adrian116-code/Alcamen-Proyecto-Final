package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarProductos extends AppCompatActivity {

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
    }

    public void btnListarProducto(View view) {

        TextView txtListado = findViewById(R.id.txtListarProductos);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Producto> lista = db.productoDao().obtenerProductos();

        StringBuilder cadenaProductos = new StringBuilder();

        for (Producto p : lista) {

            cadenaProductos.append("Código: ").append(p.getCodigo_producto()).append("\n")
                    .append(" - Nombre: ").append(p.getNombre()).append("\n")
                    .append(" - Descripción: ").append(p.getDescripcion()).append("\n")
                    .append(" - Ubicación: ").append(p.getUbicacion()).append("\n")
                    .append(" - Stock: ").append(p.getStock()).append("\n")
                    .append(" - Código Proveedor: ").append(p.getCodigo_proveedor()).append("\n")
                    .append("\n");
        }
        txtListado.setText(cadenaProductos.toString());
    }

    public void btnRegresar(View view){
        finish();
    }
}