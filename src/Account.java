public final class Account {
    public static int DEPT_MANAGER = 0;
    public static int DEPT_MEMBER = 1;
    public static int MANAGEMENT_DEPT = 1;
    public static int EVENT_DEPT = 2;
    public static int FINANCE_DEPT = 3;
    private String userName;
    private String password;
    private int role;
    private int dept;
    public static MyConnection mycon;
    public Account(){
        userName = "";
        password = "";
        role = -1;
        dept = -1;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public Account(String userName, String password){
        this.userName = new String(userName);
        this.password = new String(password);
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public int getRole(){
        return role;
    }

    public void setRole(int role){
        this.role = role;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean vaildateAccount(){
        Account temp = Main.myCon.searchAccount(this.userName, this.password);

        if (temp != null) {
            this.role = temp.role;
            this.dept = temp.dept;
            return true;
        }
        return false;
    }

    public boolean createAccount(Member member){
        return Main.myCon.addMember(member, this.userName, this.password);
    }

}
