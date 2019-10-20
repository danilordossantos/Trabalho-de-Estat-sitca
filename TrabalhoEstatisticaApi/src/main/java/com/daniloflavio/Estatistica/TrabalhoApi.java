package com.daniloflavio.Estatistica;

import com.daniloflavio.Estatistica.model.Amostra;
import com.daniloflavio.Estatistica.model.Calculo;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calc")
public class TrabalhoApi {

    @Inject
    Amostra amostra;
    @Inject
    Calculo calculo;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return this.amostra.informaDadosDosRegistros();
        //return String.valueOf(this.calculo.frequenciaAbsoluta(this.amostra.getRegistros()));
    }

    @GET
    @Path("/inserir/{chave}/{valor}")
    @Produces(MediaType.TEXT_PLAIN)
    public String inserirRegistro(@PathParam("chave") String chave, @PathParam("valor") String valor){
        try {
            int valorNumerico = Integer.valueOf(valor);
            amostra.insereRegistro(chave, valorNumerico);
            amostra.setMedia(calculo.media(amostra.getRegistros()));
            amostra.setModa(calculo.calculaModa(amostra.getRegistros()));
            return "Registro adicionado\nMédia = "+amostra.getMedia()+"\nModa = "+amostra.getModa();
        }catch (Exception e){
            e.printStackTrace();
            return "A quantidade informada nâo é um numero inteiro";
        }
    }


    @GET
    @Path("/remover/{chave}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeRegistro(@PathParam("chave")String chave){
        try{
            this.amostra.removeRegistro(chave);
            return "Registro Removido";
        }catch (Exception e){
            return "Falha  ao remover";
        }
    }

    @GET
    @Path("/media")
    @Produces(MediaType.TEXT_PLAIN)
    public String media(){
        return String.valueOf(this.calculo.media(this.amostra.getRegistros()));
    }

    @GET
    @Path("/mediana")
    @Produces(MediaType.TEXT_PLAIN)
    public String mediana(){
        return this.calculo.getMediana(this.amostra.getRegistros()) ;
    }

    @GET
    @Path("/moda")
    @Produces(MediaType.TEXT_PLAIN)
    public String moda(){
        return this.calculo.calculaModa(this.amostra.getRegistros()) ;
    }

    @GET
    @Path("/amplitude")
    @Produces(MediaType.TEXT_PLAIN)
    public String amplitudeTotal(){
        return String.valueOf(this.calculo.getAmplitudeTotal(this.amostra.getRegistros())) ;
    }

    @GET
    @Path("/desvio")
    @Produces(MediaType.TEXT_PLAIN)
    public String desvioPadrao(){
        return String.valueOf(this.calculo.getDesvioPadrao(this.amostra.getRegistros())) ;
    }

    @GET
    @Path("/variacao")
    @Produces(MediaType.TEXT_PLAIN)
    public String coeficienteDeVariacao(){
        return String.valueOf(this.calculo.getCoeficienteDeVariacao(this.amostra.getRegistros())) ;
    }

    @GET
    @Path("/resultado")
    @Produces(MediaType.TEXT_PLAIN)
    public String resultado(){
        AmostraDTO amostraDTO = new AmostraDTO();
        Gson gson = new Gson();
        amostra.calcular();
        amostraDTO.write(amostra);
        return gson.toJson(amostraDTO);
    }
}