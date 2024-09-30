package com.example.aguazero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaLocalidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_localidades);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Definimos los botones
        Button btnLocalidadantonio = findViewById(R.id.buttonAntonio);
        Button btnLocalidadbarrios = findViewById(R.id.buttonbarriosunidos);
        Button btnLocalidadbosa = findViewById(R.id.buttonbosa);
        Button btnLocalidadcandelaria = findViewById(R.id.buttonlacandelaria);
        Button btnLocalidadchapinero = findViewById(R.id.buttonchapinero);
        Button btnLocalidadciudadbolivar = findViewById(R.id.buttonciudadbolivar);
        Button btnLocalidadengativa = findViewById(R.id.buttonengativa);
        Button btnLocalidadfontibon = findViewById(R.id.buttonfontibon);
        Button btnLocalidadkennedy = findViewById(R.id.buttonkennedy);
        Button btnLocalidad1martires = findViewById(R.id.buttonmartires);
        Button btnLocalidadpuentearanda = findViewById(R.id.buttonpuentearanda);
        Button btnLocalidadrafael = findViewById(R.id.buttonrafaeluribe);
        Button btnLocalidadsancristobal = findViewById(R.id.buttonsancristobal);
        Button btnLocalidadsantafe = findViewById(R.id.buttonsantafe);
        Button btnLocalidadsuba = findViewById(R.id.buttonsuba);
        Button btnLocalidadsumapaz = findViewById(R.id.buttonsumapaz);
        Button btnLocalidadteusaquillo = findViewById(R.id.buttonteusaquillo);
        Button btnLocalidadtunjuelito = findViewById(R.id.buttontunjuelito);
        Button btnLocalidadusaquen = findViewById(R.id.buttonusaquen);
        Button btnLocalidadusme = findViewById(R.id.buttonusme);
        // Agrega más botones si es necesario...

        // Asignamos los listeners usando el método setButtonListener
        setButtonListener(btnLocalidadantonio, 0);
        setButtonListener(btnLocalidadbarrios, 1);
        setButtonListener(btnLocalidadbosa, 2);
        setButtonListener(btnLocalidadcandelaria, 3);
        setButtonListener(btnLocalidadchapinero, 4);
        setButtonListener(btnLocalidadciudadbolivar, 5);
        setButtonListener(btnLocalidadengativa, 6);
        setButtonListener(btnLocalidadfontibon, 7);
        setButtonListener(btnLocalidadkennedy, 8);
        setButtonListener(btnLocalidad1martires, 9);
        setButtonListener(btnLocalidadpuentearanda, 10);
        setButtonListener(btnLocalidadrafael, 11);
        setButtonListener(btnLocalidadsancristobal, 12);
        setButtonListener(btnLocalidadsantafe, 13);
        setButtonListener(btnLocalidadsuba, 14);
        setButtonListener(btnLocalidadsumapaz, 15);
        setButtonListener(btnLocalidadteusaquillo, 16);
        setButtonListener(btnLocalidadtunjuelito, 17);
        setButtonListener(btnLocalidadusaquen, 18);
        setButtonListener(btnLocalidadusme, 19);
        // Repite este patrón para los demás botones
    }

    // Método que asigna el listener al botón y le pasa la posición al abrir la actividad
    private void setButtonListener(Button button, final int position) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoActivity(position);
            }
        });
    }

    // Método para abrir InfoActivity y enviar la posición
    private void openInfoActivity(int position) {
        Intent intent = new Intent(ListaLocalidades.this, Infolocalidades.class);
        intent.putExtra("position", position); // Pasamos la posición seleccionada
        startActivity(intent);
    }
}
