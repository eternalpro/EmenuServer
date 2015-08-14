<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../../taglibs.jsp" %>
<layout:adminLayout title="台号管理" menu="tables" faIcon="fa-desktop" modal="true" paged="false">

    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <form id="tableForm" class="form-inline" action="/admin/settings/tables/save" method="post">
                        <div class="form-group">
                            <label for="name">快速添加台号：</label>
                            <input type="text" class="form-control required" id="name" name="table.name"
                                   placeholder="请输入名称">
                        </div>
                        <button type="submit" class="btn btn-primary" id="saveBtn">添加</button>
                    </form>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 台号列表 <small class="text-danger">拖拽台号名称可以互换位置</small>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <c:forEach items="${tables}" var="t">
                                <div class="col-md-3 margin5-tb">
                                    <div draggable="true"
                                         ondragstart="dragStart(event)"
                                         ondrop="return dragDrop(event)"
                                         ondragenter="return false"
                                         ondragover="return false"  data-id="${t.id}">
                                        <span class="label label-primary" data-id="${t.id}">
                                                ${t.name}
                                        </span>
                                        <a href="/admin/settings/tables/delete/${t.id}" title="删除"
                                           data-toggle="confirm" data-id="${t.id}"
                                           class="text-danger btn btn-xs">
                                            X
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script>
            function dragStart(event){
                var $this = $(event.target);
                var id = $this.data('id');
                event.dataTransfer.setData('sid', id);
            }
            function dragDrop(event){
                var $this = $(event.target);
                var sid = event.dataTransfer.getData('sid');
                var tid = $this.data('id');

                $.get('/admin/settings/tables/changeOrder/' + sid + '-' + tid, function(data){
                    if (data == 'success') {
                        $.fn.notify({
                            type: 'success',
                            text: '操作成功！',
                            callback: function () {
                                location.reload();
                            },
                            delay: 50
                        });
                    } else {
                        $.fn.notify({type: 'error', text: data, delay: 3000})
                    }
                });
            }
            (function () {
                $('#name').focus();
                $('#tableForm').validate();
                $('#saveBtn').on('click', function () {
                    App.SubmitForm({selector: '#tableForm'});
                });


            })();
        </script>
    </jsp:attribute>
</layout:adminLayout>