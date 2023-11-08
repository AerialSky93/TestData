package com.example.TestData.status;

import java.io.IOException;

public class FileNotLocatedException extends IOException {
    public FileNotLocatedException(String message) {
        super(message);
    }
}
