package com.example.aguazero.Localidades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aguazero.R;

public class Infolocalidades extends AppCompatActivity {

    private static int contador = 0; // Variable contador para manejar las selecciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infolocalidades);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonSeleccionar = findViewById(R.id.buttonseleccionar);

        // Obtener referencias a los TextView
        TextView tvLocalidad = findViewById(R.id.textViewnombrelocalidad);
        TextView tvFechaCorte = findViewById(R.id.textViewfecha);

        // Recibir la localidad seleccionada desde el intent
        int position = getIntent().getIntExtra("position", -1);

        if (position >= 0 && position < DatosLocalidades.LOCALIDADES.length) {
            // Mostrar la localidad y la fecha de corte usando la clase DatosLocalidades
            String localidadSeleccionada = DatosLocalidades.LOCALIDADES[position];
            tvLocalidad.setText(localidadSeleccionada);
            tvFechaCorte.setText(DatosLocalidades.FECHAS_CORTE[position]);

            buttonSeleccionar.setOnClickListener(v -> {
                Intent intent = new Intent(Infolocalidades.this, ListaUbicacion.class);
                intent.putExtra("localidadSeleccionada", localidadSeleccionada);
                intent.putExtra("contador", contador);

                contador++;
                if (contador > 2) {
                    contador = 0;
                }

                startActivity(intent);
            });
        } else {
            tvLocalidad.setText("Error: Posición no válida.");
            tvFechaCorte.setText("");
        }
    }
}





