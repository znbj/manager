var historyData={
    pageIndex: 1,//起始页码
    pageSize: 15, //默认初始化每页行数
    $: null,
    layer: null,
    form: null,
    table: null,
    util: null,
    admin: null,
    index: null,
    openPopupIndex: null,
    element: null,
    laydate: null,
    isIE:false,
    setter:null,
    options:{
        // inline: true,
        url: 'data-original',
        ready: function (e) {
            // console.log(e.type);
        },
        show: function (e) {
            // console.log(e.type);
        },
        shown: function (e) {
            // console.log(e.type);
        },
        hide: function (e) {
            // console.log(e.type);
        },
        hidden: function (e) {
            // console.log(e.type);
        },
        view: function (e) {
            // console.log(e.type);
        },
        viewed: function (e) {
            // console.log(e.type);
        },
        move: function (e) {
            // console.log(e.type);
        },
        moved: function (e) {
            // console.log(e.type);
        },
        rotate: function (e) {
            // console.log(e.type);
        },
        rotated: function (e) {
            // console.log(e.type);
        },
        scale: function (e) {
            // console.log(e.type);
        },
        scaled: function (e) {
            // console.log(e.type);
        },
        zoom: function (e) {
            // console.log(e.type);
        },
        zoomed: function (e) {
            // console.log(e.type);
        },
        play: function (e) {
            // console.log(e.type);
        },
        stop: function (e) {
            // console.log(e.type);
        }
    },
}


//页面初始化
$(function () {
    layui.use(['layer', 'form', 'table', 'util', 'laydate'], function () {
        historyData.$ = layui.jquery;
        historyData.layer = layui.layer;
        historyData.form = layui.form;
        historyData.table = layui.table;
        historyData.util = layui.util;
        historyData.form.render();
        historyData.laydate = layui.laydate;
        historyData.laydate.render({
            elem: 'input[name="historyDataDateSel"]',
            type: 'datetime',
            range: true,
            trigger: 'click'
        });
        //自定义表单验证
        // historyData.form.verify({
        //     checkTimeSlot: function (value, item) {
        //         let startTime = "";
        //         let endTime = "";
        //         if (value !== "") {
        //             var timeRange = value.split(" - ");
        //             startTime = timeRange[0];
        //             endTime = timeRange[1];
        //         }
        //         let number = historyData.datedifference(startTime, endTime);
        //         if (number > 7) {
        //             return '查询时间间隔不得超过7天';
        //         }
        //     }
        // });
        // 判断是否为IE浏览器，如果是则使公共变量的 isIE 设置为 true
        //取得浏览器的userAgent字符串
        let userAgent = navigator.userAgent;
        //判断是否是低于11版本的IE浏览器
        let isIEFlag = userAgent.indexOf("MSIE") > -1;
        //判断是否是Edge浏览器
        let isEdgeFlag = userAgent.indexOf("Edge") > -1;
        // 判断是否是IE11浏览器
        let isIE11Flag = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv") > -1;

        if (isEdgeFlag || isIE11Flag || isIEFlag) {
            historyData.isIE = true;
        }
        //初始化表格
        historyData.initTable();
        //加载表格数据
        historyData.getPassingRecordData();
        // /* 文本框清空 */
        // historyData.form.on('submit(passRecordSearchEmpty)', function (data) {
        //     $("#historydataTbSearchForm")[0].reset();
        //     historyData.form.render();
        //     return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        // });
        $("#passRecordSearchEmpty").on('click', function () {
            $("#historydataTbSearchForm")[0].reset();
            historyData.form.render();
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        /* 表格搜索 */
        historyData.form.on('submit(passRecordTbSearch)', function (data) {
            historyData.getPassingRecordData();
        });
        historyData.table.on('tool(historydataTable)', function (obj) {
            //获得当前行数据
            var data = obj.data;
            if (obj.event === 'edit') {
                layer.open({
                    type:1,
                    title: "修改",
                    fixed:false,
                    resize :false,
                    shadeClose: true,
                    area: ['550px'],
                    content:$('#setUser'),
                    success:function (layero){
                        debugger
                        $("#userId").val(data.user_id);
                        $("#mobile").val(data.phone);
                        $("#UserStatus").val(data.auditStatus);
                        $('#userName').val(data.trueName);
                        $('#remarks').val(data.remarks);
                        historyData.form.render();
                        historyData.form.on('submit(userSubmit)', function (updateData) {
                            console.log(updateData);
                            historyData.updateUser(updateData.field);
                            return false;
                        });

                        $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    },
                    end:function(){
                        cleanUser();
                    }
                });
            }else if (obj.event === 'delete') {
                layer.confirm('您确定要删除'+data.phone+'用户吗？', {
                    btn: ['确认','返回'] //按钮
                }, function(){
                    $.post("/bta/deleteUser",{"id":data.user_Id},function(data){
                        if (data.code === 0) {
                            historyData.getPassingRecordData();
                            layer.alert(data.msg,function(){
                                layer.closeAll();

                                // load(obj);
                            });
                        } else {
                            layer.alert(data.msg);
                        }
                    });
                }, function(){
                    layer.closeAll();
                });

            }
        });
    });
})
//初始化表格
historyData.initTable = function () {
    var insTB = historyData.table.render({
        elem: '#historydataTable',
        data:[],
        page: true,
        limit: historyData.pageSize,
        limits:[15,40,60,80,100,120],
        cellMinWidth: 100,
        height: 'full-100',
        cols: [[
            {type: 'numbers'},
            {field: 'phone', title: '手机号', align: 'center'},
            {field: 'createTime', title: '注册时间', align: 'center'},
            {
                title: '手持照片', templet: function (d) {
                    var url = d.imageUrl ||  '/images/img-nocar.png';
                    return '<img data-index="' + (d.LAY_INDEX - 1) + '" src="' + url + '" style="width: 80px;\n' +
                        '            height: 30px;\n' +
                        '            cursor: pointer;\n' +
                        '            border: 2px solid #dddddd;\n' +
                        '            margin-right: 10px;" class="tb-img-circle" vh-img alt>';
                }, align: 'center', minWidth: 160, unresize: true
            },
            {
                title: '身份证照片', templet: function (d) {
                    var url = d.idImageUrl || '/images/img-nocar.png';
                    return '<img data-index="' + (d.LAY_INDEX - 1) + '" src="' + url + '" style="width: 80px;\n' +
                        '            height: 30px;\n' +
                        '            cursor: pointer;\n' +
                        '            border: 2px solid #dddddd;\n' +
                        '            margin-right: 10px;" class="tb-img-circle" tb-img alt>';
                }, align: 'center', minWidth: 160, unresize: true
            },

            {field: 'trueName', title: '姓名', align: 'center'},
            {field: 'auditStatus', title: '审核状态', align: 'center',templet:function (d) {
                    var _html = "";
                    console.log(d.auditStatus)
                    if (d.auditStatus === 0) {
                        _html += '<button class="layui-btn layui-btn-xs layui-btn-normal">待审核</button>&nbsp;';
                    }else if (d.auditStatus === 1) {
                        _html += '<button class="layui-btn layui-btn-xs layui-btn-normal">审核通过</button>&nbsp;';
                    }else if (d.auditStatus === 2) {
                        _html += '<button class="layui-btn layui-btn-xs layui-btn-danger">不合格</button>&nbsp;';
                    }else if (d.auditStatus === 3) {
                        _html += '<button class="layui-btn layui-btn-xs layui-btn-danger">未实名</button>&nbsp;';
                    }
                    return _html;
                }},
            {field: 'lx', title: '操作',align: 'center', templet: function () {
                //AuditStatus 审核状态:0审核中,1审核通过,2不合格,3未实名
                    return '<button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">编辑</button>&nbsp;<button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</button>&nbsp;';
                }}
        ]],
    });
    /* 点击车牌图片放大 */
    $(document).off('click.tbImg').on('click.tbImg', '[tb-img]', function () {
        var imgList = historyData.table.cache['historydataTable'].map(function (d) {
            return {
                alt: d.trueName,
                src: d.idImageUrl || '/images/img-nocar.png'
            }
        });
        historyData.layer.photos({photos: {data: imgList, start: $(this).data('index')}, shade: .1, closeBtn: true});
    });
    /* 点击车辆图片放大 */
    $(document).off('click.vh-img').on('click.vh-img', '[vh-img]', function () {
        var imgList = historyData.table.cache['historydataTable'].map(function (d) {
            return {
                alt: d.trueName,
                src: d.imageUrl || '/images/img-nocar.png'
            }
        });
        historyData.layer.photos({photos: {data: imgList, start: $(this).data('index')}, shade: .1, closeBtn: true});
    });
    if (historyData.isIE) {
        //判断是否为IE浏览器，如果是IE浏览器则清空右上角内容
        insTB.defaultToolbar = [];
    }
};

//根据参数分页查询过车记录
historyData.getPassingRecordData = function () {
    let startTime = "";
    let endTime = "";
    let timeRange = $("#historyDataDateSel").val();
    if (timeRange !== "") {
        timeRange = timeRange.split(" - ");
        startTime = timeRange[0];
        endTime = timeRange[1];
    }
    var phone = $("#phone").val();
    var trueName = $("#trueName").val();
    var AuditStatus = $("#AuditStatus").val();
    var model = {
        beginTime:startTime,
        endTime:endTime,
        phone:phone,
        trueName:trueName,
        AuditStatus: AuditStatus
    };
    historyData.table.reload("historydataTable", {
        url: "/bta/getUserList",
        page: {
            curr: historyData.pageIndex
        },
        where: {
            queryStr: JSON.stringify(model)
        },
        done: function (res, pageIndex, count) {
        }
    });
    return false;
};

historyData.updateUser = function (data) {
    $.ajax({
        type: "POST",
        data: data,
        url: "/bta/updateUser",
        dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                historyData.getPassingRecordData();
                layer.alert(data.msg,function(){
                    layer.closeAll();
                    // load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                // load(obj);//自定义
            });
        }
    });

};

function cleanUser(){
    $("#id").val("");
    $("#mobile").val("");
    $("#UserStatus").val("");
    $('#userName').val("");
    $('#remarks').val("");
}
