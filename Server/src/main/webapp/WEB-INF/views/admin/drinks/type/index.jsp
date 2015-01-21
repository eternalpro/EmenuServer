<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../../taglibs.jsp" %>
<layout:adminLayout title="酒类维护" menu="drinkstype" faIcon="fa-list" modal="true" paged="false">

    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <a class="btn btn-primary" href="${ctx}/admin/drinks/type/form" data-toggle="modal"
                       data-target="#adminModalLg"><i class="fa fa-plus"></i> 添加
                    </a>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 酒水分类
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>序号
                                        <a href="#" data-toggle="sort" data-sort="${sort}" data-url="${ctx}/admin/drinks/type">
                                            <i class="fa fa-sort-numeric-${sort} hander"></i>
                                        </a>
                                    </th>
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="record" varStatus="i">
                                    <tr>
                                        <td>${i.index + 1}</td>
                                        <td>${record.title}</td>
                                        <td>
                                            <a href="${ctx}/admin/drinks/type/form/${record.id}" class="btn btn-primary btn-sm"
                                               data-toggle="modal" data-target="#adminModalLg">编辑</a>
                                            <a href="${ctx}/admin/drinks/type/delete/${record.id}" class="btn btn-danger btn-sm"
                                               data-toggle="delete" data-confirm="确定删除记录吗？">删除</a>
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