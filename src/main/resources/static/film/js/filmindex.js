var _parameters = $("#uuid").val();
var _url = _parameters ;
$.ajax({
    type: "post",
    url: _url, // 数据接口
    async: true,
    // data: JSON.stringify(_parameters),
    contentType: "application/json",
    success: function (res) {
        $("#video iframe").attr("src",res[0].filmUrl.split("$")[1]);
        $.each(res , function (i,info) {
            var ss = info.filmUrl;
            var atg = ss.split("$")[0];
            var href = ss.split("$")[1];
            if (i == 0) {
                $("#which").text(atg)
                $("#bof").append("<li class='active' onclick=\"toBof('"+href+"',this)\">"+atg+"</li>")
            }else{
                $("#bof").append("<li onclick=\"toBof('"+href+"',this)\">"+atg+"</li>")
            }
        })
    }
});

function toBof(href, tag) {
    $("#which").text(tag.innerText);
    $("#video iframe").attr("src","");
    $("#video iframe").attr("src",href)
    $(tag).addClass("active").siblings().removeClass("active")

}
setTimeout(watchVideo,1000*120);
function watchVideo() {
    var film_name = $("#film_name").val();
    var data = {"film":film_name};
    var _url = $("#watchVideo").val();
    $.ajax({
        type: "post",
        url: _url, // 数据接口
        async: true,
        data: JSON.stringify(data),
        contentType: "application/json",
    })
}

