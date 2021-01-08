$(document).ready(function(){
    $(".wechat").hover(function(){
        $(".call-me-layui-icon img").attr("src","/film/img/158.jpg");
        $(".call-me-layui-icon").css("display","block");
    },function(){
        $(".call-me-layui-icon").css("display","none");
    });

    $(".qq").hover(function(){
        $(".call-me-layui-icon img").attr("src","/film/img/158.jpg");
        $(".call-me-layui-icon").css("display","block");
    },function(){
        $(".call-me-layui-icon").css("display","none");
    });
    $(".telegram").hover(function(){
        $(".call-me-layui-icon img").attr("src","/film/img/158.jpg");
        $(".call-me-layui-icon").css("display","block");
    },function(){
        $(".call-me-layui-icon").css("display","none");
    });
    $(".alipay").hover(function(){
        $(".call-me-layui-icon img").attr("src","/film/img/159.jpg");
        $(".call-me-layui-icon").css("display","block");
    },function(){
        $(".call-me-layui-icon").css("display","none");
    });
    $(".wxpay").hover(function(){
        $(".call-me-layui-icon img").attr("src","/film/img/158.jpg");
        $(".call-me-layui-icon").css("display","block");
    },function(){
        $(".call-me-layui-icon").css("display","none");
    });
});

var wish =
    "\t😂 求资源,求片\n" +
    "\t😂 程序bug反馈\n" +
    "\t😂 程序优化建议\n" +
    "\t😂 加盟我们   \n" ;


$("textarea").attr("placeholder",wish);

