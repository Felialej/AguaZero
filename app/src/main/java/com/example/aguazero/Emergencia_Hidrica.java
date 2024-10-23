package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Emergencia_Hidrica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergencia_hidrica);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al botón de enviar informe
        Button enviarInformeButton = findViewById(R.id.submit_button);

        // Configurar el click listener del botón
        enviarInformeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el mensaje de Toast
                Toast.makeText(Emergencia_Hidrica.this, "El informe ha sido enviado.", Toast.LENGTH_SHORT).show();

                // Crear el Intent para volver a la pantalla de Inicio
                Intent intent = new Intent(Emergencia_Hidrica.this, Inicio.class);
                startActivity(intent); // Iniciar la actividad de Inicio
                finish(); // Cerrar la actividad actual para evitar regresar
            }
        });
    }
}
