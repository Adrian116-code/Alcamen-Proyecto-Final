package com.example.alcamenproyectofinal.Vista;

import android.content.Intent;
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
import com.google.android.material.button.MaterialButton;

public class frmModificarUsuarios extends AppCompatActivity {

    private MaterialButton btnProductos, btnSedes, btnRegresar;
    EditText codigo, dni, nombres, apellidos, edad, username, password;
    private AutoCompleteTextView rol;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frm_modificar_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnProductos = findViewById(R.id.btnProductos);
        btnSedes = findViewById(R.id.btnSedes);
        btnRegresar = findViewById(R.id.btnRegresar);

        rol = findViewById(R.id.txtrolMod);

        String[] opcionesRoles = {"Jefe", "Operario"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opcionesRoles);

        rol.setAdapter(adapter);

        rol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String rolSeleccionado = parent.getItemAtPosition(position).toString();
                Toast.makeText(frmModificarUsuarios.this, "Seleccionaste: " + rolSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        codigo = findViewById(R.id.txtCodigoUsuario);
        dni = findViewById(R.id.txtDni);
        nombres = findViewById(R.id.txtNombres);
        apellidos = findViewById(R.id.txtApellidosMod);
        edad = findViewById(R.id.txtEdadMod);
        username = findViewById(R.id.txtUsernameMod);
        password = findViewById(R.id.txtPasswordMod);

        if (getIntent().hasExtra("CODIGO_USUARIO")) {
            String codigoRecibido = getIntent().getStringExtra("CODIGO_USUARIO");
            cargarDatosUsuario(codigoRecibido);
        }

        configurarEventos();
    }

    private void cargarDatosUsuario(String codigoUsuario) {
        try {
            Usuario usuarioEncontrado = db.usuarioDao().obtenerPorId(codigoUsuario);

            if (usuarioEncontrado != null) {
                codigo.setText(String.valueOf(usuarioEncontrado.getCodigo_usuario()));
                nombres.setText(usuarioEncontrado.getNombres());
                apellidos.setText(usuarioEncontrado.getApellidos());
                dni.setText(usuarioEncontrado.getDni());
                username.setText(usuarioEncontrado.getUsername());
                password.setText(usuarioEncontrado.getPassword());
                edad.setText(String.valueOf(usuarioEncontrado.getEdad()));

                rol.setText(usuarioEncontrado.getRol(), false);

                codigo.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar el usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnModificarUsuario(View view){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "hipercorp_db").allowMainThreadQueries().build();

        Usuario usuarioEditado = new Usuario();

        usuarioEditado.setCodigo_usuario(codigo.getText().toString());
        usuarioEditado.setDni(dni.getText().toString());
        usuarioEditado.setNombres(nombres.getText().toString());
        usuarioEditado.setApellidos(apellidos.getText().toString());
        usuarioEditado.setEdad(Integer.parseInt(edad.getText().toString()));
        usuarioEditado.setRol(rol.getText().toString());
        usuarioEditado.setUsername(username.getText().toString());
        usuarioEditado.setPassword(password.getText().toString());

        db.usuarioDao().actualizarUsuario(usuarioEditado);

        Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_LONG).show();
    }

    private void configurarEventos() {
        if (btnProductos != null) btnProductos.setOnClickListener(v -> abrirProductos());
        if (btnSedes != null) btnSedes.setOnClickListener(v -> abrirSedes());
        if (btnRegresar != null) btnRegresar.setOnClickListener(v -> finish());
    }

    private void abrirProductos() {
        startActivity(new Intent(this, frmListarProductos.class));
    }

    private void abrirSedes() {
        startActivity(new Intent(this, frmListarSedes.class));
    }

    public void btnRegresar(View view){
        finish();
    }
}