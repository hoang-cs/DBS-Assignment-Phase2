import java.util.Date;
import java.util.*;
import java.sql.*;
public class Program {
    private Integer id;
    private String name, type, coordinate, nation;
    private String startTime, endTime;
    private Integer money;
    private Integer numOfStudent;

    public Program(){
        id = null;
        name = type = coordinate = nation = startTime = endTime = "";
        money = null;
    }

    public void setNumOfStudent(Integer n){
        numOfStudent = n;
    }

    public Integer getNumOfStudent(){
        return numOfStudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type += type;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
