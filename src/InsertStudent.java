import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertStudent extends JFrame{

    private Student student;
    private JPanel mainPanel;
    private JTextField uniAbbrevInput;
    private JTextField dobInput;
    private JTextField majorInput;
    private JTextField phoneNumInput;
    private JLabel headerText;
    private JLabel name;
    private JLabel ID;
    private JLabel uni;
    private JLabel uniAbbrev;
    private JLabel dob;
    private JLabel major;
    private JLabel phoneNum;
    private JLabel nameLabel;
    private JLabel IDLabel;
    private JLabel uniLabel;
    private JButton confirmButton;
    private JButton exitButton;

    public InsertStudent(Student s){
        super();
        this.student = s;
        nameLabel.setText(s.getFullName());
        IDLabel.setText(s.getId().toString());
        uniLabel.setText(s.getUni());
        uniAbbrevInput.setText(s.getUniAbbreviation());
        super.setTitle("Điền thêm thông tin");
        super.add(mainPanel);
        super.setLocationRelativeTo(null);
        super.setSize(650, 500);
        super.setVisible(true);
        exitButton.addActionListener(e -> {
            super.dispose();
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.setUniAbbreviation(uniAbbrevInput.getText());
                student.setPhoneNum(phoneNumInput.getText());
                student.setMajor(majorInput.getText());
                student.setDob(dobInput.getText());
                if(Main.val.val(student))
                if (Main.myCon.addStudentInfor(student))
                    JOptionPane.showMessageDialog(mainPanel, "Thêm thông tin thành công");
                else
                    JOptionPane.showMessageDialog(mainPanel, "Thêm thông tin thất bại, dữ liệu có thể đã bị trùng lặp\n" +
                                                                    "vui lòng thử lại");
            }
        });
    }


}
