<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<form role="form" action="${ctx}/admin/drinks/save" id="drinksForm" method="post" enctype="multipart/form-data">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
        </button>
        <h3 class="modal-title" id="myModalLabel"><c:choose><c:when
                test="${!empty(drinks)}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>酒水
            <small class="text-info">所属分类：${drinksType.title}</small>
        </h3>
    </div>
    <div class="modal-body">
        <c:if test="${!empty(drinks)}">
            <input type="hidden" name="drinks.id" value="${drinks.id}"/>
        </c:if>
        <div class="row">
            <div class="col-md-5 pad30-l">

                <div class="form-group">
                    <label for="typeSelect">选择分类：</label>
                    <select class="form-control" id="typeSelect" name="drinks.drinkstypeid">
                        <c:forEach items="${drinksTypes}" var="type">
                            <option value="${type.id}"
                                    <c:if test="${(!empty(drinks) && type.id eq drinks.drinkstypeid) || (empty(drinks) && type.id eq drinksType.id)}">selected </c:if> >${type.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <widget:adminFormGroupInput inputType="text"
                                            inputId="name"
                                            inputTitle="名称"
                                            inputClass="form-control {required: true, minlength: 2, maxlength: 10}"
                                            inputName="drinks.name"
                                            inputPlaceholder="输入酒水名称,2~10个字符"
                                            inputValue="${drinks.name}"/>

                        <div class="row">
                            <div class="form-group col-xs-5">
                                <label>价格：</label>
                                <input type="text" class="form-control {required: true, decimal: 2}"
                                       name="drinks.price"
                                       placeholder="保留2位小数"
                                       value="${drinks.price}">
                            </div>
                            <div class="form-group col-xs-4">
                                <label>单位：</label>
                                <select name="drinks.sellunit" class="form-control">
                                    <option <c:if test="${drinks.sellunit eq '瓶'}">selected</c:if>>瓶</option>
                                    <option <c:if test="${drinks.sellunit eq '听'}">selected</c:if>>听</option>
                                    <option <c:if test="${drinks.sellunit eq '打'}">selected</c:if>>打</option>
                                    <option <c:if test="${drinks.sellunit eq '扎'}">selected</c:if>>扎</option>
                                    <option <c:if test="${drinks.sellunit eq '杯'}">selected</c:if>>杯</option>
                                    <option <c:if test="${drinks.sellunit eq '壶'}">selected</c:if>>壶</option>
                                    <option <c:if test="${drinks.sellunit eq '盎司'}">selected</c:if>>盎司</option>
                                </select>
                            </div>
                        </div>

                <div class="form-group">
                    <label>其它属性(请根据此酒水特征勾选相关属性)：</label>

                    <c:forEach items="${drinksAttributesEnums}" var="attrs">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="attribute" value="${attrs.title}"
                                       <c:if test="${fn:contains(drinks.attribute, attrs.title)}">checked</c:if> > <span class="text-primary">${attrs.title}</span>
                            </label>
                        </div>
                    </c:forEach>
                </div>

            </div>
            <div class="col-md-7 pad20-l">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="smallpath">上传缩略图文件：</label>
                            <input type="file" class="<c:if test="${empty(drinks)}">required</c:if>" name="smallpath"
                                   id="smallpath">

                            <p class="help-block text-danger">大小为xx，格式为png.</p>

                            <div class="thumbnail">
                                <c:choose>
                                    <c:when test="${!empty(drinks)&&!empty(drinks.smallimagepath)}">
                                        <img id="smallImage" src="${ctx}${drinks.smallimagepath}">
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
                            <input type="file" class="<c:if test="${empty(drinks)}">required</c:if>" name="largepath"
                                   id="largepath">

                            <p class="help-block text-danger">大小为xx，格式为png.</p>

                            <div class="thumbnail">
                                <c:choose>
                                    <c:when test="${!empty(drinks)&&!empty(drinks.bigimagepath)}">
                                        <img id="largeImage" src="${ctx}${drinks.bigimagepath}">
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
        $('#drinksForm').validate();
        $('#saveBtn').on('click', function () {
            App.SubmitForm({selector: '#drinksForm'});
        });
        $("#smallpath").uploadPreview({Img: "smallImage"});
        $("#largepath").uploadPreview({Img: "largeImage"});

        /**
         * 添加价格条目
         */
        $('a.add-price-item').on('click', function (e) {
            e.preventDefault();
            var $this = $(this);
            var $currentRow = $this.closest('div');
            var $copyCurrentRow = $currentRow.clone(true);
            $currentRow.after($copyCurrentRow);
        });

        $('a.remove-price-item').on('click', function (e) {
            e.preventDefault();
            var $this = $(this);
            if ($('.remove-price-item').length > 1) {
                var $currentRow = $this.closest('div');
                $currentRow.remove();
            }
        });
    })();
</script>