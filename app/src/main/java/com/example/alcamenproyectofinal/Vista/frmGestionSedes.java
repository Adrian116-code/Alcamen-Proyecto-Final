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

public class frmGestionSedes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_sedes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnInsertarSedes(View view){
        Intent x = new Intent(this, frmInsertarSedes.class);
        startActivity(x);
    }

    public void btnListarSedes(View view){
        Intent x = new Intent(this, frmListarSedes.class);
        startActivity(x);
    }

    public void btnModificarSedes(View view){
        Intent x = new Intent(this,frmModificarSedes.class);
        startActivity(x);
    }

    public void btnEliminarSedes(View view){
        Intent x = new Intent(this, frmEliminarSedes.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}