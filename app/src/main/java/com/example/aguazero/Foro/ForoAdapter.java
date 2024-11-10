package com.example.aguazero.Foro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.aguazero.R;

public class ForoAdapter extends RecyclerView.Adapter<ForoAdapter.ForoViewHolder> {

    private List<Foro> foros;
    private ForoManager foroManager;
    private Context context;

    public ForoAdapter(List<Foro> foros, ForoManager foroManager, Context context) {
        this.foros = foros;
        this.foroManager = foroManager;
        this.context = context;
    }

    @NonNull
    @Override
    public ForoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foro, parent, false);
        return new ForoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForoViewHolder holder, int position) {
        Foro foro = foros.get(position);
        holder.titulo.setText(foro.getTitulo());
        holder.descripcion.setText(foro.getDescripcion());

        // Configuración del botón Editar
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, CrearEditarForoActivity.class);
            intent.putExtra("foro_index", position);
            intent.putExtra("titulo_foro", foro.getTitulo());
            intent.putExtra("descripcion_foro", foro.getDescripcion());
            context.startActivity(intent);
        });

        // Configuración del botón Eliminar
        holder.btnDelete.setOnClickListener(v -> {
            // Llama al método deleteForo de ForoManager para eliminar el foro de SharedPreferences
            foroManager.deleteForo(position);
            // Remueve el foro de la lista local
            foros.remove(position);
            // Notifica al adaptador que el item ha sido eliminado
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, foros.size()); // Actualiza las posiciones del resto de la lista
        });

        // Abrir ForoDetailActivity al hacer clic en el foro
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ForoDetailActivity.class);
            intent.putExtra("foro_index", position); // Pasar el índice del foro seleccionado
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foros.size();
    }

    // Método para actualizar la lista de foros
    public void updateForos(List<Foro> nuevosForos) {
        this.foros = nuevosForos;
        notifyDataSetChanged();
    }

    // Clase ViewHolder para representar cada foro en la lista
    static class ForoViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion;
        Button btnEdit, btnDelete;

        public ForoViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.foroTitulo);
            descripcion = itemView.findViewById(R.id.foroDescripcion);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
