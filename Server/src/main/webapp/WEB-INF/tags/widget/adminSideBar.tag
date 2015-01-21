<%@tag pageEncoding="UTF-8" %>
<%@ include file="../../../taglibs.jsp" %>
<%@attribute name="currentMenu" type="java.lang.String" required="true" %>
<div class="navbar-default sidebar" role="navigation">

    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <c:forEach items="${app.menus}" var="menu">
                <!-- 将菜单链接追加上下文字符串 -->
                <c:if test="${fn:contains(menu.roles, admin.rolename)}">
                    <li>
                        <c:choose>
                            <c:when test="${!empty(menu.subMenus)}">
                                <!-- 有下级菜单 -->
                                <!-- 判断下级菜单中是否包含当前菜单 -->
                                <c:set var="subMenuCurrent" value="false"/>
                                <c:forEach items="${menu.subMenus}" var="subMenu">
                                    <c:if test="${currentMenu eq subMenu.flag}">
                                        <c:set var="subMenuCurrent" value="true"/>
                                    </c:if>
                                </c:forEach>

                                <c:choose>
                                    <c:when test="${subMenuCurrent}">
                                        <!-- 下级菜单包含当前菜单，箭头方向向下，下级菜单高亮 -->
                                        <a <c:if test="${currentMenu eq menu.flag}"> class="active" </c:if>
                                                href="${ctx}${menu.url}"><i class="${menu.faIcon}"></i> ${menu.title}
                                            <span class="fa pull-right fa-angle-down"></span> </a>
                                        <ul class="nav nav-second-level collapse in" aria-expanded="true">
                                            <c:forEach items="${menu.subMenus}" var="subMenu">
                                                <li>
                                                    <a <c:if
                                                            test="${currentMenu eq subMenu.flag}"> class="active" </c:if>
                                                            href="${ctx}${subMenu.url}"><i
                                                            class="${subMenu.faIcon}"></i> ${subMenu.title}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- 当前菜单不在下级菜单中 -->
                                        <a <c:if test="${currentMenu eq menu.flag}"> class="active" </c:if>
                                                href="${ctx}${menu.url}"><i class="${menu.faIcon}"></i> ${menu.title}
                                            <span class="fa pull-right fa-angle-down"></span> </a>

                                        <ul class="nav nav-second-level">
                                            <c:forEach items="${menu.subMenus}" var="subMenu">
                                                <li>
                                                    <a href="${ctx}${subMenu.url}"><i
                                                            class="${subMenu.faIcon}"></i> ${subMenu.title}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <!-- 没有下级菜单 -->
                                <a <c:if test="${currentMenu eq menu.flag}"> class="active" </c:if>
                                        href="${ctx}${menu.url}"><i class="${menu.faIcon}"></i> ${menu.title} </a>

                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:if>
            </c:forEach>
        </ul>

    </div>

</div>