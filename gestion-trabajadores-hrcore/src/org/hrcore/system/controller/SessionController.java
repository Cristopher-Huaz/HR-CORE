package org.hrcore.system.controller;

import org.hrcore.system.model.Person;
        /*
        * Guarda el usuario que inicio sesion
        * */
public class SessionController {
    private static Person userLogued;

            public static Person getUserLogued() {
                return userLogued;
            }

            public static void setUserLogued(Person userLogued) {
                SessionController.userLogued = userLogued;
            }

            public static void clear(){
                userLogued = null;
            }
        }
