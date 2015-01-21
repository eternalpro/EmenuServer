<%@tag pageEncoding="UTF-8"  %>
<%@ include file="../../../taglibs.jsp" %>
<%@attribute name="textareaId" type="java.lang.String" required="true" description="textarea id 属性" %>
<%@attribute name="textareaTitle" type="java.lang.String" required="true" description="标题 属性" %>
<%@attribute name="textareaClass" type="java.lang.String" required="true" description="textarea class 属性" %>
<%@attribute name="textareaName" type="java.lang.String" required="true" description="textarea name 属性" %>
<%@attribute name="textareaPlaceholder" type="java.lang.String" required="true" description="textarea placeholder 属性" %>
<%@attribute name="textareaValue" type="java.lang.String" required="true" description="textarea value 属性" %>
<div class="form-group">
    <label for="${textareaId}">${textareaTitle}：</label>
    <textarea class="${textareaClass}" name="${textareaName}" id="${textareaId}"
              placeholder="${textareaPlaceholder}">${textareaValue}</textarea>
</div>