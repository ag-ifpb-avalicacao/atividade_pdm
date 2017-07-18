package com.example.juan.questao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.letItGo);
        edit = (EditText) findViewById(R.id.msg);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String texto = edit.getText().toString();
                Intent i = new Intent(getApplicationContext(), NextActivity.class);
                i.putExtra("mensagem", texto);
                startActivity(i);
            }
        });
    }

}
