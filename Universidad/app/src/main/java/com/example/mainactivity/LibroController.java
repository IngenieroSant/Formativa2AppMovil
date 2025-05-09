package com.example.mainactivity;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class LibroController {
    private BaseDatos bd;
    private Context c;

    public LibroController(Context c) {
        this.bd = new BaseDatos(c, 1);
        this.c = c;
    }

    public void agregarLibro(Libro libro) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_codigo, libro.getCodigo());
            valores.put(DefBD.col_nombre, libro.getNombre());
            valores.put(DefBD.col_autor, libro.getAutor());
            valores.put(DefBD.col_editorial, libro.getEditorial());

            if (!buscarLibro(libro)) {
                SQLiteDatabase sql = bd.getWritableDatabase();
                long id = sql.insert(DefBD.tabla_lib, null, valores);
                Toast.makeText(c, "Libro registrado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(c, "El libro ya existe", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(c, "Error agregando libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarLibro( Libro libro) {
        String args[] = {libro.getCodigo()};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor cursor = sql.query(
                DefBD.tabla_lib,
                null,
                DefBD.col_codigo + "=?",
                args,
                null, null, null
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public boolean buscarLibro(String cod) {
        String args[] = {cod};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor cursor = sql.query(
                DefBD.tabla_lib,
                null,
                DefBD.col_codigo + "=?",
                args,
                null, null, null
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public Cursor allLibros() {
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select codigo as _id , nombre, autor, editorial from libros order by " + DefBD.col_codigo, null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta biblioteca " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }


    private Cursor crearCursorVacio() {
        return new MatrixCursor(new String[]{
                DefBD.col_codigo,
                DefBD.col_nombre,
                DefBD.col_autor,
                DefBD.col_editorial
        });
    }

    public void eliminarLibro(String cod) {
        try {
            String[] args = {cod};
            if (!buscarLibro(cod)) {
                Toast.makeText(c, "Código no existe", Toast.LENGTH_LONG).show();
            } else {
                SQLiteDatabase sql = bd.getWritableDatabase();
                sql.delete(DefBD.tabla_lib, DefBD.col_codigo + "=?", args);
                Toast.makeText(c, "Libro eliminado", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(c, "Error eliminando libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarLibro(Libro libro) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_nombre, libro.getNombre());
            valores.put(DefBD.col_autor, libro.getAutor());
            valores.put(DefBD.col_editorial, libro.getEditorial());

            String[] args = {libro.getCodigo()};
            sql.update(DefBD.tabla_lib, valores, DefBD.col_codigo + "=?", args);
            Toast.makeText(c, "Libro actualizado", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(c, "Error actualizando libro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
