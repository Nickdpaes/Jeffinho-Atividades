package model;

import javax.swing.SwingUtilities;
import view.TelaCadastro;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaCadastro tela = new TelaCadastro();
                tela.setVisible(true);
            }
        });
    }
}