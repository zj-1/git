package programming3.chatsys.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    ChatMessage chatMessage;
    Timestamp timestamp;

    @BeforeEach
    void setUp() {
        timestamp = new Timestamp(System.currentTimeMillis());
        chatMessage = new ChatMessage("test","ghost",timestamp);
    }

    @Test
    void fromStringToChatMessage(){
        timestamp = new Timestamp(System.currentTimeMillis());
        String test = "hello,evan," + timestamp.toString();
        chatMessage = new ChatMessage(test);
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