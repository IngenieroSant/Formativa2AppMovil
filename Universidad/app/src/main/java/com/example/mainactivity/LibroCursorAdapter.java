    package com.example.mainactivity;


    import android.content.Context;
    import android.database.Cursor;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.CursorAdapter;
    import android.widget.TextView;

    import androidx.annotation.NonNull;

    public class LibroCursorAdapter extends CursorAdapter {

        public LibroCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false);
        }

        @Override
        public void bindView(@NonNull View view, Context context, @NonNull Cursor cursor) {

            TextView txtCodigo = view.findViewById(R.id.txtcodigo);
            TextView txtNombre = view.findViewById(R.id.txtnombre);
            TextView txtAutor = view.findViewById(R.id.txtautor);
            TextView txtEditorial = view.findViewById(R.id.txteditorial);
            String codigo = cursor.getString(0);
            String nombre = cursor.getString(1);
        String autor = cursor.getString(2);
        String editorial = cursor.getString(3);


        txtCodigo.setText(codigo);
        txtNombre.setText(nombre);
        txtAutor.setText(autor);
        txtEditorial.setText(editorial);
    }
}