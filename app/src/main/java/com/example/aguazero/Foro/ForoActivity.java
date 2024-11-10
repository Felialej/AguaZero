package com.example.aguazero.Foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aguazero.R;

public class ForoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foroactivity);

        // Referencia al TextView del título del foro
        TextView textViewNombreForo = findViewById(R.id.textViewnombreforo);

        // Referencia al botón que contiene la descripción del foro
        Button buttonDescripcionForo = findViewById(R.id.buttonForo1);

        // Referencia al TextView que contiene la descripción adicional
        TextView textViewDescripcion = findViewById(R.id.textViewdescripcion);

        // Configurar OnClickListener para enviar título y descripción a ResponderForo
        buttonDescripcionForo.setOnClickListener(v -> {
            String tituloForo = textViewNombreForo.getText().toString(); // Obtener el título
            String descripcionForo = textViewDescripcion.getText().toString(); // Obtener el texto del TextView "textViewdescripcion"

            Intent intent = new Intent(this, ResponderForo.class);
            intent.putExtra("titulo_foro", tituloForo);          // Enviar título como extra
            intent.putExtra("descripcion_foro", descripcionForo); // Enviar descripción como extra
            startActivity(intent);
        });
    }
}






