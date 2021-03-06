package com.snomyc.sys.service;

import java.util.List;
import java.util.Map;

import com.snomyc.api.user.request.UserRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.User;
public interface UserService extends BaseService<User, String>{

    public List<User> findByOrderByGroupNumAsc();
    
    public void createGroup(int num);
    
    public void deleteAll();
    
    public ResponseEntity groupLogin(UserRequest request);
    
    public List<User> findByCompanyIsNull();
    
    public Map<String,Object> activeStatus();
    
    public Map<String,Object> groupActiveStatus(int groupNum);
    
    public void updateGroup(int groupNum,String company);
}
