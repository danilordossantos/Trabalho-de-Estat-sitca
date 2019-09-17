package com.daniloflavio.Estatistica.model;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class Calculo {

    public Calculo() {}

    //Coluna 1 do lista
    public double frequenciaAbsoluta(HashMap<String, List<Integer>> registros) {
        final int NUMERO_COLUNA = 1;
        if (registros.isEmpty())return 0;
        String chaveDoRegistroAnterior = null;
        for (String registroAtual : registros.keySet()) {
            if (chaveDoRegistroAnterior != null) {
                adicionaNaColuna(registros.get(registroAtual), NUMERO_COLUNA, (registros.get(chaveDoRegistroAnterior).get(1) + registros.get(registroAtual).get(0)));
            }else{
                adicionaNaColuna(registros.get(registroAtual), NUMERO_COLUNA, registros.get(registroAtual).get(0) );
            }
            chaveDoRegistroAnterior = registroAtual;

        }
        return registros.get(chaveDoRegistroAnterior).get(1);
    }

    //Coluna 2 do list
    public double somatoriaDasFrequencias(HashMap<String, List<Integer>> registros){
        final int NUMERO_COLUNA = 2;
        int variavel = 0;
        int frequencia = 0;
        if (registros.isEmpty())return 0;
        String chaveDoRegistroAnterior = null;
        for (String registroAtual : registros.keySet()) {
            variavel = Integer.valueOf(registroAtual);
            frequencia = registros.get(registroAtual).get(0);

            if (chaveDoRegistroAnterior != null) {
                adicionaNaColuna(registros.get(registroAtual), NUMERO_COLUNA, (variavel * frequencia+registros.get(chaveDoRegistroAnterior).get(2)));
            }else{
                adicionaNaColuna(registros.get(registroAtual), NUMERO_COLUNA,(variavel * frequencia) );
            }
            chaveDoRegistroAnterior = registroAtual;
        }
        return registros.get(chaveDoRegistroAnterior).get(NUMERO_COLUNA);
    }

    public double media(HashMap<String, List<Integer>> registros){
        double frequenciaAbsoluta = frequenciaAbsoluta(registros);
        double somaDasFrequencias = somatoriaDasFrequencias(registros);
        return somaDasFrequencias/frequenciaAbsoluta;
    }

    public void adicionaNaColuna(List<Integer> colunas, int numeroColuna, Integer valor){
        if(colunas.size()<=numeroColuna)
            colunas.add(valor);
        else{
            colunas.set(numeroColuna,valor);
        }
    }

    public double calculaModa(HashMap<String, List<Integer>> registros){
        double moda = 0;
        int atual = 0;
        for(String reg : registros.keySet()){
            atual = registros.get(reg).get(0);
            if(atual>moda)
                moda = atual;
        }
        return moda;
    }
}
