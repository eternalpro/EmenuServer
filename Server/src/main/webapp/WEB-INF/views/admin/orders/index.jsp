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
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>序号
                                        <a href="#" data-toggle="sort" data-sort="${sort}"
                                           data-url="${ctx}/admin/orders">
                                            <i class="fa fa-sort-numeric-${sort} hander"></i>
                                        </a>
                                    </th>
                                    <th>桌号</th>
                                    <th>人数</th>
                                    <th>金额</th>
                                    <th>下单时间</th>
                                    <th>结账时间</th>
                                    <th>服务员</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="record" varStatus="i">
                                    <tr>
                                        <td>${i.index + 1}</td>
                                        <td>${record.tablenumber}</td>
                                        <td>${record.peoplenumber} 人</td>
                                        <td>${record.pricecount} 元</td>
                                        <td>
                                            <fmt:formatDate value="${record.createtime}" pattern="yyyy-MM-dd HH:ss:mm"/>
                                        </td>
                                        <td>
                                                ${record.paydate}${record.paytime}
                                        </td>
                                        <td>
                                                ${record.waitername}
                                        </td>
                                        <td><widget:orderStatus status="${record.status}"/></td>
                                        <td>
                                            <a class="btn btn-primary btn-xs" href="#" title="订单记录总数：5"> 详情 <span
                                                    class="badge">4</span></a>
                                            <a class="btn btn-default btn-xs" href="#"><i class="fa fa-print"></i>
                                                打印</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
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