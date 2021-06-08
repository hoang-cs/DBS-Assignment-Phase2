import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SponsorGUI extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JButton findButton;
    private JButton exitButton;
    private JTextField textField1;
    private JCheckBox OrderNameCheck;
    private JCheckBox DOBCheckBox;

    private JPanel buttonPanel;
    private JPanel inputPanel;
    private JComboBox sType;
    private Account account;
    Sponsor s;
    public SponsorGUI(Account a) {
        super();
        super.add(mainPanel);
        account = a;
        super.setTitle("Nhà tài trợ");
        super.setSize(900, 280);
        super.setLocationRelativeTo(null);
        if (account.getRole() == Account.DEPT_MANAGER &&
                (account.getDept() == Account.MANAGEMENT_DEPT || account.getDept() != Account.FINANCE_DEPT)){
            addButton.setEnabled(true);
        }else addButton.setEnabled(false);
        super.setVisible(true);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s=new Sponsor();
                boolean per=(sType.getSelectedItem().equals("Cá nhân"))?true:false;
                if(per){
                    s.person=new Sponsor.Person();
                    s.person.setFullName(textField1.getText());
                }
                else {
                    s.company=new Sponsor.Company();
                    s.company.setCompName(textField1.getText());
                }
                new SponsorResult(s, per, OrderNameCheck.isSelected(), DOBCheckBox.isSelected(), account);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng không để trống thông tin cần thêm");
                    return;
                }
                s=new Sponsor();
                s.setMoney(0);
                s.setIdSponsor(-1);
                s.setIdDept(3);
                boolean per=(sType.getSelectedItem().equals("Cá nhân"))?true:false;
                if (per) {
                    s.person = new Sponsor.Person();
                    s.person.setFullName(textField1.getText());
                    new InsertPerson(s);
                } else {
                    s.company = new Sponsor.Company();
                    s.company.setCompName(textField1.getText());
                    new InsertCompany(s);
                }
            }
        });
        OrderNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DOBCheckBox.setSelected(!DOBCheckBox.isSelected());
            }
        });


        DOBCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderNameCheck.setSelected(!OrderNameCheck.isSelected());
            }
        });
    }

};