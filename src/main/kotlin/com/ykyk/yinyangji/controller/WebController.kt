package com.ykyk.yinyangji.controller

import bean.Greeting
import com.ykyk.yinyangji.dao.GuaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 供查询的api
 */
@Controller
open class WebController(
        @Autowired
        var guaRepository: GuaRepository
) {


    @RequestMapping("/suangua")
    fun greetingForm(model: Model): String {
        model.addAttribute("greeting", Greeting())
        return "greeting"
    }

    @PostMapping("/greeting")
    fun greetingSubmit(@ModelAttribute greeting: Greeting): String {
        val s = "${greeting.id1}${greeting.id2}${greeting.id3}${greeting.id4}${greeting.id5}${greeting.id6}"
        val quaryGua = ChaGuaUtil(guaRepository).quaryGua(s)
        greeting.descGuaBean = quaryGua
        return "result"
    }
}