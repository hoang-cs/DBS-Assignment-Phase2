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
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng điền lại nội dung");
                    return false;
                }
        }
        return true;
    }

    public boolean checkNum(String Phone){
        if(Phone==null)return true;
        for(Integer j=0; j<52;j++){
            if(Phone.contains(al[j])){
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng điền lại số");
                return false;
            }
        }
        return true;
    }

    public boolean val(Program s){
        return(
        checkName(s.getName())&&
        checkName(s.getCoordinate())&&
        checkName(s.getNation())&&
        checkNum(s.getStartTime())&&
        checkNum(s.getEndTime()));
    }

    public boolean val(Sponsor s){

                if(s.person!=null)return (
        checkName(s.person.getFullName())&&
        checkName(s.person.getDistrict())&&
        checkName(s.person.getProvince()));
                else if(s.company!=null)return(
        checkName(s.company.getCompName())&&
        checkName(s.company.getRepName())&&
        checkNum(s.company.getRepPhoneNum()));
                return true;
    }

    public boolean val(Student s){
        return (checkName(s.getFullName())&&
        checkName(s.getUni())&&
        checkName(s.getMajor())&&
        checkNum(s.getPhoneNum()));
    }
    public boolean val(Member s){
        return (checkName(s.getFullName())&&
        checkNum(s.getDob())&&
        checkName(s.getRole())&&
        checkNum(s.getPhoneNum()));
    }
    public boolean val(Event e){
        return checkName(e.getDate());
    }
}