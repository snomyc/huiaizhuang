package com.snomyc.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.snomyc.api.user.request.UserRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.dao.UserDao;
import com.snomyc.sys.service.UserService;
import com.snomyc.util.CodecUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService{

    @Autowired
    private UserDao userDao;

	@Override
	public PagingAndSortingRepository<User, String> getDao() {
		return userDao;
	}

	@Override
	public List<User> findByOrderByGroupNumAsc() {
		return userDao.findByOrderByGroupNumAsc();
	}

	@Override
	public void createGroup(int num) {
		//根据传入的小组数量创建小组信息
		for (int i = 1; i <= num; i++) {
			User user = new User();
			user.setGroupNum(i);
			//设置随机6位数密码
			user.setPassword(CodecUtil.createRandom(6));
			userDao.save(user);
		}
	}

	@Override
	public void deleteAll() {
		List<User> userList = userDao.findAll();
		userDao.delete(userList);
	}

	@Override
	public ResponseEntity groupLogin(UserRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		if(StringUtils.isBlank(request.getCompany()) || StringUtils.isBlank(request.getPassword())) {
			responseEntity.failure("请填写公司名称或进入密码!");
		}
		User user = userDao.findByGroupNumAndPassword(request.getGroupNum(),request.getPassword());
		if(user == null) {
			responseEntity.failure("随机密码输入错误!");
		}
		user.setCompany(request.getCompany());
		userDao.save(user);
		responseEntity.success();
		return responseEntity;
	}
}


