package org.hrcore.system;

import org.hrcore.system.utils.ToolBCrypt;

public class TestBCrypt {
    public static void main(String[] args) {
        ToolBCrypt tool = new ToolBCrypt();
        String hash = tool.encryptToString("mipass123");
        System.out.println("Hash generado: " + hash);
        System.out.println("Valida correcta: " + tool.validatePassword("mipass123", hash));
        System.out.println("Valida incorrecta: " + tool.validatePassword("otra", hash));
    }
}