package luccasdev.idwall.desafio.crawler.service;

import lombok.extern.slf4j.Slf4j;
import luccasdev.idwall.desafio.crawler.utils.CommandEnum;
import luccasdev.idwall.desafio.crawler.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramRedditBotService extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final RedditThreadService redditThreadService;

    public TelegramRedditBotService(RedditThreadService redditThreadService) {
        this.redditThreadService = redditThreadService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText().toLowerCase();
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId());

            if (userMessage.startsWith(CommandEnum.NADA_PRA_FAZER.getText())) {
                this.redditThreadService.findHotRedditThreadsBySubreddits(MessageUtil.formatUserMessageToSubreddit(userMessage))
                        .forEach(redditThread -> {
                            response.setText(MessageUtil.formatThreadListToMessage(redditThread));
                            try {
                                execute(response);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        });
                return;
            } else if (userMessage.startsWith(CommandEnum.HELP.getText())) {
                response.setText(MessageUtil.HELP_MESSAGE);
            } else if (userMessage.startsWith(CommandEnum.START.getText())) {
                response.setText(MessageUtil.WELCOME_MESSAGE);
            } else {
                response.setText(MessageUtil.UNKNOWN_MESSAGE);
            }
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
