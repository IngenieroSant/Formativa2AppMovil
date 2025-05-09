package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LibroCursorAdapter extends CursorAdapter {

    public LibroCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    // ViewHolder pattern para mejorar rendimiento
    private static class ViewHolder {
        TextView txtCodigo;
        TextView txtNombre;
        TextView txtAutor;
        TextView txtEditorial;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.fila_libro, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.txtCodigo = view.findViewById(R.id.txtcodigo);
        holder.txtNombre = view.findViewById(R.id.txtnombre);
        holder.txtAutor = view.findViewById(R.id.txtautor);
        holder.txtEditorial = view.findViewById(R.id.txteditorial);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(@NonNull View view, @NonNull Context context, @NonNull Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        // Usamos nombres de columnas para mayor claridad
        String codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"));
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
        String editorial = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));

        holder.txtCodigo.setText(codigo);
        holder.txtNombre.setText(nombre);
        holder.txtAutor.setText(autor);
        holder.txtEditorial.setText(editorial);
    }
}