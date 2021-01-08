/**
 * 初始化table表格数据 , 基于layui
 *
 * @param _tabElem : table元素的id或者class
 * @param _url : 获取数据的url
 * @param _parameters : 请求参数
 * @param _columns : 初始化列
 * @param _event : 监听事件的方式
 * @param _func : 监听事件的方法体
 *
 */
initLayuiTable = function (_tabElem, _url, _parameters, _columns, _event, _func) {
    layui.use(['table', 'laypage'], function () {
        var table = layui.table;
        $.ajax({
            type: "post",
            url: _url, // 数据接口
            async: true,
            data: JSON.stringify(_parameters),
            contentType: "application/json",
            success: function (res) {
                table.render({
                    elem: _tabElem,
                    data: res,
                    cols: [_columns],
                    page: true, //是否显示分页
                    limit: 10,
                    limits: [10, 20, 50, 100]
                });
            }
        });

        // 监听单元格事件
        table.on(_event,_func);

    });

};

menu_parentData = function (_seleELE) {

    $.ajax({
        type: "post",
        url: '/system/getParentList', // 数据接口
        async: true,
        success: function (res) {
            var selec = "<option value=\"\">请选择</option>" ;
            $.each(res, function () {
                var id = this.id;
                var menu = this.menu;
                selec += "<option value=\""+id+"\">"+menu+"</option>"
            });
            $(_seleELE).html(selec)

        }
    });

};

/**
 * 通用方法封装处理
 * Copyright (c) 2018 isim
 */
/*
	参数解释：
	title	标题
	url		请求的url
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
layer_show = function (title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    if (url == null || url == '') {
        url = "404.html";
    }
    if (w == null || w == '') {
        w = 800;
    }
    if (h == null || h == '') {
        h = ($(window).height() - 80);
    }
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
};


modelAlert = function (title , content , func){
    layer.open({
        title: title,
        content: content,
        yes :func

    });
};

modelClose=function(){
    layer.closeAll()
};