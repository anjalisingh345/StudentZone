package com.example.astudentzone;

public class UserHelperclass  {

    String  clientMono,valiMono,pgname,city,area,ownername,prfdlanguage;

    public UserHelperclass() {

    }

    public UserHelperclass(String clientMono, String valiMono, String pgname, String city, String area, String ownername, String prfdlanguage) {
        this.clientMono = clientMono;
        this.valiMono = valiMono;
        this.pgname = pgname;
        this.city = city;
        this.area = area;
        this.ownername = ownername;
        this.prfdlanguage = prfdlanguage;
    }

    public String getClientMono() {
        return clientMono;
    }

    public void setClientMono(String clientMono) {
        this.clientMono = clientMono;
    }

    public String getValiMono() {
        return valiMono;
    }

    public void setValiMono(String valiMono) {
        this.valiMono = valiMono;
    }

    public String getPgname() {
        return pgname;
    }

    public void setPgname(String pgname) {
        this.pgname = pgname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPrfdlanguage() {
        return prfdlanguage;
    }

    public void setPrfdlanguage(String prfdlanguage) {
        this.prfdlanguage = prfdlanguage;
    }
}

