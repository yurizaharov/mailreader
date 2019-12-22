import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Pop3MailReader {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // connection parameters
        String host = "*.*.*.*";
        String user = "user";
        String password = "password";

        try {
            Properties properties = System.getProperties();
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("pop3");
            store.connect(host, user, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // get the count of inbox messages
            Message[] messages = inbox.getMessages();
            System.out.println("Найдено: " + messages.length + " сообщений");
            System.out.println();

            //String text2 = new String(text1.getBytes("utf-8"), "utf-8");
            for (int i=0; i<messages.length; i++) {
                String text1 = InternetAddress.toString(messages[i].getFrom());
                System.out.println(System.getProperty(text1.encoding));
                String text2 = new String(text1.getBytes("koi8-r"), "utf-8");
                System.out.println("Message " + (i + 1));
                System.out.println("From: " + text2);
                System.out.println("Subject: " + messages[i].getSubject());
                System.out.println("Sent Date: " + messages[i].getSentDate());
                System.out.println();
            }

            inbox.close(true);
            store.close();
        }
        catch (MessagingException e)
        {
            System.out.println("Something went wrong");
            //e.printStackTrace();
        }
    }
}
