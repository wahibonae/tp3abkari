package ma.emsi.abkari.tp3abkari.llm;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

public class LlmClient {
    private String key;
    private GuideTouristique guideTouristique;

    private ChatMemory chatMemory;

    public LlmClient() {
        this.key = System.getenv("GEMINI_KEY");

        if (key == null || key.isEmpty()) {
            System.err.println("❌ ERREUR: La clé GEMINI_KEY n'est pas définie!");
            throw new RuntimeException("La clé API GEMINI_KEY n'est pas configurée");
        }

        ChatModel chatModel = GoogleAiGeminiChatModel.builder()
                .apiKey(key)
                .modelName("gemini-2.5-flash")
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        this.guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(chatModel)
                .chatMemory(chatMemory)
                .build();
    }

    public String envoyerRequete(String lieu, int nbreEndroits) {
        String roleSysteme = String.format("""
                Tu es un guide touristique expert.
                Quand on te donne le nom d'une ville ou d'un pays, tu dois donner les %d principaux endroits à visiter
                et le prix moyen d'un repas dans la devise du pays.
                
                *N'utilise pas Markdown*
                
                Tu dois répondre uniquement avec un JSON qui a exactement ce format :
                {
                    "ville_ou_pays": "nom de la ville ou du pays",
                    "endroits_a_visiter": ["endroit 1", "endroit 2"],
                    "prix_moyen_repas": "<prix> <devise du pays>"
                }
                
                Le tableau "endroits_a_visiter" doit contenir exactement %d éléments.
                Ne donne aucune explication, uniquement le JSON demandé.
                """, nbreEndroits, nbreEndroits);
        chatMemory.clear();
        chatMemory.add(SystemMessage.from(roleSysteme));
        return guideTouristique.chat(lieu);
    }
}
