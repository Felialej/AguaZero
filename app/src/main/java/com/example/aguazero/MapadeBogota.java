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

import org.w3c.dom.Text;

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

        // Crear un único OnClickListener para todas las localidades
        View.OnClickListener localidadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el tag de la vista que fue clickeada (que contiene la posición)
                int position = (int) v.getTag(); // Recuperar el tag como la posición

                // Crear el Intent para abrir la nueva actividad
                Intent intent = new Intent(MapadeBogota.this, Infolocalidades.class);
                // Enviar la posición seleccionada
                intent.putExtra("position", position);
                startActivity(intent); // Lanza la nueva actividad
            }
        };

        // Asignar el listener y tag a todas las localidades
        TextView iraantonio = findViewById(R.id.textViewAntonioNariño);
        iraantonio.setTag(0);  // Posición de Antonio Nariño
        iraantonio.setOnClickListener(localidadClickListener);

        TextView irabarrios = findViewById(R.id.textViewBarriosUnidos);
        irabarrios.setTag(1);  // Posición de Barrios Unidos
        irabarrios.setOnClickListener(localidadClickListener);

        TextView iraBosa = findViewById(R.id.textViewbosa);
        iraBosa.setTag(2);  // Posición de Bosa
        iraBosa.setOnClickListener(localidadClickListener);

        TextView iracandelaria = findViewById(R.id.textViewCandelaria);
        iracandelaria.setTag(3);  // Posición de Chapinero
        iracandelaria.setOnClickListener(localidadClickListener);

        TextView irachapinero = findViewById(R.id.textViewChapinero);
        irachapinero.setTag(4);  // Posición de Chapinero
        irachapinero.setOnClickListener(localidadClickListener);

        TextView irabolivar = findViewById(R.id.textViewCiudadBolivar);
        irabolivar.setTag(5);  // Posición de Bolivar
        irabolivar.setOnClickListener(localidadClickListener);

        TextView iraengativa = findViewById(R.id.textViewEngativa);
        iraengativa.setTag(6);  // Posición de Engativa
        iraengativa.setOnClickListener(localidadClickListener);

        TextView irafontibon = findViewById(R.id.textViewFontibon);
        irafontibon.setTag(7);  // Posición de Fontibón
        irafontibon.setOnClickListener(localidadClickListener);

        TextView irakennedy = findViewById(R.id.textViewKennedy);
        irakennedy.setTag(8);  // Posición de Kennedy
        irakennedy.setOnClickListener(localidadClickListener);

        TextView iramartires = findViewById(R.id.textViewMartires);
        iramartires.setTag(9);  // Posición de Martires
        iramartires.setOnClickListener(localidadClickListener);

        TextView irapuentearanda = findViewById(R.id.textViewPuenteAranda);
        irapuentearanda.setTag(10);  // Posición de Puente Aranda
        irapuentearanda.setOnClickListener(localidadClickListener);

        TextView irrefaelUribe = findViewById(R.id.textViewRafaelUribe);
        irrefaelUribe.setTag(11);  // Posición de Rafael Uribe
        irrefaelUribe.setOnClickListener(localidadClickListener);

        TextView irasancristobal = findViewById(R.id.textViewSanCristobal);
        irasancristobal.setTag(12);  // Posición de San Cristóbal
        irasancristobal.setOnClickListener(localidadClickListener);

        TextView irasantafe = findViewById(R.id.textViewSantaFe);
        irasantafe.setTag(13);  // Posición de Santa Fe
        irasantafe.setOnClickListener(localidadClickListener);

        TextView irasuba = findViewById(R.id.textViewSuba);
        irasuba.setTag(14);  // Posición de Suba
        irasuba.setOnClickListener(localidadClickListener);

        TextView irasumapaz = findViewById(R.id.textViewSumapaz);
        irasumapaz.setTag(15);  // Posición de Sumapaz
        irasumapaz.setOnClickListener(localidadClickListener);

        TextView irateusaquillo = findViewById(R.id.textViewTeusaquillo);
        irateusaquillo.setTag(16);  // Posición de Teusaquillo
        irateusaquillo.setOnClickListener(localidadClickListener);

        TextView iratunjuelito = findViewById(R.id.textViewTunjuelito);
        iratunjuelito.setTag(17);  // Posición de Tunjuelito
        iratunjuelito.setOnClickListener(localidadClickListener);

        TextView irausaquen = findViewById(R.id.textViewUsaquen);
        irausaquen.setTag(18);  // Posición de Usaquén
        irausaquen.setOnClickListener(localidadClickListener);

        TextView irausme = findViewById(R.id.textViewUsme);
        irausme.setTag(19);  // Posición de Usme
        irausme.setOnClickListener(localidadClickListener);


        // Configurar el click listener del botón de inicio
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
