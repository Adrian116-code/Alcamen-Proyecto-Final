package com.example.alcamenproyectofinal.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alcamenproyectofinal.Modelo.Producto;
import com.example.alcamenproyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    public void setProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos != null ? listaProductos : new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onEditarClick(Producto producto, int position);
        void onEliminarClick(Producto producto, int position);
    }
    private List<Producto> listaProductos;
    private OnItemClickListener listener;
    public ProductoAdapter(List<Producto> listaProductos, OnItemClickListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto p = listaProductos.get(position);

        holder.tvNombreProducto.setText(p.getNombre());
        holder.tvDescripcion.setText(p.getDescripcion());

        String detalles = "Ubicación: " + p.getUbicacion() + " | Prov: " + p.getCodigo_proveedor() + " | Cód: " + p.getCodigo_producto();
        holder.tvDetallesProducto.setText(detalles);

        holder.btnEditarProducto.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(p, holder.getAdapterPosition());
            }
        });

        holder.btnEliminarProducto.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminarClick(p, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos != null ? listaProductos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreProducto, tvDescripcion, tvDetallesProducto;
        ImageButton btnEditarProducto, btnEliminarProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvDetallesProducto = itemView.findViewById(R.id.tvDetallesProducto);

            btnEditarProducto = itemView.findViewById(R.id.btnEditarProducto);
            btnEliminarProducto = itemView.findViewById(R.id.btnEliminarProducto);
        }
    }
}