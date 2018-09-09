package com.snomyc.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
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
}


