package com.kamar.imscli.department.event;

import com.vaadin.flow.component.select.Select;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * add users to department event.
 * @author kamar baraka.*/

@Getter
public class AddUsersToDeptEvent extends ApplicationEvent {

    private final Select<String> employeeField;
    private final Select<String> departmentField;

    public AddUsersToDeptEvent(Object source,
                               Select<String > departmentField,
                               Select<String > employeeField) {
        super(source);
        this.departmentField = departmentField;
        this.employeeField = employeeField;
    }
}
