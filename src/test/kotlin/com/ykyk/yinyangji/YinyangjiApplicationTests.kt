package com.ykyk.yinyangji

import com.ykyk.yinyangji.dao.Gua
import com.ykyk.yinyangji.dao.GuaRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class YinyangjiApplicationTests {

//	@Test
//	fun contextLoads() {
//		val code = "122332"
//		val toCharArray = code.toCharArray()
//		val intArray = toCharArray.map { char -> char.toInt() }.toIntArray()
//
//		print(intArray)
//	}
	@Autowired
	val guaRepository: GuaRepository? = null
	fun test (){
		guaRepository?.save(Gua("name","code",1,",desc",1,""))
	}

}
