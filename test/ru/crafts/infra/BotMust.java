package ru.crafts.infra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotMust {

    @Test
    public void whenNameAndTokenThenBotIsCreated() {
        Bot bot = new Bot("botName", "botToken");
        assertEquals("botName", bot.getBotUsername());
        assertEquals("botToken", bot.getBotToken());
    }
}
