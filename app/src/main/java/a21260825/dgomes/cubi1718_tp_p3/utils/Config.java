package a21260825.dgomes.cubi1718_tp_p3.utils;

/**
 * Created by diogo on 14-04-2018.
 */

public final class Config {
    public static final int MY_PERMMISSIONS_ALL = 111;
    public static final String PASTA_FICHEIRO_LOCAL = "/Cubi1718";
    public static final String PASTA_ARQUIVO_LOCAL = "/Arquivo/";
    public static final String PASTA_REMOTA = "a21260825";
    public static final String FICHEIRO = "cubi21260825";
    public static final String EXTENCAO = ".csv";

    public static final String ACT1 = "LshakeH"; // abanar horizontal longo
    public static final String ACT2 = "LshakeV"; // abanar vertical longo
    public static final String ACT1a = "Left"; // abanar horizontal longo
    public static final String ACT2a = "Up"; // abanar vertical longo
    public static final String ACT1b = "Right"; // abanar horizontal longo
    public static final String ACT2b = "-Down"; // abanar vertical longo
    public static final String ACT3 = "TwistH"; // girar horizontal
    public static final String ACT4 = "Sit"; // girar vertical
    public static final String ACT5 = "SshakeH"; // abanar horizontal curto
    public static final String ACT6 = "SshakeV"; // abanar vertical curto
    public static final String ACT7 = "Walk";
    public static final String ACT8 = "PushP";
    public static final String ACT9 = "GirarE";
    public static final String ACT10 = "GirarD";

    public static final String HOST = "urbysense.dei.uc.pt";
    public static final String USER = "cubistudent";
    public static final String PASSWORD = "mis_cubi_2018";

    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String ALTITUDE = "alt";
    public static final String xAcc = "acc_x";
    public static final String yAcc = "acc_y";
    public static final String zAcc = "acc_z";
    public static final  String Lum = "light";
    public static final String TRANSFERIDO = "Transferência concluida";
    public static final String NAO_TRANSFERIDO = "Nao transferido";

    public static final String MAG = "mag_mag";
    public static final String MAG_X = "mag_x";
    public static final String MAG_Y = "mag_y";
    public static final String MAG_Z = "mag_z";

    public static final String BATERIA = "Battery";

    public static final String MODE_TRAIN = "MODE_TRAIN";
    public static final String MODE_SAVE = "MODE_SAVE";
    public static final String MODE_AUTO = "MODE_AUTO";

    public static final int PREPROC_COUNTER = 128;
    public static final String FICHEIRO_PREPROC = FICHEIRO + "_preproc";
    public static final long TRAINNING_TIME = 20000; // 10 segundos

    public static final int REGISTO_INTERVALO = 5; // milliseconds
    public static final String EXTENCAO_ARFF = ".arff" ;
    public static final String FICHEIRO_TEST = "test";
    public static final String FICHEIRO_TRAIN = "train";
    public static final String CLASS_LABEL = "activity";


    public static boolean SOUND = true;

    public static boolean ARSLIB = false;
}
