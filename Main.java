import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        MedievalBattle();
        System.out.println("Hello world!");
    }

    public static void MedievalBattle() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Digite o nickname do jogador: ");
        String nickname = scanner.nextLine();

        System.out.println("Escolha sua classe de herói:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Bárbaro");
        System.out.println("3. Paladino");

        int heroChoice = scanner.nextInt();
        scanner.nextLine();

        Personagem hero;
        switch (heroChoice) {
            case 1:
                hero = new Guerreiro();
                break;
            case 2:
                hero = new Barbaro();
                break;
            case 3:
                hero = new Paladino();
                break;
            default:
                System.out.println("Escolha inválida. Guerreiro selecionado por padrão.");
                hero = new Guerreiro();
                break;
        }

        Personagem[] monstros = {new MortoVivo(), new Orc(), new Kobold()};
        Personagem monstro = monstros[random.nextInt(monstros.length)];

        int rounds = 0;
        while (hero.getPontosVida() > 0 && monstro.getPontosVida() > 0) {
            rounds++;
            int iniciativaHero = random.nextInt(10) + hero.getAgilidade();
            int iniciativaMonstro = random.nextInt(10) + monstro.getAgilidade();

            if (iniciativaHero > iniciativaMonstro) {
                realizarAtaque(hero, monstro, random);
            } else if (iniciativaMonstro > iniciativaHero) {
                realizarAtaque(monstro, hero, random);
            }

            System.out.println("Pontos de vida do herói: " + hero.getPontosVida());
            System.out.println("Pontos de vida do monstro: " + monstro.getPontosVida());
        }

        String resultado = (hero.getPontosVida() > 0) ? "GANHOU" : "PERDEU";
        salvarLog(nickname, hero, resultado, monstro, rounds);
        System.out.println("Você " + resultado + " após " + rounds + " rodadas.");
    }

    private static void realizarAtaque(Personagem atacante, Personagem alvo, Random random) {
        int ataque = atacante.calcularAtaque();
        int defesa = alvo.calcularDefesa();

        if (ataque > defesa) {
            int dano = atacante.calcularDano();
            alvo.receberDano(dano);
            System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
        } else {
            System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + ", mas não causa dano.");
        }
    }

    private static void salvarLog(String nickname, Personagem heroi, String resultado, Personagem monstro, int quantidadeDeRodadas) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataPartida = sdf.format(new Date());

        try (FileWriter writer = new FileWriter("temp/" + nickname + ".csv", true)) {
            writer.write(dataPartida + ";" + heroi.getNome() + ";" + resultado + ";" + monstro.getNome() + ";" + quantidadeDeRodadas + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Batalha medieval em andamento!");
    }
}




