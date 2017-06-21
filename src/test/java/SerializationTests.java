import chat.messages.*;
import chat.messages.resultMessages.InfoResultMessage;
import chat.serialization.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializationTests {

    private static Gson gson;

    @BeforeClass
    public static void setUpClass() {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Message.class, new MessageDeserializer());
        gson = gsonBuilder.create();
    }


    @Test
    public void generalMessageSerializationTest(){
        Message message = new LoginMessage("bobo", "password", 111L, 1L);
        String messageJson = gson.toJson(message, message.getClass());
        Message messageNew = gson.fromJson(messageJson, Message.class);

        assertEquals("failure", message, messageNew);
    }

    @Test
    public void loginMessageSerializationTest(){
        Message loginMessage = new LoginMessage("bobo", "password", 111L, 1L);
        String loginJson = gson.toJson(loginMessage, loginMessage.getClass());
        Message loginMessageNew = gson.fromJson(loginJson, Message.class);

        assertEquals("failure - names are not equal", (loginMessage), loginMessageNew);

    }

    @Test
    public void infoMessageSerializationTest(){
        Message infoMessage = new InfoMessage(2L, 1L, 222L);
        String infoJson = gson.toJson(infoMessage, infoMessage.getClass());
        Message infoMessageNew = gson.fromJson(infoJson, Message.class);

        assertEquals(infoMessage, infoMessageNew);
    }

    @Test
    public void infoResultTest(){
        Message infoRes = new InfoResultMessage("bobo", 1L);
        String infoResJson = gson.toJson(infoRes, infoRes.getClass());
        Message newInfoRes = gson.fromJson(infoResJson, Message.class);

        assertEquals(infoRes,newInfoRes);
    }

    @Test
    public void createChatTest(){
        List<Long> usersId = new LinkedList<>();
        usersId.add(666L);
        usersId.add(777L);
        usersId.add(888L);

        Message m1 = new ChatCreateMessage(usersId);
        String js = gson.toJson(m1, m1.getClass());
        Message m2 = gson.fromJson(js, Message.class);

        assertEquals(m1, m2);
    }



    @AfterClass
    public static void tearDownClass() {
        gson = null;
    }
}
