package com.example.aguazero.Foro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aguazero.R;

public class CrearEditarForoActivity extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private ForoManager foroManager;
    private int foroIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_editar_foro);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        foroManager = new ForoManager(this);

        // Verificar si estamos en modo de edición (si recibimos un índice del foro a editar)
        foroIndex = getIntent().getIntExtra("foro_index", -1);
        if (foroIndex != -1) {
            Foro foro = foroManager.getForos().get(foroIndex); // Obtenemos el foro a editar
            etTitulo.setText(foro.getTitulo());
            etDescripcion.setText(foro.getDescripcion());
        }

        // Configurar el botón para guardar el foro
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            Foro foro = new Foro(titulo, descripcion);

            if (foroIndex != -1) {
                // Editar el foro existente
                foroManager.updateForo(foroIndex, foro);
            } else {
                // Crear un nuevo foro
                foroManager.addForo(foro);
            }

            finish(); // Cerrar la actividad y volver a la lista de foros
        });
    }
}
