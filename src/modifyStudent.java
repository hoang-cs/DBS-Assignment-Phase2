import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class modifyStudent extends JDialog{
    private JLabel IDInput;
    private JTextField nameInput;
    private JTextField uniNameInput;
    private JTextField uniAbbrevInput;
    private JTextField dobInput;
    private JTextField majorInput;
    private JTextField phoneInput;
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton exitButton;
    public modifyStudent(JFrame parent, boolean modal, Student s){
        super(parent, modal);
        ArrayList<Student> std = Main.myCon.searchStudentInfor(s, true);
        s.setUniAbbreviation(std.get(0).getUniAbbreviation());
        IDInput.setText(s.getId().toString());
        nameInput.setText(s.getFullName());
        uniNameInput.setText(s.getUni());
        uniAbbrevInput.setText(s.getUniAbbreviation());
        dobInput.setText(s.getDob());
        majorInput.setText(s.getMajor());
        phoneInput.setText(s.getPhoneNum());
        super.add(mainPanel);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.setFullName(nameInput.getText());
                s.setUni(uniNameInput.getText());
                s.setUniAbbreviation(uniAbbrevInput.getText());
                s.setDob(dobInput.getText());
                s.setMajor(majorInput.getText());
                s.setPhoneNum(phoneInput.getText());

                // call SQL UPDATE
                if(Main.val.val(s))
                if(Main.myCon.modStudent(s)){
                    JOptionPane.showMessageDialog(mainPanel, "Chỉnh sửa sinh viên thành công");
                }
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");



            }
        });

        exitButton.addActionListener(e -> {
            this.dispose();
        });

        super.setSize(650, 650);
        super.setLocationRelativeTo(null);
        super.setTitle("Chỉnh sửa thông tin");
        super.setVisible(true);
    }
}
