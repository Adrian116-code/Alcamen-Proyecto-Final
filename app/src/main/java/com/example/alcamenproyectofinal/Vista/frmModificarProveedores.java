package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.VerifiedInputEvent;
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
import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;

public class frmModificarProveedores extends AppCompatActivity {

    EditText codigo, razon_social, contacto, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_proveedores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigo = findViewById(R.id.txtCodigoProveedorMod);
        razon_social = findViewById(R.id.txtRazonSocialMod);
        contacto = findViewById(R.id.txtContactoMod);
        telefono = findViewById(R.id.txtNumeroMod);
    }

    public void btnModificarProveedor(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        Proveedor proveedorEditado = new Proveedor();

        proveedorEditado.setCodigo_proveedor(codigo.getText().toString());
        proveedorEditado.setRazon_social(razon_social.getText().toString());
        proveedorEditado.setContacto(contacto.getText().toString());
        proveedorEditado.setTelefono(Integer.parseInt(telefono.getText().toString()));

        db.proveedorDao().actualizarProveedor(proveedorEditado);

        Toast.makeText(this, "Proveedor actualizado correctamente", Toast.LENGTH_LONG).show();

    }

    public void btnRegresar(View VIEW){
        finish();
    }
}