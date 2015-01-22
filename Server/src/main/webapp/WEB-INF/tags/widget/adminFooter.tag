<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../taglibs.jsp" %>
<%@ attribute name="modal" type="java.lang.Boolean" description="是否包含modal" %>
<%@attribute name="modalclass" type="java.lang.String" required="false" %>
<footer style="background: #1D1F21">
    <hr>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 footer-below text-center margin40-tb">
                <p>© 2014 Origenal , Inc. Design By Eternal Pro</p>

                <p>Version: ${app.version}</p>
            </div>
        </div>
    </div>
</footer>
<c:if test="${modal}">
    <div class="modal fade" id="adminModalLg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none;">
        <div class="modal-dialog ${modalclass}">
            <div class="modal-content" id="adminModalLgContent">
            </div>
        </div>
    </div>
</c:if>