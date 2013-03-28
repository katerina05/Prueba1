/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.grafico2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Katerina Hernandez
 */
public class EditorGrafico2 extends JFrame {

    private JTextArea textArea;
    private JScrollPane scroll;
    private JPanel contentPanel;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem submnu;
    private JMenuItem submnu1;
    private JMenuItem submnu2;

    public EditorGrafico2() {
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editor de Texto");
        contentPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setBounds(0, 50, 700, 500);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scroll = new JScrollPane();
        scroll.setSize(600, 600);
        contentPanel.add(scroll);
        setContentPane(scroll);
        scroll.setViewportView(textArea);
        menu = new JMenuBar();
        setJMenuBar(menu);

        archivo = new JMenu("Archivo");
        menu.add(archivo);

        submnu = new JMenuItem("Abrir");
        submnu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                BufferedReader br = null;
                try {
                    JFileChooser selector = new JFileChooser();
                    selector.showOpenDialog(EditorGrafico2.this);
                    String archivo = selector.getSelectedFile().getName();
                    br = new BufferedReader(new FileReader(archivo));
                    String linea = null;
                    try {
                        while ((linea = br.readLine()) != null) {
                            textArea.setWrapStyleWord(true);
                            textArea.append(linea + "\n");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(EditorGrafico2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EditorGrafico2.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(EditorGrafico2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            }
        });
        archivo.add(submnu);

        submnu1 = new JMenuItem("Guardar");
        submnu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFileChooser selector = new JFileChooser();
                int resultado = selector.showSaveDialog(null);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter file = new FileWriter(selector.getSelectedFile());
                        file.write(textArea.getText());
                        BufferedWriter bw = new BufferedWriter(file);
                        bw.flush();
                        bw.close();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), "Error al guardar el archivo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        archivo.add(submnu1);

        archivo.addSeparator();

        submnu2 = new JMenuItem("Salir");
        submnu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        archivo.add(submnu2);


    }
}
