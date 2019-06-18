package bean;

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019/3/14
 */
public class BenBianGuaBean {

    public GuaDesc benGua;//本卦
    public GuaDesc bianGua;//本卦
    public int bianyaoCount;//变爻次数

    public BenBianGuaBean() {

    }


    @Override
    public String toString() {
        String str =
                "本卦:" + benGua.toString() + ",id=" + benGua.id +
                        ",变卦:" + bianGua.toString() + ",id=" + bianGua.id;


        return str;
    }

    public GuaDesc getBianGua(GuaDesc benGua) {
//        GuaDesc.Yao[] bianYaos = new GuaDesc.Yao[6];
//        GuaDesc.Yao[] benYaos = benGua.yaos;
        int[] yaoIntBen = benGua.yaoInt;
        int[] bianYaos = new int[6];
        for (int i = 0; i < yaoIntBen.length; i++) {
            bianYaos[i] = yaoIntBen[i];
            if (yaoIntBen[i] == 0) {//老阳
                bianYaos[i] = 3;
            }
            if (yaoIntBen[i] == 3) {//老阴
                bianYaos[i] = 0;
            }
        }

        return new GuaDesc(bianYaos);
    }


    public static class GuaDesc {
        public Yao[] yaos = new Yao[6];
        public int[] yaoInt = new int[6];
        public int yaoTag = 0;//用来标识变爻位置，1，2 ，4 ，8 ，16 ，64
        public int id;

        public GuaDesc(int[] yaos) {
            this.yaoInt = yaos;
            StringBuilder erjinzhiStr = new StringBuilder();
            for (int i = 0; i < yaos.length; i++) {
                String yaoStr = "";
                int yinyang = 0;
                switch (yaos[i]) {
                    case 0:
                        yaoStr = "老阳";
                        yinyang = 1;
                        yaoTag += (int) Math.pow(2, i);
                        break;
                    case 1:
                        yaoStr = "阴爻";
                        yinyang = 0;
                        break;
                    case 2:
                        yaoStr = "阳爻";
                        yinyang = 1;

                        break;
                    case 3:
                        yaoStr = "老阴";
                        yinyang = 0;
                        yaoTag += (int) Math.pow(2, i);

                        break;
                }
                erjinzhiStr.append(yinyang);

                this.yaos[i] = new Yao(yaoStr, yaos[i], yinyang);
            }

            this.id = Integer.parseInt(erjinzhiStr.toString(), 2);


        }


        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < yaos.length; i++) {
                stringBuilder.append(i + "=" + yaos[i].yaoStr);
            }
            return stringBuilder.toString();
        }


        public static class Yao {
            public String yaoStr;
            public int yaoInt;
            public int yinYang;//阴阳,0为阴,1为阳

            public Yao(String yaoStr, int yaoInt, int yinYang) {
                this.yaoStr = yaoStr;
                this.yaoInt = yaoInt;
                this.yinYang = yinYang;
            }


            @Override
            public String toString() {
                return super.toString();
            }
        }
    }
}
