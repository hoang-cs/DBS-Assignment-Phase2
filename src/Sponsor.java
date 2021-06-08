public class Sponsor {
    private Integer idSponsor, idDept, money;
    public Person person;
    public Company company;

    public Sponsor(){
        person = null;
        company = null;
        idSponsor = idDept = money = null;
    }

    public Integer getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(Integer idSponsor) {
        this.idSponsor = idSponsor;
    }

    public Integer getIdDept() {
        return idDept;
    }

    public void setIdDept(Integer idDept) {
        this.idDept = idDept;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    public void setCompany(Company company) {
        this.company = company;
    }

    static class Person{
        private Integer CMND;
        private String fullName, village, district, province, dob;


        public Integer getCMND() {
            return CMND;
        }

        public void setCMND(Integer CMND) {
            this.CMND = CMND;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }
    }
    static class Company{
        private Integer MS;
        private String compName, repName, repPhoneNum;

        public Integer getMS() {
            return MS;
        }

        public void setMS(Integer MS) {
            this.MS = MS;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getRepName() {
            return repName;
        }

        public void setRepName(String repName) {
            this.repName = repName;
        }

        public String getRepPhoneNum() {
            return repPhoneNum;
        }

        public void setRepPhoneNum(String repPhoneNum) {
            this.repPhoneNum = repPhoneNum;
        }
    }
}
