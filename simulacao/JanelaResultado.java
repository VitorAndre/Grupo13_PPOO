import javax.swing.*;
import java.awt.*;

public class JanelaResultado {

    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel titulo;


    public JanelaResultado( Object[][] resultados){
        frame = new JFrame("Resultados");

        String[] columns = {"Indice", "Viagem", "Distância", "Passageiros","Valor da passagem", "Lucro" };
        Object[][] data = resultados;                  

        table = new JTable(data, columns);
        scrollPane = new JScrollPane(table);
        titulo = new JLabel("RESULTADO DA SIMULAÇÃO");

        montarJanela();

    }

    private void montarJanela(){
        frame.setSize(900, 200);
        table.setFillsViewportHeight(true);
        titulo.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));

        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(titulo,BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        //Definindo tamanho das colunas da tabela
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    public void exibir(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
}