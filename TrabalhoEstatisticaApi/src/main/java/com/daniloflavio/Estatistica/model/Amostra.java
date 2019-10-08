package com.daniloflavio.Estatistica.model;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class Amostra {
    private HashMap<String, List<Integer>> registros;
    private double media;
    private String moda;
    private double mediana;

    public Amostra() {
        simulaRegistrosNumericos();
    }

    public void simulaRegistros(){
        zeraRegistros();
        insereRegistro("GalaxyA10",3 );
        insereRegistro("GalaxyA20", 10);
        insereRegistro("GalaxyA30",20);
        insereRegistro("GalaxyA40", 10);
        insereRegistro("GalaxyA50",17);
    }

    public void simulaRegistrosNumericos(){

        //Idade de crianças  (idade , frequencia)
        zeraRegistros();
        insereRegistro("0",4);
        insereRegistro("1",7);
        insereRegistro("2",10);
        insereRegistro("3",12);
        insereRegistro("4",5);
        insereRegistro("5",3);
    }

    public void insereRegistro(String nomeVariavel, int quantidade){
        List<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(quantidade);
        this.registros.put(nomeVariavel, listaNumeros);
    }

    public void removeRegistro(String chave){
        this.registros.remove(chave);
    }

    public void zeraRegistros(){
        this.registros = new HashMap<String, List<Integer>>();
    }

    public String informaDadosDosRegistros(){
        StringBuffer textoRegistros = new StringBuffer();
        for(String registro : registros.keySet()){
            textoRegistros.append(registro)
                    .append(">")
                    .append(registros.get(registro) )
                    .append("\n");
        }
        return textoRegistros.toString();
    }

    public HashMap<String, List<Integer>> getRegistros() {
        return registros;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public String getModa() {
        return moda;
    }

    public void setModa(String moda) {
        this.moda = moda;
    }

    public double getMediana() {
        return mediana;
    }

    public void setMediana(double mediana) {
        this.mediana = mediana;
    }
}
