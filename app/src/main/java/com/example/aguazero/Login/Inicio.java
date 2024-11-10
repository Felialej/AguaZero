package com.example.aguazero.Login;

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

import com.example.aguazero.Emergencia_Hidrica;
import com.example.aguazero.Foro.ForoActivity;
import com.example.aguazero.Foro.ForoListActivity;
import com.example.aguazero.Localidades.ListaUbicacion;
import com.example.aguazero.Localidades.MapadeBogota;
import com.example.aguazero.R;

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

        TextView tvLocalidadInicio = findViewById(R.id.textViewnombrelocalidadinicio);
        TextView tvFechaMiLocalidad = findViewById(R.id.textViewfechamilocalidad);


        Intent intent = getIntent();
        String localidadFavorita = intent.getStringExtra("localidadFavorita");
        String fechaCorteFavorita = intent.getStringExtra("fechaCorteFavorita");

        if (localidadFavorita != null) {
            tvLocalidadInicio.setText(localidadFavorita);
        }

        if (fechaCorteFavorita != null) {
            tvFechaMiLocalidad.setText(fechaCorteFavorita);
        }

        Button listaubicaciones = findViewById(R.id.buttonlistadeubicaciones);
        ImageButton editarperfil = findViewById(R.id.imageButtoneditarperfil);
        ImageButton iramapa = findViewById(R.id.imageButtonmapa);
        Button Reportemergencia = findViewById(R.id.buttonreportaremergencia);
        ImageButton imageButtonforo = findViewById(R.id.imageButtonforo);

        imageButtonforo.setOnClickListener(v -> {
            Intent foroIntent = new Intent(Inicio.this, ForoListActivity.class);
            startActivity(foroIntent);
        });

        Reportemergencia.setOnClickListener(v -> {
                    Intent reportIntent = new Intent(Inicio.this, Emergencia_Hidrica.class);
                    startActivity(reportIntent);
                });

        listaubicaciones.setOnClickListener(v -> {
            Intent listIntent = new Intent(Inicio.this, ListaUbicacion.class);
            startActivity(listIntent);
        });

        editarperfil.setOnClickListener(v -> {
            Intent editIntent = new Intent(Inicio.this, EditarPerfil.class);
            startActivity(editIntent);
        });


        iramapa.setOnClickListener(v -> {
            Intent mapIntent = new Intent(Inicio.this, MapadeBogota.class);
            startActivity(mapIntent);
        });
    }
}

