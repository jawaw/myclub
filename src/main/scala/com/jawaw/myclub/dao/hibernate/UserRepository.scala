package com.jawaw.myclub.dao.hibernate

import com.jawaw.myclub.model.User
import org.springframework.data.jpa.repository.JpaRepository

trait UserRepository extends JpaRepository[User, Int] {

}
