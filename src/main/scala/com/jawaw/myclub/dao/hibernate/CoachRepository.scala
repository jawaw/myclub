package com.jawaw.myclub.dao.hibernate

import com.jawaw.myclub.model.Coach
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
trait CoachRepository extends JpaRepository[Coach, Int] {

}
