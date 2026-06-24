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

public class frmJefeAlmacen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_jefe_almacen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnGestionarProductos(View view){
        Intent x = new Intent(this, frmGestionProductos.class);
        startActivity(x);
    }

    public void btnGestionarSolicitudes(View view){
        Intent x = new Intent(this, frmGestionSolicitudes.class);
        startActivity(x);
    }

    public void btnGestionarProveedores(View view){
        Intent x = new Intent(this, frmGestionProveedores.class);
        startActivity(x);
    }

    public void btnListarDespacho(View view){
        Intent x = new Intent(this,frmListarDespacho.class);
        startActivity(x);

    }

    public void btnRegresar(View view){
        finish();
    }
}