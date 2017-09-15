package com.cr.pmp.common.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import com.cr.pmp.common.dict.SystemDict;

public class PermissionsDirective extends Directive {

	@Override
	public String getName() {
		return "isDisplay";
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	@Override
	public boolean render(InternalContextAdapter internalContext,
			Writer writer, Node node) throws IOException,
			ResourceNotFoundException, ParseErrorException,
			MethodInvocationException {
		HttpServletRequest request = (HttpServletRequest) internalContext
				.get("request");
		Map<String, String> userPermissionsMap = (Map<String, String>) request
				.getSession().getAttribute(SystemDict.USERPERMISSIONSKEY);
		String code = node.jjtGetChild(0).value(internalContext).toString();
		String userPermissions = null;
		if (userPermissionsMap != null) {
			userPermissions = userPermissionsMap.get(code);
		}
		boolean flag = StringUtils.isNotBlank(userPermissions);
		if (flag) {
			node.jjtGetChild(1).render(internalContext, writer);
		}
		return flag;
	}

}
