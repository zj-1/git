package programming3.chatsys.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    ChatMessage chatMessage;
    Database database;

    @BeforeEach
    void prepare(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chatMessage = new ChatMessage("test","Jhon",0001,timestamp);
    }

    @Test
    void addMessages() throws Exception {
        database =new Database();
        database.addMessages(chatMessage);
    }

    @Test
    void readMessages() throws IOException {
        database = new Database();
        chatMessage = database.readMessages().get(0);
        assertEquals(chatMessage.getUser(),"Jhon");
    }

    @Test
    void addUser(){

    }

    @Test
    void readUsers(){

    }

    @AfterEach
    void clean(){
        
    }

}