package helloworld.client;

import java.security.AllPermission;
import java.security.Permissions;

import org.eclipse.scout.rt.shared.services.common.security.AbstractSharedAccessControlService;

public class ClientAccessControlService extends AbstractSharedAccessControlService {

  @Override
  protected Permissions execLoadPermissions() {
    Permissions permissions = new Permissions();
    permissions.add(new AllPermission());
    return permissions;
  }
}
