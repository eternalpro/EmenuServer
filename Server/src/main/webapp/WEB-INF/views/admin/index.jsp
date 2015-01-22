<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../taglibs.jsp" %>
<layout:adminLayout title="餐厅概况" menu="index" faIcon="fa-dashboard">
    <jsp:attribute name="main">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-bar-chart-o fa-fw"></i> 销售业绩对比
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3">
                            昨日销售业绩：10,300,100 元
                        </div>
                        <div class="col-md-9">
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="70"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3">
                            今日销售业绩：5,300,100 元
                        </div>
                        <div class="col-md-9">
                            <div class="progress">
                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="40"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-4">
                            今日来访人数：12位
                        </div>
                        <div class="col-md-4">
                            翻台率：40%
                        </div>
                        <div class="col-md-4">
                            电子菜单连线：3/5
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-database fa-fw"></i> 当前已估清的菜品列表：
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-2">
                            小鸡炖蘑菇
                        </div>
                        <div class="col-md-2">
                            西红柿炒鸡蛋
                        </div>
                        <div class="col-md-2">
                            酸辣土豆丝
                        </div>
                        <div class="col-md-2">
                            干煸豆角
                        </div>
                        <div class="col-md-2">
                            干煸豆角
                        </div>
                        <div class="col-md-2">
                            干煸豆角
                        </div>
                        <div class="col-md-2">
                            干煸豆角
                        </div>
                        <div class="col-md-2">
                            干煸豆角
                        </div>

                        <div class="col-md-2">
                            干煸豆角
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    年销售百分比分析图
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div id="nianxiaoshou" style="height:400px"></div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    月销售业绩展示图
                </div>
                <div class="panel-body">
                    <div id="yuexiaoshou" style="height:400px"></div>
                </div>
            </div>
        </div>
    </div>
    </jsp:attribute>
    <jsp:attribute name="js">
    <script>
        (function () {
            // 年销售
            $('#nianxiaoshou').highcharts({                   //图表展示容器，与div的id保持一致
                hart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: 1,//null,
                    plotShadow: false
                },title: {
                    text: '2014年销售百分比分析图'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '占总销售额',
                    data: [
                        ['酒水',   50.0],
                        ['鲁菜',       20.0],
                        {
                            name: '川菜',
                            y: 12.8,
                            sliced: true,
                            selected: true
                        },
                        ['粤菜',    20.0],
                        ['主食',     10.0],
                    ]
                }]
            });

            // 月销售
            $('#yuexiaoshou').highcharts({
                title: {
                    text: '2014年度月销售业绩展示图',
                    x: -20 //center
                },
                subtitle: {
                    text: '展示2014年每月销售额度',
                    x: -20
                },
                xAxis: {
                    categories: ['1月', '2月', '3月', '4月', '5月', '6月','7月', '8月', '9月', '10月', '11月', '12月']
                },
                yAxis: {
                    title: {
                        text: '单位 (万元)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '°C'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '总销售额',
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                }, {
                    name: '净利润',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                }]
            });
            // 顶部提示
            $.fn.notify({type: 'info', text: '还差XX元就可突破昨天的业绩！请努力！', delay: 2000});
        })();
    </script>
    </jsp:attribute>
</layout:adminLayout>
