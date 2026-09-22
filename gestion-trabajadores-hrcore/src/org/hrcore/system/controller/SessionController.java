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

    // -------------------- Roles del sistema --------------------

    public static boolean isDirector() {
        return "Director".equalsIgnoreCase(getRoleName());
    }

    public static boolean isGestorTalento() {
        String rol = getRoleName();
        if (rol == null) return false;
        return rol.equalsIgnoreCase("Gestor de Talento")
                || rol.equalsIgnoreCase("Gestor de talento");
    }

    public static boolean isAnalista() {
        return "Analista".equalsIgnoreCase(getRoleName());
    }

    // -------------------- Permisos por botón --------------------

    /** Director ve TODOS los botones del dashboard. */
    public static boolean canSeeAll() {
        return isDirector();
    }

    /** Gestor de Talento solo ve Registrar y Editar colaborador. */
    public static boolean canManageEmployees() {
        return isGestorTalento();
    }

    /** Analista solo ve Boletas de pago. */
    public static boolean canViewPaymentSlips() {
        return isAnalista();
    }
}