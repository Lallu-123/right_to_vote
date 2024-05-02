package com.right_to_vote;

import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument(3, 2, 10));
        instruments.add(new Instrument(4, 3, 15));
        instruments.add(new Instrument(2, 1, 8));
        instruments.add(new Instrument(5, 4, 20));

        int capacityWeight = 10;
        int capacityVolume = 7;

        int[][] dp = new int[capacityWeight + 1][capacityVolume + 1];

        for (Instrument instrument : instruments) {
            for (int w = capacityWeight; w >= instrument.weight; w--) {
                for (int v = capacityVolume; v >= instrument.volume; v--) {
                    dp[w][v] = Math.max(dp[w][v], dp[w - instrument.weight][v - instrument.volume] + instrument.value);
                }
            }
        }

        List<Instrument> selectedInstruments = new ArrayList<>();
        int w = capacityWeight;
        int v = capacityVolume;
        for (Instrument instrument : instruments) {
            if (w >= instrument.weight && v >= instrument.volume && dp[w][v] == dp[w - instrument.weight][v - instrument.volume] + instrument.value) {
                selectedInstruments.add(instrument);
                w -= instrument.weight;
                v -= instrument.volume;
            }
        }

        System.out.println("Selected instruments:");
        for (Instrument instrument : selectedInstruments) {
            System.out.println("- Instrument weight: " + instrument.weight + " kg, volume: " + instrument.volume + " m^3, value: " + instrument.value);
        }
        System.out.println("Total weight: " + (capacityWeight - w) + " kg");
        System.out.println("Total volume: " + (capacityVolume - v) + " m^3");
        System.out.println("Total scientific value achieved: " + dp[capacityWeight][capacityVolume]);
    }
}
