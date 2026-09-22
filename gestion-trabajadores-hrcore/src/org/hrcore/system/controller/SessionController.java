package org.hrcore.system.controller;

import org.hrcore.system.model.Person;

public class SessionController {

    private static Person userLogued;

    public static Person getUserLogued() {
        return userLogued;
    }

    public static void setUserLogued(Person user) {
        userLogued = user;
    }

    public static void clear() {
        userLogued = null;
    }

    public static String getRoleName() {
        return userLogued != null ? userLogued.getRole() : null;
    }

    public static boolean isAdmin() {
        return "Administrador".equalsIgnoreCase(getRoleName());
    }

    public static boolean isManager() {
        return "Gerente".equalsIgnoreCase(getRoleName());
    }

    public static boolean isSupervisor() {
        return "Supervisor".equalsIgnoreCase(getRoleName());
    }

    public static boolean isEmployee() {
        return "Empleado".equalsIgnoreCase(getRoleName());
    }

    public static boolean isAnalyst() {
        return "Analista".equalsIgnoreCase(getRoleName());
    }

    public static boolean canManageUsers() {
        return isAdmin() || isManager();
    }

    public static boolean canViewAllUsers() {
        return isAdmin() || isManager() || isSupervisor();
    }

    public static boolean canViewPaymentSlips() {
        return true;
    }

    public static boolean canManageDepartments() {
        return isAdmin() || isManager();
    }
}