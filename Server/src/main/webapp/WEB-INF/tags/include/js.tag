<%@tag pageEncoding="UTF-8"  %>
<%@ include file="../../taglibs.jsp" %>
<%@attribute name="paged" type="java.lang.Boolean" required="true"%>
<!-- jQuery -->
<script src="${ctx}/resources/js/jquery.js"></script>
<script src="${ctx}/resources/js/jquery.form.js"></script>
<script src="${ctx}/resources/js/jquery.metadata.js"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script src="${ctx}/resources/js/jquery.validate.messages_cn.js"></script>
<script src="${ctx}/resources/js/jquery.cookie-1.4.1.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${ctx}/resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${ctx}/resources/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${ctx}/resources/js/plugins/morris/raphael.min.js"></script>
<%--<script src="${ctx}/resources/js/plugins/morris/morris.min.js"></script>
<script src="${ctx}/resources/js/plugins/morris/morris-data.js"></script>--%>

<!---->
<script data-pace-options='{ "ajax": true }' src="${ctx}/resources/js/pace.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/pnotify.custom.min.js"></script>

<!--fancy box-->
<script type="text/javascript" src="${ctx}/resources/fancybox/jquery.fancybox.js?v=2.1.5"></script>
<script type="text/javascript" src="${ctx}/resources/fancybox/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<script type="text/javascript" src="${ctx}/resources/fancybox/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
<script type="text/javascript" src="${ctx}/resources/fancybox/helpers/jquery.fancybox-media.js?v=1.0.6"></script>

<!-- Custom Theme JavaScript -->
<script src="${ctx}/resources/js/sb-admin-2.js"></script>
<script src="${ctx}/resources/js/app.js"></script>
<script>
    $.scrollUp({
        animation: 'fade',
        activeOverlay: false,
        scrollImg: { active: true, type: 'background', src: '${ctx}/resources/img/top.png' }
    });

    // 加入验证
    $.validator.addMethod("decimal", function (value, element) {
        var decimal = /^-?\d+(\.\d{1,2})?$/;
        return this.optional(element) || (decimal.test(value));
    }, $.validator.format("小数位数后必须是两位!"));

    $('a').tooltip();

    <c:if test="${paged}">
    // 分页
    var options = {
        totalPage: '${pageRecords.totalPage}',
        pageNumber: '${pageRecords.pageNumber}',
        pagination: $('ul.pagination')
    };
    $.fn.pagination(options);
    </c:if>

    $('#sideSwitch').on('click' ,function(){
        $.fn.toggleSide.toggle();
    });

    // Remove it (later)
</script>