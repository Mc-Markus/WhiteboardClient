package shared.messages.client;

import shared.messages.Message;
import shared.model.User;

public abstract class ClientMessage implements Message
{
    protected User sender;
    
    public ClientMessage( User sender )
    {
        this.sender = sender;
    }

    public User getSender()
    {
        return sender;
    }
}
