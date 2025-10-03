import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Ejemplo con KeyAdapter (KeyListener)
public class CapturaTeclaC extends JFrame {
    private JList<String> lista;
    private DefaultListModel<String> modeloLista;
    private JButton boton;

    public CapturaTeclaC() {
        setTitle("Captura tecla 'C' - KeyAdapter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Modelo y elementos de la lista
        modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Elemento 1");
        modeloLista.addElement("Elemento 2");
        modeloLista.addElement("Elemento 3");
        modeloLista.addElement("Elemento 4");

        lista = new JList<>(modeloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setFocusable(true); // aseguramos que pueda tener foco

        JScrollPane scroll = new JScrollPane(lista);
        boton = new JButton("Selecciona un elemento y presiona 'C'");

        // Listener de teclado registrado en la JList
        lista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Ejecutar sólo si se presiona la tecla 'C' (funciona con mayúscula o minúscula)
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    int index = lista.getSelectedIndex();
                    if (index != -1) {
                        String valor = modeloLista.getElementAt(index);
                        if (valor.startsWith("[X] ")) {
                            modeloLista.setElementAt(valor.substring(4), index);
                            System.out.println("Elemento desmarcado: " + valor);
                        } else {
                            modeloLista.setElementAt("[X] " + valor, index);
                            System.out.println("Elemento marcado: " + valor);
                        }
                    } else {
                        System.out.println("No hay elemento seleccionado.");
                    }
                }
            }
        });

        add(scroll, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        // Opcional: pedir foco a la lista al iniciar para que detecte la tecla
        SwingUtilities.invokeLater(() -> lista.requestFocusInWindow());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CapturaTeclaC());
    }
}