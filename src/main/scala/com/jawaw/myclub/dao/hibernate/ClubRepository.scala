package com.jawaw.myclub.dao.hibernate

import com.jawaw.myclub.model.Club
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait ClubRepository extends JpaRepository[Club, Int] {

}
