package com.example.aguazero.Localidades;

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

import com.example.aguazero.Foro.ForoActivity;
import com.example.aguazero.Login.Inicio;
import com.example.aguazero.Login.EditarPerfil;
import com.example.aguazero.R;

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

        ImageButton volverinicio = findViewById(R.id.imageButtoninicio);
        ImageButton IraForo = findViewById(R.id.imageButtonforo);
        ImageButton IraEditarPerfil = findViewById(R.id.imageButtoneditarperfil);

        View.OnClickListener localidadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();

                Intent intent = new Intent(MapadeBogota.this, Seleccionar_mi_localidad.class);
                intent.putExtra("localidadSeleccionada", localidades[position]);
                intent.putExtra("fechaCorte", fechasCorte[position]);

                startActivity(intent);
            }
        };

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

        volverinicio.setOnClickListener(v -> {
            Intent intent = new Intent(MapadeBogota.this, Inicio.class);
            startActivity(intent);
        });
        IraForo.setOnClickListener(v -> {
            Intent intent = new Intent(MapadeBogota.this, ForoActivity.class);
            startActivity(intent);
        });
        IraEditarPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MapadeBogota.this, EditarPerfil.class);
            startActivity(intent);
        });

    }
}



