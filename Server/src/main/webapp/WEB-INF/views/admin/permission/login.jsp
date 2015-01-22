<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<html>
<head>
    <include:meta />
    <include:css />
    <style>
        body{
            background: #585254;
            font-family: "Helvetica Neue", Helvetica, Microsoft Yahei, Hiragino Sans GB, WenQuanYi Micro Hei, sans-serif;
        }
        .form-panel {
            background: #ffffff;
            border-radius: 6px;
            padding: 30px 0px;
            margin-top: 20px;
            box-shadow:0px 0px 50px #000;
        }
    </style>
    <title>登录 - ${app.title}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <!-- bounceInDown / shake -->
            <div class="login-panel pad50-lr animated ${animate}">
                <h1 class="text-white  text-center pad20-b"><i class="fa fa-desktop"></i> ${app.title}</h1>
                <div class="form-panel">
                    <form role="form" action="${ctx}/admin/dologin" id="loginForm" method="post">
                        <fieldset>
                            <c:if test="${!empty(error)}">
                                <p class="text-danger col-sm-offset-1 col-sm-10" role="alert" >
                                    <strong>${error}</strong>
                                </p>
                            </c:if>
                            <h3 class="col-sm-offset-1 col-sm-9 margin20-b">请输入用户名和密码</h3>
                            <div class="form-group col-sm-offset-1 col-sm-10">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-user"></i> </div>
                                    <input class="form-control" type="text" placeholder="用户名" name="username" id="username">
                                </div>
                            </div>
                            <div class="form-group col-sm-offset-1 col-sm-10">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-lock"></i> </div>
                                    <input class="form-control" type="password" placeholder="密码" name="password" id="password">
                                </div>
                            </div>

                            <div class="form-group col-sm-offset-1 col-sm-10">
                                <label class="">
                                    <input name="remember" type="checkbox"> 记住密码
                                </label>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="text-white text-center margin20-t animated pulse" style="color: #979793">
                    <span>© 2014 Origenal , Inc.</span>
                    <span>Design By Eternal Pro</span>
                </div>
            </div>
        </div>
    </div>
</div>
<include:js paged="false"/>
<script>
    (function(){
        $('#loginForm').validate();
    })();
</script>
</body>
</html>
