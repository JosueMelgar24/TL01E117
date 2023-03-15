package com.example.tl01e117;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tl01e117.Configuracion.SQLiteConexion;
import com.example.tl01e117.Configuracion.Transacciones;
import com.example.tl01e117.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listacontactos;
    Button btnCompartir;
    ArrayList<Contactos> lista;
    ArrayList<String> ArregloContactos;
    private int menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        menu=1;

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listacontactos = (ListView) findViewById(R.id.listacontactos);

        btnCompartir = (Button) findViewById(R.id.btnCompartir);
        ObtenerListacontactos();





        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos );
        listacontactos.setAdapter(adp);
        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"El Id seleecionado es " + view.getTag(),Toast.LENGTH_SHORT).show();

               // Intent marcar = new Intent(getApplicationContext(), ActivityCall.class);
              //  startActivity(marcar);
            }


        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
           if (menu >0){
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);

                    intent.setType("text/plain");

                    Contactos obj = lista.get(menu);
                    String contacto= String.format("Nombre del contacto: %s\n Numero de telefono %s",obj.getNombre(),obj.getNumero());
                    intent.putExtra(Intent.EXTRA_TEXT, contacto);

                    startActivity(Intent.createChooser(intent,"Compartir contacto con:"));

                }catch (Exception e){
                e.printStackTrace();
                }

             }else {
               Toast.makeText(ActivityList.this,"Debe Seleccionar un registro",Toast.LENGTH_SHORT).show();
           }
             }
          });

    }


    private void ObtenerListacontactos()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos contac = null;
        lista = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablacontactos, null);

        while(cursor.moveToNext())
        {
            contac = new Contactos();
            contac.setId(cursor.getInt(0));
            contac.setNombre(cursor.getString(1));
            contac.setCodigo(cursor.getString(4));
            contac.setNotas(cursor.getString(3));
            contac.setNumero(cursor.getInt(2));

            lista.add(contac);
        }

        cursor.close();
        filllist();

    }

    private void filllist()
    {
        ArregloContactos = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++)
        {
            ArregloContactos.add(lista.get(i).getId() + " | "+
                    lista.get(i).getNombre() + " | " +
                    lista.get(i).getNumero() + " | " +
                    lista.get(i).getNotas() + " | " +
                    lista.get(i).getCodigo() + " | ");
        }
    }

}
