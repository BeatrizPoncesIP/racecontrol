package com.example.racecontrol.utils;

import java.util.InputMismatchException;

public class CPFUtils {

    // Método para validar o CPF
    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) return false;

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        // Validação dos dígitos verificadores
        try {
            int peso = 10;
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }

            int primeiroDigitoVerificador = 11 - (soma % 11);
            if (primeiroDigitoVerificador >= 10) primeiroDigitoVerificador = 0;
            if (primeiroDigitoVerificador != (cpf.charAt(9) - '0')) return false;

            peso = 11;
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }

            int segundoDigitoVerificador = 11 - (soma % 11);
            if (segundoDigitoVerificador >= 10) segundoDigitoVerificador = 0;
            return segundoDigitoVerificador == (cpf.charAt(10) - '0');
        } catch (InputMismatchException e) {
            return false;
        }
    }

    // Método para formatar o CPF (###.###.###-##)
    public static String formatCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", ""); // Remove caracteres não numéricos
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }
}