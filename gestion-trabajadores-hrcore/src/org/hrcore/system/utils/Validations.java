package org.hrcore.system.utils;

public class Validations {
    public Validations() {
    }

    public Boolean isEqualsText(String textOriginal, String textCompare){
        return textOriginal.equals(textCompare);
    }
    public Boolean isEmptyText(String text){
        boolean isEmpty = false;

        if(text.isEmpty() || text.isBlank())
            isEmpty = true;

        return isEmpty;
    }
    public Boolean isValidLengthText(String text, int lenghtMax){
        return text.length() <= lenghtMax;
    }

    public Boolean isValidEmail(String email){
        int dotCount=0, arrobeCount=0;
       //Solo 1 punto(no seguidos)
        for( int index=0; index< email.length(); index++ ){
            if( email.charAt( index ) == '.' )
                dotCount++;
            if( dotCount>1 )
                return false;
        }
        //Solo un @
        for( int index=0; index< email.length(); index++ ){
            if( email.charAt( index ) == '@' )
                arrobeCount++;
        }
        return arrobeCount == 1;
    }
}
