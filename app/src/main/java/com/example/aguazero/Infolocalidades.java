package com.example.aguazero;

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

public class Infolocalidades extends AppCompatActivity {

    private static int contador = 0; // Variable contador para manejar las selecciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Esto puede ser opcional si no es necesario
        setContentView(R.layout.activity_infolocalidades);

        // Ajustes de las insets de la ventana para diseño edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al botón fuera del bloque de insets
        ImageButton regresar = findViewById(R.id.imageButtonregresar);

        // Configurar el click listener del botón "regresar"
        regresar.setOnClickListener(v -> {
            Intent intent = new Intent(Infolocalidades.this, ListaLocalidades.class);
            startActivity(intent); // Lanza la nueva actividad
        });

        // Referencia al botón "buttonseleccionar"
        Button buttonSeleccionar = findViewById(R.id.buttonseleccionar);

        // Definimos dos arrays para localidades y una descripción adicional (infoAdicional)
        String[] localidades = {
                "Antonio Nariño", "Barrios Unidos", "Bosa", "La Candelaría", "Chapinero",
                "Ciudad Bolívar", "Engativá", "Fontibón", "Kennedy", "Los Martires",
                "Puente Aranda", "Rafael Uribe", "San Cristobal", "Santa Fe", "Suba",
                "Sumapaz", "Teusaquillo", "Tunjuelito", "Usaquén", "Usme"
        };

        String[] infoAdicional = {
                "29/09/2024", "29/09/2024", "--/--/----", "--/--/----", "29/09/2024", "--/--/----",
                "--/--/----", "--/--/----", "--/--/----", "29/09/2024", "29/09/2024", "29/09/2024",
                "--/--/----", "--/--/----", "--/--/----", "--/--/----", "29/09/2024", "29/09/2024",
                "29/09/2024", "--/--/----"
        };

        // Obtenemos la referencia de los TextView donde mostraremos la información
        TextView tvLocalidad = findViewById(R.id.textViewnombrelocalidad);
        TextView tvInfoAdicional = findViewById(R.id.textViewfecha);

        // Recibimos la posición enviada desde el intent de MapadeBogota
        int position = getIntent().getIntExtra("position", -1);

        // Validamos que la posición sea válida (entre 0 y el tamaño del array)
        if (position >= 0 && position < localidades.length) {
            // Mostramos la información correspondiente en los TextView
            tvLocalidad.setText(localidades[position]);
            tvInfoAdicional.setText(infoAdicional[position]);
        } else {
            // Si la posición no es válida, mostramos un mensaje de error
            tvLocalidad.setText("Error: Posición no válida.");
            tvInfoAdicional.setText("");
        }

        // Configurar el click listener del botón "buttonseleccionar"
        buttonSeleccionar.setOnClickListener(v -> {
            // Obtener la localidad seleccionada
            String localidadSeleccionada = localidades[position];

            // Crear el Intent para enviar la localidad seleccionada a ListaUbicacion
            Intent intent = new Intent(Infolocalidades.this, ListaUbicacion.class);
            intent.putExtra("localidadSeleccionada", localidadSeleccionada);
            intent.putExtra("contador", contador);  // Enviar el contador para gestionar la sobrescritura

            // Incrementar el contador y reiniciar si es necesario
            contador++;
            if (contador > 2) {
                contador = 0;  // Reiniciar el ciclo
            }

            // Iniciar la actividad ListaUbicacion
            startActivity(intent);
        });
    }
}


