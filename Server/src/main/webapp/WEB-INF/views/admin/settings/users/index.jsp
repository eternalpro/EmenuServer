<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../taglibs.jsp" %>
<layout:adminLayout title="用户管理" menu="users" faIcon="fa-user" modal="true" paged="true">

    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="col-lg-3 pull-right">
                        <form class="form-inline" role="form" action="${ctx}/admin/settings/users">
                            <div class="input-group">
                                <input type="text" class="form-control" name="key" value="${key}"
                                       placeholder="输入用户名进行查询">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </form>
                    </div>

                    <a class="btn btn-primary" href="${ctx}/admin/settings/users/form" data-toggle="modal"
                       data-target="#adminModalLg"><i class="fa fa-plus"></i> 添加
                    </a>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 用户列表
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width="100">序号
                                        <a href="#" data-toggle="sort" data-sort="${sort}" data-url="${ctx}/admin/settings/users">
                                            <i class="fa fa-sort-numeric-${sort} hander"></i>
                                        </a>
                                    </th>
                                    <th width="250">用户名</th>
                                    <th width="250">角色</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageRecords.list}" var="record" varStatus="i">
                                    <tr>
                                        <td>${i.index + 1}</td>
                                        <td>${record.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${record.rolename eq 'Manager'}">店长</c:when>
                                                <c:when test="${record.rolename eq 'Chef'}">厨师长</c:when>
                                                <c:when test="${record.rolename eq 'CEO'}">董事长</c:when>
                                                <c:when test="${record.rolename eq 'Waiter'}">服务员</c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="${ctx}/admin/settings/users/form/${record.id}" class="btn btn-primary btn-sm"
                                               data-toggle="modal" data-target="#adminModalLg">编辑</a>
                                            <a href="${ctx}/admin/settings/users/delete/${record.id}" class="btn btn-danger btn-sm"
                                               data-toggle="delete" data-confirm="确定删除记录吗？">删除</a>

                                            <a href="${ctx}/admin/settings/users/resetPassword/${record.id}" data-toggle="confirm" data-confirm="确认要将此用户的密码重置为12345678吗?" class="btn btn-success">重置密码</a>
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
    </jsp:attribute>
</layout:adminLayout>