package ru.crafts.infra.mock;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public class AbsSenderMock extends DefaultAbsSender {
    private String lastSentMessage;

    public AbsSenderMock() {
        super(new DefaultBotOptions());
    }

    public String getLastSentMessage() {
        return lastSentMessage;
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) throws TelegramApiException {
        this.lastSentMessage = method instanceof SendMessage ? ((SendMessage) method).getText() : null;
        return super.execute(method);
    }

}