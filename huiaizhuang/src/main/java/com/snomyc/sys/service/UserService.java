package com.snomyc.sys.service;

import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.User;
public interface UserService extends BaseService<User, String>{

    public User findUserById(String id);
}
