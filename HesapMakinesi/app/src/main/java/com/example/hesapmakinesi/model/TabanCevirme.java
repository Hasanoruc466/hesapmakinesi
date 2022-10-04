package com.example.hesapmakinesi.model;

import android.util.Log;

public class TabanCevirme {
    public String onlukToIkilik(long decimal){
        StringBuilder binary = new StringBuilder();
        while (decimal>0){
            if(decimal % 2 == 0)
                binary.append("0");
            else
                binary.append("1");
            decimal /= 2;
        }
        return binary.reverse().toString();
    }

    public long ikilikToOnluk(long binary){
        long decimal = 0, mod, temp = 1;
        while(binary > 0){
            mod = binary % 10;
            decimal = decimal + mod*temp;
            temp = temp * 2;
            binary = binary / 10;
        }


        return decimal;
    }

    public String onlukToOnaltilik(long decimal){
        StringBuilder hexadecimal = new StringBuilder();
        while (decimal > 0){
            int i = (int) decimal % 16;
            if(i<10){
                hexadecimal.append(i);
            }
            else{
                switch (i){
                    case 10:
                        hexadecimal.append("A");
                        break;
                    case 11:
                        hexadecimal.append("B");
                        break;
                    case 12:
                        hexadecimal.append("C");
                        break;
                    case 13:
                        hexadecimal.append("D");
                        break;
                    case 14:
                        hexadecimal.append("E");
                        break;
                    case 15:
                        hexadecimal.append("F");
                        break;
                }
            }
            decimal /= 16;
        }
        return hexadecimal.reverse().toString();
    }

    public long onaltilikToOnluk(String hexadecimal){
        long decimal = 0, mod, temp = 1;
        for(int i = hexadecimal.length()-1; i > -1; i--){
            char c = hexadecimal.charAt(i);
            if((int) c <58 && (int) c > 47){
                mod = Character.getNumericValue(c);
                decimal = decimal + mod*temp;
            }
            else{
                switch (c){
                    case 'A':
                        decimal = decimal + 10*temp;
                        break;
                    case 'B':
                        decimal = decimal + 11*temp;
                        break;
                    case 'C':
                        decimal = decimal + 12*temp;
                        break;
                    case 'D':
                        decimal = decimal + 13*temp;
                        break;
                    case 'E':
                        decimal = decimal + 14*temp;
                        break;
                    case 'F':
                        decimal = decimal + 15*temp;
                        break;
                }
            }
            temp = temp * 16;
        }

        return decimal;
    }

    public String onlukToSekizlik(long decimal){
        StringBuilder octal = new StringBuilder();
        while(decimal > 0){
            int i = (int) decimal % 8;
            octal.append(i);
            decimal = decimal / 8;
        }
        return octal.reverse().toString();
    }

    public long sekizlikToOnluk(long octal){
        long decimal = 0, mod, temp = 1;
        while (octal > 0){
            mod = octal % 10;
            decimal = decimal + mod*temp;
            temp = temp * 8;
            octal = octal / 10;
        }
        return decimal;
    }
}
