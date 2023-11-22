import java.util.Random;

interface Atacante {
    int calcularAtaque();
    int calcularDefesa();
    int calcularDano();
}

abstract class Personagem implements Atacante {
    private String nome;
    private int pontosVida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int fatorDano;

    public Personagem(String nome, int pontosVida, int forca, int defesa, int agilidade, int fatorDano) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.fatorDano = fatorDano;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0) {
            pontosVida = 0;
        }
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getFatorDano() {
        return fatorDano;
    }

    @Override
    public int calcularAtaque() {
        Random random = new Random();
        return random.nextInt(10) + getAgilidade() + getForca();
    }

    @Override
    public int calcularDefesa() {
        Random random = new Random();
        return random.nextInt(10) + getAgilidade() + getDefesa();
    }

    @Override
    public int calcularDano() {
        Random random = new Random();
        return random.nextInt(getFatorDano()) + getForca();
    }
}
