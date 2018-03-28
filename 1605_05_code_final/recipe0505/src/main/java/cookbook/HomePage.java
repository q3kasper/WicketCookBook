package cookbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static List<Contact> contacts = Arrays.asList(new Contact[] {
			new Contact("Homer Simpson", "homer@fox.com", "555-1211"),
			new Contact("Charles Montgomery Burns", "cmb@fox.com", "555-5322"),
			new Contact("Ned Flanders", "green@fox.com", "555-9732") });

	public HomePage(final PageParameters parameters) {
		add(new FeedbackPanel("feedback"));

		List<IColumn<Contact>> columns = new ArrayList<IColumn<Contact>>();
		columns.add(new PropertyColumn<Contact>(Model.of("Name"), "name"));
		columns.add(new TextPropertyColumn<Contact, String>(Model.of("Email"),
				"email"));
		columns.add(new PropertyColumn<Contact>(Model.of("Phone"), "phone"));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				info("Changes saved");
			}
		};
		add(form);

		DefaultDataTable<Contact> table = new DefaultDataTable<Contact>(
				"contacts", columns, new ContactsProvider(), 10);
		table.setItemReuseStrategy(new ReuseIfModelsEqualStrategy());

		form.add(table);
	}

	private static class ContactsProvider extends SortableDataProvider<Contact> {

		public Iterator<? extends Contact> iterator(int first, int count) {
			return contacts.subList(first,
					Math.min(first + count, contacts.size())).iterator();
		}

		public int size() {
			return contacts.size();
		}

		public IModel<Contact> model(Contact object) {
			return Model.of(object);
		}

	}

}