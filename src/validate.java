import javax.swing.*;
public class validate{
    public JPanel mainPanel;
    String num[]=new String [10];
    String al[]=new String[52];
    public validate(){
        for (Integer i = 0; i < 10; i++) {
            num[i]= (i.toString());
        }
        Integer index=0;
        for(Character i='a'; i!='z'+1; i++, index++){
            al[index]=i.toString();
        }
        for(Character i='A'; i!='Z'+1; i++, index++){
            al[index]=i.toString();
        }
    }
    public boolean checkName(String name){
        if (name==null) return true;
        for(Integer j=0; j<10;j++){
                if(name.contains(num[j])){
                    return false;
                }
        }
        return true;
    }

    public boolean checkNum(String Phone){
        if(Phone==null)return true;
        for(Integer j=0; j<52;j++){
            if(Phone.contains(al[j])){
                return false;
            }
        }
        return true;
    }

    public boolean val(Program s) {
        if (checkName(s.getName())) {
            if (checkName(s.getCoordinate())) {
                if (checkName(s.getNation())) {
                    if (checkNum(s.getStartTime())) {
                        if (checkNum(s.getEndTime())) return true;
                        else{
                            JOptionPane.showMessageDialog(mainPanel, "Ngày sai định dạng");
                            return false;}
                    }
                    JOptionPane.showMessageDialog(mainPanel, "Ngày sai định dạng");
                    return false;
                }
                JOptionPane.showMessageDialog(mainPanel, "Tên quốc gia sai");
                return false;
            }
            return false;
        }
        JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập lại tên chương trình");
        return false;
    }

    public boolean val(Sponsor s){

        if(s.person!=null){
            if(checkName(s.person.getFullName())){
                    if(checkName(s.person.getProvince()))
                        return true;
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập lại tên tỉnh");
                        return false;

            }JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập lại tên cá nhân");
            return false;
        }
        else if(s.company!=null){
            if( checkName(s.company.getRepName())){
                if(checkNum(s.company.getRepPhoneNum()))return true;
                JOptionPane.showMessageDialog(mainPanel, "Sai SĐT");
                return false;
            }
            JOptionPane.showMessageDialog(mainPanel, "Tên người đại diện lỗi");
            return false;
        }
    return true;
    }

    public boolean val(Student s){
        if(checkName(s.getFullName())){
            if(checkName(s.getUni())){
                if(checkName(s.getMajor())){
                    if(checkNum(s.getPhoneNum()))return true;
                    JOptionPane.showMessageDialog(mainPanel, "Sai SĐT");
                    return false;
                }
                JOptionPane.showMessageDialog(mainPanel, "Chuyên ngành lỗi");
                return false;
            }JOptionPane.showMessageDialog(mainPanel, "Sai tên trường");
            return false;
        }JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập lại tên");
        return false;
    }
    public boolean val(Member s){
        if(checkName(s.getFullName())){
            if(checkNum(s.getDob())){
                if(checkName(s.getRole())){
                    if(checkNum(s.getPhoneNum()))return true;
                    JOptionPane.showMessageDialog(mainPanel, "Sai SĐT");
                    return false;
                }
                JOptionPane.showMessageDialog(mainPanel, "Vai trò không đúng");
                return false;
            }JOptionPane.showMessageDialog(mainPanel, "Ngày sinh chứa kí tự chữ");
            return false;
        }JOptionPane.showMessageDialog(mainPanel, "Tên không được chứa số");
        return false;
    }
    public boolean val(Event e){
        if(checkName(e.getDate()))return true;
        JOptionPane.showMessageDialog(mainPanel, "Tên chứa kí tự số");
        return false;
    }
}