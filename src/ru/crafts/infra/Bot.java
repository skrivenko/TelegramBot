package ru.crafts.infra;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private AbsSender internalSender;

    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

        this.register(new BotCommand("start", "Start Command") {
            @Override
            public void execute(AbsSender sender, User user, Chat chat, String[] strings) {
                sendAnswer(getPrimarySender(sender), chat.getId(), "Hello!");
            }
        });
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public AbsSender getPrimarySender(AbsSender sender) {
        return this.internalSender != null ? this.internalSender : sender;
    }

    public void setInternalSender(AbsSender sender) {
        this.internalSender = sender;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    public void sendAnswer(AbsSender sender, Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        message.enableMarkdown(true); // we need markdown
        try {
            getPrimarySender(sender).execute(message);
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}