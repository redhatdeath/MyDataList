package ru.shanin.mydatalist.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;

import ru.shanin.mydatalist.domain.entity.comparators.ComparatorByID;

public class People {
    public static final Comparator<People> byID;

    static {
        byID = new ComparatorByID();
    }

    private final PeopleInfo peopleInfo;
    private final String _id_sha256;

    public String get_id_sha256() {
        return _id_sha256;
    }

    public People(PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No SHA256 Algorithm");
        }
        byte[] encodedHash = digest.digest(
                this.peopleInfo.getToSHA256().getBytes(StandardCharsets.UTF_8));
        this._id_sha256 = bytesToHex(encodedHash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return peopleInfo.toString();
    }

    public PeopleInfo getPeopleInfo() {
        return peopleInfo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        People guest = (People) obj;
        return _id_sha256.equals(guest.get_id_sha256())
                && (peopleInfo == guest.getPeopleInfo()
                || (peopleInfo != null && peopleInfo.equals(guest.getPeopleInfo())));
    }
}
