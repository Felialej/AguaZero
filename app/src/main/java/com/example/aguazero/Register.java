package com.example.aguazero;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    TextView mensajeTextView;
    EditText mensajeeEditText;


    public void buttonpress(View view) {
        Log.i("Info", "Botón presionado");
        mensajeTextView = findViewById(R.id.textViewnombreusuario);
        mensajeeEditText = findViewById(R.id.editTextnombreyapellido);
        mensajeTextView = findViewById(R.id.editText4);
        String mensajeString = mensajeeEditText.getText().toString();
        mensajeTextView.setText(mensajeString);
    }

    private EditText nombreField, emailField, passwordField;
    private UserManager userManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Ajustar insets para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los campos de entrada
        nombreField = findViewById(R.id.editTextnombreyapellido); // EditText para nombre
        emailField = findViewById(R.id.editTextcorreo); // EditText para email
        passwordField = findViewById(R.id.editTextcontraseña); // EditText para contraseña

        // Inicializar UserManager
        userManager = new UserManager(this);

        // Botones
        ImageButton atras = findViewById(R.id.imageButtonregresar);
        Button aceptar = findViewById(R.id.buttonaceptar);

        // Configurar el click listener del botón "atrás"
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la MainActivity
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        // Configurar el click listener del botón "aceptar" para registrar usuario
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear un nuevo objeto Usuario
                Usuario nuevoUsuario = new Usuario(nombre, email, password);

                // Guardar el usuario en SharedPreferences usando UserManager
                userManager.guardarUsuario(nuevoUsuario);

                // Mostrar mensaje de éxito
                Toast.makeText(Register.this, "Usuario registrado exitosamente.", Toast.LENGTH_SHORT).show();

                // Ir a la pantalla de inicio de sesión (MainActivity)
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent); // Lanza la nueva actividad
                finish(); // Cierra la actividad actual para no regresar al registro
            }
        });
    }
}

