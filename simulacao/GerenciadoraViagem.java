import java.util.*;
public class GerenciadoraViagem {

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
        Collections.sort(viagens);
        for(int i = 0; i < viagens.size(); i++){
            //Relatório das viagens
            System.out.println(i);
            System.out.println( viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino() );
            System.out.println("Distancia real percorrida: " + viagens.get(i).getDistanciaRealPercorrida());   
            System.out.println("Numero de Passageiros: " + viagens.get(i).getNumeroDePassageiros());
            System.out.printf("Valor da Passagem: %.2f \n", viagens.get(i).getValorPassagem());
            System.out.printf("Lucro: %.2f \n", viagens.get(i).getLucro());  
        }        
    }

}
