package bean;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019/3/15
 */
public class G64IndexBean {

    public List<GListBean> gList;

    public static class GListBean {
        /**
         * name : 乾
         * code : 00
         * id : 0
         * index : 0
         * desc : 元亨利贞
         * rows : ["潜龙 勿用","见龙在田 利见大人","君子终日乾乾 夕惕若 厉无咎","或跃在渊 无咎","飞龙在天 利见大人","亢龙 有悔"]
         */

        public String name;
        public String code;
        public int _id;


        public int index;
        public String desc;
        public String rows;

        @Override
        public String toString() {
            return "第" + index +
                    "卦:"+name+":"+ desc+";  卦辞:" +rows;
        }
    }
}
