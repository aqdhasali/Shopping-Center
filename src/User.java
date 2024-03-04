public class User extends Product{
    private String user_name;
    private String password;

    //Constructors
    public User(){}

    public User(String user_name, String password){
        this.user_name = user_name;
        this.password = password;
    }

    //Getter method to get the username of the user
    public String getUser_name(){
        return user_name;
    }

    //Setter Method set the username of the user
    public void setUser_name(){
        this.user_name = user_name;
    }

    //Getter method to get the password of the user
    public String getPassword(){
        return password;
    }

    //Setter Method set the password of the user
    public void setPassword(){
        this.password = password;
    }
}
