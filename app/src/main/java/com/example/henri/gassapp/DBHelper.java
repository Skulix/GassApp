package com.example.henri.gassapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by henri on 28/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    protected static final String NOME_BANCO = "GasApp.db";
    protected static final int VERSAO_BANCO = 1;
    protected static final String NOME_TABELA_POSTO =   "Postos";

    protected final static String COLUNA_CODIGO =       "PostoCodigo";
    protected final static String COLUNA_NOME =         "PostoNome";
    protected final static String COLUNA_ENDERECO =     "PostoEndereco";
    protected final static String COLUNA_BAIRRO =       "PostoBairro";
    protected final static String COLUNA_DT_PESQUISA =  "PostoDt_Pesquisa";
    protected final static String COLUNA_BANDEIRA =     "PostoBandeira";
    protected final static String COLUNA_GASOLINA =     "PostoGasolina";
    protected final static String COLUNA_ALCOOL =       "PostoAlcool";
    protected final static String COLUNA_DIESEL =       "PostoDiesel";
    protected final static String COLUNA_GNV =          "PostoGnv";
    protected final static String COLUNA_LAT =          "PostoLat";
    protected final static String COLUNA_LON =          "PostoLon";
    protected final static String COLUNA_DISTANCIA =    "PostoDistancia";
    protected final static String COLUNA_TELEFONE =     "PostoTelefone";
    protected final static String COLUNA_CIDADE =       "PostoCidade";

    public DBHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIA_TABELA_PRODUTOS = "CREATE TABLE " +
                NOME_TABELA_POSTO + "(" +
                COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUNA_NOME + " TEXT," +
                COLUNA_ENDERECO + " TEXT," +
                COLUNA_BAIRRO + " TEXT," +
                COLUNA_TELEFONE + " TEXT," +
                COLUNA_DT_PESQUISA + " TEXT," +
                COLUNA_BANDEIRA + " TEXT," +
                COLUNA_GASOLINA + " REAL," +
                COLUNA_ALCOOL + " REAL," +
                COLUNA_DIESEL + " REAL," +
                COLUNA_GNV + " REAL," +
                COLUNA_LAT + " REAL," +
                COLUNA_LON + " REAL," +
                COLUNA_DISTANCIA + " REAL," +
                COLUNA_CIDADE + " TEXT" +
                ")";
        db.execSQL(CRIA_TABELA_PRODUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ NOME_TABELA_POSTO);
        onCreate(db);
    }

    public void drop(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS "+ NOME_TABELA_POSTO);
        onCreate(this.getWritableDatabase());
    }

}
