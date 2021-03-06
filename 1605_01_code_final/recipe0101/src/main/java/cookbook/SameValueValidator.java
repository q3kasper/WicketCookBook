package cookbook;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;

public class SameValueValidator implements IFormValidator {

  private FormComponent< ? > field1;
  private FormComponent< ? > field2;

  public SameValueValidator(FormComponent< ? > field1, FormComponent< ? > field2) {
    this.field1 = field1;
    this.field2 = field2;
  }

  public FormComponent< ? >[] getDependentFormComponents() {
    return new FormComponent[] { field1, field2 };
  }

  public void validate(Form< ? > form) {
    Object input1 = field1.getConvertedInput();
    Object input2 = field2.getConvertedInput();
    if (!Objects.equal(input1, input2)) {
      ValidationError error = new ValidationError();
      error.addMessageKey(getClass().getSimpleName());
      field2.error((IValidationError)error);
    }
  }
}
