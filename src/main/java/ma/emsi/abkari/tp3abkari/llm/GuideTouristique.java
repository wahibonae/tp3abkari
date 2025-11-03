package ma.emsi.abkari.tp3abkari.llm;

import dev.langchain4j.service.SystemMessage;

public interface GuideTouristique {
    String chat(String prompt);
}
