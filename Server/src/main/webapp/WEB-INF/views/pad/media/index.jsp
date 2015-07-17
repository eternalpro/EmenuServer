<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <include:css />
</head>
<body style="background: rgba(0, 0, 0, 0)">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <img src="/resources/img/media.png" style="width: 100%; height: 100%;" id="img"/>
        </div>
    </div>
</div>
<script>
    (function(){
        document.querySelector('#img').addEventListener('click', function(){
            alert('你点击了图片！');
        })
    })();
</script>
</body>
</html>
