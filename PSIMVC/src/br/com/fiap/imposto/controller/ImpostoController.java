package br.com.fiap.imposto.controller;

import br.com.fiap.imposto.model.Imposto;
import br.com.fiap.imposto.view.TelaDeImposto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImpostoController implements ActionListener {

    private Imposto model;
    private TelaDeImposto view;

    public ImpostoController(Imposto model, TelaDeImposto view){
        System.out.println("Construtor do controller chamado");
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.calcularImposto(view.getValor());
    }
}
