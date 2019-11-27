package com.example.cadastrodeprodutos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cadastrodeprodutos.dao.ProdutoDao;
import com.example.cadastrodeprodutos.modelo.Produto;

public class ConsultaProdutos extends AppCompatActivity implements View.OnClickListener{

    EditText edtcId;
    EditText edtcNome;
    EditText edtcValor;
    ProdutoDao pDao;
    Produto p,p1;
    Button btnAlterar;
    Button btnDeleter;
    long retornoDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_produtos);
        edtcId = findViewById(R.id.edtcId);
        edtcNome = findViewById(R.id.edtcNome);
        edtcValor = findViewById(R.id.edtcValor);

        btnAlterar = findViewById(R.id.btnAlterar);
        btnAlterar.setOnClickListener(this);

        btnDeleter = findViewById(R.id.btnDeletar);
        btnDeleter.setOnClickListener(this);

        pDao = new ProdutoDao(this);
        Intent i = getIntent();
        p = (Produto) i.getSerializableExtra("Dados Enviados");
        edtcId.setText(p.getId()+"");
        edtcNome.setText(p.getNome());
        edtcValor.setText(p.getValor()+"");

        //p1.setId(p.getId());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlterar:
                retornoDB = pDao.alterarProduto(p);
                if (retornoDB == -1) {
                    alert("Erro ao alterar!");

                } else {
                    alert("Atualização realizada com sucesso!");
                }
                finish();

                break;
            case R.id.btnDeletar:
                retornoDB = pDao.excluirProduto(p);
                if (retornoDB == -1) {
                    alert("Erro ao deletar!");

                } else {
                    alert("Produto deletado com sucesso!");
                }
                finish();
                break;
        }

        pDao.close();

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
