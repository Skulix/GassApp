package com.example.henri.gassapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by henri on 28/04/2016.
 */
public class controllerPosto {
    private SQLiteDatabase db;
    private DBHelper banco;

    public controllerPosto(Context context){
        banco = new DBHelper(context);
    }

    public String inserePostoObjeto(modelPosto posto){
        ContentValues valores;
        long resultado = 0;

        try {
            db = banco.getWritableDatabase();

            valores = new ContentValues();
            valores.put(DBHelper.COLUNA_NOME, posto.getNome());
            valores.put(DBHelper.COLUNA_ENDERECO, posto.getEndereco());
            valores.put(DBHelper.COLUNA_GASOLINA, posto.getGasolina());
            valores.put(DBHelper.COLUNA_ALCOOL, posto.getAlcool());
            valores.put(DBHelper.COLUNA_DIESEL, posto.getDiesel());

            resultado = db.insert(DBHelper.NOME_TABELA_POSTO, null, valores);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            db.close();
        }

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro inserido com sucesso";
    }

    public String inserePosto(
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

    public void dropDatabase(){
        banco.drop();
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

    public ArrayList<modelPosto> preencheSpinner(){
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

        try {
            db = banco.getReadableDatabase();
            cursor = db.query(banco.NOME_TABELA_POSTO, campos, null, null, null, null, banco.COLUNA_GASOLINA + " ASC", null);

            ArrayList<modelPosto> postos = new ArrayList<>();
            if (cursor != null) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    postos.add(buscaLinha(cursor));
                }
            }
            db.close();
            return postos;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public modelPosto buscaLinha(Cursor cursor){
        modelPosto p = new modelPosto();
        p.setCodigo(Integer.parseInt(cursor.getString(0)));
        p.setNome(cursor.getString(1));
        p.setEndereco(cursor.getString(2));
        p.setBairro(cursor.getString(3));
        p.setDt_pesquisa(cursor.getString(4));
        p.setBandeira(cursor.getString(5));
        p.setGasolina(cursor.getString(6) != null? Float.parseFloat(cursor.getString(6)) : 0);
        p.setAlcool(cursor.getString(7)!= null? Float.parseFloat(cursor.getString(7)) : 0);
        p.setDiesel(cursor.getString(8)!= null? Float.parseFloat(cursor.getString(8)) : 0);
        p.setGnv(cursor.getString(9)!= null? Float.parseFloat(cursor.getString(9)) : 0);
        p.setLat(cursor.getString(10)!= null? Float.parseFloat(cursor.getString(10)) : 0);
        p.setLon(cursor.getString(11)!= null? Float.parseFloat(cursor.getString(11)) : 0);
        p.setDistancia(cursor.getString(12)!= null? Float.parseFloat(cursor.getString(12)) : 0);
        p.setTelefone(cursor.getString(13));
        p.setCidade(cursor.getString(14));
        return p;
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
