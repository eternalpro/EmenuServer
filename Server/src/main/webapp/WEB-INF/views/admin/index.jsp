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
                            昨日销售业绩：<widget:formatMoney balance="${yesterdayResult}"/> 元
                        </div>
                        <div class="col-md-9">
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" role="progressbar"
                                     aria-valuenow="${yesterdayResult}"
                                     aria-valuemin="0" aria-valuemax="${todayResult + yesterdayResult}"
                                     style="width: ${yesterdayResult / (todayResult + yesterdayResult) * 100}%">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3">
                            今日销售业绩：<widget:formatMoney balance="${todayResult}"/> 元
                        </div>
                        <div class="col-md-9">
                            <div class="progress">
                                <div class="progress-bar progress-bar-danger" role="progressbar"
                                     aria-valuenow="${todayResult}"
                                     aria-valuemin="0" aria-valuemax="${todayResult + yesterdayResult}"
                                     style="width: ${todayResult / (todayResult + yesterdayResult) * 100}%">
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
                            今日来访人数：${visitorCount} 位
                        </div>
                        <div class="col-md-4">
                            翻台率：<fmt:formatNumber value="${rateOfTableTurn}" type="percent"/>
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
                        <c:forEach items="${clearFoods}" var="food">
                            <div class="col-md-2">
                                    ${food.name}
                            </div>
                        </c:forEach>
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
                    plotBorderWidth: 1,
                    plotShadow: false
                }, title: {
                    text: new Date().getFullYear() + '年销售百分比分析图'
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
                    data: ${yearSales}
                }]
            });

            // 月销售
            $('#yuexiaoshou').highcharts({
                title: {
                    text: new Date().getFullYear() + '年度月销售业绩展示图',
                    x: -20
                },
                subtitle: {
                    text: '展示' + new Date().getFullYear() + '年每月销售额度',
                    x: -20
                },
                xAxis: {
                    categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                },
                yAxis: {
                    title: {
                        text: '单位 (元)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '总销售额',
                    data: ${monthSales}
                }]
            });

            // 顶部提示
            var yesterdayResult = Number('${yesterdayResult}');
            var todayResult = Number('${todayResult}');
            if (yesterdayResult > todayResult) {
                $.fn.notify({
                    type: 'info',
                    text: '还差 <strong>' + (yesterdayResult - todayResult) + '</strong> 元就可突破昨天的业绩！请努力！',
                    delay: 4000
                });
            }
            if (yesterdayResult < todayResult) {
                $.fn.notify({
                    type: 'error',
                    text: '已突破昨天的业绩 <strong>' + (todayResult - yesterdayResult) + '</strong> 元！请勉励！',
                    delay: 4000
                });
            }
            if (yesterdayResult == todayResult) {
                $.fn.notify({
                    type: 'warning',
                    text: '与昨天的业绩旗鼓相当，请继续努力！',
                    delay: 4000
                });
            }
        })();
    </script>
    </jsp:attribute>
</layout:adminLayout>
