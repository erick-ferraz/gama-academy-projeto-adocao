package com.academy.gama.projeto.adocao.utils;

import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.AdopterWithPreferencesDTO;
import com.academy.gama.projeto.adocao.exception.InvalidDataException;
import com.academy.gama.projeto.adocao.model.enums.PetSize;
import com.academy.gama.projeto.adocao.model.enums.PetType;
import com.academy.gama.projeto.adocao.model.enums.Sex;

import java.util.InputMismatchException;

import static com.academy.gama.projeto.adocao.utils.Constants.*;

public class Validations {

    private Validations() {
    }

    //Adopters and preferences validations

    public static void validateString(String string, String errorMessage) {
        if(string == null || string.isBlank())
            throw new InvalidDataException(errorMessage);
    }

    public static void validateAdopterWithPreferencesDto(AdopterWithPreferencesDTO dto) {
        if(dto == null)
            throw new InvalidDataException(NULL_ADOPTER);

        validateAdopterDto(dto.getNome(), dto.getCpf(), dto.getIdade(), dto.getEndereco());

        try {
            PetType.valueOf(dto.getPreferencias().getTipoPet().toUpperCase());
            PetSize.valueOf(dto.getPreferencias().getPortePet().toUpperCase());
            Sex.valueOf(dto.getPreferencias().getSexo().toUpperCase());
        }catch(IllegalArgumentException ex) {
            throw new InvalidDataException(INVALID_ENUM_FORMAT);
        }
    }

    public static void validateAdopterDto(String name, String cpf, Integer age, String address) {
        validateString(name, NULL_ADOPTER_PROPERTIES);
        validateString(cpf, NULL_ADOPTER_PROPERTIES);
        validateString(address, NULL_ADOPTER_PROPERTIES);

        if(age == null)
            throw new InvalidDataException(NULL_ADOPTER_PROPERTIES);

        if(!isValidCpf(cpf))
            throw new InvalidDataException(INVALID_CPF);
    }

    public static void validateAdopterPreferencesDto(PrefsWithAdopterRequestDTO dto) {
        validateString(dto.getCpfAdotante(), NULL_PREFERENCES_PROPERTIES);
        validateString(dto.getPortePet(), NULL_PREFERENCES_PROPERTIES);
        validateString(dto.getTipoPet(), NULL_PREFERENCES_PROPERTIES);
        validateString(dto.getSexo(), NULL_PREFERENCES_PROPERTIES);
        validateString(dto.getCor(), NULL_PREFERENCES_PROPERTIES);

        if(dto.getIdade() == null)
            throw new InvalidDataException(NULL_PREFERENCES_PROPERTIES);

        if(!isValidCpf(dto.getCpfAdotante()))
            throw new InvalidDataException(INVALID_CPF);

        try {
            PetType.valueOf(dto.getTipoPet().toUpperCase());
            PetSize.valueOf(dto.getPortePet().toUpperCase());
            Sex.valueOf(dto.getSexo().toUpperCase());
        }catch(IllegalArgumentException ex) {
            throw new InvalidDataException(INVALID_ENUM_FORMAT);
        }
    }

    public static boolean isIdConvertable(String id) {
        try {
            Long.valueOf(id);
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isValidCpf(String CPF) {
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") ||
                CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") ||
                CPF.equals("99999999999") || (CPF.length() >= 15))
            return false;

        CPF = CPF.replaceAll("\\D", "");

        if (CPF.isBlank()) return false;
        if(CPF.length() != 11) return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException exception) {
            return(false);
        }
    }
}
