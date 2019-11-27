package com.example.cadastrodeprodutos.modelo;

import android.widget.EditText;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private int valor;

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return nome;
    }
}
