package com.example.aguazero.Localidades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aguazero.Login.Inicio;
import com.example.aguazero.R;

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

        TextView tvLocalidadFavorita = findViewById(R.id.textViewnombrelocalidadfavorita);
        TextView tvFechaCorteFavorita = findViewById(R.id.textViewfecha3);
        Button buttonSeleccionarFavorita = findViewById(R.id.buttonseleccionarfavorita);

        String localidadSeleccionada = getIntent().getStringExtra("localidadSeleccionada");
        String fechaCorte = getIntent().getStringExtra("fechaCorte");

        if (localidadSeleccionada != null && fechaCorte != null) {
            tvLocalidadFavorita.setText(localidadSeleccionada);
            tvFechaCorteFavorita.setText(fechaCorte);
        }

        buttonSeleccionarFavorita.setOnClickListener(v -> {
            Intent intent = new Intent(Seleccionar_mi_localidad.this, Inicio.class);
            intent.putExtra("localidadFavorita", localidadSeleccionada);
            intent.putExtra("fechaCorteFavorita", fechaCorte);
            startActivity(intent);
        });
    }
}


