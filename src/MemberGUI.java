import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class MemberGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel resultPanel;
    private JPanel inputPanel;
    private JTable resultTable;
    private JButton addButton;
    private JButton findButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JTextField nameInput;
    private JTextField IDInput;
    private JCheckBox OrderNameCheck;
    private JCheckBox DOBCheckBox;
    private JLabel nameLabel;
    private JLabel IDLabel;
    private JLabel OrderLabel;
    private JButton modButton;
    private DefaultTableModel resultModel;
    private Account account;
    public MemberGUI(Account a){
        super();
        this.account = a;
        System.out.println(a.getDept());
        System.out.println(a.getRole());
        System.out.println(a.getUserName());
        super.add(mainPanel);
        super.setTitle("Thành viên");
        deleteButton.setEnabled(false);
        modButton.setEnabled(false);
        super.setSize(900, 620);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        exitButton.addActionListener(e -> {
            super.dispose();
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modButton.setEnabled(false);
                resultModel.getDataVector().removeAllElements();
                resultModel.fireTableDataChanged();
                String order="";
                Member m = new Member();
                m.setFullName(nameInput.getText());
                if(!IDInput.getText().equals(""))
                    m.setId(Integer.parseInt(IDInput.getText()));
                else m.setId(0);
                if(OrderNameCheck.isSelected()||DOBCheckBox.isSelected()){
                    order+=" ORDER BY ";
                    if(OrderNameCheck.isSelected()){ order+= " [Họ và tên] ";
                        if(DOBCheckBox.isSelected()) order+=", [Ngày sinh] ";}
                    else if(DOBCheckBox.isSelected()) order+=" [Ngày sinh] ";
                }

                // CALL EXECUTE QUERY
                if(Main.val.val(m)) {
                    ArrayList<Member> resultList = Main.myCon.searchMemberInfor(m, order);

                    for (Member x : resultList) {
                        String temp;
                        if (x.getIdDep() == 1) temp = "Ban quản lý";
                        else if (x.getIdDep() == 2) temp = "Ban tổ chức";
                        else temp = "Ban tài chính";
                        resultModel.addRow(new Object[]{x.getId(), x.getFullName(), x.getDob(), x.getPhoneNum(), x.getRole(), temp});
                    }
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (resultModel.getRowCount() == 0 || selectedRow == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn cần chọn 1 hàng trong bảng để xóa !");
                    return;
                }

                Member m = new Member();
                m.setId(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                m.setFullName(resultModel.getValueAt(selectedRow, 1).toString());
                m.setPhoneNum(resultModel.getValueAt(selectedRow, 3).toString());
                m.setDob(resultModel.getValueAt(selectedRow, 2).toString());
                m.setRole(resultModel.getValueAt(selectedRow, 4).toString());
                String temp = resultModel.getValueAt(selectedRow, 5).toString();
                if (temp.equals("Ban quản lý")) m.setIdDep(1);
                else if(temp.equals("Ban sự kiện")) m.setIdDep(2);
                else    m.setIdDep(3);

                int dialogResult = confirmDelete();
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // EXECUTE THE DELETION QUERY
                    if (Main.myCon.rmvMember(m)) {
                        JOptionPane.showMessageDialog(mainPanel, "Thành công");
                        resultModel.removeRow(selectedRow);
                    } else JOptionPane.showMessageDialog(mainPanel, "Thất bại");


                }
            }
        });

        /*
        * Khi chọn 1 record trong bảng kết quả
        *  => Nếu là trưởng ban quản lý thì có quyền xóa và chỉnh sửa thông tin
        *  => Còn lại không được làm gì
        * */
        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(account.getRole() == Account.DEPT_MANAGER && account.getDept() == Account.MANAGEMENT_DEPT) {
                    deleteButton.setEnabled(true);
                    modButton.setEnabled(true);
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
        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (resultModel.getRowCount() == 0 || selectedRow == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn cần chọn 1 hàng trong bảng để xóa !");
                    return;
                }

                Member m = new Member();
                m.setId(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                m.setFullName(resultModel.getValueAt(selectedRow, 1).toString());
                m.setPhoneNum(resultModel.getValueAt(selectedRow, 3).toString());
                m.setDob(resultModel.getValueAt(selectedRow, 2).toString());
                m.setRole(resultModel.getValueAt(selectedRow, 4).toString());
                String temp = resultModel.getValueAt(selectedRow, 5).toString();
                if (temp.equals("Ban quản lý")) m.setIdDep(1);
                else if(temp.equals("Ban sự kiện")) m.setIdDep(2);
                else    m.setIdDep(3);

                 new ModMem(m);
                resultModel.setValueAt(m.getFullName(), selectedRow, 1);
                resultModel.setValueAt(m.getDob(), selectedRow, 2);
                resultModel.setValueAt(m.getPhoneNum(), selectedRow, 3);
                resultModel.setValueAt(m.getRole(), selectedRow, 4);
                if (m.getIdDep() == 0)  resultModel.setValueAt("Ban quản lý", selectedRow, 5);
                else if (m.getIdDep() == 1)  resultModel.setValueAt("Ban sự kiện", selectedRow, 5);
                else  resultModel.setValueAt("Ban tài chính", selectedRow, 5);
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
    public void tick ( boolean gr){
        if (gr==true) gr=false;
        else gr = true;
    }
    private void createUIComponents() {
        String header[] = {"ID", "Họ và tên", "Ngày sinh","SĐT",  "Vai trò", "Phòng ban"};
        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);
        resultModel.setColumnIdentifiers(header);
    }
}
