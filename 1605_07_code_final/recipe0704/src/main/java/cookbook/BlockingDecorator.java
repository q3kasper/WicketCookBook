package cookbook;

import org.apache.wicket.ajax.IAjaxCallDecorator;

/**
 * Prevents multiple clicks while ajax request is executing
 * 
 * @author igor
 */
public class BlockingDecorator implements IAjaxCallDecorator
{
	private static final String latch=
		"var locked=this.hasAttribute('data-wicket-blocked');" +
		"if (locked) { return false; }"+
		"this.setAttribute('data-wicket-blocked','data-wicket-blocked');";

	private static final String reset="this.removeAttribute('data-wicket-blocked');";
	
	public CharSequence decorateScript(CharSequence script) {
		return latch + script;
	}

	public CharSequence decorateOnSuccessScript(CharSequence script) {
		return reset + script;
	}

	public CharSequence decorateOnFailureScript(CharSequence script) {
		return reset + script;
	}
}
