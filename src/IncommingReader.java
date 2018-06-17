import shared.messages.server.UsersMessage;
import shared.messages.server.WhiteboardMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by mark on 11/06/2018.
 */
public class IncommingReader implements Runnable{

    private ObjectInputStream reader;
    private WhiteboardClient client;

    public IncommingReader(Socket socket, WhiteboardClient client) {
        this.client = client;

        try {
            reader = new ObjectInputStream( socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    @Override
    public void run() {
        Object object;

        try{
            while((object = reader.readObject()) != null){

                if(object instanceof WhiteboardMessage){
                    System.out.println("whiteboard message recieved");
                    client.addIncomingWhiteboard((WhiteboardMessage)object);
                }
                else if(object instanceof UsersMessage){
                    client.addIncomingUsers((UsersMessage)object);
                }
                else{
                    System.out.println("illegal message type: " + object.getClass().getSimpleName());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
