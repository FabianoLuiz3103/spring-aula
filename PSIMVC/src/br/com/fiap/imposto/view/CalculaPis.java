package br.com.fiap.imposto.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.nio.file.ClosedFileSystemException;
import java.util.Observable;
import java.util.Observer;

public class CalculaPis implements Observer, TelaDeImposto {

    private TextField txtValorFaturado;
    private Button btnCalcular;

    public CalculaPis(){

        System.out.println("Construtor da view chamado");

        Frame frame = new Frame("Calculo do PIS MCV e Design Patterns");
        frame.add("North", new Label("Valor faturado"));

        txtValorFaturado = new TextField();
        frame.add("Center", txtValorFaturado);

        Panel panel = new Panel();
        btnCalcular = new Button("Calcular PIS");
        panel.add(btnCalcular);
        frame.add("South", panel);

        frame.addWindowListener(new CloseListner());
       frame.setSize(200, 150);
       frame.setLocation(100, 100);
       frame.setVisible(true);
    }

    public float getValor(){
        return Float.parseFloat(txtValorFaturado.getText());
    }

    public void addController(ActionListener controller){
        System.out.println("A view adicionou o controller");
        btnCalcular.addActionListener(controller);
    }

    public void update(Observable objModel, Object estadoDoModel){
        String msg = objModel.getClass() +
                " " + objModel.toString() +
                " " + ((Float)estadoDoModel).floatValue();
        JOptionPane.showMessageDialog(null, msg);
    }

    public static class CloseListner extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
