> spring项目
> 根据六次掷铜钱的字面的个数，来查询卦序卦辞 的api 或者自助算卦网页
## 请求示例:
### http://tstsolykyk.com/jiegua?code=123221
## 算卦示例
### http://tstsolykyk.com/suangua

## 2:
> html所有文件在/src/main/resources/static

## 3:打包步骤
1.gradle --> build --> bootJar
2./build/libs/yinyangji-***.jar
3.登录服务器，上传jar包
scp /Users/yiche/work/spring/yinyangji/build/libs/yinyangji-0.0.1-SNAPSHOT.jar     ubuntu@118.25.24.178:/home/ubuntu/upload/yinyangji-201912271036-SNAPSHOT.jar
4.运行下列命令，查看log.log
nohup java -jar yinyangji-0.0.1-SNAPSHOT.jar  > log.log 2>&1 &
