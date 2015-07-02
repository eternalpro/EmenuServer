<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<layout:adminLayout title="订单管理" menu="orders" faIcon="fa-edit" modal="true" paged="true">
    <jsp:attribute name="css">
        <link rel="stylesheet" href="${ctx}/resources/css/datepicker3.css"/>
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="row">
                        <div class="col-lg-12 pull-right">
                            <form class="form-inline pull-right" role="form" action="${ctx}/admin/foods/${foodstypeid}">
                                <label class="control-label">起止日期：</label>

                                <div class="input-group" id="datepicker">
                                    <input type="text" class="form-control datetimepicker" name="start"/>
                                    <span class="input-group-addon">-</span>
                                    <input type="text" class="form-control datetimepicker" name="end"/>
                                </div>
                                <button class="btn btn-primary"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 菜品分类
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table width="100%">

                                <tbody>
                                <c:forEach items="${records}" var="record" varStatus="i">
                                    <tr>
                                        <td colspan="9">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <table style="width: 100%; font-size: 12px;">
                                                        <tr>
                                                            <td width="100"><strong>桌号: </strong>${record.tablenumber}</td>
                                                            <td width="150">
                                                                <strong>服务员: </strong>
                                                                    ${record.waitername}
                                                            </td>
                                                            <td width="80"><strong>人数: </strong>${record.peoplenumber} 人</td>
                                                            <td width="150"><strong>消费金额: </strong>${record.pricecount} 元</td>
                                                            <td width="200">
                                                                <strong>下单时间: </strong>
                                                                <fmt:formatDate value="${record.createtime}"
                                                                                pattern="yyyy-MM-dd HH:ss:mm"/>
                                                            </td>
                                                            <td width="200">
                                                                <strong>结账时间: </strong>
                                                                    ${record.paytime}
                                                            </td>
                                                            <td width="50"><widget:orderStatus status="${record.status}"/></td>
                                                            <td width="50">
                                                                <a class="btn btn-primary btn-xs" href="#"><i
                                                                        class="fa fa-print"></i>
                                                                    打印
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="panel-body">
                                                    <table class="table">
                                                        <c:forEach items="${record.orderItems}" var="item" varStatus="i">
                                                            <tr>
                                                                <td><strong>名称: </strong>${item.name}</td>
                                                                <td><strong>数量: </strong>${item.count}</td>
                                                                <td><strong>单价: </strong>${item.price}</td>
                                                            </tr>
                                                        </c:forEach>

                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <widget:adminPagination page="${pageRecords}"/>
        </div>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="${ctx}/resources/js/bootstrap-datepicker.js"></script>
        <script src="${ctx}/resources/js/locales/bootstrap-datepicker.zh-CN.js"></script>
        <script>
            (function () {
                $('input.datetimepicker').datepicker({
                    format: "yyyy-mm-dd",
                    language: "zh-CN",
                    autoclose: true,
                    todayBtn: true
                });
            })();
        </script>
    </jsp:attribute>
</layout:adminLayout>