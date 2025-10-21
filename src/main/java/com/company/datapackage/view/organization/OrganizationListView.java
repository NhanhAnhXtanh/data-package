package com.company.datapackage.view.organization;

import com.company.datapackage.entity.Organization;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "organizations", layout = MainView.class)
@ViewController(id = "Organization.list")
@ViewDescriptor(path = "organization-list-view.xml")
@LookupComponent("organizationsDataGrid")
@DialogMode(width = "64em")
public class OrganizationListView extends StandardListView<Organization> {
}