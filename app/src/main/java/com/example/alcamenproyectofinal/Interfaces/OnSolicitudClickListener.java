package com.example.alcamenproyectofinal.Interfaces;

import com.example.alcamenproyectofinal.Modelo.Solicitud;
import com.example.alcamenproyectofinal.R;

public interface OnSolicitudClickListener {

    void onEditarClick(Solicitud solicitud, int posicion);
    void onEliminarClick(Solicitud solicitud, int posicion);


}
