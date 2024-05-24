package com.fiap.bank.server;

import com.fiap.bank.ws.OlaClienteService;
import com.fiap.bank.ws.OlaClienteServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class RunService {

    public static void main(String[] args) {

        try{
            OlaClienteService clienteService = new OlaClienteServiceImpl();
            JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
            factory.setServiceClass(OlaClienteService.class);
            factory.setAddress("http://localhost:8809/digaola");
            factory.setServiceBean(clienteService);
            factory.create();
            System.out.println("Serviço operacional");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
