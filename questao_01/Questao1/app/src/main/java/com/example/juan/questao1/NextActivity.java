package com.example.juan.questao1;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class NextActivity extends AppCompatActivity {

    private Switch sw;
    private TextView tw, msg;
    private Button tabela, listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        sw = (Switch) findViewById(R.id.switch1);
        listview = (Button) findViewById(R.id.listview);
        tabela = (Button) findViewById(R.id.tabela);
        tw = (TextView) findViewById(R.id.texttomada);
        msg = (TextView) findViewById(R.id.msg);
        msg.setText(getIntent().getExtras().getString("mensagem"));

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked){
                if(isChecked)
                    tw.setText("Acendeu!");
                else
                    tw.setText("Apagou!");
            }

        });

        listview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class));
            }
        });

        tabela.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), TableActivity.class));
            }
        });
    }
}
