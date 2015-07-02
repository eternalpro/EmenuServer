<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../taglibs.jsp" %>
<%@attribute name="menu" type="java.lang.String" required="true" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; padding: 10px 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${ctx}/admin"><i class="fa fa-desktop"></i> ${app.title}</a>
    </div>
    <div class="pull-right margin15-t">
        欢迎登录：<i class="fa fa-user fa-fw"></i> ${admin.name}
        |
        <a href="${ctx}/admin/settings/users/password/${admin.id}" data-toggle="modal"
           data-target="#passwordModal">
            <i class="fa fa-gear fa-fw"></i>
            修改密码
        </a>
        <a href="${ctx}/admin/logout"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
    </div>
    <widget:adminSideBar currentMenu="${menu}"/>
    <!-- sidebar end -->

    <div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <form action="${ctx}/admin/settings/users/savepassword" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">修改密码</h4>
                    </div>
                    <div class="modal-body">
                        <widget:adminFormGroupInput inputType="password"
                                                    inputId="oldpassword"
                                                    inputTitle="初始密码"
                                                    inputClass="form-control {required: true, minlength: 1, maxlength: 15}"
                                                    inputName="oldpassword"
                                                    inputPlaceholder="请输入初始密码"
                                                    inputValue=""/>
                        <widget:adminFormGroupInput inputType="password"
                                                    inputId="password"
                                                    inputTitle="新密码"
                                                    inputClass="form-control {required: true, minlength: 1, maxlength: 15}"
                                                    inputName="password"
                                                    inputPlaceholder="请输入新密码"
                                                    inputValue=""/>
                        <widget:adminFormGroupInput inputType="password"
                                                    inputId="confirm_password"
                                                    inputTitle="确认密码"
                                                    inputClass="form-control {required: true, minlength: 1, maxlength: 15}"
                                                    inputName="confirm_password"
                                                    inputPlaceholder="请再次输入新密码"
                                                    inputValue=""/>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">确认</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>