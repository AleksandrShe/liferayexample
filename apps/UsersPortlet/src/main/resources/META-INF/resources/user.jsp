<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ include file="init.jsp" %>

<jsp:useBean id = "appUserService"  class = "ru.example.service.AppUserService" />
<jsp:useBean id = "dateUtil"  class = "ru.example.util.DateUtils" />
<%
    long id = ParamUtil.getLong(request, "id");
    user = appUserService.getUserById(id);
%>
<body>
<html>
<div class="title"><liferay-ui:message key="user-id" /></div>
<div class="value"><%=user.getUserId()%></div>

<div class="title"><liferay-ui:message key="user-fio" /></div>
<div class="value"><%=user.getFullName()%></div>

<div class="title"><liferay-ui:message key="user-email" /></div>
<div class="value"><%=user.getEmailAddress()%></div>

<div class="title"><liferay-ui:message key="user-phone" /></div>
<div class="value">

    <table  cellpadding="0" cellspacing="0" border="0">
        <c:forEach items="${user.getPhones()}" var="phone">
            <tr>
                <td><c:out value="${phone.number}"/></td>
            </tr>
        </c:forEach>
    </table>

</div>

<div class="title"><liferay-ui:message key="user-jobTitle" /></div>
<div class="value"><%=user.getJobTitle()%></div>

<div class="title"><liferay-ui:message key="user-birthday" /></div>
<div class="value"><%=dateUtil.formatDate(user.getBirthday())%></div>

<div class="title"><liferay-ui:message key="user-company" /></div>
<div class="value">
    <table  cellpadding="0" cellspacing="0" border="0">
        <c:forEach items="${user.getOrganizations(true)}" var="company">
            <tr>
                <td><c:out value="${company.name}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>


<div style="margin-top: 12px">
    <a href="<%= currentURL %>"><liferay-ui:message key="BACK" /></a>
</div>
</html>
</body>