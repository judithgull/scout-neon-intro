package helloworld.client;

import java.util.Locale;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.services.common.bookmark.IBookmarkService;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.ping.IPingService;

public class ClientSession extends AbstractClientSession {

  public ClientSession() {
    super(true);
  }

  public static ClientSession get() {
    return ClientSessionProvider.currentSession(ClientSession.class);
  }

  @Override
  protected void execLoadSession() throws ProcessingException {
    CODES.getAllCodeTypes("");
    setLocale(Locale.US);
    setDesktop(new Desktop());
    BEANS.get(IBookmarkService.class).loadBookmarks();
    BEANS.get(IPingService.class).ping("ping");
  }

}
