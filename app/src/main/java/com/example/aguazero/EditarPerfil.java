package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class EditarPerfil extends AppCompatActivity {
    // Variables globales para los campos de texto
    TextView mensajeTextView;
    EditText mensajeeEditText, correoEditText, nuevaPasswordEditText, confirmarPasswordEditText;
    UserManager userManager;
    Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        // Ajustar los insets del diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button salir = findViewById(R.id.buttoncerrarsesion);

        // Configurar el click listener del botón
        salir.setOnClickListener(v -> {
            // Crear el Intent para abrir la nueva actividad
            Intent intent = new Intent(EditarPerfil.this, MainActivity.class);
            startActivity(intent); // Lanza la nueva actividad
        });

        // Inicializar UserManager
        userManager = new UserManager(this);

        // Cargar el usuario actual desde SharedPreferences
        usuarioActual = userManager.cargarUsuario();

        // Inicializar los campos de texto
        mensajeTextView = findViewById(R.id.textViewnombreusuario);
        mensajeeEditText = findViewById(R.id.editText3);
        correoEditText = findViewById(R.id.editText4);

        // Inicializar los campos de contraseña
        nuevaPasswordEditText = findViewById(R.id.editText2); // Nuevo campo de contraseña
        confirmarPasswordEditText = findViewById(R.id.editText5); // Confirmación de nueva contraseña

        // Mostrar el nombre y el correo del usuario actual en los campos correspondientes
        if (usuarioActual != null) {
            mensajeTextView.setText(usuarioActual.getNombre());
            mensajeeEditText.setText(usuarioActual.getNombre());
            correoEditText.setText(usuarioActual.getEmail());
        } else {
            mensajeTextView.setText("Nombre no disponible");
            correoEditText.setHint("Correo no disponible");
        }

        // Listener para el botón "Regresar"
        ImageButton regresar = findViewById(R.id.imageButtonregresar);
        regresar.setOnClickListener(v -> {
            Intent intent = new Intent(EditarPerfil.this, Inicio.class);
            startActivity(intent);
        });

        // Listener para el botón que sobreescribirá el TextView y EditText con los nuevos valores
        Button aceptarCambios = findViewById(R.id.buttonguardar);
        aceptarCambios.setOnClickListener(this::guardarCambios);
    }

    // Método para actualizar el nombre, correo y contraseña en SharedPreferences
    public void guardarCambios(View view) {
        Log.i("Info", "Botón de aceptar cambios presionado");

        // Obtener el nuevo nombre, correo y contraseñas
        String nuevoNombre = mensajeeEditText.getText().toString();
        String nuevoCorreo = correoEditText.getText().toString();
        String nuevaPassword = nuevaPasswordEditText.getText().toString();
        String confirmarPassword = confirmarPasswordEditText.getText().toString();

        // Validar que los campos no estén vacíos
        if (!nuevoNombre.isEmpty() && !nuevoCorreo.isEmpty()) {
            // Validar las contraseñas si el usuario ingresó algo en los campos
            if (!nuevaPassword.isEmpty() && !confirmarPassword.isEmpty()) {
                if (!nuevaPassword.equals(confirmarPassword)) {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return; // Detener la ejecución si las contraseñas no coinciden
                } else if (!validarContraseña(nuevaPassword)) {
                    Toast.makeText(this, "La contraseña no cumple con los requisitos.", Toast.LENGTH_LONG).show();
                    return; // Detener si no cumple los requisitos
                } else {
                    // Actualizar la contraseña del usuario actual
                    usuarioActual.setPassword(nuevaPassword);
                }
            }

            // Actualizar el TextView con el nuevo nombre
            mensajeTextView.setText(nuevoNombre);

            // Actualizar el objeto usuario actual
            usuarioActual.setNombre(nuevoNombre);
            usuarioActual.setEmail(nuevoCorreo);

            // Guardar los cambios en SharedPreferences usando UserManager
            userManager.guardarUsuario(usuarioActual);

            // Mostrar mensaje de éxito
            Toast.makeText(this, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("Error", "El campo de nombre o correo está vacío");
            Toast.makeText(this, "Por favor, ingresa nombre y correo", Toast.LENGTH_SHORT).show();
        }
    }

    // Validar que la nueva contraseña cumpla con los requisitos
    private boolean validarContraseña(String password) {
        // La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una minúscula, un número y un carácter especial
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return passwordPattern.matcher(password).matches();
    }
}





