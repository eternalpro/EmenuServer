<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/drinks/type/save" id="drinksTypeForm" method="post">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel"><c:choose><c:when
                test="${!empty(drinksType)}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>酒水分类
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(drinksType)}">
            <input type="hidden" name="drinksType.id" value="${drinksType.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-12 pad50-lr">
                <widget:adminFormGroupInput inputType="text"
                                            inputId="title"
                                            inputTitle="分类名称"
                                            inputClass="form-control {required: true, minlength: 2, maxlength: 10}"
                                            inputName="drinksType.title"
                                            inputPlaceholder="输入酒水分类,2~10个字符"
                                            inputValue="${drinksType.title}"/>


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
        $('#drinksTypeForm').validate();
        $('#saveBtn').on('click', function () {
            App.SubmitForm({selector: '#drinksTypeForm'});
        });

    })();
</script>