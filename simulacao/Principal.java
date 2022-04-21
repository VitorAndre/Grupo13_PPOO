/**
 * Classe Principal, que executa toda a simulacao
 * @author Luiz Merschmann
 */
public class Principal {

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Simulacao sim = new Simulacao();

        // A simulacao recebe como parametro numero de passos, quantidade de veiculos e tempo de espera
        sim.executarSimulacao(100, 4, 10);   
    }
}
