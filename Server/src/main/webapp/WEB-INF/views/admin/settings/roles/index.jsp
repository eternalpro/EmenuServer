<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../../taglibs.jsp" %>
<layout:adminLayout title="角色列表" menu="roles" faIcon="fa-users" modal="true" paged="false">

    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 角色列表
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>标识</th>
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="record" varStatus="i">
                                    <tr>
                                        <td>${i.index + 1}</td>
                                        <td>${record.name}</td>
                                        <td>${record.title}</td>
                                        <td>
                                            <a href="${ctx}/admin/settings/roles/usersmodal/${record.id}" class="btn btn-primary btn-sm"
                                               data-toggle="modal" data-target="#adminModalLg">查看角色下的所有用户</a>
                                            <c:choose>
                                                <c:when test="${record.isenable}">
                                                    <a href="${ctx}/admin/settings/roles/isenable/${record.id}-0" class="btn btn-danger btn-sm">禁用</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${ctx}/admin/settings/roles/isenable/${record.id}-1" class="btn btn-success btn-sm">启用</a>
                                                </c:otherwise>
                                            </c:choose>
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

    </jsp:attribute>
</layout:adminLayout>