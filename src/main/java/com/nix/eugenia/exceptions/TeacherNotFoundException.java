package com.nix.eugenia.exceptions;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(Long id) {
        super("Could not find teacher id = " + id + ", pleas try again");
    }
}
