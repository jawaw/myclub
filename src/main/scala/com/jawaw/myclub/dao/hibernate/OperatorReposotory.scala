package com.jawaw.myclub.dao.hibernate

import com.jawaw.myclub.model.Operator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait OperatorReposotory extends JpaRepository[Operator, Int] {

}
