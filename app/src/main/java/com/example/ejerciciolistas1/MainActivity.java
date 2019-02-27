package com.example.ejerciciolistas1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> sitios; // Declaro el arrayLst que va a contener los sitios
    private ListView milista; // declaro la lista
    private ArrayAdapter<String> adaptador1; //Declaro el adaptador necesario cuando tenemos listas para actualizarla

    Button boton; // Declaramos el boton, para ir al Main2Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creo el array y añado sitios ya por defecto
        sitios= new ArrayList<>();
        sitios.add("París");
        sitios.add("Manchester");
        sitios.add("Lisboa");

        // Ahora al adaptador le decimos que obtenga los valores del arrayList llamado sitios

        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sitios);

        milista= findViewById(R.id.lista_final); // A la lista declarada le digo su id
        milista.setAdapter(adaptador1); //Aqui le digo que el adaptador1 actualice la lista

        boton = findViewById(R.id.boton_ir_añadir);  // Le decimos a que id se refiere el boton que se usa para ir al MAin2Activity

        //Ahora le decimos a este boton que vaya al Main2Activity, pero con startActivityForResult para que nos devuelva un resultado de ese Main2Activity
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent, 2);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2) {
            String message = data.getStringExtra("MESSAGE"); // recibimos el valor del EditText del Main2Activity
            sitios.add(message); // Aqui le paso el valor  que devuelve el activity result (message) y lo añado al array
            adaptador1.notifyDataSetChanged(); // Actualizamos la lista

        }

    }

}


/*      // Como lo tenia antes,
            //textView.setText(message); // Aqui ponemos el valor del EditText del Main2Activity al TextView
            //sitios.add(textView.getText().toString()); // Aqui le añado el valor del textView el cual es el valor del Edittext del Main2Activity
            //adaptador1.notifyDataSetChanged(); // Actualizamos la lista

            */