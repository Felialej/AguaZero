package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener el TextView de la localidad favorita en la pantalla de inicio
        TextView tvLocalidadInicio = findViewById(R.id.textViewnombrelocalidadinicio);
        // Ir a Emergencia Hidrica
        Button btnEmergenciaHidrica = findViewById(R.id.buttonreportaremergencia);

        // Verificar si hay una localidad seleccionada en el Intent
        Intent intent = getIntent();
        String localidadFavorita = intent.getStringExtra("localidadFavorita");

        // Si se ha seleccionado una localidad, actualizar el TextView
        if (localidadFavorita != null) {
            tvLocalidadInicio.setText(localidadFavorita);
        }

        // Referencia al botón fuera del bloque de insets
        Button listaubicaciones = findViewById(R.id.buttonlistadeubicaciones);
        ImageButton editarperfil = findViewById(R.id.imageButtoneditarperfil);
        ImageButton iramapa = findViewById(R.id.imageButtonmapa);

        // Configurar el click listener del botón listaubicaciones

        btnEmergenciaHidrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, Emergencia_Hidrica.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        listaubicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, ListaUbicacion.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        // Configurar el click listener del botón editar perfil
        editarperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, EditarPerfil.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        // Configurar el click listener del botón iramapa
        iramapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(Inicio.this, MapadeBogota.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });
    }
}
