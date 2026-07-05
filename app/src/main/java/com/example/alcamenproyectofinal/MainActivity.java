package com.example.alcamenproyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alcamenproyectofinal.Vista.frmAdmin;
import com.example.alcamenproyectofinal.Vista.frmJefeAlmacen;
import com.example.alcamenproyectofinal.Vista.frmOperarioAlmacen;

public class MainActivity extends AppCompatActivity {

    EditText Correo, PasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Correo = findViewById(R.id.txtCorreo);
        PasswordLogin = findViewById(R.id.txtPasswordLogin);
    }

    public void btnLogin(View view) {

        String usernameInput = Correo.getText().toString().trim();

        String correoInput = Correo.getText().toString().trim();
        String passwordInput = PasswordLogin.getText().toString().trim();

        if (correoInput.isEmpty() || passwordInput.isEmpty()) {
            Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        if (correoInput.equals("admin") && passwordInput.equals("1234")) {

            Toast.makeText(this, "Bienvenido Administrador!", Toast.LENGTH_LONG).show();

            Intent x = new Intent(this, frmAdmin.class);
            startActivity(x);
            finish();


        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        Usuario usuarioLogueado = db.usuarioDao().login(usernameInput, passwordInput);

        boolean credencialesCorrectas = false;

        if (usuarioLogueado != null) {
            credencialesCorrectas = true;
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }

        if (credencialesCorrectas) {

            String rolUsuario = usuarioLogueado.getRol();

            if (rolUsuario != null && rolUsuario.equalsIgnoreCase("operario")) {
                Toast.makeText(this, "Bienvenido Operario " + usuarioLogueado.getNombres(), Toast.LENGTH_LONG).show();
                Intent x = new Intent(this, frmOperarioAlmacen.class);
                startActivity(x);
                finish();
            }

            if (rolUsuario != null && rolUsuario.equalsIgnoreCase("jefe")) {
                Toast.makeText(this, "Bienvenido Jefe " + usuarioLogueado.getNombres(), Toast.LENGTH_LONG).show();
                Intent x = new Intent(this, frmJefeAlmacen.class);
                startActivity(x);
                finish();
            }

            if (rolUsuario == null || (!rolUsuario.equalsIgnoreCase("operario") && !rolUsuario.equalsIgnoreCase("jefe"))) {
                Toast.makeText(this, "Acceso concedido, pero el rol '" + rolUsuario + "' no tiene una pantalla asignada.", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void btnAdmin(View view){
        Intent x = new Intent(this, frmAdmin.class);
        startActivity(x);
    }

    public void btnJefe(View view){
        Intent x = new Intent(this, frmJefeAlmacen.class);
        startActivity(x);
    }

    public void btnOperario(View view){
        Intent x = new Intent(this, frmOperarioAlmacen.class);
        startActivity(x);
    }
}