package com.example.weslleyq.bomdebico;

/**
 * Created by WeslleyQ on 08/06/2018.
 */

public class inserirModel {


    public String titulo;
    public String descricao;
    public String telefone;
    public String datacadastro;


    public inserirModel(String titulo, String descricao, String telefone, String datacadastro) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.telefone = telefone;
        this.datacadastro = datacadastro;
    }

    public inserirModel(String s, String format) {
    }
}

