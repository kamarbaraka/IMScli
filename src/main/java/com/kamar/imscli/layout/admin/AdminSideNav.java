package com.kamar.imscli.layout.admin;

import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;

/**
 * side navigation for the admin.
 * @author kamar baraka.*/

@Route("admin_sidenav")
public class AdminSideNav extends SideNav {

    private final SideNavItem userOptions;
    private final SideNavItem departmentOptions;
    private final SideNavItem roleOptions;
    private final SideNavItem ticketOptions;

    /*for user options*/
    private final SideNavItem userAddOption;
    private final SideNavItem userActivateOption;
    private final SideNavItem userAllUsersOption;
    private final SideNavItem userAllEmployeesOption;

    /*for department options*/
    private final SideNavItem departmentCreateOption;
    private final SideNavItem departmentAddUserOption;
    private final SideNavItem departmentAllTheDepartmentsOption;

    /*for role options*/
    private final SideNavItem roleCreateOption;
    private final SideNavItem roleElevateUserOption;
    private final SideNavItem roleAllTheRolesOption;

    /*for ticket options*/
    private final SideNavItem ticketAllTicketsOption;
    private final SideNavItem ticketAllPendingTicketsOption;
    private final SideNavItem ticketAllAssignedTicketsOption;
    private final SideNavItem ticketAllSubmittedTicketsOption;
    private final SideNavItem ticketAllResolvedTicketsOption;


    public AdminSideNav() {
        /*inject dependencies*/

        /*initialize components*/
        /*main options*/
        this.userOptions = new SideNavItem("users");
        this.departmentOptions = new SideNavItem("departments");
        this.roleOptions = new SideNavItem("roles");
        this.ticketOptions = new SideNavItem("tickets");

        /*for user options*/
        userAddOption = new SideNavItem("add user");
        userActivateOption = new SideNavItem("activate user");
        userAllUsersOption = new SideNavItem("all users");
        userAllEmployeesOption = new SideNavItem("employees");

        /*for department options*/
        departmentCreateOption = new SideNavItem("create");
        departmentAddUserOption = new SideNavItem("add user");
        departmentAllTheDepartmentsOption = new SideNavItem("all");

        /*for role options*/
        roleCreateOption = new SideNavItem("create");
        roleElevateUserOption = new SideNavItem("elevate user");
        roleAllTheRolesOption = new SideNavItem("all");

        /*for ticket options*/
        ticketAllTicketsOption = new SideNavItem("all");
        ticketAllPendingTicketsOption = new SideNavItem("pending");
        ticketAllAssignedTicketsOption = new SideNavItem("assigned");
        ticketAllSubmittedTicketsOption = new SideNavItem("submitted");
        ticketAllResolvedTicketsOption = new SideNavItem("resolved");

        /*configure layout*/

        configureLayout();

    }

    private void configureLayout() {
        /*configure layout*/

        configureUserOptions();
        configureDepartmentOptions();
        configureRoleOptions();
        configureTicketOptions();


        this.setLabel("options");
        this.setMaxWidth("15%");
        this.setCollapsible(true);
        this.setExpanded(true);

        this.addItem(userOptions, departmentOptions, roleOptions, ticketOptions);
    }

    private void configureTicketOptions() {
        /*configure the ticket options*/
        configureTicketAllTicketsOption();
        configureTicketAllPendingTicketsOption();
        configureTicketAllAssignedTicketsOption();
        configureTicketAllSubmittedTicketsOption();
        configureTicketAllResolvedTicketsOption();

        ticketOptions.addItem(ticketAllTicketsOption, ticketAllPendingTicketsOption,
                ticketAllAssignedTicketsOption, ticketAllSubmittedTicketsOption,
                ticketAllResolvedTicketsOption);

    }

    private void configureTicketAllTicketsOption() {

    }

    private void configureTicketAllPendingTicketsOption() {

    }

    private void configureTicketAllAssignedTicketsOption() {

    }

    private void configureTicketAllSubmittedTicketsOption() {

    }

    private void configureTicketAllResolvedTicketsOption() {

    }

    private void configureRoleOptions() {
        /*configure role options*/
        configureRoleCreateOption();
        configureRoleElevateUserOption();
        configureRoleAllTheRolesOption();

        roleOptions.addItem(roleCreateOption, roleElevateUserOption, roleAllTheRolesOption);

    }

    private void configureRoleCreateOption() {

    }

    private void configureRoleElevateUserOption() {

    }

    private void configureRoleAllTheRolesOption() {

    }

    private void configureDepartmentOptions() {
        /*configure the department options*/
        configureDepartmentCreateOption();
        configureDepartmentAddUserOption();
        configureDepartmentAllTheDepartmentsOption();

        departmentOptions.addItem(departmentCreateOption,
                departmentAddUserOption,
                departmentAllTheDepartmentsOption);
    }

    private void configureDepartmentCreateOption() {

    }

    private void configureDepartmentAddUserOption() {

    }

    private void configureDepartmentAllTheDepartmentsOption() {

    }

    private void configureUserOptions() {
        /*configure the user options*/
        configureUserAddOption();
        configureUserActivateOption();
        configureUserAllUsersOption();
        configureUserAllEmployeesOption();

        userOptions.addItem(userAddOption, userActivateOption,
                userAllUsersOption, userAllEmployeesOption);
    }

    private void configureUserAddOption() {
        /*configure the user add option*/
    }

    private void configureUserActivateOption() {
        /*configure user activate option*/
    }

    private void configureUserAllUsersOption() {

    }

    private void configureUserAllEmployeesOption() {

    }
}
