package bean;

import java.util.List;

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019/3/13
 */
public class G64Bean {
    public List<GBean> gList;
    public static class GBean {

        /**
         * name : 乾
         * code : 00
         * id : 0
         * desc : 元亨利贞
         * rows : ["潜龙 勿用","见龙在田 利见大人","君子终日乾乾 夕惕若 厉无咎","或跃在渊 无咎","飞龙在天 利见大人","亢龙 有悔"]
         */

        public String name;
        public String code;
        public int id;
        public String desc;
        public List<String> rows;

        @Override
        public String toString() {
            return "id="+id+",name="+name+",code="+name+",desc"+"\r\n"+"rows"+rows.get(0)+
                    rows.get(1)+rows.get(2)+rows.get(3)+rows.get(4)+rows.get(5);
        }
    }


}
