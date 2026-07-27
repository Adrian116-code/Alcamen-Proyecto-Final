package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Proveedor;
import com.example.alcamenproyectofinal.R;

import java.util.List;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ViewHolder> {

    private List<Proveedor> listaProveedores;

    public ProveedorAdapter(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
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

        // Usando getRazon_social() coincidente con tu modelo
        holder.tvRazonSocial.setText(p.getRazon_social());
        holder.tvContacto.setText("Contacto: " + p.getContacto());

        String detalles = "Teléfono: " + p.getTelefono() + " | Cód: " + p.getCodigo_proveedor();
        holder.tvDetallesProveedor.setText(detalles);
    }

    @Override
    public int getItemCount() {
        return listaProveedores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRazonSocial, tvContacto, tvDetallesProveedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRazonSocial = itemView.findViewById(R.id.tvRazonSocial);
            tvContacto = itemView.findViewById(R.id.tvContacto);
            tvDetallesProveedor = itemView.findViewById(R.id.tvDetallesProveedor);
        }
    }
}