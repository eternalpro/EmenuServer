<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../taglibs.jsp" %>
<layout:adminLayout title="菜品维护" menu="foods" faIcon="fa-cutlery" modal="true" paged="true" modalclass="modal-lg">

    <jsp:attribute name="css">
        <link rel="stylesheet" href="${ctx}/resources/bootstrap-select/css/bootstrap-select.min.css"/>
    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="row">
            <div class="col-md-12">
                <div class="well">
                    <div class="col-lg-3 pull-right">
                        <form class="form-inline" role="form" action="${ctx}/admin/foods/${foodstypeid}">
                            <div class="input-group">
                                <input type="text" class="form-control" name="key" value="${key}"
                                       placeholder="输入名称进行查询">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </form>
                    </div>

                    <select class="selectpicker pull-right" data-style="btn-info dropup" data-live-search="true"
                            id="searchPicker"
                            data-size="5" data-header="选择分类(可查询)">
                        <option value="">全部</option>
                        <c:forEach items="${foodsTypes}" var="type">
                            <option value="${type.id}" <c:if test="${foodstypeid eq type.id}">selected </c:if> >${type.title}</option>
                        </c:forEach>
                    </select>
                    <a class="btn btn-primary" href="${ctx}/admin/foods/form?foodstypeid=${foodstypeid}" id="addBtn" data-toggle="modal"
                       data-target="#adminModalLg">
                        <i class="fa fa-plus"></i> 添加
                    </a>

                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle"
                                data-toggle="dropdown">
                            批量操作
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right" role="menu">
                            <li><a href="${ctx}/admin/foods/delete" id="deleteAll"><i class="fa fa-times"></i> 删除</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-cloud-upload"></i> 导入</a>
                            </li>
                            <li><a href="#"><i class="fa fa-cloud-download"></i> 导出</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-list fa-fw"></i> 菜品列表
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width="50">
                                        <input type="checkbox" data-toggle="check" data-target=".check"/>
                                        <a href="#" data-toggle="sort" data-sort="${sort}" data-url="${ctx}/admin/foods">
                                            <i class="fa fa-sort-numeric-${sort} hander"></i>
                                        </a>
                                    </th>
                                    <th>
                                        预览

                                    </th>
                                    <th width="160">名称</th>
                                    <th width="100">分类</th>
                                    <th width="80">价格</th>
                                    <th width="100">口味</th>
                                    <th width="100">用料</th>
                                    <th width="80">点击量</th>
                                    <th width="80">属性</th>
                                    <th width="60">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageRecords.list}" var="food">
                                    <tr>
                                        <th>
                                            <input class="check" type="checkbox" name="ids" value="${food.id }"/>
                                        </th>
                                        <th>
                                            <a href="${ctx}${food.bigimagepath}" class="fancybox" data-toggle="tooltip"
                                               data-placement="top" title="点击查看展示图">
                                                <img src="${ctx}${food.smallimagepath}"
                                                     style="width: 60px; height: 50px;"/>
                                            </a>
                                        </th>
                                        <td data-toggle="mouse-hover" data-target="#hover-div-${food.id}">
                                            ${food.name}
                                            <div id="hover-div-${food.id}" class="hide">
                                                <a href="${ctx}/admin/foods/form/${food.id}?foodstypeid=${food.foodstypeid}" class="link-info"
                                                   data-toggle="modal" data-target="#adminModalLg">编辑</a>
                                                |
                                                <a href="${ctx}/admin/foods/delete/${food.id}" class="link-danger"
                                                   data-toggle="delete" data-confirm="确定删除记录吗？">删除</a>

                                            </div>
                                        </td>
                                        <td>
                                            <strong class="text-primary">${food.typetitle}</strong>
                                        </td>
                                        <td>${food.price} 元</td>
                                        <td>${food.flavour}</td>
                                        <td>${food.yongliao}</td>
                                        <td class="text-success">${food.clickcount} 次</td>
                                        <td>
                                            <c:forEach items="${food.attribute}" var="attr">
                                                <p class="margin15-b">
                                                    <span class="label label-primary">${attr}</span>
                                                </p>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:if test="${food.isenable}">
                                                <a href="/admin/foods/clear/${food.id}-false" title="点击进行估清"
                                                   class="btn btn-xs btn-primary btn-clear" data-toggle="action">估清</a>
                                            </c:if>
                                            <c:if test="${!food.isenable}">
                                                <a href="/admin/foods/clear/${food.id}-true" title="点击取消估清"
                                                   class="btn btn-xs btn-danger btn-clear" data-toggle="action">已估清</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <widget:adminPagination page="${pageRecords}"/>

        </div>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="${ctx}/resources/bootstrap-select/js/bootstrap-select.min.js"></script>
        <script src="${ctx}/resources/js/uploadPreview.js"></script>
        <script>
            (function () {
                var $fancybox = $('a.fancybox'),
                $selectpicker = $('#searchPicker');
                        $fancybox.fancybox({openEffect: 'elastic', closeEffect: 'elastic'});
                $selectpicker.on('change', function () {
                    var $this = $(this);
                    var typeId = $this.selectpicker('val');
                    location.href = "${ctx}/admin/foods/" + typeId;
                });
                /// 删除所有
                $('#deleteAll').on('click', function (e) {
                    var $this = $(this);
                    e.preventDefault();
                    var ids = DomUtils.getCheckedValues();
                    if (!ids) {
                        alert('请选择记录！');
                    } else {
                        if (confirm("确定要删除选择的记录吗？")) {
                            var url = $this.attr('href') + "?ids=" + ids;
                            location.href = url;
                        }
                    }
                });


            })();
        </script>
    </jsp:attribute>
</layout:adminLayout>