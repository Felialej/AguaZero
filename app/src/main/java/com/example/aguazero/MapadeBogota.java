package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapadeBogota extends AppCompatActivity {

    String[] localidades = {
            "Antonio Nariño", "Barrios Unidos", "Bosa", "La Candelaría", "Chapinero",
            "Ciudad Bolívar", "Engativá", "Fontibón", "Kennedy", "Los Martires",
            "Puente Aranda", "Rafael Uribe", "San Cristobal", "Santa Fe", "Suba",
            "Sumapaz", "Teusaquillo", "Tunjuelito", "Usaquén", "Usme"
    };

    String[] fechasCorte = {
            "29/09/2024", "29/09/2024", "--/--/----", "--/--/----", "29/09/2024",
            "--/--/----", "--/--/----", "--/--/----", "--/--/----", "29/09/2024",
            "29/09/2024", "29/09/2024", "--/--/----", "--/--/----", "--/--/----",
            "--/--/----", "29/09/2024", "29/09/2024", "29/09/2024", "--/--/----"
    };

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

        // Crear un único OnClickListener para todas las localidades
        View.OnClickListener localidadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el tag de la vista que fue clickeada (que contiene la posición)
                int position = (int) v.getTag(); // Recuperar el tag como la posición

                // Crear el Intent para abrir la nueva actividad Seleccionar_mi_localidad
                Intent intent = new Intent(MapadeBogota.this, Seleccionar_mi_localidad.class);

                // Enviar la localidad y su fecha correspondiente
                intent.putExtra("localidadSeleccionada", localidades[position]);
                intent.putExtra("fechaCorte", fechasCorte[position]);

                startActivity(intent); // Lanza la nueva actividad
            }
        };

        // Asignar el listener y tag a todas las localidades (esto no cambia)
        TextView iraantonio = findViewById(R.id.textViewAntonioNariño);
        iraantonio.setTag(0);
        iraantonio.setOnClickListener(localidadClickListener);

        TextView irabarrios = findViewById(R.id.textViewBarriosUnidos);
        irabarrios.setTag(1);
        irabarrios.setOnClickListener(localidadClickListener);

        TextView iraBosa = findViewById(R.id.textViewbosa);
        iraBosa.setTag(2);
        iraBosa.setOnClickListener(localidadClickListener);

        TextView iracandelaria = findViewById(R.id.textViewCandelaria);
        iracandelaria.setTag(3);
        iracandelaria.setOnClickListener(localidadClickListener);

        TextView irachapinero = findViewById(R.id.textViewChapinero);
        irachapinero.setTag(4);
        irachapinero.setOnClickListener(localidadClickListener);

        TextView irabolivar = findViewById(R.id.textViewCiudadBolivar);
        irabolivar.setTag(5);
        irabolivar.setOnClickListener(localidadClickListener);

        TextView iraengativa = findViewById(R.id.textViewEngativa);
        iraengativa.setTag(6);
        iraengativa.setOnClickListener(localidadClickListener);

        TextView irafontibon = findViewById(R.id.textViewFontibon);
        irafontibon.setTag(7);
        irafontibon.setOnClickListener(localidadClickListener);

        TextView irakennedy = findViewById(R.id.textViewKennedy);
        irakennedy.setTag(8);
        irakennedy.setOnClickListener(localidadClickListener);

        TextView iramartires = findViewById(R.id.textViewMartires);
        iramartires.setTag(9);
        iramartires.setOnClickListener(localidadClickListener);

        TextView irapuentearanda = findViewById(R.id.textViewPuenteAranda);
        irapuentearanda.setTag(10);
        irapuentearanda.setOnClickListener(localidadClickListener);

        TextView irrefaelUribe = findViewById(R.id.textViewRafaelUribe);
        irrefaelUribe.setTag(11);
        irrefaelUribe.setOnClickListener(localidadClickListener);

        TextView irasancristobal = findViewById(R.id.textViewSanCristobal);
        irasancristobal.setTag(12);
        irasancristobal.setOnClickListener(localidadClickListener);

        TextView irasantafe = findViewById(R.id.textViewSantaFe);
        irasantafe.setTag(13);
        irasantafe.setOnClickListener(localidadClickListener);

        TextView irasuba = findViewById(R.id.textViewSuba);
        irasuba.setTag(14);
        irasuba.setOnClickListener(localidadClickListener);

        TextView irasumapaz = findViewById(R.id.textViewSumapaz);
        irasumapaz.setTag(15);
        irasumapaz.setOnClickListener(localidadClickListener);

        TextView irateusaquillo = findViewById(R.id.textViewTeusaquillo);
        irateusaquillo.setTag(16);
        irateusaquillo.setOnClickListener(localidadClickListener);

        TextView iratunjuelito = findViewById(R.id.textViewTunjuelito);
        iratunjuelito.setTag(17);
        iratunjuelito.setOnClickListener(localidadClickListener);

        TextView irausaquen = findViewById(R.id.textViewUsaquen);
        irausaquen.setTag(18);
        irausaquen.setOnClickListener(localidadClickListener);

        TextView irausme = findViewById(R.id.textViewUsme);
        irausme.setTag(19);
        irausme.setOnClickListener(localidadClickListener);

        // Configurar el click listener del botón de inicio
        volverinicio.setOnClickListener(v -> {
            Intent intent = new Intent(MapadeBogota.this, Inicio.class);
            startActivity(intent);
        });
    }
}


