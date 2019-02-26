package model;

public class User {
    private int id;
    private String phone;
    private String password;
    private String nickname;
    private int byear;
    private int bmonth;
    private int bday;
    private int followCount;
    private int fansCount;
    private int weiboCount;
    private String introduction;

    public User() {
    }

    public User(int id, String phone, String nickname, int byear, int bmonth, int bday, int followCount, int fansCount,int weiboCount, String introduction) {
        this.id = id;
        this.phone = phone;
        this.nickname = nickname;
        this.byear = byear;
        this.bmonth = bmonth;
        this.bday = bday;
        this.followCount = followCount;
        this.fansCount = fansCount;
        this.weiboCount = weiboCount;
        this.introduction = introduction;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getByear() {
        return byear;
    }

    public void setByear(int byear) {
        this.byear = byear;
    }

    public int getBmonth() {
        return bmonth;
    }

    public void setBmonth(int bmonth) {
        this.bmonth = bmonth;
    }

    public int getBday() {
        return bday;
    }

    public void setBday(int bday) {
        this.bday = bday;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getWeiboCount() { return weiboCount; }

    public void setWeiboCount(int weiboCount) { this.weiboCount = weiboCount; }

    public String getIntroduction() { return introduction; }

    public void setIntroduction(String introduction) { this.introduction = introduction; }
}