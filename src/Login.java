import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame{
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton exitButton;
    private JTextField usernameInput;
    private JPasswordField password;
    private JPanel leftPanel;
    private JLabel signup;

    public Login(){
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }
        super.add(mainPanel);
        super.setSize(970, 590);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setTitle("AIESEC");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = new Account();
                account.setUserName(usernameInput.getText());
                account.setPassword(String.valueOf(password.getPassword()));
                if (account.vaildateAccount()){
                    new MainGUI(account);
                    close();
                }
                else
                    JOptionPane.showMessageDialog(mainPanel, "Tài khoản bản nhập vào không đúng.\nVui lòng thử lại");
            }
        });
        signup.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
                close();
            }

            public void mouseEntered(MouseEvent e) {
                mainPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                mainPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    public void close(){
        dispose();
    }
}
