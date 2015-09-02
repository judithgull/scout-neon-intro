package helloworld.server;

import org.eclipse.scout.commons.exception.ProcessingException;
import helloworld.shared.company.CompanySearchFormData;
import helloworld.shared.company.CompanyTablePageData;
import helloworld.shared.company.CompanyTablePageData.CompanyTableRowData;
import helloworld.shared.company.IStandardOutlineService;
import org.eclipse.scout.rt.platform.service.AbstractService;
import org.eclipse.scout.rt.server.Server;

@Server
public class StandardOutlineService extends AbstractService implements IStandardOutlineService {

  @Override
  public CompanyTablePageData getCompanyTableData(CompanySearchFormData formData) throws ProcessingException {
    CompanyTablePageData pageData = new CompanyTablePageData();
    CompanyTableRowData row = pageData.addRow();
    row.setCompanyNr(1L);
    row.setCompanyType(10003L);
    row.setName("BSI Business Systems Integration");
    row.setShortName("BSI");
    return pageData;
  }

}
