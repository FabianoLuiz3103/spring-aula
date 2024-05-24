package br.com.fiap.imposto.view;

import br.com.fiap.imposto.controller.ImpostoController;
import br.com.fiap.imposto.model.Pis;

import java.util.Observer;

public class TestaCalculo {

    public static void main(String[] args) {

        Pis modelPis = new Pis();
        CalculaPis viewCalculaPis = new CalculaPis();

        modelPis.addObserver(viewCalculaPis);
        ImpostoController controller = new ImpostoController(modelPis, viewCalculaPis);
        viewCalculaPis.addController(controller);
    }
}
