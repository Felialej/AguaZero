package com.example.aguazero;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager;
    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private Button crearcuenta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el UserManager para acceder a los usuarios guardados
        userManager = new UserManager(this);

        // Referenciar los campos de email y password
        emailField = findViewById(R.id.emailField);  // Asegúrate de que estos IDs estén en tu layout XML
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.buttoningresar);
        crearcuenta = findViewById(R.id.buttoncrearcuenta);

        // Configurar el click listener del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Validar usuario con UserManager
                if (userManager.validarUsuario(email, password)) {
                    // Si las credenciales son correctas, ir a la pantalla principal (o la que desees)
                    Intent intent = new Intent(MainActivity.this, Inicio.class); // Cambia "Inicio" por la actividad que desees
                    startActivity(intent);
                    finish(); // Cierra la actividad actual
                } else {
                    // Mostrar un mensaje de error
                    Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el click listener del botón de crear cuenta
        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a la pantalla de registro
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent); // Lanza la actividad de registro
            }
        });
    }
}


