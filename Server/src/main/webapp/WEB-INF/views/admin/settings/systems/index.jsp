<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../../taglibs.jsp" %>
<layout:adminLayout title="参数管理" menu="systems" faIcon="fa-globe" modal="true" paged="false">
    <jsp:attribute name="css">
        <link rel="stylesheet" href="${ctx}/resources/umeditor/themes/default/css/umeditor.min.css"/>
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="col-lg-3 pull-right">
                        <form class="form-inline" role="form" action="${ctx}/admin/settings/systems">
                            <div class="input-group">
                                <input type="text" class="form-control" name="key" value="${key}"
                                       placeholder="输入参数名进行查询">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </form>
                    </div>

                    <a class="btn btn-primary" href="${ctx}/admin/settings/systems/form" data-toggle="modal"
                       data-target="#adminModalLg"><i class="fa fa-plus"></i> 添加
                    </a>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 参数列表
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width="100">参数名</th>
                                    <th width="200">参数值</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="record" varStatus="i">
                                    <tr data-toggle="mouse-hover" data-target="#hover-div-${record.id}">
                                        <td>
                                                ${record.key}
                                            <div id="hover-div-${record.id}" class="hide">
                                                <a href="${ctx}/admin/settings/systems/form/${record.id}"
                                                   class="link-info"
                                                   data-toggle="modal" data-target="#adminModalLg">编辑</a>
                                                |
                                                <a href="${ctx}/admin/settings/systems/delete/${record.id}" class="link-danger"
                                                   data-toggle="delete" data-confirm="确定删除记录吗？">删除</a>
                                            </div>
                                        </td>
                                        <td>
                                            ${record.value}
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
        <script src="${ctx}/resources/umeditor/umeditor.min.js"></script>
        <script src="${ctx}/resources/umeditor/umeditor.config.js"></script>
        <script>
            (function(){
                //hide完毕前执行
                $('#adminModalLg').on('hidden.bs.modal', function () {
                    // 关闭Dialog前移除编辑器
                    UM.getEditor('container').destroy();
                });
            })();
        </script>
    </jsp:attribute>
</layout:adminLayout>