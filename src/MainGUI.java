import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame{
    private JPanel mainPanel;
    private JButton sponsorButton;
    private JButton studentButton;
    private JButton memButton;
    private JButton progButton;
    private JLabel introText;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel rightMidPanel;
    private JPanel leftMidPanel;
    private JPanel leftPanel;
    private JLabel introLabel;
    private JLabel logout;
    private Account account;
    public MainGUI(Account a){
        super();
        this.account = a;
        super.add(mainPanel);
        super.setTitle("AIESEC");
        introLabel.setText("Bạn đang đăng nhập dưới tên: " + a.getUserName());
        logout.setText("Đăng xuất");
        super.setSize(600, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

        // Exit program when click "Thoát" button


        // Open new window when click "Chương trình" button
        progButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProgGUI(account);
            }
        });
        memButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemberGUI(account);
            }
        });
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentGUI(account);
            }
        });
        sponsorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SponsorGUI(account);
            }
        });
        logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(JOptionPane.showConfirmDialog(mainPanel, "Bạn có chắc chắn muốn đăng xuất") == 0) {
                    new Login();
                    close();
                }
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
