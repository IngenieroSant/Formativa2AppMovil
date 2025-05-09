package com.example.mainactivity;


    public class DefBD {

        public static final String nameDb = "BIBLIOTECA";
        public static final String tabla_lib = "libros";


        public static final String col_codigo = "codigo";
        public static final String col_nombre = "nombre";
        public static final String col_autor = "autor";
        public static final String col_editorial = "editorial";


        public static final String create_tabla_lib =
                "CREATE TABLE IF NOT EXISTS " + tabla_lib + " ( " +
                        col_codigo + " TEXT PRIMARY KEY, " +
                        col_nombre + " TEXT NOT NULL, " +
                        col_autor + " TEXT NOT NULL, " +
                        col_editorial + " TEXT NOT NULL" +
                        ");";
    }

