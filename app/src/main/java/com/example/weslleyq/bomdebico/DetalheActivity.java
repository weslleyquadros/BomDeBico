package com.example.weslleyq.bomdebico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    public TextView campoTexto;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);



        campoTexto = (TextView)findViewById(R.id.CampoTexto);

        String Titulo = this.getIntent().getStringExtra("Titulo");
        //String Detalhes = this.getIntent().getStringExtra("Nome");
        //String Data = this.getIntent().getStringExtra("Nome");
        campoTexto.setText(Titulo);
    }
}
