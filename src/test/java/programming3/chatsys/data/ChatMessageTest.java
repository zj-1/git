package programming3.chatsys.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    ChatMessage chatMessage;

    @BeforeEach
    void setUp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chatMessage = new ChatMessage("test","ghost", 001, timestamp);
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