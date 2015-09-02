package helloworld.client.company;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import helloworld.client.company.CompanySearchForm.MainBox.ResetButton;
import helloworld.client.company.CompanySearchForm.MainBox.SearchButton;
import helloworld.client.company.CompanySearchForm.MainBox.TabBox;
import helloworld.client.company.CompanySearchForm.MainBox.TabBox.FieldBox;
import helloworld.client.company.CompanySearchForm.MainBox.TabBox.FieldBox.NameField;
import helloworld.client.company.CompanySearchForm.MainBox.TabBox.FieldBox.ShortNameField;
import helloworld.shared.company.CompanySearchFormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@FormData(value = CompanySearchFormData.class, sdkCommand = SdkCommand.CREATE)
public class CompanySearchForm extends AbstractSearchForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Company");
  }

  public CompanySearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    CompanySearchFormData formData = new CompanySearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void start() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  public ResetButton getResetButton() {
    return getFieldByClass(ResetButton.class);
  }

  public SearchButton getSearchButton() {
    return getFieldByClass(SearchButton.class);
  }

  public ShortNameField getShortNameField() {
    return getFieldByClass(ShortNameField.class);
  }

  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class FieldBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("searchCriteria");
        }

        @Order(10.0)
        public class NameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Name");
          }
        }

        @Order(20.0)
        public class ShortNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ShortName");
          }
        }
      }
    }

    @Order(20.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(30.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}
