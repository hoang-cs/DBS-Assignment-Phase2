import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SponsorResult extends JFrame{
    private JPanel mainPanel;
    private JTable resultTable;
    private JButton modifyButton;
    private JButton exitButton;
    private JScrollPane scroll;
    private JButton moreMoneyButton;
    private JButton deleteButton;
    private DefaultTableModel resultModel;
    boolean type;   // true = person
                    // false = company
    String ord="";
    String header[];
    private Account account;
    public SponsorResult(Sponsor s,boolean t, boolean ordN, boolean ordM, Account a){
        super();
        super.add(mainPanel);
        super.setSize(700, 600);
        modifyButton.setEnabled(false);
        moreMoneyButton.setEnabled(false);
        deleteButton.setEnabled(false);
        account = a;
        System.out.println(a.getDept());
        System.out.println(a.getRole());
        System.out.println(a.getUserName());
        super.setLocationRelativeTo(null);
        setVisible(true);
        type = t;
        resultModel.getDataVector().removeAllElements();
        resultModel.fireTableDataChanged();

        if(ordN||ordM){
            ord=" ORDER BY";
            if(ordN){
                if(type)ord+=" [Họ và tên]";
                else ord+=" [Tên công ty]";
                if(ordM) ord+=", [Số tiền quyên góp]";
            }
            else if(ordM) ord+=" [Số tiền quyên góp]";
        }
        if (type) {
            scroll.setBorder(BorderFactory.createTitledBorder("Cá nhân tài trợ"));
            header=new String[]{"ID", "Tên", "Ngày sinh", "Số tiền đã quyên góp"};
            ArrayList<Sponsor> resultList =Main.myCon.searchPerson(s, ord);
            resultModel.setColumnIdentifiers(header);
            for (Sponsor p : resultList){
                resultModel.addRow(new Object[]{p.getIdSponsor().toString(), p.person.getFullName(), p.person.getDob(),p.getMoney().toString()});
            }
        }
        else {
            scroll.setBorder(BorderFactory.createTitledBorder("Công ty/tập thể tài trợ"));
            header=new String[]{"ID", "Tên công ty", "Tên người đại diện", "Số tiền đã quyên góp", "SĐT Người đại diện"};
            ArrayList<Sponsor> resultList = Main.myCon.searchCom(s, ord);
            resultModel.setColumnIdentifiers(header);
            for (Sponsor p : resultList){
                resultModel.addRow(new Object[]{p.getIdSponsor(), p.company.getCompName(), p.company.getRepName(), p.getMoney(), p.company.getRepPhoneNum()});
            }
        }
        exitButton.addActionListener(e -> {
            super.dispose();
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (resultModel.getRowCount() == 0 || selectedRow == -1){
                    JOptionPane.showMessageDialog(mainPanel, "Bạn cần chọn 1 hàng trong bảng để chỉnh sửa !");
                    return;
                }

                //MOD SAU
                if(type){
                    Sponsor temp = new Sponsor();
                    temp.person = new Sponsor.Person();
                    temp.setIdSponsor(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                    temp.person.setFullName(resultModel.getValueAt(selectedRow, 1).toString());
                    ArrayList<Sponsor> tempList = Main.myCon.searchPerson(temp, ord);
                    temp = tempList.get(0);
                    callModify(temp);
                    resultModel.setValueAt(temp.person.getFullName(), selectedRow, 1);
                    resultModel.setValueAt(temp.person.getDob(), selectedRow, 2);
                }
                else {
                    Sponsor temp = new Sponsor();
                    temp.company = new Sponsor.Company();
                    temp.setIdSponsor(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                    temp.company.setCompName(resultModel.getValueAt(selectedRow, 1).toString());
                    ArrayList<Sponsor> tempList = Main.myCon.searchCom(temp, ord);
                    temp = tempList.get(0);
                    callModifyCom(temp);
                    resultModel.setValueAt(temp.company.getCompName(), selectedRow, 1);
                    resultModel.setValueAt(temp.company.getRepName(), selectedRow, 2);
                    resultModel.setValueAt(temp.company.getRepPhoneNum(), selectedRow, 4);
                }
            }
        });
        moreMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Sponsor temp = new Sponsor();
                Integer add=addMoreMoney();
                Integer money = Integer.parseInt(resultModel.getValueAt(selectedRow, 3).toString())+add;
                s.setIdSponsor(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                s.setMoney(money);
                resultModel.setValueAt(money, selectedRow, 3);
                // call UPDATE Query
               if(Main.myCon.moreMoney(s,add))
                   JOptionPane.showMessageDialog(mainPanel, "Thành công");
               else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
            }
        });
        resultTable.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 if (account.getRole() == Account.DEPT_MANAGER &&
                         (account.getDept() == Account.MANAGEMENT_DEPT || account.getDept() == Account.FINANCE_DEPT)) {
                     moreMoneyButton.setEnabled(true);
                     modifyButton.setEnabled(true);
                     deleteButton.setEnabled(true);
                 }
             }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                Sponsor temp = new Sponsor();
                s.setIdSponsor(Integer.parseInt(resultModel.getValueAt(selectedRow, 0).toString()));
                if(Main.myCon.deleteSponsor(s))
                    JOptionPane.showMessageDialog(mainPanel, "Thành công");
                else JOptionPane.showMessageDialog(mainPanel, "Thất bại");
                resultModel.removeRow(selectedRow);
            }
        });
    }

    private void callModify(Sponsor t){
        new ModifyPerson(this, rootPaneCheckingEnabled, t);
    }
    private void callModifyCom(Sponsor s){
        new ModifyCompany(this, rootPaneCheckingEnabled, s);
    }

    private int addMoreMoney(){
        return Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số tiền tài trợ cho lần này"));
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        resultModel = new DefaultTableModel();
        resultTable = new JTable(resultModel);
        resultModel.setColumnIdentifiers(header);
    }
}
