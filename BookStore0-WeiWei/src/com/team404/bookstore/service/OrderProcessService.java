package com.team404.bookstore.service;

import com.team404.bookstore.dao.AddressDao;
import com.team404.bookstore.dao.ShoppingCartDao;
import com.team404.bookstore.dao.UserDao;
import com.team404.bookstore.entity.AddressEntity;
import com.team404.bookstore.entity.ShoppingCartEntity;
import com.team404.bookstore.entity.UserEntity;
import org.hibernate.internal.build.AllowSysOut;

import java.util.List;

public class OrderProcessService {
    private UserDao userDao;
    private AddressDao addressDao;
    private ShoppingCartDao shoppingCartDao;

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

    public UserEntity GetUserByAccount(String username) {
        userDao = new UserDao();

        UserEntity userEntity = userDao.GetUserbyAccount(username);

        return  userEntity;
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

    /*Customer add item(s) to their own shopping cart*/
    public boolean AddItemtoCart(ShoppingCartEntity shoppingCartEntity) {

        shoppingCartDao = new ShoppingCartDao();

        return shoppingCartDao.AddShoppingCart(shoppingCartEntity);
    }

    /*Display customers' shopping cart contents*/
    public List<ShoppingCartEntity> DisplayShoppingCart(int userid) {
        shoppingCartDao = new ShoppingCartDao();

        return shoppingCartDao.GetShoppingCartByUid(userid);
    }

    /*Delete single item in shopping cart*/
    public boolean DeleteSingleItem(int id) {
        shoppingCartDao = new ShoppingCartDao();

        return shoppingCartDao.DeleteShoppingItemById(id);
    }


}
