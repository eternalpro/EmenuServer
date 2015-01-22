<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../taglibs.jsp" %>
<%@attribute name="page" type="com.jfinal.plugin.activerecord.Page" required="true"%>
<div class="col-md-8">
    <nav>
        <ul class="pagination">

        </ul>
    </nav>
</div>
<div class="col-md-4">
    <p class="text-right margin20-t">当前第 ${page.pageNumber} 页&nbsp;|&nbsp;共 ${page.totalPage} 页</p>
</div>