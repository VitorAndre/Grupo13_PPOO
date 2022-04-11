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

        for(int i = 0; i < viagens.size(); i++){
            System.out.println(i);
            System.out.println( viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino() );
            viagens.get(i).calcularDistanciaRealPercorrida();
            System.out.println("Distancia real percorrida: " + viagens.get(i).getDistanciaRealPercorrida());   
            
            viagens.get(i).CalcularLucro();
            System.out.println("Lucro: " + viagens.get(i).getLucro());  
        }        
    }

}
