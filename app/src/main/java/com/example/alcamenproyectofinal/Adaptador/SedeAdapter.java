package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class SedeAdapter extends RecyclerView.Adapter<SedeAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onEditarClick(Sede sede, int position);
        void onEliminarClick(Sede sede, int position);
    }
    private List<Sede> listaSedes;
    private OnItemClickListener listener;

    public SedeAdapter(List<Sede> listaSedes, OnItemClickListener listener) {
        this.listaSedes = listaSedes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sede, parent, false); // Revisa que el layout coincida
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sede s = listaSedes.get(position);

        holder.tvNombreSede.setText(s.getNombre());
        holder.tvDireccionSede.setText("Dirección: " + s.getDireccion());
        holder.tvCodigoSede.setText("Código: " + s.getCodigo_sede());

        holder.btnEditarSede.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(s, holder.getAdapterPosition());
            }
        });

        holder.btnEliminarSede.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminarClick(s, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSedes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreSede, tvDireccionSede, tvCodigoSede;
        ImageButton btnEditarSede, btnEliminarSede;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Revisa que estas IDs coincidan con tu item_sede.xml
            tvNombreSede = itemView.findViewById(R.id.tvNombreSede);
            tvDireccionSede = itemView.findViewById(R.id.tvDireccionSede);
            tvCodigoSede = itemView.findViewById(R.id.tvCodigoSede);

            btnEditarSede = itemView.findViewById(R.id.btnEditarSede);
            btnEliminarSede = itemView.findViewById(R.id.btnEliminarSede);
        }
    }
}