package com.academy.gama.projeto.adocao.utils;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class Constants {

    private Constants() {
    }

    public static DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String CPF_NOT_FOUND = "O CPF informado não corresponde à nenhum adotante.";
    public static String INVALID_CPF = "O CPF informado é inválido.";
    public static String NULL_ADOPTER = "Adotante não pode ser nulo.";
    public static String NULL_ADOPTER_PROPERTIES = "Propriedades do adotante não podem ser nulas ou vazias";
    public static String NULL_PREFERENCES_PROPERTIES = "Propriedades de preferências do adotante não podem ser nulas ou vazias";
    public static String DUPLICATED_ADOPTER = "Adotante já cadastrado.";
    public static String INVALID_ENUM_FORMAT = "Formato do tipo do pet, porte ou sexo inválido(s).";
    public static String INVALID_ID = "O ID informado é inválido.";
    public static String ID_NOT_FOUND = "O ID informado não corresponde à nenhuma entidade.";
}
