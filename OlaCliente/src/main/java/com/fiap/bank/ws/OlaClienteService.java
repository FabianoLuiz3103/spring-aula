package com.fiap.bank.ws;
import com.fiap.bank.entity.Cliente;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface OlaClienteService {
    String digaOla(@WebParam(name="cliente")
                   Cliente cliente);
}
