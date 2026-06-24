package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
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
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class frmListarProveedores extends AppCompatActivity {

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
    }

    public void btnListarProveedor(View view) {

        TextView txtListado = findViewById(R.id.txtListarProveedores);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        List<Proveedor> lista = db.proveedorDao().obtenerProveedores();

        StringBuilder cadenaProveedores = new StringBuilder();

        for (Proveedor p : lista) {

            cadenaProveedores.append("Código: ").append(p.getCodigo_proveedor()).append("\n")
                    .append(" - Razón Social: ").append(p.getRazon_social()).append("\n")
                    .append(" - Contacto: ").append(p.getContacto()).append("\n")
                    .append(" - Teléfono: ").append(p.getTelefono()).append("\n")
                    .append("\n");
        }
        txtListado.setText(cadenaProveedores.toString());
    }

    public void btnRegresar(View view){
        finish();
    }
}