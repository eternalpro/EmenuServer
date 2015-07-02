<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../taglibs.jsp" %>
<%@ attribute name="status" type="java.lang.String" description="订单状态" %>
<div>
    <c:choose>
        <c:when test="${status eq 'UNPAY'}">
            <span class="label label-danger">未付款</span>
        </c:when>
        <c:when test="${status eq 'PAYED'}">
            <span class="label label-primary">已付款</span>
        </c:when>
        <c:when test="${status eq 'FINISH'}">
            <span class="label label-success">已完结</span>
        </c:when>
        <c:otherwise>
            <span class="label label-default">未知</span>
        </c:otherwise>
    </c:choose>
</div>