package com.ykyk.yinyangji.dao

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019-07-17
 */


@Entity
open class Gua : Serializable {
    @Column(name = "name")
    var name: String? = null

    @Column(name = "code")
    var code: String? = null

    @Id
    @GeneratedValue
    var id: Long = 0
        private set

    @Column(name = "myId")
    var myId: Long = 0

    @Column(name = "desct")
    var desc: String? = null

    @Column(name = "indext")
    var index: Int = 0

    @Column(name = "rowst")
    var rows: String? = null

    constructor(name: String, code: String, myId: Int, desc: String, index: Int,
                rows: String) {
        this.name = name
        this.code = code
        this.myId = myId.toLong()
        this.desc = desc
        this.index = index
        this.rows = rows
    }

    constructor() {}

    fun setId(id: Int) {
        this.id = id.toLong()
    }
}