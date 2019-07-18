package com.ykyk.yinyangji.dao

import org.springframework.data.jpa.repository.JpaRepository

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019-07-17
 */
interface GuaRepository : JpaRepository<Gua, Long> {
    fun findByMyId(id: Long): Gua
}
