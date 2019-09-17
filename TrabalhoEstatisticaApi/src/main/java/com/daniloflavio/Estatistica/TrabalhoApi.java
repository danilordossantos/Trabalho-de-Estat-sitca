package com.daniloflavio.Estatistica;

import com.daniloflavio.Estatistica.model.Amostra;
import com.daniloflavio.Estatistica.model.Calculo;

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
            return "Registro adicionado";
        }catch (Exception e){
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
}