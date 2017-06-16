import chat.messages.LoginMessage;
import chat.messages.Message;
import org.junit.Test;

public class MessageTest
{
    @Test
    public void msgTest(){
        LoginMessage m1 = new LoginMessage("pep", "pop", 1L, 1L);
        LoginMessage m2 = new LoginMessage("pep", "pop", 1L, 1L);

        Message m3 = (Message) m1;
        System.out.println(m1.equals(m2));

    }
}
