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
                        <i class="fa fa-list fa-fw"></i> 订单列表
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
                                                    <table style="width: 100%; font-size: 12px; ">
                                                        <tr>
                                                            <td width="180"><strong>订单编号: </strong><a href="#"
                                                                                                      class="toggle-detail">${record.orderno}</a>
                                                            </td>
                                                            <td width="60"><strong>桌号: </strong>${record.tablenumber}
                                                            </td>
                                                            <td width="150">
                                                                <strong>服务员: </strong>
                                                                    ${record.waitername}
                                                            </td>
                                                            <td width="60"><strong>人数: </strong>${record.peoplenumber}
                                                            </td>
                                                            <td width="150"><strong>消费金额: </strong>${record.pricecount}
                                                                元
                                                            </td>
                                                            <td width="220">
                                                                <strong>下单时间: </strong>
                                                                <fmt:formatDate value="${record.createtime}"
                                                                                pattern="yyyy-MM-dd HH:ss:mm"/>
                                                            </td>
                                                            <td width="220">
                                                                <strong>结账时间: </strong>
                                                                <fmt:formatDate value="${record.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                            </td>
                                                            <td width="60"><widget:orderStatus
                                                                    status="${record.status}"/>
                                                            </td>
                                                            <c:if test="${record.status eq 'UNPAY'}">
                                                                <td width="50">
                                                                    <a href="${ctx}/admin/orders/pay/${record.id}"
                                                                       class="btn btn-primary btn-xs"
                                                                       data-toggle="confirm"
                                                                       data-confirm="确认要对订单进行结账吗?">结账</a>
                                                                </td>
                                                            </c:if>
                                                            <td width="50">

                                                                <a class="btn btn-default btn-xs" data-toggle="action" href="${ctx}/admin/orders/print/${record.id}"><i
                                                                        class="fa fa-print"></i>
                                                                    打印
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="panel-body" style="display: none;">
                                                    <c:forEach items="${record.orderItems}" var="item" varStatus="i">
                                                        <div class="col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <img src="${ctx}${item.imagePath}"
                                                                     style="height: 150px;">

                                                                <div class="caption">
                                                                    <p>
                                                                        <widget:productType type="${item.type}"/>
                                                                            ${item.name}
                                                                        <c:if test="${item.status eq 1}">
                                                                            <span class="label label-success">+</span>
                                                                        </c:if>
                                                                    </p>

                                                                    <p>
                                                                        <strong>数量: </strong>${item.count}
                                                                        <strong>单价: </strong><span
                                                                            class="text-danger">${item.price}</span> 元
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
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

                $('a.toggle-detail').on('click', function (e) {
                    e.preventDefault();
                    var $this = $(this);
                    $this.closest('.panel-heading').siblings('.panel-body').slideToggle(300);
                })
            })();
        </script>
    </jsp:attribute>
</layout:adminLayout>