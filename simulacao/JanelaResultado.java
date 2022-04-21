import javax.swing.*;
import java.awt.*;

/**
 * Fornece a visualizacao dos resultados da simulacao em uma tabela
 */
public class JanelaResultado {

    private JFrame janela;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel titulo;

    /**
    * Metodo que cria a janela, a tabela e os demais componentes
    * Define o titulo das colunas da tabela, por ultimo chama o metodo para montar a janela
    * @param resultados matriz com os resultados da simulacao
    */
    public JanelaResultado( Object[][] resultados){
        janela = new JFrame("Resultados");

        String[] columns = {"Indice", "Viagem", "Distância", "Passageiros","Valor da passagem", "Lucro" };
        Object[][] data = resultados;                  

        table = new JTable(data, columns);
        scrollPane = new JScrollPane(table);
        titulo = new JLabel("RESULTADO DA SIMULAÇÃO");

        montarJanela();

    }

    /**
    * Monta a janela definindo seu tamanho, layout e adicionando componentes
    */
    private void montarJanela(){
        janela.setSize(900, 400);
        table.setFillsViewportHeight(true);
        titulo.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(titulo,BorderLayout.PAGE_START);
        janela.getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        //Definindo tamanho das colunas da tabela
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    /**
    * Exibe a janela e define que a aplicacao sera encerrada ao clicar x
    */
    public void exibir(){
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
}
