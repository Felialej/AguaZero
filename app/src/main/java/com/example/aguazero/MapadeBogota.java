package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapadeBogota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapade_bogota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    // Referencia al botón fuera del bloque de insets
    ImageButton volverinicio = findViewById(R.id.imageButtoninicio);

    // Configurar el click listener del botón
        volverinicio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Crear el Intent para abrir la nueva actividad
            Intent intent = new Intent(MapadeBogota.this, Inicio.class);
            startActivity(intent); // Lanza la nueva actividad
        }
        });
    }
}