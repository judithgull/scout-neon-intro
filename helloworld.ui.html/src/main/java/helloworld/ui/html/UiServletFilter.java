/*
 * Copyright (c) BSI Business Systems Integration AG. All rights reserved.
 * http://www.bsiag.com/
 */
package helloworld.ui.html;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.ServletFilterHelper;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.TrivialAuthenticator;

/**
 * This is the main UI servlet filter
 *
 * @since 5.0
 */
public class UiServletFilter implements Filter {

  private TrivialAuthenticator m_trivialAuthenticator;
  private LoginFormAuthenticator m_formAuthenticator;

  @Override
  public void init(FilterConfig config) throws ServletException {
    m_trivialAuthenticator = BEANS.get(TrivialAuthenticator.class);
    m_formAuthenticator = BEANS.get(LoginFormAuthenticator.class);

    m_trivialAuthenticator.init(config);
    m_formAuthenticator.init(config);
  }

  @Override
  public void destroy() {
    m_trivialAuthenticator.destroy();
    m_formAuthenticator.destroy();
  }

  @Override
  public void doFilter(final ServletRequest in, final ServletResponse out, FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) in;
    final HttpServletResponse resp = (HttpServletResponse) out;

    //call to /auth is handled in advance by the corresponding form auth handler
    if (isLoginFormAction(req) && m_formAuthenticator.handle(req, resp)) {
      return;
    }

    if (isLogoutRequest(req)) {
      BEANS.get(ServletFilterHelper.class).doLogout(req);
      BEANS.get(ServletFilterHelper.class).forwardToLogoutForm(req, resp);
      return;
    }

    if (isLoginFormAction(req)) {
      BEANS.get(ServletFilterHelper.class).forwardToLoginForm(req, resp);
      return;
    }

    if (m_trivialAuthenticator.handle(req, resp, chain)) {
      return;
    }

    BEANS.get(ServletFilterHelper.class).forwardToLoginForm(req, resp);
  }

  protected boolean allowNegotiate(HttpServletRequest req) {
    return "true".equals(req.getHeader("X-Negotiate-Enabled"));
  }

  protected boolean isLogoutRequest(HttpServletRequest req) {
    return ("/login".equals(req.getPathInfo()) || "/logout".equals(req.getPathInfo()));
  }

  protected boolean isLoginFormAction(HttpServletRequest req) {
    return "/auth".equals(req.getPathInfo());
  }
}
