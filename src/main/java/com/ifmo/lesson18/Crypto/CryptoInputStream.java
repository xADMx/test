package com.ifmo.lesson18.Crypto;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream {

    byte[] key;
    int count;

    protected CryptoInputStream(InputStream in, byte[] key) {
        super(in);
        this.key = key;
        this.count = 0;
    }

    @Override
    public int read() throws IOException {
        return super.read() ^ getNextKey();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int res = super.read(b, off, len);
        for (int i = off; i < len; i++) {
            b[i] = (byte) (b[i] ^ getNextKey());
        }
        return res;
    }

    private byte getNextKey(){
        if(count == key.length){
            count = 0;
        }
        return key[count++];
    }
}
