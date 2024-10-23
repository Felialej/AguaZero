package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Seleccionar_mi_localidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionar_mi_localidad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener referencias a los TextViews
        TextView tvLocalidadFavorita = findViewById(R.id.textViewnombrelocalidadfavorita);
        TextView tvFechaCorteFavorita = findViewById(R.id.textViewfecha3);
        Button buttonSeleccionarFavorita = findViewById(R.id.buttonseleccionarfavorita);

        // Recibir la localidad y la fecha de corte del Intent
        String localidadSeleccionada = getIntent().getStringExtra("localidadSeleccionada");
        String fechaCorte = getIntent().getStringExtra("fechaCorte");

        // Actualizar los TextViews con la información recibida
        if (localidadSeleccionada != null && fechaCorte != null) {
            tvLocalidadFavorita.setText(localidadSeleccionada);
            tvFechaCorteFavorita.setText(fechaCorte);
        }

        // Cuando se presione el botón, regresar a la actividad Inicio con el nombre de la localidad
        buttonSeleccionarFavorita.setOnClickListener(v -> {
            Intent intent = new Intent(Seleccionar_mi_localidad.this, Inicio.class);
            intent.putExtra("localidadFavorita", localidadSeleccionada);
            startActivity(intent); // Lanza la nueva actividad Inicio
        });
    }
}

