package com.company.datapackage.view.datapackage;

import com.company.datapackage.entity.*;
import com.company.datapackage.enums.Object;
import com.company.datapackage.view.main.MainView;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.formlayout.JmixFormLayout;
import io.jmix.flowui.component.multiselectcombobox.JmixMultiSelectComboBox;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.InstanceLoader;
import io.jmix.flowui.view.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Route(value = "data-packages/:id", layout = MainView.class)
@ViewController(id = "DataPackage.detail")
@ViewDescriptor(path = "data-package-detail-view.xml")
@EditedEntityContainer("dataPackageDc")
public class DataPackageDetailView extends StandardDetailView<DataPackage> {

    @ViewComponent
    private JmixSelect<Object> objectField;
    @ViewComponent
    private JmixMultiSelectComboBox<Organization> organizationsField;
    @ViewComponent
    private JmixMultiSelectComboBox<Role> rolesField;
    @ViewComponent
    private JmixMultiSelectComboBox<User> usersField;
    @ViewComponent
    private InstanceContainer<DataPackage> dataPackageDc;
    @ViewComponent
    private InstanceLoader<DataPackage> dataPackageDl;
    @ViewComponent
    private CollectionPropertyContainer<DataPackOrg> datapackorgDc;
    @ViewComponent
    private CollectionPropertyContainer<DataPackRole> datapackroleDc;
    @ViewComponent
    private CollectionPropertyContainer<DataPackUser> datapackuserDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        // lắng nghe thay đổi
        objectField.addValueChangeListener(e -> applyVisibility(e.getValue()));

    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        DataPackage entity = getEditedEntity();
        Object object = entity.getObject();

        applyVisibility(object);

        if (Object.UNIT.equals(object) && entity.getDatapackorg() != null) {
            organizationsField.setValue(
                    entity.getDatapackorg().stream()
                            .map(DataPackOrg::getOrganization)
                            .filter(o -> o != null)
                            .collect(Collectors.toSet())
            );
        }

        if (Object.ROLE.equals(object) && entity.getDatapackrole() != null) {
            rolesField.setValue(
                    entity.getDatapackrole().stream()
                            .map(DataPackRole::getRole)
                            .filter(r -> r != null)
                            .collect(Collectors.toSet())
            );
        }

        if (Object.SPECIFIC_USER.equals(object) && entity.getDatapackuser() != null) {
            usersField.setValue(
                    entity.getDatapackuser().stream()
                            .map(DataPackUser::getUser)
                            .filter(u -> u != null)
                            .collect(Collectors.toSet())
            );
        }


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

    @Subscribe(id = "saveAndCloseButton", subject = "clickListener")
    public void onSaveAndCloseButtonClick(final ClickEvent<JmixButton> event) {
        DataPackage dataPackage = getEditedEntity();
        DataContext dataContext = getViewData().getDataContext();

        Object object = dataPackage.getObject();
        if (object == null) return;

        if (Object.UNIT.equals(object)) {
            datapackorgDc.getMutableItems().forEach(dataContext::remove);
            datapackorgDc.getMutableItems().clear();

            Set<Organization> selectedOrgs = organizationsField.getValue();
            if (selectedOrgs != null && !selectedOrgs.isEmpty()) {
                List<DataPackOrg> newList = selectedOrgs.stream()
                        .map(org -> {
                            DataPackOrg link = dataContext.create(DataPackOrg.class);
                            link.setDataPackage(dataPackage);
                            link.setOrganization(org);
                            return link;
                        })
                        .toList();
                datapackorgDc.getMutableItems().addAll(newList);
            }
        } else if (Object.ROLE.equals(object)) {
            datapackroleDc.getMutableItems().forEach(dataContext::remove);
            datapackroleDc.getMutableItems().clear();

            Set<Role> selectedRoles = rolesField.getValue();
            if (selectedRoles != null && !selectedRoles.isEmpty()) {
                List<DataPackRole> newList = selectedRoles.stream()
                        .map(role -> {
                            DataPackRole link = dataContext.create(DataPackRole.class);
                            link.setDataPackage(dataPackage);
                            link.setRole(role);
                            return link;
                        })
                        .toList();
                datapackroleDc.getMutableItems().addAll(newList);
            }
        } else if (Object.SPECIFIC_USER.equals(object)) {
            datapackuserDc.getMutableItems().forEach(dataContext::remove);
            datapackuserDc.getMutableItems().clear();

            Set<User> selectedUsers = usersField.getValue();
            if (selectedUsers != null && !selectedUsers.isEmpty()) {
                List<DataPackUser> newList = selectedUsers.stream()
                        .map(user -> {
                            DataPackUser link = dataContext.create(DataPackUser.class);
                            link.setDataPackage(dataPackage);
                            link.setUser(user);
                            return link;
                        })
                        .toList();
                datapackuserDc.getMutableItems().addAll(newList);
            }
        }

    }

}