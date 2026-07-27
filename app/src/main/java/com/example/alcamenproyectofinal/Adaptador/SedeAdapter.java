package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Sede;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class SedeAdapter extends RecyclerView.Adapter<SedeAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Sede sede);
    }

    private List<Sede> listaSedes;
    private OnItemClickListener listener;

    // Constructor doble por si deseas pasar listener o solo la lista
    public SedeAdapter(List<Sede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    public SedeAdapter(List<Sede> listaSedes, OnItemClickListener listener) {
        this.listaSedes = listaSedes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sede, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sede s = listaSedes.get(position);

        // Adapta los getters según tu modelo de Sede
        holder.tvNombreSede.setText(s.getNombre());
        holder.tvDireccionSede.setText("Dirección: " + s.getDireccion());
        holder.tvCodigoSede.setText("Código: " + s.getCodigo_sede());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSedes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreSede, tvDireccionSede, tvCodigoSede;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreSede = itemView.findViewById(R.id.tvNombreSede);
            tvDireccionSede = itemView.findViewById(R.id.tvDireccionSede);
            tvCodigoSede = itemView.findViewById(R.id.tvCodigoSede);
        }
    }
}