package com.example.cadastrodeprodutos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cadastrodeprodutos.modelo.Produto;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ProdutoDao extends SQLiteOpenHelper implements Serializable {

    private static final String NOME_BANCO = "Produto.db";
    private static final int VERSION = 1;
    private static final String TABELA = "produto";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String VALOR = "valor";
    private SQLiteDatabase banco;


    public ProdutoDao(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase  db) {
        String sql = "CREATE TABLE "+TABELA+" ( " + " "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " "+NOME+" TEXT, "+VALOR+" INTEGER);";

    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        String sql = "DROP TABLE IS EXISTS "+TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarProduto(Produto p){
        ContentValues values = new ContentValues();
        long retornoDB;
        values.put(NOME, p.getNome());
        values.put(VALOR, p.getValor());
        retornoDB = getWritableDatabase().insert(TABELA,null,values);


        return retornoDB;
    }

    public long alterarProduto(Produto p){
        ContentValues values = new ContentValues();
       long retornoDB;
        values.put(NOME, p.getNome());
        values.put(VALOR, p.getValor());

        String[] args = {String.valueOf(p.getId())};
       retornoDB = getWritableDatabase().update(TABELA,values,"id=?",args);

       return retornoDB;

    }

    public long excluirProduto(Produto p){

        long retornoDB;
        String[] args = {String.valueOf(p.getId())};
        retornoDB = getWritableDatabase().delete(TABELA,"id=?",args);

        return retornoDB;
    }


    public ArrayList<Produto> selectAllProduto(){
        String[] coluns = {ID,NOME,VALOR};
        Cursor cursor = getWritableDatabase().query(TABELA,coluns,null,null,null,null,null,null);
        ArrayList<Produto> listProduto = new ArrayList<Produto>();
        while(cursor.moveToNext()){
            Produto p = new Produto();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setValor(cursor.getInt(2));

            listProduto.add(p);
        }

        return listProduto;
    }


}
