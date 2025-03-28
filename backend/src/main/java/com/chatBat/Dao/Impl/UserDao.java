package com.chatBat.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chatBat.Dao.IUserDao;
import com.chatBat.Dao.base.BaseDao;
import com.chatBat.Dao.entity.User;


@Repository
public class UserDao extends BaseDao implements IUserDao{
	
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		
		List<Object> param = new ArrayList<Object>();
		return nativeQuery("SELECT * FROM users", User.class, param);
	}

	@Override
    public void save(User user) {
        if (user.getId() == null) {
            persist(user);
        } else {
            merge(user);
        }
    }

}
