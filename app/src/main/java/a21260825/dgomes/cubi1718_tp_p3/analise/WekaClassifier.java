package a21260825.dgomes.cubi1718_tp_p3.analise;

/**
 * Created by diogo on 04-07-2018.
 */

// Generated with Weka 3.8.2
//
// This code is public domain and comes with no warranty.
//
// Timestamp: Wed Jul 04 21:49:01 WEST 2018

public class WekaClassifier {

    public static double classify(Object[] i)
            throws Exception {

        double p = Double.NaN;
        p = WekaClassifier.N76ae506c0(i);
        return p;
    }
    static double N76ae506c0(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 28.23585) {
            p = WekaClassifier.N6ea138031(i);
        } else if (((Double) i[4]).doubleValue() > 28.23585) {
            p = WekaClassifier.N6e5c805063(i);
        }
        return p;
    }
    static double N6ea138031(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= 11.28) {
            p = WekaClassifier.N3c2f6c282(i);
        } else if (((Double) i[7]).doubleValue() > 11.28) {
            p = WekaClassifier.N305dbd49(i);
        }
        return p;
    }
    static double N3c2f6c282(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -16.08) {
            p = WekaClassifier.N51d03af03(i);
        } else if (((Double) i[6]).doubleValue() > -16.08) {
            p = WekaClassifier.N350f23cd10(i);
        }
        return p;
    }
    static double N51d03af03(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() <= 0.64336014) {
            p = WekaClassifier.N55f92d964(i);
        } else if (((Double) i[2]).doubleValue() > 0.64336014) {
            p = WekaClassifier.N2521b1a57(i);
        }
        return p;
    }
    static double N55f92d964(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 6;
        } else if (((Double) i[4]).doubleValue() <= 22.138372) {
            p = WekaClassifier.N32ff660a5(i);
        } else if (((Double) i[4]).doubleValue() > 22.138372) {
            p = WekaClassifier.N75f602516(i);
        }
        return p;
    }
    static double N32ff660a5(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 2;
        } else if (((Double) i[7]).doubleValue() <= 0.9) {
            p = 2;
        } else if (((Double) i[7]).doubleValue() > 0.9) {
            p = 6;
        }
        return p;
    }
    static double N75f602516(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -1.7443886) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -1.7443886) {
            p = 3;
        }
        return p;
    }
    static double N2521b1a57(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() <= 14.82) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() > 14.82) {
            p = WekaClassifier.N3928ad0a8(i);
        }
        return p;
    }
    static double N3928ad0a8(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -2.6296263) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -2.6296263) {
            p = WekaClassifier.N702e53139(i);
        }
        return p;
    }
    static double N702e53139(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 0.67783546) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 0.67783546) {
            p = 5;
        }
        return p;
    }
    static double N350f23cd10(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 24.046005) {
            p = WekaClassifier.N1d0b105011(i);
        } else if (((Double) i[4]).doubleValue() > 24.046005) {
            p = WekaClassifier.N6bd9f05435(i);
        }
        return p;
    }
    static double N1d0b105011(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 11.94) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() > 11.94) {
            p = WekaClassifier.N6137be0612(i);
        }
        return p;
    }
    static double N6137be0612(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 0;
        } else if (((Double) i[6]).doubleValue() <= -10.559999) {
            p = WekaClassifier.N6303dff813(i);
        } else if (((Double) i[6]).doubleValue() > -10.559999) {
            p = WekaClassifier.N70d0b5b733(i);
        }
        return p;
    }
    static double N6303dff813(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= -1.7615838) {
            p = WekaClassifier.N2f5e891e14(i);
        } else if (((Double) i[1]).doubleValue() > -1.7615838) {
            p = WekaClassifier.N167e74d920(i);
        }
        return p;
    }
    static double N2f5e891e14(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= -4.38) {
            p = WekaClassifier.N6a12953515(i);
        } else if (((Double) i[7]).doubleValue() > -4.38) {
            p = WekaClassifier.N4b0834c917(i);
        }
        return p;
    }
    static double N6a12953515(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -3.240328) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -3.240328) {
            p = WekaClassifier.N3689c1ff16(i);
        }
        return p;
    }
    static double N3689c1ff16(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 4.234765) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() > 4.234765) {
            p = 2;
        }
        return p;
    }
    static double N4b0834c917(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 15.719999) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() > 15.719999) {
            p = WekaClassifier.N10c542cf18(i);
        }
        return p;
    }
    static double N10c542cf18(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 0;
        } else if (((Double) i[3]).doubleValue() <= 1.007103) {
            p = WekaClassifier.N6e00c6df19(i);
        } else if (((Double) i[3]).doubleValue() > 1.007103) {
            p = 0;
        }
        return p;
    }
    static double N6e00c6df19(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= -0.53999996) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() > -0.53999996) {
            p = 6;
        }
        return p;
    }
    static double N167e74d920(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 1.3110296) {
            p = WekaClassifier.N1c5ab4a321(i);
        } else if (((Double) i[1]).doubleValue() > 1.3110296) {
            p = WekaClassifier.N6266693725(i);
        }
        return p;
    }
    static double N1c5ab4a321(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= -3.0093827) {
            p = WekaClassifier.N26b35edd22(i);
        } else if (((Double) i[2]).doubleValue() > -3.0093827) {
            p = WekaClassifier.N4eed52ea23(i);
        }
        return p;
    }
    static double N26b35edd22(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= -3.55721) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() > -3.55721) {
            p = 0;
        }
        return p;
    }
    static double N4eed52ea23(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 20.921165) {
            p = WekaClassifier.N2b503f5224(i);
        } else if (((Double) i[4]).doubleValue() > 20.921165) {
            p = 0;
        }
        return p;
    }
    static double N2b503f5224(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 0;
        } else if (((Double) i[3]).doubleValue() <= -1.5110307) {
            p = 0;
        } else if (((Double) i[3]).doubleValue() > -1.5110307) {
            p = 6;
        }
        return p;
    }
    static double N6266693725(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= 0.71999997) {
            p = WekaClassifier.N5e9f861026(i);
        } else if (((Double) i[7]).doubleValue() > 0.71999997) {
            p = WekaClassifier.N50c1ff8e28(i);
        }
        return p;
    }
    static double N5e9f861026(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= -2.2804432) {
            p = WekaClassifier.N7ac1f52b27(i);
        } else if (((Double) i[2]).doubleValue() > -2.2804432) {
            p = 0;
        }
        return p;
    }
    static double N7ac1f52b27(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 2.1042266) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 2.1042266) {
            p = 2;
        }
        return p;
    }
    static double N50c1ff8e28(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= -0.10162592) {
            p = WekaClassifier.N13f1b7029(i);
        } else if (((Double) i[3]).doubleValue() > -0.10162592) {
            p = WekaClassifier.N2332b24130(i);
        }
        return p;
    }
    static double N13f1b7029(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= 0.20410538) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() > 0.20410538) {
            p = 6;
        }
        return p;
    }
    static double N2332b24130(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 18.24) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() > 18.24) {
            p = WekaClassifier.N562f63a131(i);
        }
        return p;
    }
    static double N562f63a131(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 6;
        } else if (((Double) i[3]).doubleValue() <= 0.20574254) {
            p = 6;
        } else if (((Double) i[3]).doubleValue() > 0.20574254) {
            p = WekaClassifier.N9cdcb6532(i);
        }
        return p;
    }
    static double N9cdcb6532(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 20.039999) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() > 20.039999) {
            p = 6;
        }
        return p;
    }
    static double N70d0b5b733(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 1.843813) {
            p = WekaClassifier.N4646b15634(i);
        } else if (((Double) i[1]).doubleValue() > 1.843813) {
            p = 2;
        }
        return p;
    }
    static double N4646b15634(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 6;
        } else if (((Double) i[3]).doubleValue() <= -0.39150095) {
            p = 6;
        } else if (((Double) i[3]).doubleValue() > -0.39150095) {
            p = 0;
        }
        return p;
    }
    static double N6bd9f05435(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= -0.7088671) {
            p = WekaClassifier.Nc2888a936(i);
        } else if (((Double) i[2]).doubleValue() > -0.7088671) {
            p = WekaClassifier.N4426210343(i);
        }
        return p;
    }
    static double Nc2888a936(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -9.48) {
            p = WekaClassifier.N7920f99637(i);
        } else if (((Double) i[6]).doubleValue() > -9.48) {
            p = WekaClassifier.N6be4f0ec42(i);
        }
        return p;
    }
    static double N7920f99637(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= 2.6200771) {
            p = WekaClassifier.Na1f3be538(i);
        } else if (((Double) i[1]).doubleValue() > 2.6200771) {
            p = WekaClassifier.N1857201041(i);
        }
        return p;
    }
    static double Na1f3be538(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 2;
        } else if (((Double) i[7]).doubleValue() <= -9.66) {
            p = WekaClassifier.N2ee021a239(i);
        } else if (((Double) i[7]).doubleValue() > -9.66) {
            p = 2;
        }
        return p;
    }
    static double N2ee021a239(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -12.0) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > -12.0) {
            p = WekaClassifier.N66c5891740(i);
        }
        return p;
    }
    static double N66c5891740(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= -2.6651573) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() > -2.6651573) {
            p = 0;
        }
        return p;
    }
    static double N1857201041(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 20.16) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() > 20.16) {
            p = 2;
        }
        return p;
    }
    static double N6be4f0ec42(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() <= 20.939999) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() > 20.939999) {
            p = 0;
        }
        return p;
    }
    static double N4426210343(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 22.619999) {
            p = WekaClassifier.Nee195a144(i);
        } else if (((Double) i[5]).doubleValue() > 22.619999) {
            p = 6;
        }
        return p;
    }
    static double Nee195a144(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 2;
        } else if (((Double) i[7]).doubleValue() <= -9.54) {
            p = 2;
        } else if (((Double) i[7]).doubleValue() > -9.54) {
            p = WekaClassifier.N2f1763cd45(i);
        }
        return p;
    }
    static double N2f1763cd45(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= 7.68) {
            p = WekaClassifier.N6338855346(i);
        } else if (((Double) i[7]).doubleValue() > 7.68) {
            p = 2;
        }
        return p;
    }
    static double N6338855346(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= 3.06) {
            p = WekaClassifier.N16638e0e47(i);
        } else if (((Double) i[7]).doubleValue() > 3.06) {
            p = 0;
        }
        return p;
    }
    static double N16638e0e47(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() <= 0.37610245) {
            p = WekaClassifier.N142d4dfe48(i);
        } else if (((Double) i[2]).doubleValue() > 0.37610245) {
            p = 0;
        }
        return p;
    }
    static double N142d4dfe48(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 0;
        } else if (((Double) i[6]).doubleValue() <= -11.5199995) {
            p = 0;
        } else if (((Double) i[6]).doubleValue() > -11.5199995) {
            p = 6;
        }
        return p;
    }
    static double N305dbd49(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 2;
        } else if (((Double) i[4]).doubleValue() <= 22.680397) {
            p = WekaClassifier.N270542f550(i);
        } else if (((Double) i[4]).doubleValue() > 22.680397) {
            p = WekaClassifier.N51ff337953(i);
        }
        return p;
    }
    static double N270542f550(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= 1.8763552) {
            p = WekaClassifier.N2d9cbbe551(i);
        } else if (((Double) i[2]).doubleValue() > 1.8763552) {
            p = WekaClassifier.N5320411552(i);
        }
        return p;
    }
    static double N2d9cbbe551(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 5;
        } else if (((Double) i[2]).doubleValue() <= -11.89193) {
            p = 5;
        } else if (((Double) i[2]).doubleValue() > -11.89193) {
            p = 2;
        }
        return p;
    }
    static double N5320411552(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -16.08) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > -16.08) {
            p = 5;
        }
        return p;
    }
    static double N51ff337953(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 5;
        } else if (((Double) i[5]).doubleValue() <= 10.86) {
            p = WekaClassifier.N5c60d15954(i);
        } else if (((Double) i[5]).doubleValue() > 10.86) {
            p = 2;
        }
        return p;
    }
    static double N5c60d15954(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -21.72) {
            p = WekaClassifier.N14eb0aa355(i);
        } else if (((Double) i[6]).doubleValue() > -21.72) {
            p = WekaClassifier.N4512d9c256(i);
        }
        return p;
    }
    static double N14eb0aa355(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= 0.9648699) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > 0.9648699) {
            p = 5;
        }
        return p;
    }
    static double N4512d9c256(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 5;
        } else if (((Double) i[1]).doubleValue() <= 0.1321618) {
            p = 5;
        } else if (((Double) i[1]).doubleValue() > 0.1321618) {
            p = WekaClassifier.N6d5e4ee257(i);
        }
        return p;
    }
    static double N6d5e4ee257(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 5;
        } else if (((Double) i[7]).doubleValue() <= 16.74) {
            p = WekaClassifier.N37eeb00958(i);
        } else if (((Double) i[7]).doubleValue() > 16.74) {
            p = WekaClassifier.N7509e28662(i);
        }
        return p;
    }
    static double N37eeb00958(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 5;
        } else if (((Double) i[1]).doubleValue() <= 0.21428221) {
            p = WekaClassifier.N7b9c1a4559(i);
        } else if (((Double) i[1]).doubleValue() > 0.21428221) {
            p = WekaClassifier.N2893103d60(i);
        }
        return p;
    }
    static double N7b9c1a4559(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() <= 3.48) {
            p = 2;
        } else if (((Double) i[5]).doubleValue() > 3.48) {
            p = 5;
        }
        return p;
    }
    static double N2893103d60(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 5;
        } else if (((Double) i[5]).doubleValue() <= 9.179999) {
            p = 5;
        } else if (((Double) i[5]).doubleValue() > 9.179999) {
            p = WekaClassifier.N3725629361(i);
        }
        return p;
    }
    static double N3725629361(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 5;
        } else if (((Double) i[1]).doubleValue() <= 3.0769508) {
            p = 5;
        } else if (((Double) i[1]).doubleValue() > 3.0769508) {
            p = 2;
        }
        return p;
    }
    static double N7509e28662(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 5;
        } else if (((Double) i[3]).doubleValue() <= -0.33008933) {
            p = 5;
        } else if (((Double) i[3]).doubleValue() > -0.33008933) {
            p = 2;
        }
        return p;
    }
    static double N6e5c805063(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 4.7119966) {
            p = WekaClassifier.N5efe775e64(i);
        } else if (((Double) i[1]).doubleValue() > 4.7119966) {
            p = WekaClassifier.N23f0e67b157(i);
        }
        return p;
    }
    static double N5efe775e64(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= -5.8582845) {
            p = WekaClassifier.N4253b7e865(i);
        } else if (((Double) i[1]).doubleValue() > -5.8582845) {
            p = WekaClassifier.N5bd2cffa70(i);
        }
        return p;
    }
    static double N4253b7e865(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 3.9535117) {
            p = WekaClassifier.N78f460f666(i);
        } else if (((Double) i[3]).doubleValue() > 3.9535117) {
            p = 4;
        }
        return p;
    }
    static double N78f460f666(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 8.58) {
            p = WekaClassifier.N72a031b867(i);
        } else if (((Double) i[7]).doubleValue() > 8.58) {
            p = WekaClassifier.N5f49fc3568(i);
        }
        return p;
    }
    static double N72a031b867(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= -19.14) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > -19.14) {
            p = 1;
        }
        return p;
    }
    static double N5f49fc3568(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 1;
        } else if (((Double) i[2]).doubleValue() <= 1.266996) {
            p = 1;
        } else if (((Double) i[2]).doubleValue() > 1.266996) {
            p = WekaClassifier.Nfafcc369(i);
        }
        return p;
    }
    static double Nfafcc369(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() <= -13.905959) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() > -13.905959) {
            p = 1;
        }
        return p;
    }
    static double N5bd2cffa70(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= 11.759999) {
            p = WekaClassifier.N288c18c971(i);
        } else if (((Double) i[5]).doubleValue() > 11.759999) {
            p = WekaClassifier.N6cd819ab90(i);
        }
        return p;
    }
    static double N288c18c971(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 4;
        } else if (((Double) i[6]).doubleValue() <= -17.76) {
            p = WekaClassifier.N38b4e1b972(i);
        } else if (((Double) i[6]).doubleValue() > -17.76) {
            p = WekaClassifier.Nf715d6f89(i);
        }
        return p;
    }
    static double N38b4e1b972(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= 0.7318884) {
            p = WekaClassifier.N6fff0e8873(i);
        } else if (((Double) i[2]).doubleValue() > 0.7318884) {
            p = WekaClassifier.N2c09bce082(i);
        }
        return p;
    }
    static double N6fff0e8873(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= -2.168529) {
            p = WekaClassifier.N229fa19274(i);
        } else if (((Double) i[2]).doubleValue() > -2.168529) {
            p = WekaClassifier.N753b229b76(i);
        }
        return p;
    }
    static double N229fa19274(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 4;
        } else if (((Double) i[4]).doubleValue() <= 29.185297) {
            p = WekaClassifier.N6a9dbf5875(i);
        } else if (((Double) i[4]).doubleValue() > 29.185297) {
            p = 4;
        }
        return p;
    }
    static double N6a9dbf5875(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 4;
        } else if (((Double) i[6]).doubleValue() <= -21.539999) {
            p = 4;
        } else if (((Double) i[6]).doubleValue() > -21.539999) {
            p = 1;
        }
        return p;
    }
    static double N753b229b76(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() <= -2.3589811) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() > -2.3589811) {
            p = WekaClassifier.N62a7e9fa77(i);
        }
        return p;
    }
    static double N62a7e9fa77(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -23.22) {
            p = WekaClassifier.N3deb470d78(i);
        } else if (((Double) i[6]).doubleValue() > -23.22) {
            p = WekaClassifier.N16889ab780(i);
        }
        return p;
    }
    static double N3deb470d78(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() <= 0.67501354) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() > 0.67501354) {
            p = WekaClassifier.N3a0b489779(i);
        }
        return p;
    }
    static double N3a0b489779(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() <= 2.0873604) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() > 2.0873604) {
            p = 3;
        }
        return p;
    }
    static double N16889ab780(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= -19.98) {
            p = WekaClassifier.N4b3d59a581(i);
        } else if (((Double) i[6]).doubleValue() > -19.98) {
            p = 4;
        }
        return p;
    }
    static double N4b3d59a581(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() <= 15.96) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() > 15.96) {
            p = 1;
        }
        return p;
    }
    static double N2c09bce082(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 3;
        } else if (((Double) i[2]).doubleValue() <= 3.1487265) {
            p = WekaClassifier.N4c26060683(i);
        } else if (((Double) i[2]).doubleValue() > 3.1487265) {
            p = 4;
        }
        return p;
    }
    static double N4c26060683(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() <= -0.71467924) {
            p = WekaClassifier.N1ea9701784(i);
        } else if (((Double) i[3]).doubleValue() > -0.71467924) {
            p = WekaClassifier.N229d9c4b87(i);
        }
        return p;
    }
    static double N1ea9701784(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 6;
        } else if (((Double) i[4]).doubleValue() <= 29.45175) {
            p = WekaClassifier.N3f78e21685(i);
        } else if (((Double) i[4]).doubleValue() > 29.45175) {
            p = 4;
        }
        return p;
    }
    static double N3f78e21685(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() <= -3.6599004) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() > -3.6599004) {
            p = WekaClassifier.N5ef7a7a586(i);
        }
        return p;
    }
    static double N5ef7a7a586(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() <= 2.4365091) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() > 2.4365091) {
            p = 3;
        }
        return p;
    }
    static double N229d9c4b87(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= 10.38) {
            p = WekaClassifier.N776169c788(i);
        } else if (((Double) i[5]).doubleValue() > 10.38) {
            p = 3;
        }
        return p;
    }
    static double N776169c788(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 3;
        } else if (((Double) i[7]).doubleValue() <= 20.16) {
            p = 3;
        } else if (((Double) i[7]).doubleValue() > 20.16) {
            p = 4;
        }
        return p;
    }
    static double Nf715d6f89(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 5;
        } else if (((Double) i[5]).doubleValue() <= 1.14) {
            p = 5;
        } else if (((Double) i[5]).doubleValue() > 1.14) {
            p = 3;
        }
        return p;
    }
    static double N6cd819ab90(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() <= 1.3199999) {
            p = WekaClassifier.N22e9afbf91(i);
        } else if (((Double) i[7]).doubleValue() > 1.3199999) {
            p = WekaClassifier.N771c2e7794(i);
        }
        return p;
    }
    static double N22e9afbf91(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() <= 0.9) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() > 0.9) {
            p = WekaClassifier.N5ebad57492(i);
        }
        return p;
    }
    static double N5ebad57492(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= -0.083245516) {
            p = WekaClassifier.N5ba561e993(i);
        } else if (((Double) i[3]).doubleValue() > -0.083245516) {
            p = 6;
        }
        return p;
    }
    static double N5ba561e993(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -0.053258896) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -0.053258896) {
            p = 3;
        }
        return p;
    }
    static double N771c2e7794(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() <= 22.26) {
            p = WekaClassifier.N18e7e37c95(i);
        } else if (((Double) i[5]).doubleValue() > 22.26) {
            p = WekaClassifier.N42791a148(i);
        }
        return p;
    }
    static double N18e7e37c95(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() <= 29.731981) {
            p = WekaClassifier.N107e846196(i);
        } else if (((Double) i[4]).doubleValue() > 29.731981) {
            p = WekaClassifier.N4aed113c133(i);
        }
        return p;
    }
    static double N107e846196(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 3;
        } else if (((Double) i[2]).doubleValue() <= 1.2165747) {
            p = WekaClassifier.N602415ae97(i);
        } else if (((Double) i[2]).doubleValue() > 1.2165747) {
            p = WekaClassifier.N34452f9b129(i);
        }
        return p;
    }
    static double N602415ae97(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 3;
        } else if (((Double) i[2]).doubleValue() <= -1.1259518) {
            p = WekaClassifier.N76dde04b98(i);
        } else if (((Double) i[2]).doubleValue() > -1.1259518) {
            p = WekaClassifier.Na32bfe2108(i);
        }
        return p;
    }
    static double N76dde04b98(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 3;
        } else if (((Double) i[7]).doubleValue() <= 15.299999) {
            p = WekaClassifier.N510601ef99(i);
        } else if (((Double) i[7]).doubleValue() > 15.299999) {
            p = WekaClassifier.N46fd9150104(i);
        }
        return p;
    }
    static double N510601ef99(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -4.4532633) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -4.4532633) {
            p = WekaClassifier.N353fca22100(i);
        }
        return p;
    }
    static double N353fca22100(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= -2.3431652) {
            p = WekaClassifier.N75f6304e101(i);
        } else if (((Double) i[1]).doubleValue() > -2.3431652) {
            p = WekaClassifier.N6c304903102(i);
        }
        return p;
    }
    static double N75f6304e101(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 3;
        } else if (((Double) i[2]).doubleValue() <= -7.682846) {
            p = 3;
        } else if (((Double) i[2]).doubleValue() > -7.682846) {
            p = 6;
        }
        return p;
    }
    static double N6c304903102(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() <= -18.42) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() > -18.42) {
            p = WekaClassifier.Ndd82413103(i);
        }
        return p;
    }
    static double Ndd82413103(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 3.423091) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 3.423091) {
            p = 2;
        }
        return p;
    }
    static double N46fd9150104(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= -6.1306953) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() > -6.1306953) {
            p = WekaClassifier.N2cdad81105(i);
        }
        return p;
    }
    static double N2cdad81105(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 0.29455733) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 0.29455733) {
            p = WekaClassifier.N6cdb848a106(i);
        }
        return p;
    }
    static double N6cdb848a106(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= -20.4) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() > -20.4) {
            p = WekaClassifier.N20efcdbe107(i);
        }
        return p;
    }
    static double N20efcdbe107(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 2.6880946) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 2.6880946) {
            p = 2;
        }
        return p;
    }
    static double Na32bfe2108(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -19.08) {
            p = WekaClassifier.N65fcba80109(i);
        } else if (((Double) i[6]).doubleValue() > -19.08) {
            p = WekaClassifier.N5ecb60de128(i);
        }
        return p;
    }
    static double N65fcba80109(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= -0.32679343) {
            p = WekaClassifier.N55b39d61110(i);
        } else if (((Double) i[3]).doubleValue() > -0.32679343) {
            p = WekaClassifier.N288fc973120(i);
        }
        return p;
    }
    static double N55b39d61110(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 2.3543904) {
            p = WekaClassifier.Nbef3854111(i);
        } else if (((Double) i[1]).doubleValue() > 2.3543904) {
            p = WekaClassifier.N64119d5c119(i);
        }
        return p;
    }
    static double Nbef3854111(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() <= -0.34405208) {
            p = WekaClassifier.N14bd5b64112(i);
        } else if (((Double) i[1]).doubleValue() > -0.34405208) {
            p = WekaClassifier.N5d0414bf114(i);
        }
        return p;
    }
    static double N14bd5b64112(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= -1.0572631) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() > -1.0572631) {
            p = WekaClassifier.N1415dbc9113(i);
        }
        return p;
    }
    static double N1415dbc9113(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -2.803802) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -2.803802) {
            p = 6;
        }
        return p;
    }
    static double N5d0414bf114(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 15.179999) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() > 15.179999) {
            p = WekaClassifier.N2f5baad3115(i);
        }
        return p;
    }
    static double N2f5baad3115(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() <= 28.918505) {
            p = WekaClassifier.N5143b227116(i);
        } else if (((Double) i[4]).doubleValue() > 28.918505) {
            p = WekaClassifier.N6b32e055117(i);
        }
        return p;
    }
    static double N5143b227116(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() <= 0.03434372) {
            p = 6;
        } else if (((Double) i[2]).doubleValue() > 0.03434372) {
            p = 3;
        }
        return p;
    }
    static double N6b32e055117(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() <= 15.54) {
            p = WekaClassifier.N16eeb060118(i);
        } else if (((Double) i[5]).doubleValue() > 15.54) {
            p = 3;
        }
        return p;
    }
    static double N16eeb060118(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() <= 0.80426884) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() > 0.80426884) {
            p = 3;
        }
        return p;
    }
    static double N64119d5c119(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -21.3) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() > -21.3) {
            p = 1;
        }
        return p;
    }
    static double N288fc973120(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 6;
        } else if (((Double) i[4]).doubleValue() <= 29.67884) {
            p = WekaClassifier.N54c3b47f121(i);
        } else if (((Double) i[4]).doubleValue() > 29.67884) {
            p = WekaClassifier.N34041eb5127(i);
        }
        return p;
    }
    static double N54c3b47f121(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -3.2857404) {
            p = WekaClassifier.N374d6337122(i);
        } else if (((Double) i[1]).doubleValue() > -3.2857404) {
            p = WekaClassifier.N5f5ef3ff123(i);
        }
        return p;
    }
    static double N374d6337122(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= -3.7155542) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > -3.7155542) {
            p = 6;
        }
        return p;
    }
    static double N5f5ef3ff123(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -19.5) {
            p = WekaClassifier.N9cc6d36124(i);
        } else if (((Double) i[6]).doubleValue() > -19.5) {
            p = WekaClassifier.N1c6dc7cc126(i);
        }
        return p;
    }
    static double N9cc6d36124(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -23.46) {
            p = WekaClassifier.N40e5403c125(i);
        } else if (((Double) i[6]).doubleValue() > -23.46) {
            p = 6;
        }
        return p;
    }
    static double N40e5403c125(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 15.0) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() > 15.0) {
            p = 3;
        }
        return p;
    }
    static double N1c6dc7cc126(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() <= -1.1306461) {
            p = 6;
        } else if (((Double) i[1]).doubleValue() > -1.1306461) {
            p = 3;
        }
        return p;
    }
    static double N34041eb5127(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 3;
        } else if (((Double) i[7]).doubleValue() <= 6.42) {
            p = 3;
        } else if (((Double) i[7]).doubleValue() > 6.42) {
            p = 6;
        }
        return p;
    }
    static double N5ecb60de128(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() <= 17.34) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() > 17.34) {
            p = 2;
        }
        return p;
    }
    static double N34452f9b129(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= -1.7977257) {
            p = WekaClassifier.N46e4dd78130(i);
        } else if (((Double) i[3]).doubleValue() > -1.7977257) {
            p = WekaClassifier.N520182a7131(i);
        }
        return p;
    }
    static double N46e4dd78130(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= 16.38) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() > 16.38) {
            p = 3;
        }
        return p;
    }
    static double N520182a7131(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() <= 28.39547) {
            p = WekaClassifier.N57c9c5a9132(i);
        } else if (((Double) i[4]).doubleValue() > 28.39547) {
            p = 3;
        }
        return p;
    }
    static double N57c9c5a9132(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= 1.8371029) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() > 1.8371029) {
            p = 3;
        }
        return p;
    }
    static double N4aed113c133(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= 1.8838611) {
            p = WekaClassifier.N2a021a04134(i);
        } else if (((Double) i[3]).doubleValue() > 1.8838611) {
            p = WekaClassifier.N5311708e146(i);
        }
        return p;
    }
    static double N2a021a04134(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() <= -3.3378022) {
            p = 4;
        } else if (((Double) i[3]).doubleValue() > -3.3378022) {
            p = WekaClassifier.N74c8b159135(i);
        }
        return p;
    }
    static double N74c8b159135(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= -1.6921544) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > -1.6921544) {
            p = WekaClassifier.N6db5c6e4136(i);
        }
        return p;
    }
    static double N6db5c6e4136(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= -21.3) {
            p = WekaClassifier.N4f735e9f137(i);
        } else if (((Double) i[6]).doubleValue() > -21.3) {
            p = WekaClassifier.N729e653140(i);
        }
        return p;
    }
    static double N4f735e9f137(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 0.25538445) {
            p = WekaClassifier.N759baf4f138(i);
        } else if (((Double) i[3]).doubleValue() > 0.25538445) {
            p = 3;
        }
        return p;
    }
    static double N759baf4f138(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= -7.682846) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() > -7.682846) {
            p = WekaClassifier.N44688adc139(i);
        }
        return p;
    }
    static double N44688adc139(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 3.1793408) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > 3.1793408) {
            p = 6;
        }
        return p;
    }
    static double N729e653140(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 2.1949992) {
            p = WekaClassifier.N52fbd78f141(i);
        } else if (((Double) i[1]).doubleValue() > 2.1949992) {
            p = WekaClassifier.N7a0cb0b8143(i);
        }
        return p;
    }
    static double N52fbd78f141(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= -0.89453983) {
            p = WekaClassifier.N7a32fb48142(i);
        } else if (((Double) i[1]).doubleValue() > -0.89453983) {
            p = 3;
        }
        return p;
    }
    static double N7a32fb48142(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= -0.95596725) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > -0.95596725) {
            p = 1;
        }
        return p;
    }
    static double N7a0cb0b8143(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= 0.5478344) {
            p = WekaClassifier.N32dfcad4144(i);
        } else if (((Double) i[3]).doubleValue() > 0.5478344) {
            p = 1;
        }
        return p;
    }
    static double N32dfcad4144(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= 21.48) {
            p = WekaClassifier.N3e9424af145(i);
        } else if (((Double) i[5]).doubleValue() > 21.48) {
            p = 3;
        }
        return p;
    }
    static double N3e9424af145(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() <= 3.9758594) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() > 3.9758594) {
            p = 6;
        }
        return p;
    }
    static double N5311708e146(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= 15.36) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() > 15.36) {
            p = WekaClassifier.N4c078324147(i);
        }
        return p;
    }
    static double N4c078324147(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() <= -20.22) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() > -20.22) {
            p = 1;
        }
        return p;
    }
    static double N42791a148(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= -16.68) {
            p = WekaClassifier.N1f6c94e1149(i);
        } else if (((Double) i[6]).doubleValue() > -16.68) {
            p = WekaClassifier.N4a752258156(i);
        }
        return p;
    }
    static double N1f6c94e1149(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= -0.8489499) {
            p = WekaClassifier.N47211282150(i);
        } else if (((Double) i[3]).doubleValue() > -0.8489499) {
            p = WekaClassifier.N37cfa7e1151(i);
        }
        return p;
    }
    static double N47211282150(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() <= 22.74) {
            p = 3;
        } else if (((Double) i[5]).doubleValue() > 22.74) {
            p = 2;
        }
        return p;
    }
    static double N37cfa7e1151(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() <= 5.94) {
            p = 6;
        } else if (((Double) i[7]).doubleValue() > 5.94) {
            p = WekaClassifier.N245692ab152(i);
        }
        return p;
    }
    static double N245692ab152(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= -18.539999) {
            p = WekaClassifier.N5f66d15c153(i);
        } else if (((Double) i[6]).doubleValue() > -18.539999) {
            p = WekaClassifier.N109c6d30154(i);
        }
        return p;
    }
    static double N5f66d15c153(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 2.0561278) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 2.0561278) {
            p = 1;
        }
        return p;
    }
    static double N109c6d30154(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 2.007336) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > 2.007336) {
            p = WekaClassifier.N5aa6306d155(i);
        }
        return p;
    }
    static double N5aa6306d155(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 2.975236) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > 2.975236) {
            p = 6;
        }
        return p;
    }
    static double N4a752258156(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= 1.3486845) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() > 1.3486845) {
            p = 6;
        }
        return p;
    }
    static double N23f0e67b157(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 12.78) {
            p = WekaClassifier.N50f6ead8158(i);
        } else if (((Double) i[7]).doubleValue() > 12.78) {
            p = 1;
        }
        return p;
    }
    static double N50f6ead8158(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 6;
        } else if (((Double) i[5]).doubleValue() <= 17.279999) {
            p = WekaClassifier.N3b389df3159(i);
        } else if (((Double) i[5]).doubleValue() > 17.279999) {
            p = 1;
        }
        return p;
    }
    static double N3b389df3159(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() <= -20.58) {
            p = 6;
        } else if (((Double) i[6]).doubleValue() > -20.58) {
            p = 1;
        }
        return p;
    }
}
