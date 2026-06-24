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

public class frmGestionProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnInsertarProductos(View view){
        Intent x = new Intent(this, frmInsertarProductos.class);
        startActivity(x);
    }

    public void btnListarProductos(View view){
        Intent x = new Intent(this, frmListarProductos.class);
        startActivity(x);
    }

    public void btnModificarProductos(View view){
        Intent x = new Intent(this, frmModificarProductos.class);
        startActivity(x);
    }

    public void btnEliminarProductos(View view){
        Intent x = new Intent(this, frmEliminarProductos.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}