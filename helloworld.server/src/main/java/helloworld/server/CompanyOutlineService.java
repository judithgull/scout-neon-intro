package helloworld.server;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.Server;

import helloworld.shared.company.CompanySearchFormData;
import helloworld.shared.company.CompanyTablePageData;
import helloworld.shared.company.CompanyTablePageData.CompanyTableRowData;
import helloworld.shared.company.ICompanyOutlineService;

@Server
public class CompanyOutlineService implements ICompanyOutlineService {

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
