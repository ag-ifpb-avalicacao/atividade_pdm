package com.example.juan.questao5;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botao1, botao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao1 = (Button) findViewById(R.id.pessoa1);
        botao2 = (Button) findViewById(R.id.pessoa2);
        botao1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(verificaConexao()) {
                    Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                    //adiciona uma informação ao intent para que na activity seguinte possa-se identificar
                    //qual o usuario atual
                    i.putExtra("badge", 1);
                    startActivity(i);
                }else
                    Toast.makeText(MainActivity.this, "voce nao esta conectado a internet", Toast.LENGTH_LONG).show();
            }
        });
        botao2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(verificaConexao()) {
                    Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                    //adiciona uma informação ao intent para que na activity seguinte possa-se identificar
                    //qual o usuario atual
                    i.putExtra("badge", 2);
                    startActivity(i);
                }else
                    Toast.makeText(MainActivity.this, "voce nao esta conectado a internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
