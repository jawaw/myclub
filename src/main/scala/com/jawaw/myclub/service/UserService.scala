package com.jawaw.myclub.service

import com.jawaw.myclub.model.User

trait UserService {

  def list(): java.util.List[User]

  def searchUser(clubName: String): java.util.List[User]

  def findUserById(id: Int): User

  def createUser(club: User): Unit

  def updateUserById(club: User): Unit

  def deleteUserById(id: Int): Unit


}
