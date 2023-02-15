<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ include file="init.jsp" %>

<jsp:useBean id="appUserServce" class="ru.example.service.AppUserService"/>
<portlet:defineObjects/>

<liferay-ui:search-container  var="searchContainer" emptyResultsMessage="no-users-found" delta="1"
							  total='<%=((List<User>)request.getAttribute("UsersObjects")).size()%>'>

	<liferay-ui:search-container-results
			results='<%=ListUtil.subList((List<User>)request.getAttribute("UsersObjects"),  searchContainer.getStart(), searchContainer.getEnd())%>'/>
	<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" modelVar="user"
									 keyProperty="userId">
		<liferay-portlet:renderURL varImpl="userURL">
			<portlet:param name="backURL" value="<%= currentURL %>"/>
			<portlet:param name="mvcPath" value="/user.jsp"/>
			<%session.setAttribute("user", (User)user); %>
		</liferay-portlet:renderURL>
		<liferay-ui:search-container-column-text property="fullName" href="<%=userURL%>" name="user name"/>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>

