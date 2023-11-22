import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedievalBattleReports {
    public static void main(String[] args) {
        String nickname = "NomeDoJogador";

        List<BattleLog> battleLogs = lerLogs(nickname);
        if (battleLogs.isEmpty()) {
            System.out.println("Nenhum registro encontrado para o jogador " + nickname);
        } else {
            exibirRelatorios(battleLogs);
        }
    }

    private static List<BattleLog> lerLogs(String nickname) {
        List<BattleLog> battleLogs = new ArrayList<>(); 

        return battleLogs;
    }

    private static void exibirRelatorios(List<BattleLog> battleLogs) {
        int totalPontos = 0;
        Map<String, Integer> heroiContagem = new HashMap<>();
        Map<String, Integer> monstroContagem = new HashMap<>();

        for (BattleLog battleLog : battleLogs) {
            totalPontos += 100 - battleLog.getQuantidadeDeRodadas(); 

            heroiContagem.put(battleLog.getHeroiEscolhido(), heroiContagem.getOrDefault(battleLog.getHeroiEscolhido(), 0) + 1);
           
            monstroContagem.put(battleLog.getMonstroEnfrentado(), monstroContagem.getOrDefault(battleLog.getMonstroEnfrentado(), 0) + 1);
        }
        
        String heroiMaisJogado = encontrarMaximo(heroiContagem);
       
        String monstroMaisEnfrentado = encontrarMaximo(monstroContagem);
        
        System.out.println("Quantidade total de Pontos: " + totalPontos);
        System.out.println("Herói mais jogado: " + heroiMaisJogado);
        System.out.println("Monstro mais enfrentado: " + monstroMaisEnfrentado);
    }

    private static String encontrarMaximo(Map<String, Integer> contagem) {
        String maxKey = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : contagem.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return maxKey;
    }
}
