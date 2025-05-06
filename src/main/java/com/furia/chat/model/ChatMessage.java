package com.furia.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CHAT_MESSAGE")
@NoArgsConstructor @Data
public class ChatMessage {
    @Id
    @Column(name = "ID", length = 36)
    private String id;
    @Column(name = "SENDER", nullable = false, length = 60)
    private String sender;
    @Column(name = "MESSAGE", nullable = false, length = 300)
    private String message;
    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    public ChatMessage(String sender, String message) {
        if (id == null)
            this.id = UUID.randomUUID().toString();
        if (timestamp == null)
            this.timestamp = LocalDateTime.now();
        this.sender = sender;
        this.message = message;
    }

    public static boolean isToBot(String message) {
        message = message.trim();
        return message.startsWith("@Furia");
    }

    public static String answer(String message) {
        if (isToBot(message)) {
            String normalized = Normalizer
                    .normalize(message, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                    .toLowerCase();

            if (normalized.contains("jogo")) {
                if (normalized.contains("proximo")) {
                    return "O próximo jogo da Fúria é amanhã às 20h contra a NAVI, pela ESL Pro League.";
                } else if (normalized.contains("ultimo")) {
                    return "O último jogo que a Fúria disputou foi contra a Team Liquid pela World Cup. A Fúria venceu por 2x1.";
                }
                return "Posso te informar sobre o próximo jogo ou o resultado do anterior.";
            }

            if (normalized.contains("elenco") || normalized.contains("jogadores") || normalized.contains("time") || normalized.contains("line")) {
                if (normalized.contains("cs") || normalized.contains("counter")) {
                    return "O elenco atual de CS2 da Fúria inclui: arT, KSCERATO, yuurih, chelo e FalleN.";
                } else if (normalized.contains("lol") || normalized.contains("league")) {
                    return "O elenco de League of Legends da Fúria é formado por: Envy, Tyrin, RedBert, Netuno e Sephis.";
                } else if (normalized.contains("valorant")) {
                    return "O time de Valorant da Fúria conta com mwzera, Khalil, kon4n, qck e xand.";
                } else if (normalized.contains("rainbow") || normalized.contains("r6")) {
                    return "O elenco de Rainbow Six da Fúria inclui: Twister, Miracle, Handy, Bassetto e Lenda.";
                } else if (normalized.contains("rocket")) {
                    return "O time de Rocket League da Fúria tem CaioTG1, Bemmz e Lostt.";
                } else if (normalized.contains("pubg")) {
                    return "O elenco de PUBG da Fúria inclui: Rustyzera, Spark, Phex e Jowzera.";
                } else if (normalized.contains("apex")) {
                    return "O time de Apex Legends da Fúria conta com Pandx, Edshots e Twize.";
                } else {
                    return "Qual modalidade você quer saber? Temos times de CS, LoL, Valorant, R6, Rocket League, PUBG e Apex.";
                }
            }
            return "Você pode perguntar sobre o próximo jogo, o elenco de alguma modalidade ou os resultados recentes.";
        }
        return null;
    }


}
