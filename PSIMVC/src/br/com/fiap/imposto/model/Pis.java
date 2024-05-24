package br.com.fiap.imposto.model;

import br.com.fiap.imposto.util.AliquotaSingleton;

import java.util.Observable;

public class Pis extends Observable implements Imposto {

    final float ALIQUOTA = Float.parseFloat(AliquotaSingleton.getInstance().getProperty("aliquotaPis"));
    float valorDoPis = 0;

    public Pis(){
        System.out.println("Construtor do Model chamado! ");
    }

    public float getALIQUOTA() {
        return ALIQUOTA;
    }

    public float getValorDoPis() {
        return valorDoPis;
    }

    @Override
    public void calcularImposto(float valor) {

        valorDoPis = valor * ALIQUOTA;

        setChanged();
        notifyObservers(valorDoPis);
    }

    @Override
    public String toString() {
        return "Pis{" +
                "ALIQUOTA=" + ALIQUOTA +
                ", valorDoPis=" + valorDoPis +
                '}';
    }
}
