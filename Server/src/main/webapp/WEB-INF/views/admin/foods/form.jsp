<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/foods/save" id="foodForm" method="post" enctype="multipart/form-data">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel"><c:choose><c:when
                test="${!empty(food)}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>菜品
            <small class="text-info">所属分类：${foodsType.title}</small>
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(food)}">
            <input type="hidden" name="food.id" value="${food.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-5 pad30-l">

                <div class="form-group">
                    <label for="typeSelect">选择分类：</label>
                    <select class="form-control" id="typeSelect" name="food.foodstypeid">
                        <c:forEach items="${foodsTypes}" var="type">
                            <option value="${type.id}"
                                    <c:if test="${(!empty(food) && food.foodstypeid eq type.id) || (empty(food) && foodsType.id eq type.id)}">selected </c:if> >${type.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <widget:adminFormGroupInput inputType="text"
                                            inputId="name"
                                            inputTitle="名称"
                                            inputClass="form-control {required: true, minlength: 2, maxlength: 7}"
                                            inputName="food.name"
                                            inputPlaceholder="输入菜品名称,2~7个字符"
                                            inputValue="${food.name}"/>

                <widget:adminFormGroupInput inputType="text"
                                            inputId="price"
                                            inputTitle="价格"
                                            inputClass="form-control {required: true, decimal: 2}"
                                            inputName="food.price"
                                            inputPlaceholder="输入菜品价格,小数点后最多保留2位"
                                            inputValue="${food.price}"/>

                <widget:adminFormGroupInput inputType="text"
                                            inputId="flavour"
                                            inputTitle="口味"
                                            inputClass="form-control required"
                                            inputName="food.flavour"
                                            inputPlaceholder="输入菜品的口味,2~10个字符"
                                            inputValue="${food.flavour}"/>

                <widget:adminFormGroupTextarea textareaId="yongliao"
                                               textareaTitle="用料"
                                               textareaClass="form-control required"
                                               textareaName="food.yongliao"
                                               textareaPlaceholder="输入菜品用料"
                                               textareaValue="${food.yongliao}"/>

                <widget:adminFormGroupTextarea textareaId="memo"
                                               textareaTitle="介绍"
                                               textareaClass="form-control"
                                               textareaName="food.memo"
                                               textareaPlaceholder=""
                                               textareaValue="${food.memo}"/>

                <div class="form-group">
                    <label>其它属性(请根据此菜品特征勾选相关属性)：</label>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="food.issu" value="t"
                                   <c:if test="${food.issu eq 't'}">checked </c:if> > <span class="text-danger">素菜</span>
                        </label>
                    </div>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="food.isqingzhen" value="t"
                                   <c:if test="${food.isqingzhen eq 't'}">checked </c:if> > <span
                                class="text-primary">清真</span>
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="food.isliang" value="t"
                                   <c:if test="${food.isliang eq 't'}">checked </c:if> > <span
                                class="text-default">凉菜</span>
                        </label>
                    </div>
                </div>

            </div>
            <div class="col-md-7 pad20-l">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="smallpath">上传缩略图文件：</label>
                            <input type="file" class="<c:if test="${empty(food)}">required</c:if>" name="smallpath"
                                   id="smallpath">

                            <p class="help-block text-danger">大小为xx，格式为png.</p>

                            <div class="thumbnail">
                                <c:choose>
                                    <c:when test="${!empty(food)&&!empty(food.smallimagepath)}">
                                        <img id="smallImage" src="${ctx}${food.smallimagepath}">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="smallImage" src="${ctx}/resources/img/smallcover.png">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="largepath">上传展示图文件：</label>
                            <input type="file" class="<c:if test="${empty(food)}">required</c:if>" name="largepath"
                                   id="largepath">

                            <p class="help-block text-danger">大小为xx，格式为png.</p>

                            <div class="thumbnail">
                                <c:choose>
                                    <c:when test="${!empty(food)&&!empty(food.bigimagepath)}">
                                        <img id="largeImage" src="${ctx}${food.bigimagepath}">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="largeImage" src="${ctx}/resources/img/largecover.png">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" id="saveBtn">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</form>
<script>
    (function () {
        $('#foodForm').validate();
        $('#saveBtn').on('click', function () {
            App.SubmitForm({selector: '#foodForm'});
        });
        $("#smallpath").uploadPreview({Img: "smallImage"});
        $("#largepath").uploadPreview({Img: "largeImage"});
    })();
</script>