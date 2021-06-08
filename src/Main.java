import javax.swing.*;

public class Main {
    public static MyConnection myCon;
    public static validate val;
    public static void main(String[] args) {

        myCon = new MyConnection();
        val=new validate();

        new Login();
    }
}