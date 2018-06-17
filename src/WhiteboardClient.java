import shared.messages.Message;
import shared.messages.client.InitialMessage;
import shared.messages.server.UsersMessage;
import shared.messages.server.WhiteboardMessage;
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

    public void setUpNetworking(String address, int port){

        try {
            socket = new Socket(address, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("network connection established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){
        try {
            writer.writeObject( message);
            writer.flush();
            System.out.println("message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return user.getName();
    }

    public void addIncomingUsers(UsersMessage message){
        setChanged();
        notifyObservers(message);
    }

    public void addIncomingWhiteboard(WhiteboardMessage message){
        setChanged();
        notifyObservers(message);
    }
}
