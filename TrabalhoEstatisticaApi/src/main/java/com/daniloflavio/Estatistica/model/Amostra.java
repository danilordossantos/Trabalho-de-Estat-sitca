package com.daniloflavio.Estatistica.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Amostra {
    private HashMap<String, List<Integer>> registros;

    public Amostra() {
        registros = registrosZerados();
    }

    private void simulaRegistros(){
        registros = registrosZerados();
        insereDado("Bebes",3 );
        insereDado("Crianças", 10);
        insereDado("Jovems",20);
        insereDado("Adultos", 10);
        insereDado("Idosos",17);
    }

    private void insereDado(String nomeVariavel, int quantidade){
        List<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(quantidade);
        registros.put(nomeVariavel, listaNumeros);
    }

    private void removeRegistro(String chave){
        this.registros.remove(chave);
    }

    private HashMap<String, List<Integer>> registrosZerados(){
        return new HashMap<String, List<Integer>>();
    }
}
