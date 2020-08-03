package com.example.jdbcTemplate;

/**
 * @author zhoujun
 * @date 2020-07-31 09:35
 */
public class Emp {
    private Integer edi;

    private String ename;

    private Integer age;

    private String sex;

    public Integer getEdi() {
        return edi;
    }

    public void setEdi(Integer edi) {
        this.edi = edi;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "emp{" +
                "edi=" + edi +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
