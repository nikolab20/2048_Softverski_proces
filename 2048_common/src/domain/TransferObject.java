/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author nikolab
 */
public class TransferObject {

    public DomainObject gdo;
    public String poruka;
    public boolean signal; // signal o uspesnosti izvrsenja operacije.
    public int currentRecord = -1;

    public void postaviDK(DomainObject gdo) {
        this.gdo = (DomainObject) gdo;
    }

    public String vratiPoruka() {
        return poruka;
    }

    public boolean vratiSignal() {
        return signal;
    }

    public DomainObject vratiDK() {
        return (DomainObject) gdo;
    }

    public void postaviSignal(boolean signal) {
        this.signal = signal;
    }
}
