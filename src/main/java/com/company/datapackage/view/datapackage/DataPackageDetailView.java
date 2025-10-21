package com.company.datapackage.view.datapackage;

import com.company.datapackage.entity.DataPackage;
import com.company.datapackage.entity.Organization;
import com.company.datapackage.entity.Role;
import com.company.datapackage.entity.User;
import com.company.datapackage.enums.Object;
import com.company.datapackage.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.multiselectcombobox.JmixMultiSelectComboBox;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.view.*;

import java.util.Collections;

@Route(value = "data-packages/:id", layout = MainView.class)
@ViewController(id = "DataPackage.detail")
@ViewDescriptor(path = "data-package-detail-view.xml")
@EditedEntityContainer("dataPackageDc")
public class DataPackageDetailView extends StandardDetailView<DataPackage> {
    @ViewComponent
    private JmixSelect<Object> objectField;
    @ViewComponent
    private JmixMultiSelectComboBox<User> usersField;
    @ViewComponent
    private JmixMultiSelectComboBox<Role> rolesField;
    @ViewComponent
    private JmixMultiSelectComboBox<Organization> organizationsField;

    @Subscribe
    public void onInit(final InitEvent event) {
        // đồng bộ UI lúc mở
        applyVisibility(objectField.getValue());

        // lắng nghe thay đổi
        objectField.addValueChangeListener(e -> applyVisibility(e.getValue()));
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<DataPackage> event) {
        // chỉ set mặc định khi tạo mới
        event.getEntity().setObject(Object.UNIT);
    }

    private void applyVisibility(Object value) {
        boolean showOrganization = Object.UNIT.equals(value);
        boolean showRole = Object.ROLE.equals(value);
        boolean showUser = Object.SPECIFIC_USER.equals(value);

        organizationsField.setVisible(showOrganization);
        rolesField.setVisible(showRole);
        usersField.setVisible(showUser);

        if (!showOrganization) organizationsField.clear();
        if (!showRole) rolesField.clear();
        if (!showUser) usersField.clear();
    }

}