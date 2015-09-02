package helloworld.shared.company;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.shared.TunnelToServer;

@ApplicationScoped
@TunnelToServer
public interface IStandardOutlineService {

  CompanyTablePageData getCompanyTableData(CompanySearchFormData formData) throws ProcessingException;

}
