<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../../taglibs.jsp" %>
<%@ attribute name="title" type="java.lang.String" description="页面标题" %>
<%@ attribute name="faIcon" type="java.lang.String" description="菜单图标" %>
<%@ attribute name="menu" type="java.lang.String" description="页面当前菜单" %>
<%@ attribute name="paged" type="java.lang.Boolean" description="是否分页" %>
<%@ attribute name="modal" type="java.lang.Boolean" description="是否包含Modal,如果要异步弹出Modal对话框， 请设置为true" %>
<%@ attribute name="modalclass" type="java.lang.String" description="页面当modal样式" %>
<%@ attribute name="main" fragment="true" description="主体信息，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>
<%@ attribute name="css" fragment="true" description="需要引入的额外的css信息或自定义的css，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>
<%@ attribute name="js" fragment="true" description="需要引入的额外的js信息或自定义的js，注意：用jsp:attribute调用，不要把此标签设置到属性上" %>

<html>
<head>
    <include:meta/>
    <include:css/>
    <jsp:invoke fragment="css"/>
    <title>${title} - ${app.title}</title>
</head>
<body>

<div id="wrapper">

    <widget:adminNav menu="${menu}"/>


    <div id="page-wrapper">
        <div style="cursor: pointer; width: 20px;margin-left:-30px; margin-top:0px; margin-bottom: -4px; height: 15px; background: #F8F8F8; border-right: 1px solid #DBDEE0; border-bottom: 1px solid #DBDEE0;"
             id="sideSwitch">
            <i class="fa fa-angle-double-left" id="sideIcon" style="margin-left: 5px; margin-top: -4px;"></i>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header  animated bounce" id="xiaoshou"><i
                        class="fa ${faIcon} fa-fw"></i> ${title}</h1>
            </div>
        </div>
        <jsp:invoke fragment="main"/>
    </div>
</div>
<widget:adminFooter modal="${modal}" modalclass="${modalclass}"/>
<include:js paged="${paged}"/>
<jsp:invoke fragment="js"/>
</body>
</html>