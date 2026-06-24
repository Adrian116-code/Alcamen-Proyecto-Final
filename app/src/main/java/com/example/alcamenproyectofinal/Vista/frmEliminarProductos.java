package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class frmEliminarProductos extends AppCompatActivity {

    EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_eliminar_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtCodigoProductoEliminar);
    }

    public void btnEliminarProducto(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        String codigoAEliminar = codigo.getText().toString();

        Producto productoEncontrado = db.productoDao().obtenerPorId(codigoAEliminar);

        if (productoEncontrado != null) {
            db.productoDao().eliminarProducto(productoEncontrado);
                Toast.makeText(this, "Producto eliminado con éxito", Toast.LENGTH_LONG).show();

            codigo.setText("");
        } else {
            Toast.makeText(this, "El código no existe en la base de datos", Toast.LENGTH_LONG).show();
        }
    }

    public void btnRegresar(View view){
        finish();
    }
}