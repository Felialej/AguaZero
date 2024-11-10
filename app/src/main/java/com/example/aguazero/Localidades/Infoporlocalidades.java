package com.example.aguazero.Localidades;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aguazero.R;

public class Infoporlocalidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infoporlocalidades);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener la referencia a los TextView donde se mostrarán la localidad y la fecha
        TextView tvLocalidad = findViewById(R.id.textViewnombrelocalidad);
        TextView tvFechaCorte = findViewById(R.id.textViewfecha);

        // Recibir la localidad seleccionada desde ListaUbicacion
        String localidadSeleccionada = getIntent().getStringExtra("localidadSeleccionada");

        if (localidadSeleccionada != null) {
            // Mostrar la localidad en el TextView correspondiente
            tvLocalidad.setText(localidadSeleccionada);

            // Obtener y mostrar la fecha de corte correspondiente usando DatosLocalidades
            String fechaCorte = DatosLocalidades.obtenerFechaCorte(localidadSeleccionada);
            tvFechaCorte.setText(fechaCorte);
        }
    }
}


