package org.vaadin.mockapp.samples.crud;

import java.util.Collection;

import org.vaadin.mockapp.samples.AttributeExtension;
import org.vaadin.mockapp.samples.data.Category;
import org.vaadin.mockapp.samples.data.State;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ProductForm extends GridLayout {

	TextField stockCount = new TextField("In stock");
	NativeSelect state = new NativeSelect("State");
	TwinColSelect category = new TwinColSelect("Categories");
	TextField price = new TextField("Price");
	TextField productName = new TextField("Product name");
	Button saveButton = new Button("Save");
	Button deleteButton = new Button("Delete");
	Button discardButton = new Button("Discard");
	private SampleCrudLogic viewLogic;

	public ProductForm(SampleCrudLogic sampleCrudLogic) {
		super(3, 3);
		this.viewLogic = sampleCrudLogic;
		setSpacing(true);
		setMargin(true);

		productName.setWidth("100%");
		addComponent(productName, 0, 0, 2, 0);

		price.setConverter(new EuroConverter());
		price.setWidth("60px");
		addComponent(price);

		AttributeExtension ae = new AttributeExtension();
		ae.extend(stockCount);
		ae.setAttribute("type", "number");
		stockCount.setWidth("80px");
		addComponent(stockCount);

		state.setNullSelectionAllowed(false);
		for (State s : State.values()) {
			state.addItem(s);
		}
		addComponent(state);

		category.setWidth("100%");
		addComponent(category, 0, 2, 2, 2);

		addComponent(saveButton);
		addComponent(discardButton);
		addComponent(deleteButton);

		setColumnExpandRatio(2, 1);

		saveButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				viewLogic.saveProduct();
			}
		});

		deleteButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				viewLogic.deleteProduct();
			}
		});
		
		discardButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				viewLogic.discardProduct();
			}
		});

	}

	public void setCategories(Collection<Category> categories) {
		category.removeAllItems();
		for (Category c : categories) {
			category.addItem(c);
		}
	}

}
