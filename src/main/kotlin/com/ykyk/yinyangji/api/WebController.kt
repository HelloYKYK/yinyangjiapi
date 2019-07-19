package com.ykyk.yinyangji.api

import bean.BenBianGuaBean
import bean.DescGuaBean
import bean.Greeting
import com.ykyk.yinyangji.dao.Gua
import com.ykyk.yinyangji.dao.GuaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

/**
 * 供查询的api
 */
@Controller
open class WebController(
        @Autowired
        var guaRepository: GuaRepository
) {


    fun quaryGua(code: String): DescGuaBean {

        val intArray = code.toCharArray().map { char -> char.toString().toInt() }.toIntArray()

        val guaDesc = BenBianGuaBean.GuaDesc(intArray)
        val benBianGua = BenBianGuaBean()
        benBianGua.benGua = guaDesc
        benBianGua.bianGua = benBianGua.getBianGua(guaDesc)
        benBianGua.bianyaoCount = benBianGua.bianGua.bianYaoCount


        val id = guaDesc.id
        val longId: Long = id.toLong()
        val benGua = guaRepository.findByMyId(longId)


        val bianGua = guaRepository.findByMyId(benBianGua.bianGua.id.toLong())
        val duanGua = duanGua(benBianGua, benGua, bianGua, benBianGua.bianyaoCount)



        return duanGua
    }


    private fun duanGua(benBianGuaBean: BenBianGuaBean, benGua: Gua, bianGua: Gua, bianyaoCount: Int): DescGuaBean {
        val yaoInts = benBianGuaBean.benGua.yaoInt//本卦的0123 code值,1为阳,2为阴,3为老阳
        var mTvGTitle: String = ""
        var mtvGName: String = ""
        var mTvGDesc: String = ""
        when (bianyaoCount) {
            0//六爻安定的，以本卦卦辞断之。
            -> {
                mTvGTitle = "此卦当以本卦卦辞断之："
                mtvGName = "第" + (benGua.index + 1) + "卦： " + benGua.name
                mTvGDesc = "辞曰：" + benGua.desc + "\n ,卦爻:" + benGua.rows
            }
            1// 一爻动，以动爻之爻辞断之。
            -> {
                mTvGTitle = "此卦当以本卦变爻爻辞断之："
                mtvGName = "第" + (benGua.index + 1) + "卦  " + benGua.name
                val yaoTag = benBianGuaBean.benGua.yaoTag
                var yaoStr = ""
                var s = ""//九，六，阴阳
                when (yaoTag) {
                    1//初爻动
                    -> {
                        s = if (benBianGuaBean.benGua.yaos[0].yinYang == 1) "初九" else "初六"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(0) ?: "")
                    }
                    2 -> {
                        s = if (benBianGuaBean.benGua.yaos[1].yinYang == 1) "九二" else "六二"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(1) ?: "")
                    }
                    4 -> {
                        s = if (benBianGuaBean.benGua.yaos[2].yinYang == 1) "九三" else "六三"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(2) ?: "")
                    }
                    8 -> {
                        s = if (benBianGuaBean.benGua.yaos[3].yinYang == 1) "九四" else "六四"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(3) ?: "")
                    }
                    16 -> {
                        s = if (benBianGuaBean.benGua.yaos[4].yinYang == 1) "九五" else "六五"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(4) ?: "")
                    }
                    32 -> {
                        s = if (benBianGuaBean.benGua.yaos[5].yinYang == 1) "上九" else "上六"
                        yaoStr = s + "：" + (asArra(benGua.rows)?.get(5) ?: "")
                    }
                }
                mTvGDesc = yaoStr
            }
            2 -> {
                val descYang = StringBuilder()//阳爻
                val descYin = StringBuilder()//阴爻
                val yao22 = ArrayList<String>()

                for (i in yaoInts.indices) {
                    if (yaoInts[i] == 0 || yaoInts[i] == 3) {
                        asArra(benGua.rows)?.get(i)?.let { yao22.add(it) }
                    }

                }
                mTvGTitle = "两爻动，则取阴爻指爻辞以为断,上爻为主"
                // 两爻动，则取阴爻指爻辞以为断，盖以“阳主过去，阴主未来”故也。
                // 如天风姤卦，初六，九五两爻皆动，则以初六爻断之，
                // 九五爻为辅助之断，“阳主过去，阴主未来”，其中大有学问。

                mtvGName = "第" + (benGua.index + 1) + "卦： " + benGua.name
                mTvGDesc = "上爻:" + yao22[1] + ",下爻:" + yao22[0]
            }
            3 -> {
                mTvGTitle = "三爻动者，,本卦卦辞为主,变卦卦辞为辅断之。"
                mtvGName = "第" + (benGua.index + 1) + "卦： " + benGua.name

                mTvGDesc = "本卦卦辞:" + benGua.desc + "\n ,卦爻:" + benGua.rows + "\n变卦卦辞:" + bianGua.desc + "\n ,卦爻:" + bianGua.rows
            }
            4 -> {
                mTvGTitle = " 四爻动者，以下静之爻辞断之。"
                mtvGName = "第" + (benGua.index + 1) + "卦： " + benGua.name
                var shangYao = ""
                var xiaYao = ""
                val yao2 = ArrayList<String>()

                for (i in yaoInts.indices) {
                    if (yaoInts[i] != 0 && yaoInts[i] != 3) {
                        asArra(benGua.rows)?.get(i)?.let { yao2.add(it) }
                    }
                }
                xiaYao = yao2[1]
                shangYao = yao2[0]
                mTvGDesc = "上爻:$shangYao\n 下爻:$xiaYao"
            }
            5 -> {
                mTvGTitle = "五爻多动者，取变卦静爻的爻辞断之。"
                mtvGName = "第" + (benGua.index + 1) + "卦： " + benGua.name
                var jingYao = ""
                for (i in yaoInts.indices) {
                    if (yaoInts[i] != 0 && yaoInts[i] != 3) {
                        jingYao = asArra(bianGua.rows)?.get(i) ?: ""
                    }
                }
                mTvGDesc = "变卦:第" + (bianGua.index + 1) + "卦:" + bianGua.name + "静爻:" + jingYao
            }
            6 ->
                //                六爻皆动的卦，如果是乾坤二卦，以“用九”、“用六”之辞断之。如乾卦六爻皆动，则为群龙无首，吉。
                //
                //                除乾坤两卦外其余各卦，若是六爻皆动，则以变卦的彖辞断之。
                if (benGua.index == 0 || benGua.index == 1) {//乾坤二卦
                    if (benBianGuaBean.benGua.yaos[6].yinYang == 0) {
                        mTvGTitle = "坤卦六爻皆动,当以用六断之"
                    } else {
                        mTvGTitle = "乾卦六爻皆动,当以用九断之"
                    }
                } else {
                    mTvGTitle = "六爻皆动,当以变卦卦辞断之"
                    mtvGName = "第" + (bianGua.index + 1) + "卦： " + bianGua.name
                    mTvGDesc = "卦辞:" + bianGua.desc + "\n ,卦爻:" + bianGua.rows
                }
        }
        return DescGuaBean(mTvGTitle, mtvGName, mTvGDesc)
    }


    /**
     *
     * @param rows
     * @return
     */
    private fun asArra(rows: String?): Array<String>? {
        return rows?.replace("[", "")?.replace("]", "")
                ?.split(",".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
    }


    @RequestMapping("/suangua")
    fun greetingForm(model: Model): String {
        model.addAttribute("greeting", Greeting())
        return "greeting"
    }

    @PostMapping("/greeting")
    fun greetingSubmit(@ModelAttribute greeting: Greeting): String {
        val s = "${greeting.id1}${greeting.id2}${greeting.id3}${greeting.id4}${greeting.id5}${greeting.id6}"
        val quaryGua = quaryGua(s)
        greeting.descGuaBean = quaryGua
        return "result"
    }
}