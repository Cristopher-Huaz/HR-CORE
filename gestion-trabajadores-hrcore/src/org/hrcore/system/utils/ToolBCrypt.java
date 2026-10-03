package org.hrcore.system.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Clase utilitaria para el manejo de hash y verificacion de contrasenas usando BCrypt.
 */
public class ToolBCrypt {

    /** Factor de costo por defecto (2^6 = 64 rondas). */
    private static final int DEFAULT_COST = 6;

    public ToolBCrypt() {
    }

    /**
     * Genera un hash BCrypt listo para guardar en la base de datos.
     */
    public String encryptToString(String password) {
        String hashString = BCrypt.withDefaults()
                .hashToString(DEFAULT_COST, password.toCharArray());
        return hashString;
    }

    /**
     * Valida si la contrasena en texto plano coincide con el hash BCrypt.
     */
    public Boolean validatePassword(String password, String hashed) {
        BCrypt.Result valid = BCrypt.verifyer()
                .verify(password.toCharArray(), hashed.toCharArray());
        return valid.verified;
    }
}