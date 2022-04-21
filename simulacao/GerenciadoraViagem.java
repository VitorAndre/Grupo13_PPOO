/**
 * Classe que gerencia as viagens.
 * Ela eh responsavel por armazenar todas as viagens e por gerar um relatorio contendo
 * seus dados.
 */

import java.util.*;
public class GerenciadoraViagem {

    private JanelaResultado janelaComResultado;
    private ArrayList<Viagem> viagens;

    /**
     * Cria vetor para armazenar as viagens.
     * 
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
     * Metodo para atribuir os lucros de cada viagem
     */
    public void defineLucros() {
        for (int i = 0; i < viagens.size(); i++) {
            viagens.get(i).calcularLucro();
        }
    }

    /** 
     * Metodo para gerar um relatorio contendo os dados de cada viagem.
     * Os dados serão ordenados pelo lucro em ordem decrescente.
     */
    public void imprimirRelatorio(){
        defineLucros();
        Collections.sort(viagens);
        for(int i = 0; i < viagens.size(); i++){
            System.out.println(i+1);
            System.out.println( viagens.get(i).getOrigem() +" -> "+ viagens.get(i).getDestino() );
            System.out.println("Distancia real percorrida: " + viagens.get(i).getDistanciaRealPercorrida());   
            System.out.println("Numero de Passageiros: " + viagens.get(i).getNumeroDePassageiros());
            System.out.printf("Valor da Passagem: %.2f \n", viagens.get(i).getValorPassagem());
            System.out.printf("Lucro: %.2f \n", viagens.get(i).getLucro());  
        }        
    }

    /** 
     * Metodo para abrir uma janela que mostra os resultados da simulacao
     * Os dados do resultado da simulação são armazenados em uma matriz
     * Então é criada uma janela que recebe essa matriz e depois os disponibiliza em uma tabela
     */
    public void mostrarRelatorioJanela(){

        Object[][] data = new Object[viagens.size()][6]; 

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
