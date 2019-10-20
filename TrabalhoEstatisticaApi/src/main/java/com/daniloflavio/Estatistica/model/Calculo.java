package com.daniloflavio.Estatistica.model;

import javax.enterprise.context.ApplicationScoped;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

@ApplicationScoped
public class Calculo {
    private static final int INDICE_FREQUENCIA = 0;
    private static final int INDICE_FREQUENCIA_ACUMULADA = 1;
    private static final int INDICE_SOMA_DA_MULTIPLICACAO_DAS_FREQUENCIAS = 2;

    public Calculo() {}

    //Coluna 1 do lista
    public double frequenciaAbsoluta(HashMap<String, List<Integer>> registros) {
        if (registros.isEmpty())return 0;
        String chaveDoRegistroAnterior = null;
        for (String registroAtual : registros.keySet()) {
            if (chaveDoRegistroAnterior != null) {
                adicionaNaColuna(registros.get(registroAtual), INDICE_FREQUENCIA_ACUMULADA, (registros.get(chaveDoRegistroAnterior).get(1) + registros.get(registroAtual).get(0)));
            }else{
                adicionaNaColuna(registros.get(registroAtual), INDICE_FREQUENCIA_ACUMULADA, registros.get(registroAtual).get(0) );
            }
            chaveDoRegistroAnterior = registroAtual;

        }
        return registros.get(chaveDoRegistroAnterior).get(INDICE_FREQUENCIA_ACUMULADA);
    }

    //FiXi Coluna 2 do list
    public double somaFrequenciasDosRegistros(HashMap<String, List<Integer>> registros){
        int variavel = 0;
        int frequencia = 0;
        if (registros.isEmpty())return 0;
        String chaveDoRegistroAnterior = null;
        for (String registroAtual : registros.keySet()) {
            variavel = Integer.valueOf(registroAtual);
            frequencia = registros.get(registroAtual).get(INDICE_FREQUENCIA);

            if (chaveDoRegistroAnterior != null) { //Caso o registro atual nao seja o primeiro
                adicionaNaColuna(registros.get(registroAtual), INDICE_SOMA_DA_MULTIPLICACAO_DAS_FREQUENCIAS, (variavel * frequencia+registros.get(chaveDoRegistroAnterior).get(2)));
            }else{ // Quando o registro verificado for o  primeiro
                adicionaNaColuna(registros.get(registroAtual), INDICE_SOMA_DA_MULTIPLICACAO_DAS_FREQUENCIAS,(variavel * frequencia) );
            }
            chaveDoRegistroAnterior = registroAtual;
        }
        return registros.get(chaveDoRegistroAnterior).get(INDICE_SOMA_DA_MULTIPLICACAO_DAS_FREQUENCIAS);
    }
    //FiXi ao quadrado
    public double somaFrequenciasDosRegistrosAoQuadrado(HashMap<String, List<Integer>> registros){
        double somatoria = 0;
        int variavel = 0;
        int frequencia = 0;
        double valorAtual = 0;
        if (registros.isEmpty())return 0;

        for (String registroAtual : registros.keySet()) {
            variavel = Integer.valueOf(registroAtual);
            frequencia = registros.get(registroAtual).get(INDICE_FREQUENCIA);
            valorAtual = Math.pow((variavel*frequencia),2);

            if (somatoria == 0) { //Caso o registro atual nao seja o primeiro
                somatoria = valorAtual;
            }else{ // Quando o registro verificado for o  primeiro
                somatoria += valorAtual;
            }
        }
        return somatoria;
    }

    public double media(HashMap<String, List<Integer>> registros){
        double frequenciaAbsoluta = frequenciaAbsoluta(registros);
        double somaDasFrequencias = somaFrequenciasDosRegistros(registros);
        return somaDasFrequencias/frequenciaAbsoluta;
    }

    public void adicionaNaColuna(List<Integer> colunas, int numeroColuna, Integer valor){
        if(colunas.size()<=numeroColuna)
            colunas.add(valor);
        else{
            colunas.set(numeroColuna,valor);
        }
    }

    public String calculaModa(HashMap<String, List<Integer>> registros){
        String moda = "";
        int frequenciaDaModa = 0;
        int frequenciaAtual = 0;
        for(String reg : registros.keySet()){
            if(moda.equals("")) {
                moda=reg;
                continue;
            }
            frequenciaAtual = registros.get(reg).get(INDICE_FREQUENCIA);
            frequenciaDaModa = registros.get(moda).get(INDICE_FREQUENCIA);
            if(frequenciaAtual>frequenciaDaModa)
                moda = reg;
        }
        return moda;
    }

    public String getMediana(HashMap<String,List<Integer>> registros){
        if(getSomatoriaDasFrequencias(registros)%2 == 0)
            return getMedianaAmplitudePar(registros);
        else
            return getMedianaAmplitudeImpar(registros);
    }

    private String getMedianaAmplitudeImpar(HashMap<String,List<Integer>> registros){
        int indiceMediana = (getSomatoriaDasFrequencias(registros)+1)/2;
        int valorMediana = getFrequenciaPorFrequenciaAcumulada(registros,indiceMediana);
        return String.valueOf(valorMediana);
    }

    private String getMedianaAmplitudePar(HashMap<String,List<Integer>> registros){
        int indiceSuperior = (getSomatoriaDasFrequencias(registros)+1)/2;
        int indiceInferior = indiceSuperior-1;

        try{
            NumberFormat formato = new DecimalFormat("0.00");
            int valorSuperior = getFrequenciaPorFrequenciaAcumulada(registros,indiceSuperior);
            int valorInferior = getFrequenciaPorFrequenciaAcumulada(registros,indiceInferior);
            double resultado = (valorSuperior+valorInferior)/2;
            return formato.format(resultado);
        }catch (Exception e){
            return "A mediana estã entre "+ indiceSuperior+" e "+indiceInferior;
        }
    }

    private int getFrequenciaPorFrequenciaAcumulada(HashMap<String,List<Integer>> registros, int indice){
       int contador = 0;
        for(String reg : registros.keySet()) {
            contador+= registros.get(reg).get(0);
            if(contador>=indice){
                return registros.get(reg).get(0);
            }
        }
        return 0;
    }

    public int getSomatoriaDasFrequencias(HashMap<String,List<Integer>> registros){
        NavigableMap <String,List<Integer>> map = new TreeMap<>(registros);
        //A frequencia acumulada do ultimo indice corresponnde a somatória das frequencias
        return map.lastEntry().getValue().get(INDICE_FREQUENCIA_ACUMULADA);
    }

    public int getAmplitudeTotal(HashMap<String,List<Integer>> registros){
        try{
            NavigableMap <String,List<Integer>> map = new TreeMap<>(registros);
            int ultimaVariavel = Integer.valueOf(map.lastKey());
            int primeiraVariavel = Integer.valueOf(map.firstKey());
            return ultimaVariavel-primeiraVariavel;
        }catch (Exception e){
            return 0;
        }
    }

    public double getDesvioPadrao(HashMap<String,List<Integer>> registros){
        double desvioPadrao = Math.sqrt((somaFrequenciasDosRegistrosAoQuadrado(registros)/2)- Math.pow((getSomatoriaDasFrequencias(registros)/2),2));
        return desvioPadrao;
    }

    public double getCoeficienteDeVariacao(HashMap<String,List<Integer>> registros){
        double coeficienteDeVariacao = getDesvioPadrao(registros)/media(registros)*100;
        return coeficienteDeVariacao;
    }

}
