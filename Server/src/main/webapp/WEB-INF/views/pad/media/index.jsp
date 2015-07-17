<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<img src="/resources/img/media.png" id="mediaImg">
<script>
    (function(){
        document.querySelector('#mediaImg').addEventListener('click', function(){
           alert('你点击了图片！')
        });
    })();
</script>
</body>
</html>
