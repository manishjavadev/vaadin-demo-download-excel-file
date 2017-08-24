package com.manish.javadev.app;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaddinUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AccountService accountService;

	@Override
	protected void init(VaadinRequest request) {

		TextField textField = new TextField("Enter Your Name");
		final Button button = new Button("Send");
		VerticalLayout verticalLayout = new VerticalLayout(textField, button);
		verticalLayout.setMargin(true);
		verticalLayout.setSpacing(true);
		setContent(verticalLayout);

		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				File file = new File("SampleData.xlsx");
				System.out.println(file.getAbsolutePath());

				String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
				System.out.println("basepath = " + basepath);
				if (file.exists()) {
					VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
					VaadinResponse vaadinResponse = VaadinService.getCurrentResponse();

					Resource resource = new FileResource(file);
					FileDownloader fileDownloader = new FileDownloader(resource);
					fileDownloader.extend(button);
					ExternalResource resource11 = new ExternalResource(file.getAbsolutePath());
					Page.getCurrent().open(resource11.getURL(), "Download", false);
					System.out.println("Done");
				} else {
					System.out.println("File Not Found");
				}

			}
		});

	}

}
