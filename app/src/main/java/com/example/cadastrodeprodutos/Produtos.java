package com.example.cadastrodeprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cadastrodeprodutos.dao.ProdutoDao;
import com.example.cadastrodeprodutos.modelo.Produto;

import java.util.ArrayList;

public class Produtos extends AppCompatActivity  {
    ListView listaProdutos;

    ProdutoDao pDao;
    ArrayList<Produto> arrayListProduto;
    ArrayAdapter<Produto> arrayAdapterProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);




        listaProdutos = findViewById(R.id.listaPessoas);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto p = arrayAdapterProduto.getItem(position);

                Intent i = new Intent(Produtos.this,ConsultaProdutos.class);
                i.putExtra("Dados Enviados",p);
                startActivity(i);

            }
        });

    }


    public void produtoLista(){
        pDao = new ProdutoDao(this);
        arrayListProduto = pDao.selectAllProduto();
        pDao.close();

        if(listaProdutos !=null){
            arrayAdapterProduto = new ArrayAdapter<Produto>(this,android.R.layout.simple_expandable_list_item_1,arrayListProduto);
            listaProdutos.setAdapter(arrayAdapterProduto);

        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        produtoLista();

    }
}
