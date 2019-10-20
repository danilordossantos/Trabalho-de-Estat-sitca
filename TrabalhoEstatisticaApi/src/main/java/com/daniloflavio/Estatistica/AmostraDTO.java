package com.daniloflavio.Estatistica;

import com.daniloflavio.Estatistica.model.Amostra;

public class AmostraDTO {
    public String media;
    public String moda;
    public String mediana;
    public String[][] rol;

    public void write(Amostra amostra){
        this.media = String.valueOf(amostra.getMedia());
        this.moda = String.valueOf(amostra.getModa());
        this.mediana = String.valueOf(amostra.getMediana());
        rol = new String[amostra.getRegistros().size()][2];

        int contador = 0;
        for(String registro : amostra.getRegistros().keySet()){
            rol[contador][0] = registro;
            rol[contador][1] = amostra.getRegistros().get(registro).get(0).toString();
            ++contador;
        }
    }
}
