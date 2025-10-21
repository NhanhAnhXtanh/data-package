package com.company.datapackage.view.organization;

import com.company.datapackage.entity.Organization;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "organizations/:id", layout = MainView.class)
@ViewController(id = "Organization.detail")
@ViewDescriptor(path = "organization-detail-view.xml")
@EditedEntityContainer("organizationDc")
public class OrganizationDetailView extends StandardDetailView<Organization> {
}