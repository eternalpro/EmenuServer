<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/foods/type/save" id="foodsTypeForm" method="post">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel">
            角色{<label class="text-primary">${role.title}</label>}下的用户列表
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(foodsType)}">
            <input type="hidden" name="foodsType.id" value="${foodsType.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-12 pad50-lr">
                <table class="table">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${user.name}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</form>
<script>
    (function () {

    })();
</script>