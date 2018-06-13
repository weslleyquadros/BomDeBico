package com.example.weslleyq.bomdebico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    public TextView campoTexto, detalheDescricao, detalheFone;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);



        campoTexto = (TextView)findViewById(R.id.CampoTexto);
        detalheDescricao = (TextView) findViewById(R.id.detalheDescricao);
        detalheFone = (TextView) findViewById(R.id.detalheFone);

        String Titulo = this.getIntent().getStringExtra("Titulo");
        String Descricao = this.getIntent().getStringExtra("Descricao");
        String Telefone = this.getIntent().getStringExtra("Telefone");
        //String Detalhes = this.getIntent().getStringExtra("Nome");
        //String Data = this.getIntent().getStringExtra("Nome");
        campoTexto.setText(Titulo);
        detalheDescricao.setText(Descricao);
        detalheFone.setText(Telefone);
    }
}
