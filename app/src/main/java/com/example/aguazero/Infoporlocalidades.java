package com.example.aguazero;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // Obtener la referencia al TextView donde se mostrará la localidad
        TextView tvLocalidad = findViewById(R.id.textViewnombrelocalidad);

        // Recibir la localidad seleccionada desde ListaUbicacion
        String localidadSeleccionada = getIntent().getStringExtra("localidadSeleccionada");

        if (localidadSeleccionada != null) {
            // Mostrar la localidad en el TextView
            tvLocalidad.setText(localidadSeleccionada);
        }
    }
}
