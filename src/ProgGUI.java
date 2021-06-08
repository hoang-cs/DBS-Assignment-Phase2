import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProgGUI extends JFrame{
    private JPanel mainPanel;
    private JTable resultTable;
    private JButton addButton;
    private JButton findButton;
    private JButton modifyButton;
    private JButton exitButton;
    private JTextField nameInput;
    private JTextField startTimeInput;
    private JCheckBox volunCheck;
    private JCheckBox eduCheck;
    private JCheckBox internCheck;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JTextField endTimeInput;
    private JLabel startTime;
    private JLabel endTime;
    private JButton addEventButton;
    private JButton viewEventButton;
    private DefaultTableModel resultModel;
    private Account account;

    public ProgGUI(Account a){
        super();
        account = a;
        System.out.println(a.getDept());
        System.out.println(a.getRole());
        System.out.println(a.getUserName());
        super.add(mainPanel);
        super.setTitle("Chương trình");
        exitButton.addActionListener(e -> {
            super.dispose();
        });

        if(account.getRole() != Account.DEPT_MANAGER &&
                (account.getDept() != Account.MANAGEMENT_DEPT || account.getDept() != Account.EVENT_DEPT)){
            addButton.setEnabled(false);
        }else addButton.setEnabled(true);
        addEventButton.setEnabled(false);
        viewEventButton.setEnabled(false);
        modifyButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Program program = new Program();
                if (nameInput.getText().equals("") || startTimeInput.getText().equals("") ||
                        endTimeInput.getText().equals("") || (!volunCheck.isSelected() &&
                        !eduCheck.isSelected() && !internCheck.isSelected())){
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng không để trống thông tin cần thêm");
                    return;
                }
                program.setName(nameInput.getText());
                program.setStartTime(startTimeInput.getText());
                program.setEndTime(endTimeInput.getText());
                if (volunCheck.isSelected())
                    program.setType("TN");
                if (eduCheck.isSelected())
                    program.setType("GD");
                if (internCheck.isSelected())
                    program.setType("TT");
                if(Main.val.val(program))
                    new InsertProgram(program);
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEventButton.setEnabled(false);
                viewEventButton.setEnabled(false);
                modifyButton.setEnabled(false);
                resultModel.getDataVector().removeAllElements();
                resultModel.fireTableDataChanged();

                Program program = new Program();
                program.setName(nameInput.getText());
                program.setStartTime(startTimeInput.getText());
                program.setEndTime(endTimeInput.getText());
                if (volunCheck.isSelected())
                    program.setType("TN");
                if (eduCheck.isSelected())
                    program.setType("GD");
                if (internCheck.isSelected())
                    program.setType("TT");
                if(Main.val.val(program)) {
                    ArrayList<Program> resultList = Main.myCon.searchProgramInfor(program);
                    for (Program p : resultList) {
                        resultModel.addRow(new Object[]{p.getId(), p.getName(), p.getType(), p.getCoordinate(), p.getNation(),
                                p.getStartTime(), p.getEndTime(), p.getMoney(), p.getNumOfStudent()});
                    }
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();

                Program selected = new Program();
                selected.setId(Integer.parseInt(resultModel.getValueAt(selectedRow,0).toString()));
                selected.setName(resultModel.getValueAt(selectedRow, 1).toString());
                selected.setType(resultModel.getValueAt(selectedRow, 2).toString());
                selected.setCoordinate(resultModel.getValueAt(selectedRow, 3).toString());
                selected.setNation(resultModel.getValueAt(selectedRow, 4).toString());
                selected.setStartTime(resultModel.getValueAt(selectedRow, 5).toString());
                selected.setEndTime(resultModel.getValueAt(selectedRow, 6).toString());
                selected.setMoney(Integer.parseInt(resultModel.getValueAt(selectedRow, 7).toString()));
                selected.setNumOfStudent(Integer.parseInt(resultModel.getValueAt(selectedRow, 8).toString()));

                callModify(selected);

                resultModel.setValueAt(selected.getId(), selectedRow, 0);
                resultModel.setValueAt(selected.getName(), selectedRow, 1);
                resultModel.setValueAt(selected.getType(), selectedRow, 2);
                resultModel.setValueAt(selected.getCoordinate(), selectedRow, 3);
                resultModel.setValueAt(selected.getNation(), selectedRow, 4);
                resultModel.setValueAt(selected.getStartTime(), selectedRow, 5);
                resultModel.setValueAt(selected.getEndTime(), selectedRow, 6);
                resultModel.setValueAt(selected.getMoney(), selectedRow, 7);
                resultModel.setValueAt(selected.getNumOfStudent(), selectedRow, 8);
            }
        });

        /*
        * Khi chọn 1 record trong bảng kết quả
        * => Nếu là trưởng ban tổ chức hoặc trưởng ban quản lý thì
        * cho nhất nút "Chỉnh sửa", "Thêm sự kiện" và "Xem sự kiện"
        * => Còn lại chỉ hiện nút "Xem sự kiện"
        * */
        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(account.getRole() == Account.DEPT_MANAGER
                        && (account.getDept() == Account.MANAGEMENT_DEPT ||
                        account.getDept() == Account.EVENT_DEPT)) {
                    addEventButton.setEnabled(true);
                    modifyButton.setEnabled(true);
                }
                viewEventButton.setEnabled(true);

            }
        });
        viewEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Program selected = new Program();
                selected.setId(Integer.parseInt(resultModel.getValueAt(selectedRow,0).toString()));
                selected.setName(resultModel.getValueAt(selectedRow, 1).toString());
                selected.setType(resultModel.getValueAt(selectedRow, 2).toString());
                selected.setCoordinate(resultModel.getValueAt(selectedRow, 3).toString());
                selected.setNation(resultModel.getValueAt(selectedRow, 4).toString());
                selected.setStartTime(resultModel.getValueAt(selectedRow, 5).toString());
                selected.setEndTime(resultModel.getValueAt(selectedRow, 6).toString());
                selected.setMoney(Integer.parseInt(resultModel.getValueAt(selectedRow, 7).toString()));
                selected.setNumOfStudent(Integer.parseInt(resultModel.getValueAt(selectedRow, 8).toString()));

                ArrayList<Event> l = Main.myCon.searchEvent(selected);
                callShowEvents(l, selected.getName());
            }
        });
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Event event = new Event();
                event.setIdDept(2);
                event.setIdProg(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));

                callAddEvent(event, resultModel.getValueAt(selectedRow, 1).toString());
            }
        });
        super.setSize(900, 630);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private void callShowEvents(ArrayList<Event> l, String progName){
        new EvenListShow(l, progName, this, true);
    }
    private void callAddEvent(Event e, String progName){
        new AddEvent(this, true, e, progName);
    }
    private void callModify(Program p){

        new ModifyProgram(this, rootPaneCheckingEnabled, p);
    }
    private void createUIComponents() {
        String header[] = {"ID chương trình", "Tên chương trình", "Loại chương trình", "Tỉnh/bang/thành phố ", "Quốc gia", "Thời gian bắt đầu", "Thời gian kết thúc", "Số tiền được chi", "Số sinh viên"};
        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);

        resultModel.setColumnIdentifiers(header);
    }
//    public static void main(String[] args) {
//        new ProgGUI();
//    }
}
