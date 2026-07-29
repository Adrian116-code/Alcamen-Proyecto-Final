package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onEditarClick(Proveedor proveedor, int position);
        void onEliminarClick(Proveedor proveedor, int position);
    }

    private List<Proveedor> listaProveedores;
    private OnItemClickListener listener;

    // Constructor actualizado para recibir el listener
    public ProveedorAdapter(List<Proveedor> listaProveedores, OnItemClickListener listener) {
        this.listaProveedores = listaProveedores;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proveedor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Proveedor p = listaProveedores.get(position);

        holder.tvRazonSocial.setText(p.getRazon_social());
        holder.tvContacto.setText("Contacto: " + p.getContacto());

        String detalles = "Teléfono: " + p.getTelefono() + " | Cód: " + p.getCodigo_proveedor();
        holder.tvDetallesProveedor.setText(detalles);

        // Clic en el botón Lápiz (Editar)
        holder.btnEditarProveedor.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(p, holder.getAdapterPosition());
            }
        });

        // Clic en el botón X (Eliminar)
        holder.btnEliminarProveedor.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminarClick(p, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProveedores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRazonSocial, tvContacto, tvDetallesProveedor;
        ImageButton btnEditarProveedor, btnEliminarProveedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRazonSocial = itemView.findViewById(R.id.tvRazonSocial);
            tvContacto = itemView.findViewById(R.id.tvContacto);
            tvDetallesProveedor = itemView.findViewById(R.id.tvDetallesProveedor);

            // Verifica que estas IDs coincidan con tu item_proveedor.xml
            btnEditarProveedor = itemView.findViewById(R.id.btnEditarProveedor);
            btnEliminarProveedor = itemView.findViewById(R.id.btnEliminarProveedor);
        }
    }
}