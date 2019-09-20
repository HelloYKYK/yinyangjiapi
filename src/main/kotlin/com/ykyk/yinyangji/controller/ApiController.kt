package com.ykyk.yinyangji.controller

import bean.DescGuaBean
import bean.G64IndexBean
import com.google.gson.Gson
import com.ykyk.yinyangji.dao.Gua
import com.ykyk.yinyangji.dao.GuaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * 供查询的api
 */
@RestController
open class ApiController(
        @Autowired
        var guaRepository: GuaRepository
) {

    init {

        val readText = "{\"gList\":[{\"_id\":0,\"code\":\"00\",\"desc\":\"元亨利贞\",\"index\":0,\"name\":\"乾\",\"rows\":\"[潜龙 勿用, 见龙在田 利见大人, 君子终日乾乾 夕惕若 厉无咎, 或跃在渊 无咎, 飞龙在天 利见大人, 亢龙 有悔]\"},{\"_id\":1,\"code\":\"31\",\"desc\":\"不利有攸往\",\"index\":22,\"name\":\"剥\",\"rows\":\"[剥床以足 蔑贞凶, 剥床以辨 蔑贞凶, 剥之 无咎, 剥床以肤 凶, 贯鱼以宫人宠 无不利, 硕果不食 君子得舆 小人剥庐]\"},{\"_id\":2,\"code\":\"71\",\"desc\":\"吉 原筮 元 永贞 无咎 不宁方来 后夫凶\",\"index\":7,\"name\":\"比\",\"rows\":\"[有孚比之 无咎 有孚盈缶 终来有它 吉, 比之自内 贞吉, 比之匪人, 外比之 贞吉, 显比 王用三驱 失前禽 邑人不诫 吉, 比之无首 凶]\"},{\"_id\":3,\"code\":\"41\",\"desc\":\"盥而不荐 有孚顒若\",\"index\":19,\"name\":\"观\",\"rows\":\"[童观 小人无咎 君子咎, 窥观 利女贞, 观我生 进退, 观国之光 利用宾于王, 观我生 君子无咎, 观其生 君子无咎]\"},{\"_id\":4,\"code\":\"51\",\"desc\":\"利建侯行师\",\"index\":15,\"name\":\"豫\",\"rows\":\"[鸣豫 凶, 介于石 不终日 贞吉, 盱豫 悔 迟有悔, 由豫 大有得 勿疑 朋盍簪, 贞疾 恒不死, 冥豫 成有渝 无咎]\"},{\"_id\":5,\"code\":\"61\",\"desc\":\"康侯用锡马蕃庶 昼日三接\",\"index\":34,\"name\":\"晋\",\"rows\":\"[晋如摧如 贞吉 罔孚 裕无咎, 晋如 愁如 贞吉 受兹介福于 其王母, 众允 悔亡, 晋如鼫鼠 贞厉, 悔亡 失得 勿恤 往吉 无不利, 晋其角 维用伐邑 厉吉 无咎 贞吝]\"},{\"_id\":6,\"code\":\"21\",\"desc\":\"\",\"index\":44,\"name\":\"萃\",\"rows\":\"[初六：有孚不终，乃乱乃萃，若号一握为笑，勿恤，往无咎。, 六二：引吉，无咎，孚乃利用□①。, 六三：萃如，嗟如，无攸利，往无咎，小吝。, 九四：大吉，无咎。, 九五：萃有位，无咎。, null]\"},{\"_id\":7,\"code\":\"01\",\"desc\":\"否之匪人 不利君子贞 大往小来\",\"index\":11,\"name\":\"否\",\"rows\":\"[拔茅茹以其汇 贞吉 亨, 包承 小人吉 大人否 亨, 包羞, 有命 无咎 畴离祉, 休否 大人吉 其亡其亡 系于包桑, 倾否 先否后喜]\"},{\"_id\":8,\"code\":\"13\",\"desc\":\"亨 君子有终\",\"index\":14,\"name\":\"谦\",\"rows\":\"[谦谦君子 用涉大川 吉, 鸣谦 贞吉, 劳谦君子 有终 吉, 无不利 捴谦, 不富以其邻 利用侵伐 无不利, 鸣谦 利用行师征邑国]\"},{\"_id\":9,\"code\":\"33\",\"desc\":\"\",\"index\":51,\"name\":\"艮\",\"rows\":\"[初六：艮其趾，无咎，利永贞。, 六二：艮其腓，不拯其随，其心不快。, 九三：艮其限，列其夤，厉薰心。, 六四：艮其身，无咎。, 六五：艮其辅，言有序，悔亡。, 上九：敦艮，吉。]\"},{\"_id\":10,\"code\":\"73\",\"desc\":\"\",\"index\":38,\"name\":\"蹇\",\"rows\":\"[初六：往蹇，来誉。, 六二：王臣蹇蹇，匪躬之故。, 九三：往蹇来反。, 六四：往蹇来连。, 九五：大蹇朋来。, 上六：往蹇来硕，吉；利见大人。]\"},{\"_id\":11,\"code\":\"43\",\"desc\":\"\",\"index\":52,\"name\":\"渐\",\"rows\":\"[初六：鸿渐于干，小子厉，有言，无咎。, 六二：鸿渐于磐，饮食□□，吉。, 九三：鸿渐于陆，夫征不复，妇孕不育，凶；利御寇。, null, null, null]\"},{\"_id\":12,\"code\":\"53\",\"desc\":\"\",\"index\":61,\"name\":\"小过\",\"rows\":\"[初六：飞鸟以凶。, 六二：过其祖，遇其妣；不及其君，遇其臣；无咎。, 九三：弗过防之，从或戕之，凶。, 九四：无咎，弗过遇之。 往厉必戒，勿用永贞。, 六五：密云不雨，自我西郊，公弋取彼在穴。, 上六：弗遇过之，飞鸟离之，凶，是谓灾眚。]\"},{\"_id\":13,\"code\":\"63\",\"desc\":\"\",\"index\":55,\"name\":\"旅\",\"rows\":\"[初六：旅琐琐，斯其所取灾。, 六二：旅即次，怀其资，得童仆贞。, 九三：旅焚其次，丧其童仆，贞厉。, null, null, null]\"},{\"_id\":14,\"code\":\"23\",\"desc\":\"亨 利贞 取女吉\",\"index\":30,\"name\":\"咸\",\"rows\":\"[咸其拇, 咸其腓 凶 居吉, 咸其股 执其随 往吝, 贞吉 悔亡 憧憧往来 朋從尔思, 咸其脢 无悔, 限期附加]\"},{\"_id\":15,\"code\":\"03\",\"desc\":\"亨 小利贞\",\"index\":32,\"name\":\"遁\",\"rows\":\"[遁尾 厉 勿用有攸往, 执之用黄牛之革 莫之胜说, 系遁 有疾厉 畜臣妾吉, 好遁 君子吉 小人否, 嘉遁 贞吉, 肥遁 无不利]\"},{\"_id\":16,\"code\":\"17\",\"desc\":\"贞丈人吉 无咎\",\"index\":6,\"name\":\"师\",\"rows\":\"[师出以律 否臧凶, 在师中吉 无咎 天三锡命, 师或舆尸 凶, 师左次 无咎, 田有禽 利执言 无咎 长子帅师 弟子舆尸 贞凶, 大君有命 开国承家 小人勿用]\"},{\"_id\":17,\"code\":\"37\",\"desc\":\"亨 匪我求童蒙 童蒙求我 初筮告 再三渎 渎则不告 利贞\",\"index\":3,\"name\":\"蒙\",\"rows\":\"[发蒙 利用刑人 用说桎梏 以往吝, 包蒙 吉 纳妇 吉 子克家, 勿用取女 见金夫 不有躬 无攸利, 困蒙 吝, 童蒙 吉, 击蒙 不利为寇 利御寇]\"},{\"_id\":18,\"code\":\"47\",\"desc\":\"\",\"index\":58,\"name\":\"涣\",\"rows\":\"[初六：用拯马壮，吉。, 九二：涣奔其机，悔亡。, 六三：涣其躬，无悔。, 六四：涣其群，元吉。, 九五：涣汗其大号，涣王居，无咎。, 上九：涣其血，去逖出，无咎。]\"},{\"_id\":20,\"code\":\"57\",\"desc\":\"\",\"index\":39,\"name\":\"解\",\"rows\":\"[初六：无咎。, 九二：田获三狐，得黄矢，贞吉。, 六三：负且乘，致寇至，贞吝。, 九四：解而拇，朋至斯孚。, 六五：君子维有解，吉；有孚于小人。, 上六：公用射隼，于高墉之上，获之，无不利。]\"},{\"_id\":21,\"code\":\"67\",\"desc\":\"亨，小狐汔济，濡其尾，无攸利。\",\"index\":63,\"name\":\"未济\",\"rows\":\"[初六：濡其尾，吝。, 九二：曳其轮，贞吉。, 六三：未济，征凶，利涉大川。, 九四：贞吉，悔亡，震用伐鬼方，三年有赏于大国。, 六五：贞吉，无悔，君子之光，有孚，吉。, 上九：有孚于饮酒，无咎，濡其首，有孚失是。]\"},{\"_id\":22,\"code\":\"27\",\"desc\":\"\",\"index\":46,\"name\":\"困\",\"rows\":\"[初六：臀困于株木，入于幽谷，三岁不见。, 九二：困于酒食，朱绂方来，利用亨祀，征凶，无咎。, 六三：困于石，据于蒺藜，入于其宫，不见其妻，凶。, null, null, null]\"},{\"_id\":23,\"code\":\"07\",\"desc\":\"有孚窒惕 中吉 终凶 利见大人 不利涉大川\",\"index\":5,\"name\":\"讼\",\"rows\":\"[不永所事 小有言 终吉, 不克讼 归而逋 其邑人三百户 无眚, 食旧德 贞厉 终吉 或從王事 无成, 不克讼 复既命渝 安贞吉, 讼 元吉, 或锡之鞶带 终朝三褫之]\"},{\"_id\":24,\"code\":\"14\",\"desc\":\"\",\"index\":45,\"name\":\"升\",\"rows\":\"[初六：允升，大吉。, 九二：孚乃利用□，无咎。, 九三：升虚邑。, 六四：王用亨于岐山，吉无咎。, 六五：贞吉，升阶。, 上六：冥升，利于不息之贞。]\"},{\"_id\":25,\"code\":\"34\",\"desc\":\"元亨 利涉大川 先甲三日 后甲三日\",\"index\":17,\"name\":\"蛊\",\"rows\":\"[干父之蛊 有子 考无咎 厉 终吉, 干母之蛊 不可贞, 干父之蛊 小有悔 无大咎, 裕父之蛊 往见咎, 干父之蛊 用誉, 不事王侯 高尚其事]\"},{\"_id\":26,\"code\":\"74\",\"desc\":\"\",\"index\":47,\"name\":\"井\",\"rows\":\"[初六：井泥不食，旧井无禽。, null, null, null, null, null]\"},{\"_id\":27,\"code\":\"44\",\"desc\":\"\",\"index\":56,\"name\":\"巽\",\"rows\":\"[初六：进退，利武人之贞。, null, null, null, null, null]\"},{\"_id\":28,\"code\":\"54\",\"desc\":\"亨 无咎 利贞 利有攸往\",\"index\":31,\"name\":\"恒\",\"rows\":\"[浚恒 贞凶 无攸利, 悔亡, 不恒其德 或承之羞 贞吝, 田无禽, 恒其德 贞 妇人吉 夫子凶, 振恒 凶]\"},{\"_id\":29,\"code\":\"64\",\"desc\":\"\",\"index\":49,\"name\":\"鼎\",\"rows\":\"[初六：鼎颠趾，利出否，得妾以其子，无咎。, null, null, null, null, null]\"},{\"_id\":30,\"code\":\"24\",\"desc\":\"栋挠 利有攸往 亨\",\"index\":27,\"name\":\"大过\",\"rows\":\"[藉用白茅 无咎, 枯杨生稊 老夫得其女妻 无不利, 栋桡 凶, 栋隆 吉 有它 吝, 枯杨生华 老妇得其士夫 无咎无誉, 过涉灭顶 凶 无咎]\"},{\"_id\":31,\"code\":\"04\",\"desc\":\"\",\"index\":43,\"name\":\"姤\",\"rows\":\"[初六：系于金□②，贞吉，有攸往，见凶，羸豕踟躅。, 九二：包有鱼，无咎，不利宾。, 九三：臀无肤，其行次且，厉，无大咎。, 九四：包无鱼，起凶。, 九五：以杞包瓜，含章，有陨自天。, null]\"},{\"_id\":32,\"code\":\"15\",\"desc\":\"亨 出入无疾 朋来无咎 反复其道 七日来复 利有攸往\",\"index\":23,\"name\":\"复\",\"rows\":\"[不远复 无祗悔 元吉, 休复 吉, 频复 厉 无咎, 中行独复, 敦复 无悔, 迷复 凶灾眚 用行师 终有大败 以其国君凶 至于十年不克征 ]\"},{\"_id\":33,\"code\":\"35\",\"desc\":\"贞吉 观颐 自求口实\",\"index\":26,\"name\":\"颐\",\"rows\":\"[舍尔灵龟 观我朵颐 凶, 颠颐拂经于丘颐 征凶, 拂颐 贞凶 十年勿用 无攸利, 颠颐 吉 虎视眈眈 其欲逐逐 无咎, 拂经 居贞吉 不可涉大川, 由颐 厉 吉 利涉大川]\"},{\"_id\":34,\"code\":\"75\",\"desc\":\"元亨利贞 勿用有攸往 利建侯\",\"index\":2,\"name\":\"屯\",\"rows\":\"[盘桓 利居贞 利建候, 屯如 遣如 乘马班如 匪寇婚媾 女子贞不字 十年乃字, 即鹿无虞 惟入于林中 君子几 不如舍 往吝, 乘马班如 求婚媾 往吉 无不利, 屯其膏 小 贞吉 大 贞吉, 乘马班如 泣血涟如]\"},{\"_id\":35,\"code\":\"45\",\"desc\":\"\",\"index\":41,\"name\":\"益\",\"rows\":\"[初九：利用为大作，元吉，无咎。, 六二：或益之，十朋之龟弗克违，永贞吉。, null, null, null, null]\"},{\"_id\":36,\"code\":\"55\",\"desc\":\"\",\"index\":50,\"name\":\"震\",\"rows\":\"[初九：震来□①□①，后笑言哑哑，吉。, null, null, null, null, null]\"},{\"_id\":37,\"code\":\"65\",\"desc\":\"亨 利用狱\",\"index\":20,\"name\":\"噬嗑\",\"rows\":\"[履校灭趾 无咎, 噬肤灭鼻 无咎, 噬腊肉遇毒 小吝 无咎, 噬乾胏 得金矢 利艰贞 吉, 噬干肉得黄金 贞厉 无咎, 何校灭耳 凶]\"},{\"_id\":38,\"code\":\"25\",\"desc\":\"元亨 利贞 无咎\",\"index\":16,\"name\":\"随\",\"rows\":\"[官有渝 贞吉 出门交有功, 系小子 失丈夫, 系丈夫 失小子 随有求 得 利居贞, 随有获 贞凶 有孚在道 以明 何咎, 孚于嘉 吉, 拘系之 乃从维之 王用亨于西山]\"},{\"_id\":39,\"code\":\"05\",\"desc\":\"元亨 利贞 其匪正有眚 不利有攸往\",\"index\":24,\"name\":\"无妄\",\"rows\":\"[无妄往 吉, 不耕获 不菑畲 则利用有望, 无妄之灾 或系之牛 行人之得 邑人之灾, 可贞 无咎, 无妄之疾 勿药有喜, 无妄行 有眚 无攸利]\"},{\"_id\":40,\"code\":\"16\",\"desc\":\"利艰贞\",\"index\":35,\"name\":\"明夷\",\"rows\":\"[明夷 于飞垂其翼 君子于行 三日不食 有攸往 主人有言, 明夷夷于左股 用拯马壮 吉, 明夷于南狩 得其大首 不可疾贞, 入于左腹 获明夷之心 于出门庭, 箕子之明夷 利贞, 不明 晦 初登于天 后入于地]\"},{\"_id\":41,\"code\":\"36\",\"desc\":\"亨 小利有攸往\",\"index\":21,\"name\":\"贲\",\"rows\":\"[贲其趾 舍车而徒, 贲其须, 贲如 濡如 永贞吉, 贲如皤如 白马翰如 匪寇 婚媾, 贲于丘园 束帛戋戋 吝 终吉, 自贲 无咎]\"},{\"_id\":42,\"code\":\"76\",\"desc\":\"亨，小利贞，初吉终乱。\",\"index\":62,\"name\":\"既济\",\"rows\":\"[初九：曳其轮，濡其尾，无咎。, 六二：妇丧其□①，勿逐，七日得。□① ＝ 上髟 下弗, 九三：高宗伐鬼方，三年克之，小人勿用。, 六四：□②有衣□③，终日戒。□② ＝ 纟 ＋ 需;□③ ＝ 衤 ＋ 如, 九五：东邻杀牛，不如西邻之□④祭，实受其福。□④ ＝ 礻 ＋ 龠, 上六：濡其首，厉。]\"},{\"_id\":43,\"code\":\"46\",\"desc\":\"\",\"index\":36,\"name\":\"家人\",\"rows\":\"[初九：闲有家，悔亡。, 六二：无攸遂，在中馈，贞吉。, 九三：家人□□，悔厉吉；妇子嘻嘻，终吝。, 六四：富家，大吉。, 九五：王假有家，勿恤吉。, 上九：有孚威如，终吉。]\"},{\"_id\":44,\"code\":\"56\",\"desc\":\"\",\"index\":54,\"name\":\"丰\",\"rows\":\"[初九：遇其配主，虽旬无咎，往有尚。, 六二：丰其□①，日中见斗，往得疑疾，有孚发若，吉。, 九三：丰其沛，日中见昧，折其右肱，无咎。, null, null, null]\"},{\"_id\":45,\"code\":\"66\",\"desc\":\"利贞 亨 畜牝牛吉\",\"index\":29,\"name\":\"离\",\"rows\":\"[履错然 敬之无咎, 黄离 元吉, 日昃之离 不鼓缶而歌 则大耋之嗟 凶, 突如 其来如 焚如 死如 弃如, 出涕沱若 戚嗟若 吉, 王用出征 有嘉折首 获匪其丑 无咎]\"},{\"_id\":46,\"code\":\"26\",\"desc\":\"\",\"index\":48,\"name\":\"革\",\"rows\":\"[初九：巩用黄牛之革。, 六二：己日乃革之，征吉，无咎。, 九三：征凶，贞厉，革言三就，有孚。, 九四：悔亡，有孚改命，吉。, 九五：大人虎变，未占有孚。, 上六：君子豹变，小人革面，征凶，居贞吉。]\"},{\"_id\":47,\"code\":\"06\",\"desc\":\"同人于野 亨 利涉大川 利君子贞\",\"index\":12,\"name\":\"同人\",\"rows\":\"[同人于门 无咎, 同人于宗 吝, 伏戎于莽 升其高陵 三岁不兴, 乘其墉 弗克攻 吉主, 同人先号咷而后笑 大师克 相遇, 同人于郊 无悔]\"},{\"_id\":48,\"code\":\"12\",\"desc\":\"元亨 利贞 至于八月有凶\",\"index\":18,\"name\":\"临\",\"rows\":\"[咸临 贞吉, 咸临 吉 无不利, 甘临 无攸利 既忧之 无咎, 至临 无咎, 知临 大君之宜 吉, 敦临 吉 无咎]\"},{\"_id\":49,\"code\":\"32\",\"desc\":\"\",\"index\":40,\"name\":\"损\",\"rows\":\"[初九：已事遄往，无咎，酌损之。, 九二：利贞，征凶，弗损益之。, 六三：三人行，则损一人；一人行，则得其友。, 六四：损其疾，使遄有喜，无咎。, 六五：或益之，十朋之龟弗克违，元吉。, 上九：弗损益之，无咎，贞吉，利有攸往，得臣无家。]\"},{\"_id\":50,\"code\":\"72\",\"desc\":\"\",\"index\":59,\"name\":\"节\",\"rows\":\"[初九：不出户庭，无咎。, 九二：不出门庭，凶。, 六三：不节若，则嗟若，无咎。, 六四：安节，亨。, 九五：甘节，吉；往有尚。, 上六：苦节，贞凶，悔亡。]\"},{\"_id\":51,\"code\":\"42\",\"desc\":\"\",\"index\":60,\"name\":\"中孚\",\"rows\":\"[初九：虞吉，有他不燕。, 九二：鸣鹤在阴，其子和之，我有好爵，吾与尔靡之。, 六三：得敌，或鼓或罢，或泣或歌。, 六四：月几望，马匹亡，无咎。, 九五：有孚挛如，无咎。, 上九：翰音登于天，贞凶。]\"},{\"_id\":52,\"code\":\"52\",\"desc\":\"\",\"index\":53,\"name\":\"归妹\",\"rows\":\"[初九：归妹以娣，跛能履，征吉。, null, null, null, null, null]\"},{\"_id\":53,\"code\":\"62\",\"desc\":\"\",\"index\":37,\"name\":\"睽\",\"rows\":\"[初九：悔亡，丧马勿逐，自复；见恶人无咎。, 九二：遇主于巷，无咎。, 六三：见舆曳，其牛掣，其人天且劓，无初有终。, null, null, null]\"},{\"_id\":54,\"code\":\"22\",\"desc\":\"\",\"index\":57,\"name\":\"兑\",\"rows\":\"[初九：和兑，吉。, 九二：孚兑，吉，悔亡。, 六三：来兑，凶。, 九四：商兑，未宁，介疾有喜。, 九五：孚于剥，有厉。, 上六：引兑。]\"},{\"_id\":55,\"code\":\"02\",\"desc\":\"履虎尾 不咥人 亨\",\"index\":9,\"name\":\"履\",\"rows\":\"[素履往 无咎, 履道坦坦 幽人贞吉, 眇能视 跛能履 履虎尾 咥人 凶 武人为于大君, 履虎尾 愬愬 终吉, 夬履 贞厉, 视履考祥 其旋元吉]\"},{\"_id\":56,\"code\":\"10\",\"desc\":\"小往大来 吉 亨\",\"index\":10,\"name\":\"泰\",\"rows\":\"[拔茅茹以其汇 征吉, 包荒 用冯河 不遐遗 朋亡 得尚于中行, 无平不陂 无往不复 艰贞无咎 勿恤其孚 于食有福, 翩翩 不富以其邻 不戒以孚, 帝乙归妹 以祉元吉, 城复于隍 勿用师 自邑告命 贞吝]\"},{\"_id\":57,\"code\":\"30\",\"desc\":\"利贞 不家食吉 利涉大川\",\"index\":25,\"name\":\"大畜\",\"rows\":\"[有厉 利己, 舆说輹, 良马逐 利艰贞 曰闲舆卫 利有攸往, 童牛之牿 元吉, 豮豕之牙 吉, 何天之衢 亨]\"},{\"_id\":58,\"code\":\"70\",\"desc\":\"有孚 光亨 贞吉 利涉大川\",\"index\":4,\"name\":\"需\",\"rows\":\"[需于郊 利用恒 无咎, 需于沙 小有言 终吉, 需于泥 致寇至, 需于血 出自穴, 需于酒食 贞吉, 入于穴 有不速之客三人来 敬之终吉]\"},{\"_id\":59,\"code\":\"40\",\"desc\":\"亨 密云不雨 自我西郊\",\"index\":8,\"name\":\"小畜\",\"rows\":\"[复自道 何其咎 吉, 牵复 吉, 舆说辐 夫妻反目, 有孚 血去 惕出无咎, 有孚挛如 富以其邻, 既雨既处 尚德载 妇贞厉 月几望 君子征凶]\"},{\"_id\":60,\"code\":\"50\",\"desc\":\"利贞\",\"index\":33,\"name\":\"大壮\",\"rows\":\"[壮于趾 征凶 有孚, 贞吉, 小人用壮 君子用罔 贞厉 羝羊触藩 羸其角, 贞吉 悔亡 藩决不羸 壮于大舆之輹, 丧羊于易 无悔, 羝羊触藩 不能退 不能遂 无攸利 艰则吉]\"},{\"_id\":62,\"code\":\"20\",\"desc\":\"\",\"index\":42,\"name\":\"夬\",\"rows\":\"[初九：壮于前趾，往不胜为吝。, 九二：惕号，莫夜有戎，勿恤。, 九三：壮于□②，有凶。, null, null, null]\"},{\"_id\":63,\"code\":\"11\",\"desc\":\"元亨 利牝马之贞 君子有攸往 先迷 后得主 利 西南得朋 东北丧朋 安贞吉\",\"index\":1,\"name\":\"坤\",\"rows\":\"[履霜 坚冰至, 直方大 不习 无不利, 含章可贞 或从王事 无成有终, 括囊 无咎无誉, 黄裳 元吉, 龙战于野 其血玄黄]\"}]}"


        val g64IndexBean = Gson().fromJson(readText, G64IndexBean::class.java)
        val list = ArrayList<Gua>()
        for (i in g64IndexBean.gList.indices) {
            val gListBean = g64IndexBean.gList[i]
            val gua = Gua(gListBean.name, gListBean.code, gListBean._id, gListBean.desc,
                    gListBean.index, gListBean.rows)
            list.add(gua)
        }
        guaRepository.saveAll(list)
    }


    @RequestMapping("/controller", params = ["code"])
    @ResponseBody
    fun getCode(@RequestParam code: String): DescGuaBean {

        return try {
            ChaGuaUtil(guaRepository).quaryGua(code)

        } catch (e: Exception) {
            DescGuaBean("查询失败", "", "")
        }

    }


}