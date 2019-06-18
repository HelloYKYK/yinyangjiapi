package com.ykyk.yinyangji

import bean.BenBianGuaBean
import bean.G64IndexBean
import com.google.gson.Gson
import org.springframework.stereotype.Controller
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.Exception


/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019-06-18
 */

@Controller
class HelloWorldController {

    @RequestMapping("/", params = ["code"])
    @ResponseBody
    fun home(@RequestParam code: String): String {
        return code
    }
var qureyStr =""
    //字面个数,3个表示老阴,2个表示阳爻,1个表示阴爻,0个表示老阳
    @RequestMapping("/getCode", params = ["code"])
    @ResponseBody
    fun getCode(@RequestParam code: String): String {
        qureyStr=code
        return try {
            quaryGua(code)
        }catch (e : Exception){
            "请检查请求参数"
        }
    }

    fun quaryGua(code: String): String {

        val split = code.split(',')
        val intArray = IntArray(6)
        for (index in 0..5) {
            val toInt = split[index].toInt()
            intArray[index] = toInt
        }
        val guaDesc = BenBianGuaBean.GuaDesc(intArray)

        val id = guaDesc.id

//        val resource = ClassPathResource("/gua.txt")
        val file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/gua.txt")
        val readText = file.readText()
        val guaIndexBean = Gson().fromJson(readText, G64IndexBean::class.java)
        for ( gua in guaIndexBean.gList){
            if (gua._id == id){
                print("有相等"+id)
                return "("+qureyStr+"):"+gua.toString()
            }
        }
        print("无相等"+id)

        return guaIndexBean.toString()
    }

}

