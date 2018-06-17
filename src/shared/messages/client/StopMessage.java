package shared.messages.client;

import shared.model.User;

public class StopMessage extends ClientMessage
{
    private static final long serialVersionUID = 11200;
    
    public StopMessage(User sender)
    {
        super(sender);
    }

    public StopMessage(StopMessage message)
    {
        this(message.sender);
    }
}
