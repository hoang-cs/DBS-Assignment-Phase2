import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertCompany extends JFrame{
    private JPanel mainPanel;
    private JTextField MSInput;
    private JTextField nameRepInput;
    private JTextField phoneInput;
    private JLabel nameLabel;
    private JButton confirmButton;
    private JButton exitButton;

    public InsertCompany(Sponsor s){
        super();
        super.add(mainPanel);
        nameLabel.setText(s.company.getCompName());
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        super.setSize(600, 430);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.setMoney(0);
                s.setIdDept(3);
                s.company.setMS(Integer.parseInt((MSInput.getText())));
                s.company.setRepName(nameRepInput.getText());
                s.company.setRepPhoneNum(phoneInput.getText());
                // EXECUTE INSERT QUERY
                if(Main.myCon.addCom(s.company)) {
                    JOptionPane.showMessageDialog(mainPanel, "Thành công");
                    close();
                }
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");

            }
        });
    }
    public void close(){dispose();}
}
