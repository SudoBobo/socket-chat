import chat.messages.InfoMessage;
import chat.messages.LoginMessage;
import chat.messages.Message;
import chat.messages.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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



    @AfterClass
    public static void tearDownClass() {
        gson = null;
    }
}
