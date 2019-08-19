layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool', '$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var record_Id;
    var user= [];
    var uid = new Array();
    var uname = [];
    var resId = new Array();
    var fenshu = new Array();

    var de= [];
    var neng= [];
    var qin= [];
    var ji= [];
    var lian= [];

    /**
     * 页面初始化
     * */
    function init() {
        load();
    }

    init();

    /**
     * 初始化
     */
    function load() {
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        myChart.setOption({
            color: ['#37a2da', '#32c5e9', '#67e0e3', '#9fe6b8', '#ffdb5c'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['德', '能', '勤', '绩', '廉']
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    axisTick: {show: false},
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '德',
                    type: 'bar',
                    barGap: 0,
                    // label: labelOption,
                    data: []
                },
                {
                    name: '能',
                    type: 'bar',
                    // label: labelOption,
                    data: []
                },
                {
                    name: '勤',
                    type: 'bar',
                    // label: labelOption,
                    data: []
                },
                {
                    name: '绩',
                    type: 'bar',
                    // label: labelOption,
                    data: []
                },
                {
                    name: '廉',
                    type: 'bar',
                    // label: labelOption,
                    data: []
                }
            ]
        });

        var queryArgs = $tool.getQueryParam();
        record_Id = queryArgs['record_Id'];
        var req = {
            record_Id: record_Id,
        }
        $api.GetShuJu(req, function (res) {
            var data = res.data;
            uid = data.uid;
            resId = data.resId;
            fenshu = data.fenshu;
            console.log(data);
            for (var a=0; a<(fenshu.length/resId.length); a++) {
                if (resId[a] == 37) {
                    de.push(fenshu[a]);
                    de.push(fenshu[a+resId.length]);
                    de.push(fenshu[a+(resId.length*2)]);
                    de.push(fenshu[a+(resId.length*3)]);
                    de.push(fenshu[a+(resId.length*4)]);
                } else if (resId[a] == 38) {
                    neng.push(fenshu[a]);
                    neng.push(fenshu[a+resId.length]);
                    neng.push(fenshu[a+(resId.length*2)]);
                    neng.push(fenshu[a+(resId.length*3)]);
                    neng.push(fenshu[a+(resId.length*4)]);
                } else if (resId[a] == 39) {
                    qin.push(fenshu[a]);
                    qin.push(fenshu[a+resId.length]);
                    qin.push(fenshu[a+(resId.length*2)]);
                    qin.push(fenshu[a+(resId.length*3)]);
                    qin.push(fenshu[a+(resId.length*4)]);
                } else if (resId[a] == 40) {
                    ji.push(fenshu[a]);
                    ji.push(fenshu[a+resId.length]);
                    ji.push(fenshu[a+(resId.length*2)]);
                    ji.push(fenshu[a+(resId.length*3)]);
                    ji.push(fenshu[a+(resId.length*4)]);
                } else if (resId[a] == 41) {
                    lian.push(fenshu[a]);
                    lian.push(fenshu[a+resId.length]);
                    lian.push(fenshu[a+(resId.length*2)]);
                    lian.push(fenshu[a+(resId.length*3)]);
                    lian.push(fenshu[a+(resId.length*4)]);
                }
            }

            // var a = 0;
            // var li1 = '<li id="kong"></li>';
            // $('.tmson').append($(li1));
            // for (var x = 0; x < resId.length; x++) {
            //     if (resId[x] == 37) {
            //         var de = '<li class="tm" id="de"><h1>德:</h1></li>';
            //         $('.tmson').append($(de));
            //     } else if (resId[x] == 38) {
            //         var neng = '<li class="tm" id="neng"><h1>能:</h1></li>';
            //         $('.tmson').append($(neng));
            //     } else if (resId[x] == 39) {
            //         var qin = '<li class="tm" id="qin"><h1>勤:</h1></li>';
            //         $('.tmson').append($(qin));
            //     } else if (resId[x] == 40) {
            //         var ji = '<li class="tm" id="ji"><h1>绩:</h1></li>';
            //         $('.tmson').append($(ji));
            //     } else if (resId[x] == 41) {
            //         var lian = '<li class="tm" id="lian"><h1>廉:</h1></li>';
            //         $('.tmson').append($(lian));
            //     }
            // };

            $api.GetUid(null, function (res) {
                var data = res.data;

                for (var a=0; a < data.length; a++) {
                    user.push(data[a].uname)
                }

                var j = 0;
                for (var y = 0; y < data.length; y++) {
                    if (data[y].uid == uid[j]) {
                        uname.push(data[y].uname);
                        j++;
                    };
                };

                myChart.setOption({
                    xAxis: {
                        data: uname
                    },
                    series: [
                        {
                            name: '德',
                            type: 'bar',
                            barGap: 0,
                            // label: labelOption,
                            data: de
                        },
                        {
                            name: '能',
                            type: 'bar',
                            // label: labelOption,
                            data: neng
                        },
                        {
                            name: '勤',
                            type: 'bar',
                            // label: labelOption,
                            data: qin
                        },
                        {
                            name: '绩',
                            type: 'bar',
                            // label: labelOption,
                            data: ji
                        },
                        {
                            name: '廉',
                            type: 'bar',
                            // label: labelOption,
                            data: lian
                        }
                    ]
                });

                // var li2 = '';
                //
                // $('.son').append($(li2));
                // for (var z = 0; z < uid.length; z++) {
                //     var li3 = '';
                //     for (var z1 = 0; z1 < resId.length; z1++) {
                //         // console.log(fenshu[a]);
                //         li3 += '<li><h1>' + fenshu[a] + '</h1></li>';
                //         a = a + 1;
                //     }
                //     for (var z2 = 0; z2 < 5 - resId.length; z2++) {
                //         li3 += '<li></li>';
                //     }
                //     $('.son .renyuan').eq(a1).append($(li3));
                //     a1=a1+1;
                // };
            });
        });



        // myChart.setOption(option);
    };
});
