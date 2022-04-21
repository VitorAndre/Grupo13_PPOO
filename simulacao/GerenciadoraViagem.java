import java.util.*;
public class GerenciadoraViagem {

    private JanelaResultado janelaComResultado;

    private ArrayList<Viagem> viagens;

    public GerenciadoraViagem(){
        viagens = new ArrayList<Viagem>();        
    }

    public void adicionarViagem(Viagem umaViagem){ 
        viagens.add(umaViagem);        
    }


    // Metodo para imprimir resultados
    public void imprimirRelatorio(){
        //System.out.println("Distancia real entre dois pontos " + viagens.get(0).getDistanciaEntreDoisPontos());
        for(int i = 0; i < viagens.size(); i++){
            //Relatório das viagens
            System.out.println(i);
            System.out.println( viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino() );
            System.out.println("Distancia real percorrida: " + viagens.get(i).getDistanciaRealPercorrida());   
            System.out.println("Numero de Passageiros: " + viagens.get(i).getNumeroDePassageiros());
            System.out.printf("Valor da Passagem: %.2f \n", viagens.get(i).getValorPassagem());
            viagens.get(i).CalcularLucro();
            System.out.printf("Lucro: %.2f \n", viagens.get(i).getLucro());  
        }        
    }

    //Metodo para mostrar resultados em uma janela
    public void mostrarRelatorioJanela(){

        Object[][] data = new Object[viagens.size()][6]; 

        //Formatar dados
        for(int i = 0; i < viagens.size(); i++){
            data[i][0] = i+1;
            data[i][1] = viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino();
            data[i][2] = viagens.get(i).getDistanciaRealPercorrida();
            data[i][3] = viagens.get(i).getNumeroDePassageiros();
            data[i][4] = viagens.get(i).getValorPassagem();
            data[i][5] = viagens.get(i).getLucro();            
        }               

        janelaComResultado = new JanelaResultado(data);
        janelaComResultado.exibir();
    }
}
