package com.jawaw.myclub.service.impl

import java.util

import com.jawaw.myclub.dao.hibernate.UserRepository
import com.jawaw.myclub.dao.mybatis.UserMapper
import com.jawaw.myclub.model.User
import com.jawaw.myclub.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired()(val userMapper: UserMapper,
                                   val userRepository: UserRepository) extends UserService{

  override def list(): util.List[User] = {
    userRepository.findAll()
  }

  override def searchUser(name: String): util.List[User] = {
    userMapper.searchUserByName(name)
  }

  override def findUserById(id: Int): User = {
    userRepository.findById(id).get()
  }

  override def createUser(user: User): Unit = {
    userRepository.save(user)
  }

  override def updateUserById(user: User): Unit = {
    userRepository.save(user)
  }

  override def deleteUserById(id: Int): Unit = {
    userRepository.deleteById(id)
  }

}
