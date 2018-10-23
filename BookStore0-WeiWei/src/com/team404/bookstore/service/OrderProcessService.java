package com.team404.bookstore.service;

import com.team404.bookstore.dao.AddressDao;
import com.team404.bookstore.dao.UserDao;
import com.team404.bookstore.entity.AddressEntity;
import com.team404.bookstore.entity.UserEntity;
import org.hibernate.internal.build.AllowSysOut;

public class OrderProcessService {
    private UserDao userDao;
    private AddressDao addressDao;

    /*Submit Function*/
    public boolean CreateAccount(UserEntity userEntity, AddressEntity addressEntity) {
        boolean flag = true;
        userDao = new UserDao();
        addressDao = new AddressDao();
        UserEntity userEntity1 = userDao.GetUserbyAccount(userEntity.getUsername());

        if(userEntity1 != null) {
            flag = false;
        }
        else {
            int id = userDao.AddUser(userEntity);
            addressEntity.setUserid(id);
            System.out.print(addressEntity.getUserid());
            addressDao.AddAddress(addressEntity);
        }
        return  flag;
    }

    /*Login Verification*/
    public boolean getAccount(UserEntity userEntity) {
        boolean flag = true;
        userDao = new UserDao();

        UserEntity userEntity1 = userDao.GetUserbyAccount(userEntity.getUsername());

        if(userEntity1 != null){
            if(userEntity.getPassword().equals(userEntity1.getPassword())) {
                flag = true;
            } else {
                flag = false;
            }
        } else {

            flag = false;
        }
        return flag;
    }

    /*Personal Info-UserAccount Info*/
    public UserEntity getUserinfo(int id) {
        userDao = new UserDao();

        UserEntity userEntity = userDao.GetUserById(id);

        return userEntity;
    }

    /*Personal Info-Address Info*/
    public AddressEntity getAddressinfo(int userid) {
        addressDao = new AddressDao();

        AddressEntity addressEntity = addressDao.getAddressByUid(userid);

        return addressEntity;
    }

}
