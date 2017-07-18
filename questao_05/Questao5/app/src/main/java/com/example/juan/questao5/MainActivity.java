package com.example.juan.questao5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                //adiciona uma informação ao intent para que na activity seguinte possa-se identificar
                //qual o usuario atual
                i.putExtra("badge", 1);
                startActivity(i);
            }
        });
        botao2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                //adiciona uma informação ao intent para que na activity seguinte possa-se identificar
                //qual o usuario atual
                i.putExtra("badge", 2);
                startActivity(i);
            }
        });
    }
}
