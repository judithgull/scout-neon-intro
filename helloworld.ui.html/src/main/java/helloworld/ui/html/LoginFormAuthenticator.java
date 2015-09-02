/*
 * Copyright (c) BSI Business Systems Integration AG. All rights reserved.
 * http://www.bsiag.com/
 */
package helloworld.ui.html;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.ServletFilterHelper;

/**
 * This is the login.html action /auth that checks whether user/password = scout/secure
 *
 * @since 5.0
 */
@Bean
public class LoginFormAuthenticator {
  private boolean m_active;

  public void init(final FilterConfig filterConfig) throws ServletException {
    m_active = true;
  }

  public boolean isActive() {
    return m_active;
  }

  public void destroy() {
  }

  /**
   * @return true if the request was handled (caller returns), false if nothing was done (caller continues)
   */
  public boolean handle(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
    if (!isActive()) {
      return false;
    }

    String[] cred = readCredentials(req);
    if (cred == null || cred.length != 2 || !"scout".equals(cred[0]) || !"secure".equals(cred[1])) {
      writeForbiddenResponse(resp);
      return true;
    }

    final Principal principal = new SimplePrincipal(cred[0]);

    //OWASP: force a new http session
    HttpSession session = req.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    ServletFilterHelper helper = BEANS.get(ServletFilterHelper.class);
    //cache principal
    helper.putPrincipalOnSession(req, principal);
    return true;
  }

  protected String[] readCredentials(HttpServletRequest req) {
    String user = req.getParameter("user");
    if (user != null) {
      return new String[]{user, req.getParameter("password")};
    }
    return null;
  }

  protected void writeForbiddenResponse(HttpServletResponse resp) throws IOException {
    //wait some time
    try {
      Thread.sleep(500L);
    }
    catch (InterruptedException e) {
      //nop
    }
    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
  }

}
