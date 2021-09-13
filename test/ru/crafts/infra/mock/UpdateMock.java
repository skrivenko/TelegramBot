package ru.crafts.infra.mock;

import org.telegram.telegrambots.meta.api.objects.*;

import java.util.List;

public class UpdateMock extends Update {

    public UpdateMock(String text) {
        super();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1234L);
        message.setChat(chat);
        message.setText(text);
        this.setMessage(message);
    }

    public UpdateMock(String text, boolean isCommand) {
        this(text);

        if (isCommand) {
            MessageEntity entity = new MessageEntity();
            entity.setType(EntityType.BOTCOMMAND);
            entity.setOffset(0);
            this.getMessage().setEntities(List.of(entity));
        }
    }
}
