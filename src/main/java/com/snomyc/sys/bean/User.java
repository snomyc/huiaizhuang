package com.snomyc.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.snomyc.base.domain.BaseEntity;

@Entity
public class User extends BaseEntity{
    
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true,nullable = false,length=50)
    private String userName;
	
	@Column(nullable = false, unique = true,length=50)
    private String password;
	
    private int age;

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
}
