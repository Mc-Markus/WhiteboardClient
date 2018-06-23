import shared.messages.Message;
import shared.messages.client.InitialMessage;
import shared.messages.client.StopMessage;
import shared.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by mark on 11/06/2018.
 */
public class WhiteboardClient extends Observable {

    private User user;
    private Socket socket;
    private ObjectOutputStream writer;

    public WhiteboardClient(String address, int port, User user){

        this.user = user;

        setUpNetworking( address, port);

        sendMessage( new InitialMessage(user));

        new IncommingReader(socket, this);
    }

    //sets up the networking
    public void setUpNetworking(String address, int port){

        try {
            socket = new Socket(address, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("network connection established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //sends the message to the server
    public void sendMessage(Message message){
        try {
            synchronized (writer){
                writer.writeObject( message);
                writer.flush();
            }
            System.out.println("message send");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //closes writer after sending stopmessage
        if(message instanceof StopMessage){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return user.getName();
    }

    //notifies the view to show the message
    public void addIncoming(Message message){
        setChanged();
        notifyObservers(message);
    }

    public User getUser() {
        return user;
    }
}
