package com.ykyk.yinyangji.dao;

import java.util.ArrayList;

import javax.print.DocFlavor;

import bean.BenBianGuaBean;

/**
 * 项目名称：app.carmodel
 *
 * @author zhaohan
 * @date 2019-07-17
 */

class kotlinchange {



    private void duanGua(BenBianGuaBean benBianGuaBean, Gua benGua, Gua bianGua, int bianyaoCount) {
        int[] yaoInts = benBianGuaBean.benGua.yaoInt;//本卦的0123 code值,1为阳,2为阴,3为老阳
        String mTvGTitle;
        String mtvGName;
        String mTvGDesc;
        switch (bianyaoCount) {
            case 0://六爻安定的，以本卦卦辞断之。
                mTvGTitle=("此卦当以本卦卦辞断之：");
                mtvGName=("第" + (benGua.getIndex()+1) + "卦： " + benGua.getName());
                mTvGDesc=("辞曰：" + benGua.getDesc()+"\n ,卦爻:"+benGua.getRows());
                break;
            case 1:// 一爻动，以动爻之爻辞断之。
                mTvGTitle=("此卦当以本卦变爻爻辞断之：");
                mtvGName=("第" + (benGua.getIndex()+1) + "卦  " + benGua.getName());
                int yaoTag = benBianGuaBean.benGua.yaoTag;
                String yaoStr = "";
                String s = "";//九，六，阴阳
                switch (yaoTag) {
                    case 1://初爻动
                        s = benBianGuaBean.benGua.yaos[0].yinYang == 1 ? "初九" : "初六";
                        yaoStr = s + "：" + asArra(benGua.getRows())[0];
                        break;
                    case 2:
                        s = benBianGuaBean.benGua.yaos[1].yinYang == 1 ? "九二" : "六二";
                        yaoStr = s + "：" + asArra(benGua.getRows())[1];
                        break;
                    case 4:
                        s = benBianGuaBean.benGua.yaos[2].yinYang == 1 ? "九三" : "六三";
                        yaoStr = s + "：" + asArra(benGua.getRows())[2];
                        break;
                    case 8:
                        s = benBianGuaBean.benGua.yaos[3].yinYang == 1 ? "九四" : "六四";
                        yaoStr = s + "：" + asArra(benGua.getRows())[3];
                        break;
                    case 16:
                        s = benBianGuaBean.benGua.yaos[4].yinYang == 1 ? "九五" : "六五";
                        yaoStr = s + "：" + asArra(benGua.getRows())[4];
                        break;
                    case 32:
                        s = benBianGuaBean.benGua.yaos[5].yinYang == 1 ? "上九" : "上六";
                        yaoStr = s + "：" + asArra(benGua.getRows())[5];
                        break;
                }
                mTvGDesc=(yaoStr);
                break;
            case 2:
                StringBuilder descYang = new StringBuilder();//阳爻
                StringBuilder descYin = new StringBuilder();//阴爻
                ArrayList<String> yao22= new ArrayList<>();

                for (int i = 0; i < yaoInts.length; i++) {
                    if (yaoInts[i]==0||yaoInts[i]==3){
                        yao22.add(asArra(benGua.getRows())[i]);
                    }

                }
                mTvGTitle=("两爻动，则取阴爻指爻辞以为断,上爻为主");
                // 两爻动，则取阴爻指爻辞以为断，盖以“阳主过去，阴主未来”故也。
                // 如天风姤卦，初六，九五两爻皆动，则以初六爻断之，
                // 九五爻为辅助之断，“阳主过去，阴主未来”，其中大有学问。

                mtvGName=("第" +  (benGua.getIndex()+1)  + "卦： " + benGua.getName());
                mTvGDesc=("上爻:"+yao22.get(1)+",下爻:"+yao22.get(0));
                break;
            case 3:
                mTvGTitle=("三爻动者，,本卦卦辞为主,变卦卦辞为辅断之。");
                mtvGName=("第" +  (benGua.getIndex()+1)  + "卦： " + benGua.getName());

                mTvGDesc=("本卦卦辞:"+benGua.getDesc()+"\n ,卦爻:"+benGua.getRows()+"\n变卦卦辞:"+bianGua.getDesc()+"\n ,卦爻:"+bianGua.getRows());
                break;
            case 4:
                mTvGTitle=(" 四爻动者，以下静之爻辞断之。");
                mtvGName=("第" +  (benGua.getIndex()+1)  + "卦： " + benGua.getName());
                String shangYao = "";
                String xiaYao = "";
                ArrayList<String> yao2= new ArrayList<>();

                for (int i = 0; i < yaoInts.length; i++) {
                    if (yaoInts[i]!=0&&yaoInts[i]!=3){
                        yao2.add(asArra(benGua.getRows())[i]);
                    }
                }
                xiaYao = yao2.get(1);
                shangYao = yao2.get(0);
                mTvGDesc=("上爻:"+shangYao+"\n 下爻:"+xiaYao);
                break;
            case 5:
                mTvGTitle=("五爻多动者，取变卦静爻的爻辞断之。");
                mtvGName=("第" +  (benGua.getIndex()+1)  + "卦： " + benGua.getName());
                String jingYao ="";
                for (int i = 0; i < yaoInts.length; i++) {
                    if (yaoInts[i]!=0&&yaoInts[i]!=3){
                        jingYao=asArra(bianGua.getRows())[i];
                    }
                }
                mTvGDesc=("变卦:第"+ (bianGua.getIndex()+1) +"卦:"+bianGua.getName()+"静爻:"+jingYao);
                break;
            case 6:
//                六爻皆动的卦，如果是乾坤二卦，以“用九”、“用六”之辞断之。如乾卦六爻皆动，则为群龙无首，吉。
//
//                除乾坤两卦外其余各卦，若是六爻皆动，则以变卦的彖辞断之。
                if (benGua.getIndex()==0||benGua.getIndex()==1){//乾坤二卦
                    if (benBianGuaBean.benGua.yaos[6].yinYang==0){
                        mTvGTitle=("坤卦六爻皆动,当以用六断之");
                    }else {
                        mTvGTitle=("乾卦六爻皆动,当以用九断之");
                    }
                }else {
                    mTvGTitle=("六爻皆动,当以变卦卦辞断之");
                    mtvGName=("第"+(bianGua.getIndex()+1)  + "卦： " + bianGua.getName());
                    mTvGDesc=("卦辞:"+bianGua.getDesc()+"\n ,卦爻:"+bianGua.getRows());
                }
                break;


        }
    }




    String mtvGName;
    String mTvGDesc;
    private String[] asArra(String rows) {
        rows = rows.replace("[", "");
        rows = rows.replace("]", "");
        return rows.split(",");
    }


}
