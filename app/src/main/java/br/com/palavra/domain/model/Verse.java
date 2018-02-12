package br.com.palavra.domain.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Verse implements Comparable<Verse> {

    private final int mNumber;

    public Verse(int number) {
        mNumber = number;
    }

    @Override
    public int compareTo(@NonNull Verse o) {
        return Integer.compare(mNumber, o.getNumber());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Verse && ((Verse) obj).getNumber() == mNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(mNumber);
    }

    public int getNumber() {
        return mNumber;
    }

}
