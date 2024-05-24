package com.fiap.bank.ws;

import com.fiap.bank.entity.Cliente;
import jakarta.jws.WebService;

@WebService
public class OlaClienteServiceImpl implements OlaClienteService {
    @Override
    public String digaOla(Cliente cliente) {
        return "Olá " + cliente.toString();
    }
}
