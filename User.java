package gol;

public class User 
{
    private String txtUserName;
    private String txtPassword;

    public User() 
    {}
    
    public void setTxtUserName(String s)
    {
        txtUserName = s;
    }
    
    public void setTxtPassword(String s)
    {
        int i;
        txtPassword = "";
        for(i =0 ; i< s.length(); i++)
        {
            txtPassword = txtPassword + (char)((int)s.charAt(i) + 1);
        }
    }
    
   
    public String getTxtUserName()
    {
        return txtUserName;
    }
    
    public String getTxtPassword()
    {
        int i;
        String s = "";
        for(i =0 ; i< txtPassword.length(); i++)
        {
            s = s + (char)((int)txtPassword.charAt(i) -1);
        }
        return s;
    }
    

    public boolean registerUser()
    {
        DBManager dbm = DBManager.getInstance();
        return dbm.addUser(txtUserName, txtPassword);
    }
    
    public Member authenticateUser()
    {
        DBManager mgr = DBManager.getInstance();
        return mgr.authenticateUser(txtUserName, txtPassword);
    }
    
    public String isUserNameAvailable(String uName)
    {
        DBManager dbm = DBManager.getInstance();
        if(dbm.isUserNameAvailable(uName))
            return "1";
        else
            return "0";
    }
}
