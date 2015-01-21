<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/settings/users/save" id="userForm" method="post">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel"><c:choose><c:when
                test="${!empty(user)}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>用户信息
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(user)}">
            <input type="hidden" name="user.id" value="${user.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-12 pad50-lr">
                <widget:adminFormGroupInput inputType="text"
                                            inputId="name"
                                            inputTitle="用户名"
                                            inputClass="form-control {required: true, minlength: 1, maxlength: 15}"
                                            inputName="user.name"
                                            inputPlaceholder="输入用户名,最多15个字符"
                                            inputValue="${user.name}"/>

                <div class="form-group">
                    <label for="role">角色：</label>
                    <select class="form-control" id="role" name="user.rolename">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.name}"
                                    <c:if test="${user.rolename eq role.name}">selected </c:if> >${role.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <p class="help-block text-danger">初始密码：12345678</p>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" id="saveBtn">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</form>
<script>
    (function () {
        $('#userForm').validate();
        $('#saveBtn').on('click', function () {
            App.SubmitForm({selector: '#userForm'});
        });

    })();
</script>