package com.ifmo.lesson18.Crypto;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream {

    byte[] key;
    int count;

    protected CryptoOutputStream(OutputStream out, byte[] key) {
        super(out);
        this.key = key;
        this.count = 0;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b ^ getNextKey());
    }

    private byte getNextKey(){
        if(count == key.length){
            count = 0;
        }
        return key[count++];
    }
}
