package org.hrcore.system.service;
/**
 * Distintos estados o posibles casos que hay cuando se intenta el login
 */

public enum AuthenticationStatus {
        LOGIN_SUCCESS,
        ERROR_USER_NOT_FOUND,
        ERROR_CREDENTIALS,
        ERROR_USER_SEARCH,
        ERROR_LOGIN,
}
