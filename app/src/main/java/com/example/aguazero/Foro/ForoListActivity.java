package com.example.aguazero.Foro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.aguazero.R;

public class ForoListActivity extends AppCompatActivity {

    private ForoManager foroManager;
    private ForoAdapter foroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_list);

        // Inicializamos ForoManager para manejar los datos de los foros
        foroManager = new ForoManager(this);

        // Configuramos el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewForos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializamos el adaptador y le pasamos la lista de foros
        foroAdapter = new ForoAdapter(foroManager.getForos(), foroManager, this); // Asegúrate de pasar el contexto
        recyclerView.setAdapter(foroAdapter);

        // Configuramos el FloatingActionButton para añadir un nuevo foro
        FloatingActionButton fab = findViewById(R.id.fab_add_foro);
        fab.setOnClickListener(view -> {
            // Abre la actividad CrearEditarForoActivity para crear un nuevo foro
            startActivity(new Intent(this, CrearEditarForoActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresca la lista de foros cuando regresa a esta actividad
        foroAdapter.updateForos(foroManager.getForos());
    }
}
