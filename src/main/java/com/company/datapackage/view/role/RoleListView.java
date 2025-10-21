package com.company.datapackage.view.role;

import com.company.datapackage.entity.Role;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "roles", layout = MainView.class)
@ViewController(id = "Role.list")
@ViewDescriptor(path = "role-list-view.xml")
@LookupComponent("rolesDataGrid")
@DialogMode(width = "64em")
public class RoleListView extends StandardListView<Role> {
}