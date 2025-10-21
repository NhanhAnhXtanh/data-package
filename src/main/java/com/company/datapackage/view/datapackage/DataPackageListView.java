package com.company.datapackage.view.datapackage;

import com.company.datapackage.entity.DataPackage;
import com.company.datapackage.entity.Organization;
import com.company.datapackage.entity.Role;
import com.company.datapackage.entity.User;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route(value = "data-packages", layout = MainView.class)
@ViewController(id = "DataPackage.list")
@ViewDescriptor(path = "data-package-list-view.xml")
@LookupComponent("dataPackagesDataGrid")
@DialogMode(width = "64em")
public class DataPackageListView extends StandardListView<DataPackage> {

    @ViewComponent
    private DataGrid<DataPackage> dataPackagesDataGrid;

    @Supply(to = "dataPackagesDataGrid.allRelationsNames", subject = "renderer")
    private Renderer<DataPackage> dataPackagesDataGridAllRelationsNamesRenderer() {
        return new TextRenderer<>(dp -> {
            List<String> names = new ArrayList<>();

            if (dp.getOrganization() != null) {
                names.addAll(dp.getOrganization().stream()
                        .map(Organization::getName)
                        .toList());
            }

            if (dp.getRole() != null) {
                names.addAll(dp.getRole().stream()
                        .map(Role::getName)
                        .toList());
            }

            if (dp.getUser() != null) {
                names.addAll(dp.getUser().stream()
                        .map(User::getUsername)
                        .toList());
            }

            return String.join(", ", names);
        });
    }

}