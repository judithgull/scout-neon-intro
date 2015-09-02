package helloworld.shared.company;

import java.security.BasicPermission;

public class UpdateCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  public UpdateCompanyPermission() {
    super("UpdateCompany");
  }
}
