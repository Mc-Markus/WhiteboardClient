package shared.messages.client;

import shared.model.User;

public class UserMessage extends ClientMessage
{
    private static final long serialVersionUID = 11300;
    
    public UserMessage( User sender )
    {
        super( sender );
    }
}
