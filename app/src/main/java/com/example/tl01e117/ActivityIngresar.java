package com.example.tl01e117;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tl01e117.Configuracion.SQLiteConexion;
import com.example.tl01e117.Configuracion.Transacciones;

public class ActivityIngresar extends AppCompatActivity {
    Spinner tipo_pais;

    EditText id, nombre, notas, numero,codigo,rub;
    Button btnguardar,btnContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        tipo_pais = (Spinner) findViewById(R.id.txt_pais);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pais, android.R.layout.simple_spinner_item);
        tipo_pais.setAdapter(adapter);

        tipo_pais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("pais", "" + parent.getItemAtPosition(position));
                Mandarcodigo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("pais", "Sin pais seleccionado");
            }
        });



            try {

                id = (EditText) findViewById(R.id.txtId);
                nombre = (EditText)  findViewById(R.id.txtNombre);
                numero = (EditText) findViewById(R.id.txtNumero);
                tipo_pais = (Spinner) findViewById(R.id.txt_pais);
                notas = (EditText) findViewById(R.id.txtNota);
                btnguardar = (Button)findViewById(R.id.btnGuardar);
                btnContactos = (Button)findViewById(R.id.btnContactos);
                rub = (EditText)findViewById(R.id.txtNumeroPais);

                btnContactos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // llamar a lista
                        Intent Lista = new Intent(getApplicationContext(), ActivityList.class);
                        startActivity(Lista);
                    }
                });




                btnguardar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                     ValidarCampos();

                    }
                });
            }
            catch (Exception ex)
            {
                Toast.makeText(this, ex.toString(),Toast.LENGTH_SHORT).show();
            }

            }








    private void AgregarContactos()
    {
        try {

            Mandarcodigo();
            SQLiteConexion conexion = new SQLiteConexion(this,
                    Transacciones.NameDatabase,
                    null,
                    1);

            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombre, nombre.getText().toString());
            valores.put(Transacciones.notas, notas.getText().toString());
            valores.put(Transacciones.numero, numero.getText().toString());
            valores.put(Transacciones.codigo, rub.getText().toString());

            Long resultado = db.insert(Transacciones.tablacontactos, Transacciones.id, valores);

            Toast.makeText(this, "Ingresado con exito", Toast.LENGTH_SHORT).show();
        CleanPantalla();
            
        }
        catch (Exception ex)
        {
            ex.toString();
        }

    }
    private void CleanPantalla() {
        nombre.setText("");
        numero.setText("");
        notas.setText("");

    }
    private void Mandarcodigo()
    {    int tip_ru = tipo_pais.getSelectedItemPosition();

        if (tip_ru == 0) {
            rub.setText("504");
        }
        if(tip_ru == 1) {
            rub.setText("506");
        }
        if(tip_ru == 2) {
            rub.setText("503");
        }
        if(tip_ru == 3) {
            rub.setText("502");
        }
    }

    private void ValidarCampos()
    {
        String nombres = nombre.getText().toString();
        String numeros = numero.getText().toString();
        String nota = notas.getText().toString();

        if (nombres.length()==0){
            Toast.makeText(this,"DEBE INGRESAR UN NOMBRE",Toast.LENGTH_LONG).show();
        }
        if (numeros.length()==0){
            Toast.makeText(this,"DEBE INGRESAR UN NUMERO",Toast.LENGTH_LONG).show();
        }
        if (nota.length()==0){
            Toast.makeText(this,"DEBE INGRESAR UNA NOTA",Toast.LENGTH_LONG).show();
        }
        if (nombres.length()!=0 && numeros.length()!=0 && nota.length()!=0 ){
            AgregarContactos();
         }
}
}
