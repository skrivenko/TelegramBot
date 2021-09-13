package ru.crafts.infra;

import org.junit.jupiter.api.Test;
import ru.crafts.infra.mock.AbsSenderMock;
import ru.crafts.infra.mock.UpdateMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotMust {

    @Test
    public void beCreatedWithNameAndToken() {
        Bot bot = new Bot("botName", "botToken");
        assertEquals("botName", bot.getBotUsername());
        assertEquals("botToken", bot.getBotToken());
    }

    @Test
    public void answerStartCommandWithHello() {
        Bot bot = new Bot("botName", "botToken");
        AbsSenderMock senderMock = new AbsSenderMock();
        bot.setInternalSender(senderMock);
        bot.onUpdateReceived(new UpdateMock("/start", true));

        assertEquals("Hello!", senderMock.getLastSentMessage());
    }


}
