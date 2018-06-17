package shared.messages.client;

import shared.model.User;

public class InitialMessage extends ClientMessage
{
    private static final long serialVersionUID = 11100;
    
    public InitialMessage(User sender)
    {
        super(sender);
    }

    public InitialMessage(InitialMessage message)
    {
        this(message.sender);
    }
}
