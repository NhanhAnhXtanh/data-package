package com.company.datapackage.view.datapackage;

import com.company.datapackage.entity.DataPackage;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;


@Route(value = "data-packages", layout = MainView.class)
@ViewController(id = "DataPackage.list")
@ViewDescriptor(path = "data-package-list-view.xml")
@LookupComponent("dataPackagesDataGrid")
@DialogMode(width = "64em")
public class DataPackageListView extends StandardListView<DataPackage> {
    @ViewComponent
    private DataGrid<DataPackage> dataPackagesDataGrid;

}