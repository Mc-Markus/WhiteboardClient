package shared.messages.server;

import java.util.List;

import shared.model.User;

public class UsersMessage extends ServerMessage
{
    private static final long serialVersionUID = 12200;
    
    protected List<User> users;
    
    public UsersMessage( List<User> users )
    {
        super();
        this.users = users;
    }

    public List < User > getUsers()
    {
        return users;
    }
}
