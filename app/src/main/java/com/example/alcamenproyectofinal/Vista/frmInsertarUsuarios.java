package com.example.alcamenproyectofinal.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.alcamenproyectofinal.Datos.AppDatabase;
import com.example.alcamenproyectofinal.Modelo.Usuario;
import com.example.alcamenproyectofinal.R;

public class frmInsertarUsuarios extends AppCompatActivity {

    EditText codigo, dni, nombres, apellidos, edad, username, password;

    private AutoCompleteTextView rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_insertar_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rol = findViewById(R.id.txtrol);

        String[] opcionesRoles = {"Jefe", "Operario"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opcionesRoles);

        rol.setAdapter(adapter);

        rol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String rolSeleccionado = parent.getItemAtPosition(position).toString();
                Toast.makeText(frmInsertarUsuarios.this, "Seleccionaste: " + rolSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });

        codigo = findViewById(R.id.txtCodigoUsuario);
        dni = findViewById(R.id.txtDni);
        nombres = findViewById(R.id.txtNombres);
        apellidos = findViewById(R.id.txtApellidos);
        edad = findViewById(R.id.txtEdad);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
    }

    public void btnInsertarUsuario(View view){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCodigo_usuario(codigo.getText().toString());
        nuevoUsuario.setDni(dni.getText().toString());
        nuevoUsuario.setNombres(nombres.getText().toString());
        nuevoUsuario.setApellidos(apellidos.getText().toString());
        nuevoUsuario.setEdad(Integer.parseInt(edad.getText().toString()));
        nuevoUsuario.setRol(rol.getText().toString());
        nuevoUsuario.setUsername(username.getText().toString());
        nuevoUsuario.setPassword(password.getText().toString());
        db.usuarioDao().insertarUsuario(nuevoUsuario);
        Toast.makeText(this, "Usuario guardado en Room", Toast.LENGTH_LONG).show();
    }

    public void btnRegresar(View view){
        finish();
    }
}