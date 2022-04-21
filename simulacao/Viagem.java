/**
 * Classe que representa uma viagem do navio.
 * Ela eh responsavel por armazenar os dados da viagem e calcular o lucro da mesma.
 */

public class Viagem implements Comparable<Viagem> {
    private Veiculo veiculo;
    private int numeroDePassageiros; 
    private double valorPassagem;
    private double lucro;
    private String origem;
    private String destino;

    /**
     * Cria uma viagem para armazenar seus dados.
     * @param veiculo: Veiculo.
     * @param numeroDePassageiros: Numero de passageiros.
     * @param valorPassagem: Valor da passagem.
     * @param origem: Cidade de origem.
     * @param destino: Cidade de destino.
     */
    public Viagem(Veiculo veiculo, String origem, String destino, int numeroDePassageiros, double valorPassagem){
        this.veiculo = veiculo;
        this.numeroDePassageiros = numeroDePassageiros;
        this.valorPassagem = valorPassagem;        
        this.origem = origem;
        this.destino = destino;
    }

    /** 
     * Retorna o veiculo.
     * @return Veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    /**
     * Retorna o numero de passageiros. 
     * @return int
     */
    public int getNumeroDePassageiros() {
        return numeroDePassageiros;
    }
    
    /** 
     * Retorna o valor da passagem.
     * @return double
     */
    public double getValorPassagem(){
        return Math.round(valorPassagem*100.0)/100.0 ;
    }
    
    /** 
     * @return int
     */
    public int getDistanciaRealPercorrida() {
        return veiculo.getDistanciaPercorrida();
    }
    
    /** 
     * Retorna o lucro.
     * @return double
     */
    public double getLucro(){        
        return Math.round(lucro*100.0)/100.0 ;
    }
    
    /** 
     * retorna a cidade de origem.
     * @return String
     */
    public String getOrigem(){
        return origem;
    }
    
    /** 
     * Retorna a cidade de destino.
     * @return String
     */
    public String getDestino(){
        return destino;
    }

    /** 
     * Metodo que calcula o lucro de cada viagem
     * Define o preco do combustivel e a quantidade de litros consumidos por km.
     * Em seguida, usa os dados da classe para calcular o custo e a receita. Por fim,
     * calcula o lucro da viagem.
     */
     public void calcularLucro(){
        double precoCombustivelPorLitro = 1450;
        double litrosConsumidosPorKm = 20; 
        double custo = precoCombustivelPorLitro * (litrosConsumidosPorKm * getDistanciaRealPercorrida()) ;
        double receita = numeroDePassageiros * valorPassagem;
        lucro = receita - custo;
     }

    
    /** 
     * Metodo recebe um objeto do tipo viagem e compara o valor do seu lucro com o valor do lucro da classe.
     * @param o 
     * @return int
     */
    @Override
    public int compareTo(Viagem o) {
         if (o.getLucro() > getLucro()) {
            return 1;
         } else {
            return -1;
         }
     }
}
