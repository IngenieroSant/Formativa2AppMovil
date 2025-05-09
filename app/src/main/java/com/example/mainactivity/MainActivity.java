package com.example.mainactivity;

import android.os.Bundle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Libro libro;
    LibroController libroController;
    EditText codigo, nombre, autor, editorial;
    Button agregar, cancelar, mostrar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        autor = findViewById(R.id.edtautor);
        editorial = findViewById(R.id.edteditorial);


        agregar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        mostrar = findViewById(R.id.btnlistado);
        eliminar = findViewById(R.id.btneliminar);

        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);

        libroController = new LibroController(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnguardar) {

            if (TextUtils.isEmpty(codigo.getText()) ||
                    TextUtils.isEmpty(nombre.getText()) ||
                    TextUtils.isEmpty(autor.getText()) ||
                    TextUtils.isEmpty(editorial.getText())) {

                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            } else {

                libro = new Libro(
                        codigo.getText().toString(),
                        nombre.getText().toString(),
                        autor.getText().toString(),
                        editorial.getText().toString()
                );

                if (libroController.buscarLibro(libro)) {
                    Toast.makeText(this, "¡El código ya existe!", Toast.LENGTH_LONG).show();
                } else {
                    libroController.agregarLibro(libro);
                    Toast.makeText(this, "Libro registrado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if (v.getId() == R.id.btnlistado) {

            startActivity(new Intent(this, ListadoActivity.class));
        }
        else if (v.getId() == R.id.btneliminar) {

            if (!TextUtils.isEmpty(codigo.getText())) {
                libroController.eliminarLibro(codigo.getText().toString());
                Toast.makeText(this, "Libro eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ingrese un código", Toast.LENGTH_LONG).show();
            }
        }
        else if (v.getId() == R.id.btncancelar) {

            codigo.setText("");
            nombre.setText("");
            autor.setText("");
            editorial.setText("");
        }
    }
}
