package com.daniloflavio.Estatistica.model;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class Amostra {
    private HashMap<String, List<Integer>> registros;

    public Amostra() {
        simulaRegistros();
    }

    public void simulaRegistros(){
        zeraRegistros();
        insereRegistro("Bebes",3 );
        insereRegistro("Crianças", 10);
        insereRegistro("Jovems",20);
        insereRegistro("Adultos", 10);
        insereRegistro("Idosos",17);
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
}
