package todolist.service;

import java.util.List;

import todolist.model.User;

public interface UserService {
    
    public User getLoginUser(String user);
    
    public List<User> getUsers();
    
}