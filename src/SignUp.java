import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame{
    private JTextField nameInput;
    private JTextField phoneNumInput;
    private JTextField dobInput;
    private JComboBox dept;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton exitButton;
    private JButton signinButton;
    private JCheckBox memCheckBox;
    private JCheckBox managerCheckBox;
    private JPanel mainPanel;

    public SignUp() {
        super();
        super.add(mainPanel);
        super.setSize(700, 600);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exitButton.addActionListener(e -> {
            new Login();
            super.dispose();
        });

        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Member member = new Member();
                Account account = new Account();
                member.setFullName(nameInput.getText());
                member.setDob(dobInput.getText());
                member.setPhoneNum(phoneNumInput.getText());

                if(memCheckBox.isSelected()) {
                    member.setRole("Thanh vien");
                    account.setRole(Account.DEPT_MEMBER);
                }
                else {
                    member.setRole("Truong ban");
                    account.setRole(Account.DEPT_MANAGER);
                }
                member.setIdDep(dept.getSelectedIndex()+1);
                account.setDept(dept.getSelectedIndex()+1);
                System.out.println(dept.getSelectedIndex());
                account.setUserName(usernameInput.getText());
                account.setPassword(String.valueOf(passwordInput.getPassword()));


                // EXECUTE QUERY
                if (account.createAccount(member)){
                    JOptionPane.showMessageDialog(mainPanel, "Đăng ký tài khoản thành công");
                    close();
                    new Login();
                }
                else
                    JOptionPane.showMessageDialog(mainPanel, "Đăng ký thất bại.\nVui lòng thử lại sau");

            }
        });
        super.setVisible(true);
        memCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerCheckBox.setSelected(!managerCheckBox.isSelected());
            }
        });


        managerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memCheckBox.setSelected(!memCheckBox.isSelected());
            }
        });
    }

    public void close(){
        dispose();
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
