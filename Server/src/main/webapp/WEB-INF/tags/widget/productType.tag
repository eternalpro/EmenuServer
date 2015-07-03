<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../taglibs.jsp" %>
<%@ attribute name="type" type="java.lang.String" description="食物类别" %>
<small>
    <c:choose>
        <c:when test="${type eq 'DRINK'}">
            <span class="label label-info">酒水</span>
        </c:when>
        <c:when test="${type eq 'FOOD'}">
            <span class="label label-primary">菜品</span>
        </c:when>
        <c:otherwise>
            <span class="label label-default">未知</span>
        </c:otherwise>
    </c:choose>
</small>