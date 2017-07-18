package com.example.juan.questao2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button calcular;
    EditText adultosText, criancasText;
    TextView boloText, doceText, salgadoText, refriText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adultosText = (EditText) findViewById(R.id.textAdulto);
        criancasText = (EditText) findViewById(R.id.textCrianca);
        calcular = (Button) findViewById(R.id.calcular);
        boloText = (TextView) findViewById(R.id.quantBolo);
        doceText = (TextView) findViewById(R.id.quantDoce);
        salgadoText = (TextView) findViewById(R.id.quantSalgado);
        refriText = (TextView) findViewById(R.id.quantRefri);

        calcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("teste", "alguem?");
                try{
                    Double adultoValor = Double.parseDouble(adultosText.getText().toString());
                    Double criancaValor = Double.parseDouble(criancasText.getText().toString());
                    tratarCalculo(adultoValor, criancaValor);
                }catch (NumberFormatException ex){
                    tratarCalculo(0.0, 0.0);
                    Toast.makeText(getApplicationContext(), "algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void tratarCalculo(Double valorA, Double valorC){
            boloText.setText(String.valueOf(valorA * 0.6 + valorC * 0.4));
            doceText.setText(String.valueOf(valorA * 8.0 + valorC * 6.0));
            salgadoText.setText(String.valueOf(valorA * 6.0 + valorC * 4.0));
            refriText.setText(String.valueOf(valorA * 0.6 + valorC * 0.5));
    }
}
