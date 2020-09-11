package luccasdev.idwall.desafio.crawler.utils;

import lombok.experimental.UtilityClass;
import luccasdev.idwall.desafio.crawler.model.RedditThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class MessageUtil {

    public static final String BREAK_LINE = "\n";

    public static final String ON_RESPONSE_MESSAGE = "Se liga! Encontrei umas Threads que estão bombando, olha ai!";

    public static final String HELP_MESSAGE = "Vi que você precisa de ajuda, para ficar por dentro das threads" +
            " que estão bombando, basta digitar /NadaPraFazer [+ Lista de subrredits]";

    public static final String WELCOME_MESSAGE = "Olá, sou o MrLuke, o melhor BOT para te ajudar a ficar por dentro" +
            " das threads que estão bombando!, digite /NadaPraFazer [+ Lista de subrredits], que eu te mostro!";

    public static final String UNKNOWN_MESSAGE = "Desculpe não entendi, tente digitar o comando /Help";

    public List<String> formatUserMessageToSubreddit(String userMessage) {
        List<String> subRedditList = new ArrayList<>();
        userMessage = userMessage.replaceAll(CommandEnum.NADA_PRA_FAZER.getText(), "").trim();

        if (userMessage.isEmpty()) {
            return subRedditList;
        }

        subRedditList = Arrays.asList(userMessage.replaceAll("(^;|;$)", "").split(";"));

        if (subRedditList.size() == 0) {
            subRedditList.add(userMessage);
        }

        return subRedditList;
    }

    public String formatThreadListToMessage(RedditThread redditThread) {
        return  ON_RESPONSE_MESSAGE +
                BREAK_LINE +
                "Título - "+ redditThread.getTitle() +
                BREAK_LINE +
                "Subreddit - " + redditThread.getSubreddit() + " - pontuação: " +redditThread.getUpVotes() +
                BREAK_LINE +
                "Link - "+ redditThread.getLink() +
                BREAK_LINE;
    }

}
