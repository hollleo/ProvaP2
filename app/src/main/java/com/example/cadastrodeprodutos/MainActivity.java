package com.example.cadastrodeprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cadastrodeprodutos.dao.ProdutoDao;
import com.example.cadastrodeprodutos.modelo.Produto;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        {

     EditText edtValorProd;
     EditText edtNomeProd;
     Button btnCadastrar;
     Produto p1;
     ProdutoDao pDao;
     long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeProd = (EditText) findViewById(R.id.edtNomeProd);
        edtValorProd =(EditText) findViewById(R.id.edtValorProd);

        btnCadastrar = findViewById(R.id.btnpCadastrar);
        btnCadastrar.setOnClickListener(this);

        p1 = new Produto();
        pDao = new ProdutoDao(this);

        

    }

    @Override
            public void onClick (View v){
        p1.setNome(edtNomeProd.getText().toString());
        p1.setValor(Integer.parseInt(edtValorProd.getText().toString()));
        retornoDB = pDao.salvarProduto(p1);

        if(retornoDB == -1){
            alert("Erro ao cadastrar!");
        }else{
            alert("Cadastro realizado com sucesso");
        }

        Intent i = new Intent(this,Produtos.class);
        startActivity(i);

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }



    }


