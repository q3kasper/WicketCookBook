package cookbook;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;

public class HomePage extends WebPage {

  private static final String ZIPCODE = "ZIPCODE";
  private static final String PHONE = "PHONE";
  private static final List<String> TYPES = Arrays.asList(new String[] { ZIPCODE, PHONE });

  public HomePage(final PageParameters parameters) {
    add(new FeedbackPanel("feedback"));

    final DropDownChoice<String> type = new DropDownChoice<String>("type", new Model<String>(
        ZIPCODE), TYPES);
    type.setRequired(true);

    final TextField<String> keywords = new TextField<String>("keywords", new Model<String>());
    keywords.setRequired(true);

    Form< ? > form = new Form<Void>("form") {
      @Override
      protected void onValidate() {
        super.onValidate();

        if (hasError()) {
          return;
        }

        final String selectedType = type.getConvertedInput();
        final String query = keywords.getConvertedInput();
        if (ZIPCODE.equals(selectedType)) {
          if (!Pattern.matches("[0-9]{5}", query)) {
            keywords.error((IValidationError)new ValidationError().addMessageKey("invalidZipcode"));
          }
        }
        else if (PHONE.equals(selectedType)) {
          if (!Pattern.matches("[0-9]{10}", query)) {
            keywords.error((IValidationError)new ValidationError().addMessageKey("invalidPhone"));
          }
        }
      }

      @Override
      protected void onSubmit() {
        info("Form successfully submitted");
      }
    };

    add(form);
    form.add(type);
    form.add(keywords);
  }
}
