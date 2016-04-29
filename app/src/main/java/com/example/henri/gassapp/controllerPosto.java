package com.example.henri.gassapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by henri on 28/04/2016.
 */
public class controllerPosto {
    private SQLiteDatabase db;
    private DBHelper banco;

    public controllerPosto(Context context){
        banco = new DBHelper(context);
    }

    public String inserePosto(
            int codigo,
            String nome,
            String endereco,
            String bairro,
            String dt_pesquisa,
            String bandeira,
            float gasolina,
            float alcool,
            float diesel,
            float gnv,
            float lat,
            float lon,
            float distancia,
            String telefone,
            String cidade) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBHelper.COLUNA_CODIGO, codigo);
        valores.put(DBHelper.COLUNA_NOME, nome);
        valores.put(DBHelper.COLUNA_ENDERECO, endereco);
        valores.put(DBHelper.COLUNA_BAIRRO, bairro);
        valores.put(DBHelper.COLUNA_DT_PESQUISA, dt_pesquisa);
        valores.put(DBHelper.COLUNA_BANDEIRA, bandeira);
        valores.put(DBHelper.COLUNA_GASOLINA, gasolina);
        valores.put(DBHelper.COLUNA_ALCOOL, alcool);
        valores.put(DBHelper.COLUNA_DIESEL, diesel);
        valores.put(DBHelper.COLUNA_GNV, gnv);
        valores.put(DBHelper.COLUNA_LAT, lat);
        valores.put(DBHelper.COLUNA_LON, lon);
        valores.put(DBHelper.COLUNA_DISTANCIA, distancia);
        valores.put(DBHelper.COLUNA_TELEFONE, telefone);
        valores.put(DBHelper.COLUNA_CIDADE, cidade);

        resultado = db.insert(DBHelper.NOME_TABELA_POSTO, null, valores);
        db.close();
        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro inserido com sucesso";
    }

    public void alteraRegistro(
            int codigo,
            String nome,
            String endereco,
            String bairro,
            String dt_pesquisa,
            String bandeira,
            float gasolina,
            float alcool,
            float diesel,
            float gnv,
            float lat,
            float lon,
            float distancia,
            String telefone,
            String cidade){
        ContentValues valores; String where;
        db = banco.getWritableDatabase();
        where = DBHelper.COLUNA_CODIGO + "=" + codigo;
        valores = new ContentValues();
        valores.put(DBHelper.COLUNA_NOME, nome);
        valores.put(DBHelper.COLUNA_ENDERECO, endereco);
        valores.put(DBHelper.COLUNA_BAIRRO, bairro);
        valores.put(DBHelper.COLUNA_DT_PESQUISA, dt_pesquisa);
        valores.put(DBHelper.COLUNA_BANDEIRA, bandeira);
        valores.put(DBHelper.COLUNA_GASOLINA, gasolina);
        valores.put(DBHelper.COLUNA_ALCOOL, alcool);
        valores.put(DBHelper.COLUNA_DIESEL, diesel);
        valores.put(DBHelper.COLUNA_GNV, gnv);
        valores.put(DBHelper.COLUNA_LAT, lat);
        valores.put(DBHelper.COLUNA_LON, lon);
        valores.put(DBHelper.COLUNA_DISTANCIA, distancia);
        valores.put(DBHelper.COLUNA_TELEFONE, telefone);
        valores.put(DBHelper.COLUNA_CIDADE, cidade);
        db.update(DBHelper.NOME_TABELA_POSTO,valores,where,null);
        db.close();
    }

    public void apagaRegistro(int postoCodigo){
        String where = DBHelper.COLUNA_CODIGO + "=" + postoCodigo;
        db = banco.getReadableDatabase();
        db.delete(DBHelper.NOME_TABELA_POSTO,where,null);
        db.close();
    }

    public Cursor preencheSpinner(){
        Cursor cursor;
        String[] campos = new String[] {
                                        banco.COLUNA_CODIGO,
                                        banco.COLUNA_NOME,
                                        banco.COLUNA_ENDERECO,
                                        banco.COLUNA_BAIRRO,
                                        banco.COLUNA_DT_PESQUISA,
                                        banco.COLUNA_BANDEIRA,
                                        banco.COLUNA_GASOLINA,
                                        banco.COLUNA_ALCOOL,
                                        banco.COLUNA_DIESEL,
                                        banco.COLUNA_GNV,
                                        banco.COLUNA_LAT,
                                        banco.COLUNA_LON,
                                        banco.COLUNA_DISTANCIA,
                                        banco.COLUNA_TELEFONE,
                                        banco.COLUNA_CIDADE };

        db = banco.getReadableDatabase();
        cursor = db.query(banco.NOME_BANCO, campos, null, null, null, null, banco.COLUNA_GASOLINA+" ASC", null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaRegistroUnico(int codigo){
        Cursor cursor;
        String[] campos = new String[] {
                banco.COLUNA_CODIGO,
                banco.COLUNA_NOME,
                banco.COLUNA_ENDERECO,
                banco.COLUNA_BAIRRO,
                banco.COLUNA_DT_PESQUISA,
                banco.COLUNA_BANDEIRA,
                banco.COLUNA_GASOLINA,
                banco.COLUNA_ALCOOL,
                banco.COLUNA_DIESEL,
                banco.COLUNA_GNV,
                banco.COLUNA_LAT,
                banco.COLUNA_LON,
                banco.COLUNA_DISTANCIA,
                banco.COLUNA_TELEFONE,
                banco.COLUNA_CIDADE };
        String where = DBHelper.COLUNA_CODIGO + "=" + codigo;
        db = banco.getReadableDatabase();
        cursor = db.query(DBHelper.NOME_TABELA_POSTO,campos,where, null, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }





}
