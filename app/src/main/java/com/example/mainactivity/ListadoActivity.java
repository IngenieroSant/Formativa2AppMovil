package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class ListadoActivity extends AppCompatActivity {

    private static final String TAG = "ListadoActivity_Debug";
    ListView listado;
    LibroController libroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        // 1. Verificar que el ListView existe
        listado = findViewById(R.id.lstlistado);
        if (listado == null) {
            Log.e(TAG, "ListView lstlistado no encontrado!");
            Toast.makeText(this, "Error interno: ListView no existe", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        libroController = new LibroController(this);

        // 2. Obtener datos y verificar cursor
        try {
            Log.d(TAG, "Intentando obtener libros...");
            Cursor c = libroController.allLibros();

            if (c == null) {
                Log.e(TAG, "Cursor es NULL!");
                Toast.makeText(this, "Error al acceder a la base de datos", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            // 3. Verificar columnas del cursor
            String[] columnas = c.getColumnNames();
            Log.d(TAG, "Columnas en el cursor: " + Arrays.toString(columnas));

            if (!Arrays.asList(columnas).contains(DefBD.col_editorial)) {
                Log.e(TAG, "Falta columna editorial en el cursor!");
            }

            // 4. Verificar conteo de datos
            Log.d(TAG, "Número de registros: " + c.getCount());

            // 5. Configurar adapter
            LibroCursorAdapter lcursor = new LibroCursorAdapter(this, c, false);
            listado.setAdapter(lcursor);

        } catch (Exception e) {
            Log.e(TAG, "EXCEPCIÓN: " + e.getMessage(), e);
            Toast.makeText(this, "Error crítico: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }

        // 6. Configurar clics
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // 7. Verificar que las vistas existen
                    TextView txtCodigo = view.findViewById(R.id.txtcodigo);
                    TextView txtNombre = view.findViewById(R.id.txtnombre);
                    TextView txtAutor = view.findViewById(R.id.txtautor);
                    TextView txtEditorial = view.findViewById(R.id.txteditorial);

                    if (txtCodigo == null || txtNombre == null || txtAutor == null || txtEditorial == null) {
                        Log.e(TAG, "Alguna vista es null!");
                        throw new RuntimeException("Vistas no encontradas en el layout");
                    }

                    // 8. Loggear datos
                    Log.d(TAG, "Datos seleccionados: " +
                            "Código: " + txtCodigo.getText() +
                            ", Nombre: " + txtNombre.getText() +
                            ", Autor: " + txtAutor.getText() +
                            ", Editorial: " + txtEditorial.getText());

                    // 9. Pasar datos a EdicionActivity
                    Intent i = new Intent(ListadoActivity.this, EdicionActivity.class);
                    i.putExtra("codigo", txtCodigo.getText().toString());
                    i.putExtra("nombre", txtNombre.getText().toString());
                    i.putExtra("autor", txtAutor.getText().toString());
                    i.putExtra("editorial", txtEditorial.getText().toString());

                    startActivity(i);

                } catch (Exception e) {
                    Log.e(TAG, "Error en clic: " + e.getMessage(), e);
                    Toast.makeText(ListadoActivity.this, "Error al seleccionar libro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
