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

public class frmGestionUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_gestion_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnInsertarUsuarios(View view){
        Intent x = new Intent(this, frmInsertarUsuarios.class);
        startActivity(x);
    }

    public void btnListarUsuarios(View view){
        Intent x = new Intent(this, frmListarUsuarios.class);
        startActivity(x);
    }

    public void btnModificarUsuarios(View view){
        Intent x = new Intent(this, frmModificarUsuarios.class);
        startActivity(x);
    }

    public void btnEliminarUsuarios(View view){
        Intent x = new Intent(this, frmEliminarUsuarios.class);
        startActivity(x);
    }

    public void btnRegresar(View view){
        finish();
    }
}