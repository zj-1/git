package programming3.chatsys.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    ChatMessage chatMessage;

    @BeforeEach
    void setUp() {
        chatMessage = new ChatMessage("test",001);
    }

    @Test
    void getMessage() {
        assertEquals(chatMessage.getMessage(), "test");
    }

    @Test
    void format() {
    }

    @Test
    void parse() {
    }

    @Test
    void save(){
        //chatMessage.save("messages.csv");
    }

    @AfterEach
    void clean(){

    }
}