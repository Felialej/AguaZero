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
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarPerfil extends AppCompatActivity {
    // Variables globales para los campos de texto
    TextView mensajeTextView;
    EditText mensajeeEditText, correoEditText;
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
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(EditarPerfil.this, MainActivity.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });


        // Inicializar UserManager
        userManager = new UserManager(this);

        // Cargar el usuario actual desde SharedPreferences
        usuarioActual = userManager.cargarUsuario();

        // Inicializar los campos de texto
        mensajeTextView = findViewById(R.id.textViewnombreusuario); // TextView que vas a actualizar
        mensajeeEditText = findViewById(R.id.editText3); // EditText de donde tomas el texto del nombre
        correoEditText = findViewById(R.id.editText4); // EditText de donde tomas el texto del correo

        // Mostrar el nombre y el correo del usuario actual en los campos correspondientes
        if (usuarioActual != null) {
            mensajeTextView.setText(usuarioActual.getNombre()); // Mostrar el nombre en el TextView
            mensajeeEditText.setText(usuarioActual.getNombre()); // Mostrar el nombre en el EditText (este es el cambio)
            correoEditText.setText(usuarioActual.getEmail()); // Mostrar el correo
        } else {
            mensajeTextView.setText("Nombre no disponible");
            correoEditText.setHint("Correo no disponible");
        }

        // Referencia al botón "Regresar"
        ImageButton regresar = findViewById(R.id.imageButtonregresar);

        // Listener para el botón "Regresar"
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(EditarPerfil.this, Inicio.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        // Listener para el botón que sobreescribirá el TextView y EditText con los nuevos valores
        Button aceptarCambios = findViewById(R.id.buttonguardar); // Asegúrate que tengas un botón con este ID en tu XML
        aceptarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para actualizar los valores
                guardarCambios(v);
            }
        });

    }

    // Método para actualizar el nombre y correo en SharedPreferences y mostrarlos en los campos
    public void guardarCambios(View view) {
        Log.i("Info", "Botón de aceptar cambios presionado");

        // Obtener el nuevo nombre y correo
        String nuevoNombre = mensajeeEditText.getText().toString();
        String nuevoCorreo = correoEditText.getText().toString();

        // Validar que los campos no estén vacíos
        if (!nuevoNombre.isEmpty() && !nuevoCorreo.isEmpty()) {
            // Actualizar el TextView con el nuevo nombre
            mensajeTextView.setText(nuevoNombre);  // Sobrescribir el contenido del TextView con el nuevo nombre

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
}




