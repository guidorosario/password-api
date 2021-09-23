package br.com.password.constraints;

public class ValidatorConstraints {

    public static final String SPACE_BETWEEN = "(?=.*\\s).*";
    public static final String DIGIT = "(?=.*\\d).*";
    public static final String LOWER_LETTER = "(?=.*[a-z]).*";
    public static final String UPPER_LETTERS = "(?=.*[A-Z]).*";
    public static final String SPECIAL_CHAR = "(?=.*[!@#$%^&*()-+]).*";
    public static final String LENGTH = ".{9,}";
    public static final String REPETITION = "^.*(.).*\\1.*$";
}
