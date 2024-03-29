package com.daniloflavio.Estatistica.model;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSProvider implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        CorsFilter filter = new CorsFilter();
        filter.getAllowedOrigins().add("*");
        filter.setAllowedMethods("GET, POST, OPTIONS, HEAD");
        filter.setAllowedHeaders("accept, content-type, origin");
        context.register(filter);
        return true;
    }
}