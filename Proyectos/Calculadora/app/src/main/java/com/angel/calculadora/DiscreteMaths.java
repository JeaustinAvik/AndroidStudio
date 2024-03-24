package com.angel.calculadora;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiscreteMaths {



    //public static ArrayList<Double> numeros = new ArrayList<>();


    public DiscreteMaths() {

    }

    public void mostrarToast(Context context,String mensaje){
        Toast.makeText(context,mensaje, Toast.LENGTH_SHORT).show();
    }

    public String operation(TextView tv, MainActivity m) {
        ArrayList<String> signos = new ArrayList<>();

        ArrayList<String> numeros = new ArrayList<>();
        ArrayList<String> SumaReales=new ArrayList<>();//para agregar numero
        String num = (String) tv.getText();
        String editNum = "0";
        String caracteresNumeros = "";
        int longitud=num.length();

        for (int i = 0; i < num.length(); i++) {

            char caracter = num.charAt(i);
          //  System.out.println(longitud);
            if (esNumero(caracter)||caracter=='.') {
                if (i==0) signos.add("+");
                caracteresNumeros += caracter;
                if (i==num.length()-1)
                    numeros.add(caracteresNumeros);
            } else {
                if (caracter == 'x' || caracter == '/' || caracter == '+' || caracter == '-'|| caracter == '%')
                    signos.add(String.valueOf(caracter));
                     if (i!=0)
                    numeros.add(caracteresNumeros);
                    caracteresNumeros="";
            }





        }


 if (num.charAt(num.length() - 1)=='.') return "";


        int i = 0;
        String n="1";
        for (String signo : signos) {
            char UltimoCaracter = num.charAt(num.length() - 1);
            System.out.println(signo);
            switch (signo) {
                case "x":
                    if (UltimoCaracter == 'x') return "";
                    else {
                        editNum = multiplicacion(n, numeros.get(i));

                    }
                    break;
                case "/":
                    if (UltimoCaracter == '/') return "";

                    else
                        editNum = division(n, numeros.get(i),m);
                    break;
                case "+":
                    if (UltimoCaracter == '+') return "";
                    else {


                        if (i!=0) {
                            SumaReales.add(editNum);
                            n=numeros.get(i);
                        }else n=numeros.get(i);
                        editNum = suma(n);

                    }
                    break;
                case "-":
                    if (UltimoCaracter == '-') return "";

                    else {
                        if (i!=0) {
                            SumaReales.add(editNum);
                            n=numeros.get(i);
                        }else n=numeros.get(i);
                        editNum = resta(n);
                    }

                    break;

                case "%":
                    editNum=modulo(n);

                    break;

                default:

                    break;
            }
            if (esNumero(UltimoCaracter)) {
                if (i==signos.size()-1)
                SumaReales.add(editNum);
                else n=editNum;

            }

            i++;

        }
        String SumT="0";
        for(String sumaT: SumaReales){
            SumT= String.valueOf(Double.parseDouble(SumT)+Double.parseDouble(sumaT));

        }
      String UltimoSigno=(signos.get(signos.size()-1));
        char[] signo=UltimoSigno.toCharArray();
        char Usigno=signo[signo.length-1];
        if (Usigno=='%') {

            System.out.println(SumT);
            return SumT;
        }

        if (Double.parseDouble(SumT)%1!=0)
            return SumT;
        return String.valueOf((int) Double.parseDouble(SumT));
    }


    public boolean esNumero(char str) {

        try {
           Double.parseDouble(String.valueOf((str)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String suma(String n1) {
        for (int i=0;i<n1.length();i++) {
            char caracter = n1.charAt(i);
            if (caracter=='.') return String.valueOf(Double.parseDouble(n1) * 1);
        }
        return String.valueOf(Double.parseDouble(n1) * 1);
    }

    public String resta(String n1) {

        for (int i=0;i<n1.length();i++) {
            char caracter = n1.charAt(i);
            if (caracter=='.') return String.valueOf(Double.parseDouble(n1) * -1);
        }
        return String.valueOf(Double.parseDouble(n1) *-1);

    }

    public String multiplicacion(String n1, String n2) {
        for (int i=0;i<n1.length();i++) {
            char caracter = n1.charAt(i);
            if (caracter=='.') {
                double r=Double.parseDouble(n1) * Double.parseDouble(n2);
                double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
                return String.valueOf(resultadoRedondeado);
            }
        }
        for (int i=0;i<n2.length();i++) {
            char caracter = n2.charAt(i);
            if (caracter=='.') {
                double r=Double.parseDouble(n1) * Double.parseDouble(n2);
                double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
                return String.valueOf(resultadoRedondeado);
            }
        }

        double r=Double.parseDouble(n1) * Double.parseDouble(n2);
        double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
        return String.valueOf(resultadoRedondeado);
    }

    public String division(String n1, String n2, MainActivity getApplicationContext) {
        System.out.println(n2);

        if (Double.parseDouble(n2)==0) {
            mostrarToast(getApplicationContext, "Operacion Invalida");
            return n2;
        }
        else {
            for (int i=0;i<n1.length();i++) {
                char caracter = n1.charAt(i);
                if (caracter=='.') {
                    double r=Double.parseDouble(n1) / Double.parseDouble(n2);
                    double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
                    return String.valueOf(resultadoRedondeado);
                }
            }
            for (int i=0;i<n2.length();i++) {
                char caracter=n2.charAt(i);
                if (caracter=='.') {
                    double r=Double.parseDouble(n1) / Double.parseDouble(n2);
                    double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
                    return String.valueOf(resultadoRedondeado);
                }
            }

            double r=Double.parseDouble(n1) / Double.parseDouble(n2);
            double resultadoRedondeado = Math.round(r * 100.0) / 100.0;
            return String.valueOf(resultadoRedondeado);
        }
    }

    public String modulo(String n1){
        double r=Double.parseDouble(n1) / 100;

        return String.valueOf(r);

    }


}
