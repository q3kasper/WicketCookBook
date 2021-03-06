package cookbook;

import org.apache.wicket.ajax.IAjaxCallDecorator;

public class BlockUIDecorator implements IAjaxCallDecorator {

	public CharSequence decorateScript(CharSequence script) {
		return "$.blockUI({message: 'Processing, Please Wait...'});" + script;
	}

	public CharSequence decorateOnSuccessScript(CharSequence script) {
		return "$.unblockUI();" + script;
	}

	public CharSequence decorateOnFailureScript(CharSequence script) {
		return "$.unblockUI();" + script;
	}

}
