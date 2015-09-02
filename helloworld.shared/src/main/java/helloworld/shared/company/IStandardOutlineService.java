package helloworld.shared.company;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IStandardOutlineService extends IService {

  CompanyTablePageData getCompanyTableData(CompanySearchFormData formData) throws ProcessingException;

}
