package com.example.aguazero.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aguazero.R;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText nombreField, emailField, passwordField, confirmarPasswordField;
    private UserManager userManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los campos de entrada
        nombreField = findViewById(R.id.editTextnombreyapellido);
        emailField = findViewById(R.id.editTextcorreo);
        passwordField = findViewById(R.id.editTextcontraseña);
        confirmarPasswordField = findViewById(R.id.editTextConfirmarContraseña); // Nuevo campo para confirmar contraseña

        // Inicializar UserManager
        userManager = new UserManager(this);

        // Botones
        ImageButton atras = findViewById(R.id.imageButtonregresar);
        Button aceptar = findViewById(R.id.buttonaceptar);

        atras.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
        });

        // Listener para el botón de "aceptar"
        aceptar.setOnClickListener(v -> {
            String nombre = nombreField.getText().toString();
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            String confirmarPassword = confirmarPasswordField.getText().toString();

            // Validaciones de entrada
            if (!validarNombreUsuario(nombre)) {
                Toast.makeText(Register.this, "El nombre debe tener entre 5 y 20 caracteres.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validarCorreo(email)) {
                Toast.makeText(Register.this, "Ingresa un correo electrónico válido.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userManager.correoRegistrado(email)) {
                Toast.makeText(Register.this, "El correo ya está registrado.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validarContraseña(password)) {
                Toast.makeText(Register.this, "La contraseña contiene errores.", Toast.LENGTH_LONG).show();
                return;
            }

            if (!password.equals(confirmarPassword)) {
                Toast.makeText(Register.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
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
            startActivity(intent);
            finish();
        });
    }

    // Método para validar el nombre de usuario
    private boolean validarNombreUsuario(String nombre) {
        // Validar longitud y caracteres alfanuméricos
        return nombre.length() >= 5 && nombre.length() <= 20 && nombre.matches("[a-zA-Z0-9]+");
    }

    // Método para validar el correo electrónico
    private boolean validarCorreo(String email) {
        // Validar si el email tiene formato válido
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Método para validar la contraseña
    private boolean validarContraseña(String password) {
        // La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una minúscula, un número y un carácter especial
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return passwordPattern.matcher(password).matches();
    }
}


