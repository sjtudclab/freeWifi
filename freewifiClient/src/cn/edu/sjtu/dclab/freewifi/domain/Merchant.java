package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
/**
 * 商家
 */

public class Merchant implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1490545911509368537L;

    private long id;

    private String loginname;

    private String password;

    private String name;

    private String address;

    private String tel;

    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Merchant() {
        super();
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}