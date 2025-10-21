package com.company.datapackage.view.role;

import com.company.datapackage.entity.Role;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "roles/:id", layout = MainView.class)
@ViewController(id = "Role.detail")
@ViewDescriptor(path = "role-detail-view.xml")
@EditedEntityContainer("roleDc")
public class RoleDetailView extends StandardDetailView<Role> {
}