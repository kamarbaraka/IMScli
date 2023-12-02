package com.kamar.imscli.department.event;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * department creation event.
 * @author kamar baraka.*/

@Getter
public class DepartmentCreationEvent extends ApplicationEvent {

    private final TextField departmentNameField;
    private final TextField emailField;
    private final Select<String> headOfDepartmentField;

    public DepartmentCreationEvent(Object source,
                                   TextField departmentNameField,
                                   TextField emailField,
                                   Select<String > headOfDepartmentField) {
        super(source);
        this.departmentNameField = departmentNameField;
        this.emailField = emailField;
        this.headOfDepartmentField = headOfDepartmentField;
    }
}
