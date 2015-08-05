<%@tag pageEncoding="UTF-8" %>
<%@tag trimDirectiveWhitespaces="true" %>
<%@ include file="../../taglibs.jsp" %>
<%@ attribute name="balance" type="java.lang.Double" description="金额" %>
<fmt:formatNumber value="${balance}" maxFractionDigits="2" type="currency"/>