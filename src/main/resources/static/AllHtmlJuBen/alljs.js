
(function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if(clientWidth>=640){
                    docEl.style.fontSize = '50px';
                }else{
                    docEl.style.fontSize = 24 * (clientWidth / 640) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);

    })
    (document, window);
 function Trim(str)
         {
             return str.replace(/(^\s*)|(\s*$)/g, "");
     }
 window.onload=function(){
      var divs=document.getElementsByTagName("div");
      //人物所在的行(div)
      var index =0;
      for (var l = 0; l < 10; l++) {
        var str = divs[l].innerHTML;
        if (str.indexOf("人物")>=0) {index=l};
      };
      console.log(index);
      var pepoleline = divs[index].innerHTML;
      // 人物:旁白(大夫)凤姐，平儿，贾琏。
      var pepole = pepoleline.substr(3,pepoleline.length);
      //旁白 大夫 凤姐 平儿 贾琏
      var pepoles = pepole.split("，");

      //颜色值
      var colors = new Array();
      colors[0]="#FFC0CB";
      colors[1]="#53c280";
      colors[2]="#CD96CD";
      colors[3]="#BCEE68";
      colors[4]="#00E5EE";
      colors[5]="#E066FF";
      colors[6]="#FF69B4";
      colors[7]="#F08080";
      colors[8]="#7FFFAA";

      var pepolenames = new Array();

      for (var j = 0; j < pepoles.length; j++) {
          pepolenames[j]=pepoles[j].substr(0,2);
          // console.log(pepolenames[j]);
        };

      for(var i=0;i<divs.length;i++){
//        var str  = $.trim(divs[i].innerHTML);
//        var str  = (divs[i].innerHTML).trim();
          var str  = Trim(divs[i].innerHTML);

        for (var k = 0; k < pepolenames.length; k++) {
          if (str.substr(0,2)==pepolenames[k]&&str.substr(0,2)!="旁白") {divs[i].style.backgroundColor = colors[k%9];console.log("hello"+str.substr(0,2));};
        };
      }
   }
