
var prefix = '/admin/manager/15628783286' ;

layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#demo'
        // ,height: 312
        ,url: prefix + '/list' //数据接口
        ,page: false //开启分页
        ,cols: [[ //表头
            {field: 'uuid', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'filmName', title: '电影名称'}
            ,{field: 'filmHref', title: '超链接'}
            ,{field: 'filmPic', title: '图片地址'}
            ,{field: 'filmNotes', title: '用户名'}
            ,{field: 'filmStars', title: '用户名'}
            ,{field: 'filmDirector', title: '用户名'}
            ,{field: 'filmColumn', title: '用户名'}
            ,{field: 'filmTimeLength', title: '用户名'}
            ,{field: 'filmType', title: '用户名'}
            ,{field: 'filmLanguage', title: '用户名'}
            ,{field: 'filmArea', title: '用户名'}
            ,{field: 'filmReleasesYear', title: '用户名'}
            ,{field: 'filmUpdateTime', title: '用户名'}
            ,{field: 'filmScore', title: '用户名'}
        ]]
    });

});