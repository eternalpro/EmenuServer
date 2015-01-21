<%@tag pageEncoding="UTF-8"  %>
<%@ include file="../../../taglibs.jsp" %>
<%@attribute name="inputType" type="java.lang.String" required="true" description="input type 属性" %>
<%@attribute name="inputId" type="java.lang.String" required="true" description="input id 属性" %>
<%@attribute name="inputTitle" type="java.lang.String" required="true" description="标题 属性" %>
<%@attribute name="inputClass" type="java.lang.String" required="true" description="input class 属性" %>
<%@attribute name="inputName" type="java.lang.String" required="true" description="input name 属性" %>
<%@attribute name="inputPlaceholder" type="java.lang.String" required="true" description="input placeholder 属性" %>
<%@attribute name="inputValue" type="java.lang.String" required="true" description="input value 属性" %>
<div class="form-group">
    <label for="${inputId}">${inputTitle}：</label>
    <input type="${inputType}" class="${inputClass}"
           name="${inputName}" id="${inputId}"
           placeholder="${inputPlaceholder}" value="${inputValue}">
</div>