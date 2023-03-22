import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class prohlizecSouboru extends JFrame {
    private JButton btn;
    private JTextArea textArea;

    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public prohlizecSouboru () {


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vybiraniSouboru();
            }
        });
    }

    public void vybiraniSouboru(){

        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showOpenDialog(this);
        if (result == fileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String radek = reader.readLine();
                while(radek != null){
                    textArea.append(radek + "\n");
                    radek = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            textArea.setText("Soubor nebyl vybraný!");
        }

    }

    public static void main(String[] args) {
        prohlizecSouboru prohlizec = new prohlizecSouboru();
        JMenuBar menuBar1 = new JMenuBar();
        prohlizec.setJMenuBar(menuBar1);
        JMenu menu1 = new JMenu("File");
        menuBar1.add(menu1);
        JMenuItem menuItem1 = new JMenuItem("Open");
        menu1.add(menuItem1);
        menuItem1.addActionListener(e -> prohlizec.vybiraniSouboru());
        prohlizec.setContentPane(prohlizec.mainPanel);
        prohlizec.setTitle("Výběr souboru");
        prohlizec.setVisible(true);
        prohlizec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prohlizec.setBounds(600, 300, 500, 400);

    }

}


