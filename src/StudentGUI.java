import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StudentGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel resultPanel;
    private JPanel inputPanel;
    private JTable resultTable;
    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton exitButton;
    private JTextField nameInput;
    private JTextField IDInput;
    private JLabel nameLabel;
    private JCheckBox OrderNameCheck;
    private JCheckBox DOBCheckBox;
    private JTextField schoolNameInput;
    private JLabel schoolNameLabel;
    private JButton joinProg;
    private JTextField uniAbbrev;
    private JButton joinedProgButton;
    private DefaultTableModel resultModel;
    private Student selectedStudent;
    private Account account;
    public StudentGUI(Account a){
        super();
        account = a;

        if (a.getRole() != Account.DEPT_MANAGER && a.getDept() != Account.MANAGEMENT_DEPT)
            addButton.setEnabled(false);
        else    addButton.setEnabled(true);
        deleteButton.setEnabled(false);
        joinedProgButton.setEnabled(false);
        joinProg.setEnabled(false);
        modifyButton.setEnabled(false);
        super.add(mainPanel);
        super.setTitle("Sinh viên");
        exitButton.addActionListener(e -> {
            super.dispose();
        });

        deleteButton.setEnabled(false);
        modifyButton.setEnabled(false);
        joinProg.setEnabled(false);

        super.setSize(900, 700);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(false);
                modifyButton.setEnabled(false);
                joinProg.setEnabled(false);
                resultModel.getDataVector().removeAllElements();
                resultModel.fireTableDataChanged();

                Student student = new Student();
                student.setFullName(nameInput.getText());
                student.setUni(schoolNameInput.getText());
                student.setUniAbbreviation(uniAbbrev.getText());
                if (!IDInput.getText().equals(""))
                    student.setId(Integer.parseInt(IDInput.getText()));

                boolean order = OrderNameCheck.isSelected();

                ArrayList<Student> resultList = Main.myCon.searchStudentInfor(student, order);

                DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
                for (Student s : resultList){
                    model.addRow(new Object[]{s.getFullName(), s.getId(), s.getUni(),
                            s.getUniAbbreviation(),s.getMajor(),s.getDob(), s.getPhoneNum()});
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student();
                if (nameInput.getText().equals("") || IDInput.getText().equals("") || schoolNameInput.getText().equals("")){
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng không để trống thông tin cần thêm");
                    return;
                }
                s.setFullName(nameInput.getText());
                s.setId(Integer.parseInt(IDInput.getText()));
                s.setUni(schoolNameInput.getText());
                s.setUniAbbreviation(uniAbbrev.getText());
                new InsertStudent(s);
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (resultModel.getRowCount() == 0 || selectedRow == -1){
                    JOptionPane.showMessageDialog(mainPanel, "Bạn cần chọn 1 hàng trong bảng để chỉnh sửa !");
                    return;
                }
                selectedStudent = new Student();
                selectedStudent.setFullName(resultModel.getValueAt(selectedRow, 0).toString());
                selectedStudent.setId(Integer.valueOf(resultModel.getValueAt(selectedRow, 1).toString()));
                selectedStudent.setUni(resultModel.getValueAt(selectedRow, 2).toString());
                selectedStudent.setMajor(resultModel.getValueAt(selectedRow, 4).toString());
                selectedStudent.setDob(resultModel.getValueAt(selectedRow, 5).toString());
                selectedStudent.setPhoneNum(resultModel.getValueAt(selectedRow, 6).toString());
                selectedStudent.setUniAbbreviation(resultModel.getValueAt(selectedRow, 3).toString());

                callModify(selectedStudent);

                resultModel.setValueAt(selectedStudent.getFullName(),selectedRow, 0);
                resultModel.setValueAt(selectedStudent.getId(),selectedRow, 1);
                resultModel.setValueAt(selectedStudent.getUni(),selectedRow, 2);
                resultModel.setValueAt(selectedStudent.getMajor(),selectedRow, 4);
                resultModel.setValueAt(selectedStudent.getDob(),selectedRow, 5);
                resultModel.setValueAt(selectedStudent.getPhoneNum(),selectedRow, 6);
                resultModel.setValueAt(selectedStudent.getUniAbbreviation(), selectedRow, 3);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStudent = new Student();
                int selectedRow = resultTable.getSelectedRow();
                if (resultModel.getRowCount() == 0 || selectedRow == -1){
                    JOptionPane.showMessageDialog(mainPanel, "Bạn cần chọn 1 hàng trong bảng để xóa !");
                    return;
                }
                selectedStudent = new Student();
                selectedStudent.setFullName(resultModel.getValueAt(selectedRow, 0).toString());
                selectedStudent.setId(Integer.valueOf(resultModel.getValueAt(selectedRow, 1).toString()));
                selectedStudent.setUni(resultModel.getValueAt(selectedRow, 2).toString());
                selectedStudent.setMajor(resultModel.getValueAt(selectedRow, 4).toString());
                selectedStudent.setDob(resultModel.getValueAt(selectedRow, 5).toString());
                selectedStudent.setPhoneNum(resultModel.getValueAt(selectedRow, 6).toString());
                selectedStudent.setUniAbbreviation(resultModel.getValueAt(selectedRow,3).toString());

                int dialogResult = confirmDelete();
                if(dialogResult == JOptionPane.YES_OPTION){
                    // EXECUTE THE DELETION QUERY
                    if(Main.myCon.rmvStudent(selectedStudent)){
                        JOptionPane.showMessageDialog(mainPanel, "Thành công");
                        resultModel.removeRow(selectedRow);
                    }
                    else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
                }
            }
        });
        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (account.getRole() == Account.DEPT_MANAGER && account.getDept() == Account.MANAGEMENT_DEPT) {
                    joinProg.setEnabled(true);
                    modifyButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
                joinedProgButton.setEnabled(true);
            }
        });
        joinProg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student();
                int selectedRow = resultTable.getSelectedRow();
                s.setId(Integer.parseInt(resultModel.getValueAt(selectedRow, 1).toString()));
                s.setUniAbbreviation(resultModel.getValueAt(selectedRow, 3).toString());
                callJoinProg(s);
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
        joinedProgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Student s = new Student();
                s.setId(Integer.parseInt(resultModel.getValueAt(selectedRow, 1).toString()));
                s.setUniAbbreviation(resultModel.getValueAt(selectedRow, 3).toString());
                ArrayList<Program> l = Main.myCon.searchJoinedProgram(s);
                new JoinedProgramList(l);
            }
        });
    }

    private int confirmDelete(){
        return JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa hàng được chọn ?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);
    }

    private void callModify(Student s){
        new modifyStudent(this, rootPaneCheckingEnabled, s);
    }
    private void callJoinProg(Student s) {new JoinProg(this, rootPaneCheckingEnabled, s);}
    private void createUIComponents() {
        Object header[] = {"Họ tên", "Mã số","Tên trường","Tên trường viết tắt", "Ngành học", "D.O.B", "SĐT"};
        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);

        resultModel.setColumnIdentifiers(header);
    }
}
