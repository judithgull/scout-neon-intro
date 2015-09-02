package helloworld.client.company;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;

public class CompanyDetailsNodePage extends AbstractPageWithNodes {

  private Long m_companyNr;

  @FormData
  public Long getCompanyNr() {
    return m_companyNr;
  }

  @FormData
  public void setCompanyNr(Long companyNr) {
    m_companyNr = companyNr;
  }
}
