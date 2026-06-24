package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.R;

public class frmGestionProveedores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_proveedores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnInsertarProveedores(View view){
        Intent x = new Intent(this, frmInsertarProveedores.class);
        startActivity(x);
    }

    public void btnListarProveedores(View view){
        Intent x = new Intent(this, frmListarProveedores.class);
        startActivity(x);
    }

    public void btnModificarProveedores(View view){
        Intent x = new Intent(this, frmModificarProveedores.class);
        startActivity(x);
    }

    public void btnEliminarProveedores(View view){
        Intent x = new Intent(this, frmEliminarProveedores.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}