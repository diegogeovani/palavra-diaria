package br.com.palavra.domain.model;

public class DailyWord {

    private String mMessage;
    private Reference mReference;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Reference getReference() {
        return mReference;
    }

    public void setReference(Reference reference) {
        mReference = reference;
    }

}
