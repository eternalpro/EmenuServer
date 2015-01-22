<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/settings/systems/save" id="systemForm" method="post">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel"><c:choose><c:when
                test="${!empty(system)}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>参数信息
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(system)}">
            <input type="hidden" name="systemInfo.id" value="${system.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-12 pad50-lr">
                <widget:adminFormGroupInput inputType="text"
                                            inputId="key"
                                            inputTitle="参数名"
                                            inputClass="form-control {required: true}"
                                            inputName="systemInfo.key"
                                            inputPlaceholder="输入参数名,最多15个字符"
                                            inputValue="${system.key}"/>

                <div class="form-group">
                    <label for="container">参数值：</label>
                    <%--<textarea class="form-control" name="systemInfo.value" rows="3" id="value">${system.value}</textarea>--%>
                    <script type="text/plain" id="container" name="systemInfo.value" style="height:140px">${system.value}</script>
                </div>
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
        $('#systemForm').validate();
        $('#saveBtn').on('click', function () {
            App.SubmitForm({selector: '#systemForm'});
        });

        UM.getEditor('container', {
            toolbars: [
                ['fullscreen', 'source', 'undo', 'redo', 'bold']
            ],
            autoHeightEnabled: true,
            autoFloatEnabled: true
        });
    })();
</script>