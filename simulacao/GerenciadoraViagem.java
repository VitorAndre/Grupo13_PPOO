/**
 * Classe que gerencia as viagens.
 * Ela e responsavel por armazenar todas as viagens e por gerar um relatório contendo
 * seus dados.
 */

import java.util.*;
public class GerenciadoraViagem {

    private ArrayList<Viagem> viagens;

    /**
     * Cria vetor para armazenar as viagens.
     * @param viagens: Vetor contendo todas viagens.
     */
    public GerenciadoraViagem(){
        viagens = new ArrayList<Viagem>();        
    }

    
    /** 
     * Metodo para adicionar uma viagem ao vetor de viagens.
     * @param umaViagem
     */
    public void adicionarViagem(Viagem umaViagem){ 
        viagens.add(umaViagem);        
    }


    /** 
     * Metodo para gerar um relatorio contendo os dados de cada viagem.
     * Os dados serão ordenados pelo lucro em ordem decrescente.
     */
    public void imprimirRelatorio(){
        Collections.sort(viagens);
        for(int i = 0; i < viagens.size(); i++){
            System.out.println(i);
            System.out.println( viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino() );
            System.out.println("Distancia real percorrida: " + viagens.get(i).getDistanciaRealPercorrida());   
            System.out.println("Numero de Passageiros: " + viagens.get(i).getNumeroDePassageiros());
            System.out.printf("Valor da Passagem: %.2f \n", viagens.get(i).getValorPassagem());
            System.out.printf("Lucro: %.2f \n", viagens.get(i).getLucro());  
        }        
    }

}
