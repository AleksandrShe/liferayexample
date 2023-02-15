package ru.example.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Reference;
import ru.example.constants.UsersPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import ru.example.service.AppUserService;

import java.io.IOException;
import java.util.List;

/**
 * @author user
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Users",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UsersPortletKeys.USERS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UsersPortlet extends MVCPortlet {

	@Reference
	private volatile AppUserService appUserService;


	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {

		ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List<Role> roles = null;
		try {
			roles = RoleServiceUtil.getUserRoles(td.getUserId());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		request.setAttribute("UsersObjects", appUserService.users(roles));
		super.render(request, response);
	}
}