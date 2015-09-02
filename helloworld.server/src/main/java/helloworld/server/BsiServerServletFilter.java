package helloworld.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.DevelopmentAuthenticator;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.ServiceTunnelAccessTokenAuthenticator;
import org.eclipse.scout.rt.server.commons.servlet.filter.authentication.TrivialAuthenticator;

/**
 * Main server servlet filter
 */
public class BsiServerServletFilter implements Filter {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(BsiServerServletFilter.class);

  private TrivialAuthenticator m_trivialAuthenticator;
  private ServiceTunnelAccessTokenAuthenticator m_tunnelTokenAuthenticator;
  private DevelopmentAuthenticator m_devAuthenticator;

  @Override
  public void init(FilterConfig config) throws ServletException {
    m_trivialAuthenticator = BEANS.get(TrivialAuthenticator.class);
    m_tunnelTokenAuthenticator = BEANS.get(ServiceTunnelAccessTokenAuthenticator.class);
    m_devAuthenticator = BEANS.get(DevelopmentAuthenticator.class);

    m_trivialAuthenticator.init(config);
    m_tunnelTokenAuthenticator.init(config);
    m_devAuthenticator.init(config);
  }

  @Override
  public void destroy() {
    m_trivialAuthenticator.destroy();
    m_tunnelTokenAuthenticator.destroy();
    m_devAuthenticator.destroy();
  }

  @Override
  public void doFilter(final ServletRequest in, final ServletResponse out, final FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) in;
    final HttpServletResponse resp = (HttpServletResponse) out;

    if (m_trivialAuthenticator.handle(req, resp, chain)) {
      return;
    }

    if (m_tunnelTokenAuthenticator.handle(req, resp, chain)) {
      return;
    }

    if (m_devAuthenticator.handle(req, resp, chain)) {
      return;
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("Forwarding '{0}' to /login.jsp", ((HttpServletRequest) in).getPathInfo());
    }
    in.getRequestDispatcher("/login.jsp").forward(in, out);
  }

}
