package com.example.henri.gassapp;

/**
 * Created by henri on 28/04/2016.
 */
public class modelPosto {
    private int codigo;
    private String nome;
    private String endereco;
    private String bairro;
    private String dt_pesquisa;
    private String bandeira;
    private float gasolina;
    private float alcool;
    private float diesel;
    private float gnv;
    private float lat;
    private float lon;
    private float distancia;
    private String telefone;
    private String cidade;
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDt_pesquisa() {
        return dt_pesquisa;
    }

    public void setDt_pesquisa(String dt_pesquisa) {
        this.dt_pesquisa = dt_pesquisa;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public float getGasolina() {
        return gasolina;
    }

    public void setGasolina(float gasolina) {
        this.gasolina = gasolina;
    }

    public float getAlcool() {
        return alcool;
    }

    public void setAlcool(float alcool) {
        this.alcool = alcool;
    }

    public float getDiesel() {
        return diesel;
    }

    public void setDiesel(float diesel) {
        this.diesel = diesel;
    }

    public float getGnv() {
        return gnv;
    }

    public void setGnv(float gnv) {
        this.gnv = gnv;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }
}
